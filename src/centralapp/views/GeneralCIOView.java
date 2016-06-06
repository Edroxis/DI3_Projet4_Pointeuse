package centralapp.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.GeneralCIOControler;
import centralapp.model.*;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class GeneralCIOView extends JPanel {

	private CentralApp mainControler;
	private GeneralCIOControler controler;
	private JTable table;
	private DefaultTableModel model;
	private RowSorter<TableModel> sorter;

	/**
	 * Create the panel.
	 */
	public GeneralCIOView(CentralApp mainControler, GeneralCIOControler localControler) {
		this.mainControler = mainControler;
		controler = localControler;

		// updateTable();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);
	}

	public void updateTable() {// update the model of the JTable
		int i = 0;
		String[][] data = new String[mainControler.getCompany().getTotalCIO()][5];
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm");

		for (Employee emp : mainControler.getCompany().getEmployees()) {
			for (CheckInOut cio : emp.getCheckInOut()) {
				data[i][0] = Integer.toString(emp.getId());
				data[i][1] = emp.getfName();
				data[i][2] = emp.getlName();
				data[i][3] = emp.getDpt().toString();
				data[i][4] = cio.getDate().format(dateFormatter);
				i++;
			}
		}
		
		model = new DefaultTableModel(data,
				new String[] { "Id", "First Name", "Last Name", "Department", "Hour of check" }
		);

		table.setModel(model);
	}

}
