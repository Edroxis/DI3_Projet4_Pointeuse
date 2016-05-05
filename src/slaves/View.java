package slaves;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JLabel dateTimeInfo;
	private JButton checkButton;
	private JFormattedTextField numberField;
	
	public View() {
		//GTK theme 
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {	}
		
		//Ensure to exit the program and not only the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		//TODO: Use the view as the main layout
		dateTimeInfo = new JLabel();
		dateTimeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		dateTimeInfo.setFont(new Font(dateTimeInfo.getFont().getName(), Font.PLAIN, 72));
		
		DateTimeInfoUpdater updater = new DateTimeInfoUpdater();
		updater.actionPerformed(null);
		new Timer(30000, updater).start(); //Update every 30 secs
		
		numberField = new JFormattedTextField(NumberFormat.getNumberInstance());
		
		checkButton = new JButton("Check in/out");
		checkButton.addActionListener(new CheckInOutListener());
		
		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
		horizontalPanel.add(numberField);
		horizontalPanel.add(checkButton);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(dateTimeInfo, BorderLayout.CENTER);
		mainPanel.add(horizontalPanel, BorderLayout.PAGE_END);
		
		this.add(mainPanel);
	}	


	private class CheckInOutListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
			Object obj = numberField.getValue();
			
			if(obj != null) {
				int id = ((Number)obj).intValue();
				TimeClockModel.add(id);
				System.out.println("Pointage de " + id);
			}
	    }
	}
	
	private class DateTimeInfoUpdater implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	ZonedDateTime dateTime = ZonedDateTime.now();
	    	ZonedDateTime roundedDateTime = Utils.roundTimeMinQuarter(dateTime);
	    	DateTimeFormatter date = DateTimeFormatter.ofPattern("EEEE, dd MMMM YYYY");
	    	DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
	    	
	    	String text = "<html>Welcome<br><br>" + dateTime.format(date) + "<br>" +
	    	dateTime.format(time) + " (" + roundedDateTime.format(time) +
	    	")" + "</html>";

	    	dateTimeInfo.setText(text);
	    }
	}
}