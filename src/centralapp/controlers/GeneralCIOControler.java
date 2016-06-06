package centralapp.controlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import centralapp.model.Boss;
import centralapp.model.CheckInOut;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.model.ManagementDpt;
import centralapp.views.GeneralCIOView;

/**
 * <b>GeneralCIOC : Controler of the view with all checks</b>
 * 
 * @author Julien
 */
public class GeneralCIOControler {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the view
	 */
	private GeneralCIOView view;

	/**
	 * General format for dates
	 */
	private String formatStr = "yyyy-MM-dd|HH:mm" + "-Europe/Paris";

	/**
	 * Object used to cast String to dates and dates to Strings
	 */
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm-VV");

	/**
	 * Constructor of the class
	 * 
	 * @param mainControler
	 *            reference to the CentrallApp
	 */
	public GeneralCIOControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new GeneralCIOView(mainControler, this); // Construct the view
		view.setMinTextField(formatStr);
		view.setMaxTextField(formatStr);
	}

	/**
	 * Get reference to the view
	 * 
	 * @return Reference to the View
	 */
	public GeneralCIOView getView() {
		return view;
	}

	/**
	 * Update Table on the view
	 * 
	 * @see GeneralCIOView#updateTable()
	 */
	public void updateTable() {
		view.updateTable();
	}

	/**
	 * Get the Formatter of dates to String
	 * 
	 * @return The DateFormatter
	 */
	public DateTimeFormatter getDateFormatter() {
		return dateFormatter;
	}

	/**
	 * Convert a String to a Date
	 * 
	 * @param str
	 *            String to convert in a ZonedDateTime
	 * 
	 * @return The date
	 */
	public ZonedDateTime strToDate(String str) {
		ZonedDateTime result = null;
		try {
			result = ZonedDateTime.parse(str, dateFormatter);
		} catch (DateTimeParseException e) {
			System.err.println("Invalid Date Format");
		}

		return result;
	}

	/**
	 * Mouse adapter to manage click on the button to Filter with dates
	 */
	public class FilterEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Get String contained on view's TextField
			String strDateMin = view.getMinTextField();
			String strDateMax = view.getMaxTextField();

			// Convert into Date
			ZonedDateTime dateMin = strToDate(strDateMin);
			ZonedDateTime dateMax = strToDate(strDateMax);

			// Change filters on view
			view.setFilterMin(dateMin);
			view.setFilterMax(dateMax);

			updateTable();
		}
	}

	/**
	 * Mouse Adapter to manage Reset button
	 */
	public class ResetEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Delete Filter
			view.setFilterMin(null);
			view.setFilterMax(null);

			// Put Default String on TextField
			view.setMinTextField(formatStr);
			view.setMaxTextField(formatStr);

			updateTable();
		}
	}

	/**
	 * Manage button to see currently working people
	 */
	public class WorkingEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// Stock today's date
			String format = "dd/MM/yyyy";
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
			java.util.Date date = new java.util.Date();
			String today = formater.format(date);

			Company cmp = mainControler.getCompany();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			ArrayList<Employee> currentlyWorking = new ArrayList<Employee>();

			// Look on all employees
			for (Employee emp : cmp.getEmployees()) {
				ArrayList<CheckInOut> list = emp.getCheckInOut();
				// If their last check correspond to today
				if (!list.isEmpty()) {
					if (list.get(list.size() - 1).getDate().format(formatter).equals(today)) {
						// Add if only 1 check
						if (list.size() == 1) {
							currentlyWorking.add(emp);
						} else {
							// if more than 1 check, verify if previous one is
							// not at today too
							if (!list.get(list.size() - 2).getDate().format(formatter).equals(today))
								currentlyWorking.add(emp);
						}
					}
				}
			}
			view.printCurrentlyWorking(currentlyWorking);
		}
	}
}
