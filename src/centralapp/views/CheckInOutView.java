package centralapp.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.CheckInOutControler;
import centralapp.controlers.ComControler;
import centralapp.controlers.CompanyControler;
import centralapp.model.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class CheckInOutView extends JPanel {

	private CentralApp mainControler;
	private CheckInOutControler controler;
	private JTable table;
	
	private JLabel noCheckLabel = new JLabel("No Check in or out for this Employee");
	
	/**
	 * Create the panel.
	 */
	public CheckInOutView(CentralApp mainControler, CheckInOutControler localControler, Employee emp) {
		this.mainControler = mainControler;
		controler = localControler;
		
		table = new JTable();
		
		updateTable(emp);		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(table);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel);
		
		JButton btnClose = new JButton("Close");
		panel.add(btnClose);
		btnClose.addMouseListener(controler.new CloseEvent());
	}
	
	public void updateTable(Employee emp){//update the model of the JTable
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		remove(noCheckLabel);
		if(emp.getCheckInOut().size() == 0)
			add(noCheckLabel);
		
		String[][] data = new String[emp.getCheckInOut().size()][2] ;
		int i = 0;
		
		for(CheckInOut ch : emp.getCheckInOut()){
			data[i][0] = ch.getDate().format(dateFormatter);
			data[i][1] = ch.getDate().format(hourFormatter);
			i++;
		}
		
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"Jour", "Heure"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
	}

}
