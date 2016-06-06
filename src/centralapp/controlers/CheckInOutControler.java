package centralapp.controlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Employee;
import centralapp.views.CheckInOutView;

/**
 * <b>CheckInOutControler : Manage the view of each person's checks</b>
 * 
 */
public class CheckInOutControler {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the view
	 */
	private CheckInOutView view;

	/**
	 * Reference to the corresponding Employee
	 */
	private Employee employee;

	/**
	 * Index of the Tab
	 */
	public int tabNb;

	/**
	 * Constructor of the class
	 * 
	 * @param mainControler
	 *            Reference to the CentralApp
	 * @param emp
	 *            Reference to the corresponding Empoyee
	 * @param tabNb
	 *            Index of the tab
	 */
	public CheckInOutControler(CentralApp mainControler, Employee emp, int tabNb) {
		this.mainControler = mainControler;
		employee = emp;
		view = new CheckInOutView(mainControler, this, employee);
		this.tabNb = tabNb;
	}

	/**
	 * Return the Employee
	 * 
	 * @return The reference to the Employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Return the index of the tab where the viex is open
	 * 
	 * @return Index of the tab
	 */
	public int getTabNb() {
		return tabNb;
	}

	/**
	 * Return reference to the view
	 * 
	 * @return The view
	 */
	public CheckInOutView getView() {
		return view;
	}

	/**
	 * Return reference to the current object
	 * 
	 * @return this
	 */
	public CheckInOutControler getHimself() {
		return this;
	}

	/**
	 * Class to manage Close ButtonS
	 */
	public class CloseEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			mainControler.closeCheckTab(getHimself());
		}
	}
}
