package centralapp.views;

import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.CheckInOutControler;
import centralapp.controlers.ComControler;
import centralapp.controlers.CompanyControler;
import centralapp.model.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CheckInOutView extends JPanel {

	private CentralApp mainControler;
	private CheckInOutControler controler;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public CheckInOutView(CentralApp mainControler, CheckInOutControler localControler, Employee emp) {
		this.mainControler = mainControler;
		controler = localControler;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		table = new JTable();
		String[][] data = new String[emp.getCheckInOut().size()][2] ;
		int i = 1;
		
		for(CheckInOut ch : emp.getCheckInOut()){
			data[i][0] = ch.getDate().format(dateFormatter);
			data[i][1] = ch.getDate().format(hourFormatter);
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
		add(table);
	}

}
