package centralapp.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.GeneralCIOControler;
import centralapp.model.CheckInOut;
import centralapp.model.Employee;

/**
 * <b>GeneralCIOView : View of all checks</b>
 * 
 */
@SuppressWarnings("serial")
public class GeneralCIOView extends JPanel {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the Controler
	 */
	private GeneralCIOControler controler;

	/**
	 * The table to print database
	 */
	private JTable table;

	/**
	 * the model of the JTable
	 */
	private DefaultTableModel model;

	/**
	 * TextField for minimum Date
	 */
	private JTextField dateMinTextField;

	/**
	 * TextField for maximum Date
	 */
	private JTextField dateMaxTextField;

	/**
	 * Minimum Date to Filter Table Entries
	 */
	private ZonedDateTime filterMin;

	/**
	 * Maximum Date to Filter Table Entries
	 */
	private ZonedDateTime filterMax;

	/**
	 * Constructor of the class
	 * 
	 * @param mainControler
	 *            Reference to the CentralApp
	 * @param localControler
	 *            Reference to the Controler
	 */
	public GeneralCIOView(CentralApp mainControler, GeneralCIOControler localControler) {
		this.mainControler = mainControler;
		controler = localControler;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Initialize Pane for Scrolling
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		// Initialize Table
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		// Set Panel containing buttons and TextFields
		JPanel panel = new JPanel();
		add(panel);

		JLabel lblNewLabel = new JLabel("Print checks ");
		panel.add(lblNewLabel);

		JLabel lblDatemin = new JLabel("after :");
		panel.add(lblDatemin);

		// Initialize TextField containing minDate
		dateMinTextField = new JTextField();
		panel.add(dateMinTextField);
		dateMinTextField.setColumns(17);

		JLabel lblDatemax = new JLabel("and before :");
		panel.add(lblDatemax);

		// Initialize TextField Containing maxDate
		dateMaxTextField = new JTextField();
		panel.add(dateMaxTextField);
		dateMaxTextField.setColumns(17);

		// Init buttons
		JButton btnFilter = new JButton("Filter");
		panel.add(btnFilter);

		JButton btnReset = new JButton("Reset");
		panel.add(btnReset);

		JButton btnWorking = new JButton("Currently Working");
		panel.add(btnWorking);

		// Set up events
		btnFilter.addMouseListener(controler.new FilterEvent());
		btnReset.addMouseListener(controler.new ResetEvent());
		btnWorking.addMouseListener(controler.new WorkingEvent());
	}

	/**
	 * To print something on minDateTextField
	 * 
	 * @param str
	 *            The string you want to put in
	 */
	public void setMinTextField(String str) {
		dateMinTextField.setText(str);
	}

	/**
	 * To get what's printed on the minDateTextField
	 * 
	 * @return The String contained
	 */
	public String getMinTextField() {
		return dateMinTextField.getText();
	}

	/**
	 * To print something on maxDateTextField
	 * 
	 * @param str
	 *            The string you want to put in
	 */
	public void setMaxTextField(String str) {
		dateMaxTextField.setText(str);
	}

	/**
	 * To get what's printed on the maxDateTextField
	 * 
	 * @return String of what's in TextField
	 */
	public String getMaxTextField() {
		return dateMaxTextField.getText();
	}

	/**
	 * To set the min filter
	 * 
	 * @param date
	 *            The minimum date
	 */
	public void setFilterMin(ZonedDateTime date) {
		filterMin = date;
	}

	/**
	 * To set the max filter
	 * 
	 * @param date
	 *            The maximum date
	 */
	public void setFilterMax(ZonedDateTime date) {
		filterMax = date;
	}

	/**
	 * Method to update Table Content
	 */
	public void updateTable() {
		int i = 0;
		Employee MrX = mainControler.getCompany().getMrX();
		String[][] data = new String[mainControler.getCompany().getTotalCIO() + MrX.getTotalCIO()][5];
		DateTimeFormatter dateFormatter = controler.getDateFormatter();

		// Look on all Employees
		for (Employee emp : mainControler.getCompany().getEmployees()) {
			// Look all checks
			for (CheckInOut cio : emp.getCheckInOut()) {
				// If min filter is set and check is greater than min filter
				if ((filterMin != null && cio.getDate().compareTo(filterMin) >= 0) || filterMin == null) {
					// If max filter is set and check is greater than max filter
					if ((filterMax != null && cio.getDate().compareTo(filterMax) <= 0) || filterMax == null) {
						// Fill the Line
						data[i][0] = Integer.toString(emp.getId());
						data[i][1] = emp.getfName();
						data[i][2] = emp.getlName();
						if (emp.getDpt() != null)
							data[i][3] = emp.getDpt().toString();
						else
							data[i][3] = "unassigned";
						data[i][4] = cio.getDate().format(dateFormatter);
						i++;
					}
				}
			}
		}

		// Print checks of unknown employees
		for (CheckInOut cio : MrX.getCheckInOut()) {
			data[i][0] = "X";
			data[i][1] = "Unknown Employee";
			data[i][2] = "Unknown Employee";
			data[i][3] = "Unknown Employee";
			data[i][4] = cio.getDate().format(dateFormatter);
			i++;
		}

		// Set the new model
		model = new DefaultTableModel(data,
				new String[] { "Id", "First Name", "Last Name", "Department", "Hour of check" });

		// Print model on window
		table.setModel(model);
	}

	/**
	 * Method to make appear last check of employees who are still working
	 * 
	 * @param currentlyWorking
	 *            List of Employees who are still working
	 */
	public void printCurrentlyWorking(ArrayList<Employee> currentlyWorking) {
		String[][] data = new String[currentlyWorking.size()][5];
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm");
		CheckInOut cio;
		int i = 0;

		// If nobody is working currently
		if (currentlyWorking.isEmpty()) {
			data = new String[1][5];
			data[0][0] = "No";
			data[0][1] = "Employee";
			data[0][2] = "Currently";
			data[0][3] = "Working";
			data[0][4] = "!";
		} else {
			// Fill the line for each employee
			for (Employee emp : currentlyWorking) {
				cio = emp.getCheckInOut().get(currentlyWorking.size() - 1);
				data[i][0] = Integer.toString(emp.getId());
				data[i][1] = emp.getfName();
				data[i][2] = emp.getlName();
				if (emp.getDpt() != null)
					data[i][3] = emp.getDpt().toString();
				else
					data[i][3] = "unassigned";
				data[i][4] = cio.getDate().format(dateFormatter);
				i++;
			}
		}

		// Print the result
		table.setModel(new DefaultTableModel(data,
				new String[] { "Id", "First Name", "Last Name", "Department", "Hour of check" }));

	}

}
