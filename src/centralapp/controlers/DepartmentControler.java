package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import centralapp.model.AbstractDpt;
import centralapp.model.AbstractPerson;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.model.ManagementDpt;
import centralapp.model.Manager;
import centralapp.views.DepartmentView;

/**
 * <b>DepartmentControler : Controler of the view with the Departments</b>
 * 
 * @author Julien
 */
public class DepartmentControler {
	/**
	 * Reference to the main Controler
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the view
	 */
	private DepartmentView view;

	/**
	 * @param mainControler
	 *            Reference to the CentralApp
	 */
	public DepartmentControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new DepartmentView(mainControler, this);
	}

	/**
	 * @return Reference to the view
	 */
	public DepartmentView getView() {
		return view;
	}

	/**
	 * Calls method to update view.
	 * 
	 * @param list
	 *            List of Departments
	 */
	public void updateDepartmentsList(ArrayList<Department> list) {
		view.updateDepartmentsList(list);
	}

	/**
	 * Calls method to update view.
	 * 
	 * @param list
	 */
	public void updatePeopleList(ArrayList<Employee> list) {
		view.updatePeopleList(list);
	}

	/**
	 * Class to Manage Manager selection
	 */
	public class SelectEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				view.updateFromChoosenDpt(view.getDpt());
			}
		}
	}

	/**
	 * Class to manage Add button
	 */
	public class AddEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			String newDptName = view.getName();
			Company company = mainControler.getCompany();
			Department newDpt = new Department(newDptName, company);
			Employee futureManager = view.getManager();
			if (futureManager != null) {
				newDpt.setManager(new Manager(futureManager));
				futureManager.removeEmployee();
			}
			mainControler.notifyDptListModification();
		}
	}

	/**
	 * Class to Manage Apply Button
	 */
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Department dpt = view.getDpt();
			dpt.setName(view.getName());
			Employee futureManager = view.getManager();
			if (futureManager != null) {
				dpt.setManager(new Manager(futureManager));
				futureManager.removeEmployee();
			}
			mainControler.notifyDptListModification();
		}
	}

	/**
	 * Class to manage Remove Button
	 */
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			mainControler.getCompany().removeDepartment(view.getDpt());
			mainControler.notifyDptListModification();
		}
	}
}
