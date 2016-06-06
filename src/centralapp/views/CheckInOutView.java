package centralapp.views;

import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.CheckInOutControler;
import centralapp.model.CheckInOut;
import centralapp.model.Employee;

/**
 * <b>CheckInOutView : Manage the view which contains check of a particular employee</b>
 * 
 */
@SuppressWarnings("serial")
public class CheckInOutView extends JPanel {	
	/**
	 * Reference to the Controler
	 */
	private CheckInOutControler controler;
	
	/**
	 * Table containing Checks
	 */
	private JTable table;

	/**
	 * Reference to corresponding Employee
	 */
	private Employee employee;

	/**
	 * Message if there's no check on this employee checkList
	 */
	private JLabel noCheckLabel = new JLabel("No Check in or out for this Employee");
	
	/**
	 * Constructor of this class
	 * 
	 * @param mainControler Reference to the CentralApp
	 * @param localControler Reference to the Controler
	 * @param emp Reference to the Employee
	 */
	public CheckInOutView(CentralApp mainControler, CheckInOutControler localControler, Employee emp) {
		controler = localControler;
		employee = emp;
		
		table = new JTable();
		
		updateTable();		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Initialize Pane for Scrolling
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		//Initialize panel containing the close button
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel);
		
		//Initialize Close Button
		JButton btnClose = new JButton("Close");
		panel.add(btnClose);
		btnClose.addMouseListener(controler.new CloseEvent());
	}
	
	/**
	 * Update Table Content
	 */
	public void updateTable(){
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		//Put the emptyMessage (or not) depending on checkList Emptiness
		remove(noCheckLabel);
		if(employee.getCheckInOut().size() == 0)
			add(noCheckLabel);
		
		String[][] data = new String[employee.getCheckInOut().size()][2] ;
		int i = 0;
		
		//Fill the Table
		for(CheckInOut ch : employee.getCheckInOut()){
			data[i][0] = ch.getDate().format(dateFormatter);
			data[i][1] = ch.getDate().format(hourFormatter);
			i++;
		}
		
		//Print Table
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
