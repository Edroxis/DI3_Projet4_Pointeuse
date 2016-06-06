package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import centralapp.model.AbstractPerson;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.views.PeopleView;
import centralapp.views.PeopleView.MyDefaultMutableTreeNode;

/**
 * <b>PeopleControler : Controler of the view with peoples<\b>
 * 
 * @author Julien
 */
public class PeopleControler {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the view
	 */
	private PeopleView view;

	/**
	 * ID of last selected Item (Department if negative, employee otherwise)
	 */
	private int lastSelectedID;

	/**
	 * @param controler
	 *            Reference to the CentralApp
	 */
	public PeopleControler(CentralApp controler) {
		mainControler = controler;
		view = new PeopleView(controler, this);
	}

	/**
	 * @return The view
	 */
	public PeopleView getView() {
		return view;
	}

	/**
	 * @param list
	 *            List of Departments
	 */
	public void updateDepartmentsList(ArrayList<Department> list) {
		view.updateDepartmentsList(list);
	}

	/**
	 * @param list
	 *            List of Peoples
	 */
	public void updatePeopleList(ArrayList<Employee> list) {
		view.updatePeopleList(list);
	}

	/**
	 * Class to manage JTree Selection
	 *
	 */
	public class TreeSelectEvent implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent event) {
			TreePath path = event.getNewLeadSelectionPath();

			if (path != null) {
				MyDefaultMutableTreeNode actualNode = ((MyDefaultMutableTreeNode) path.getLastPathComponent());
				lastSelectedID = actualNode.getId();

				if (lastSelectedID >= 0) {
					Employee employee = mainControler.getCompany().findEmployee(lastSelectedID);

					view.setFirstName(employee.getfName());
					view.setLastName(employee.getlName());

					// Change selected item on comboBox to Dpt of selected
					// Employee
					view.selectDepartmentId(-((MyDefaultMutableTreeNode) actualNode.getParent()).getId());
				} else
					view.selectDepartmentId(-lastSelectedID); // Change selected
																// item on
																// comboBox to
																// selected Dpt
			}
		}
	}

	/**
	 * Class to manage Click on the tree
	 */
	public class TreeClickEvent extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent arg0) {
			if (arg0.getClickCount() == 2) {
				if (lastSelectedID >= 0)
					mainControler.openCheckTab(mainControler.getCompany().findEmployee(lastSelectedID));
			}
		}
	}

	/**
	 * Class to manage clic on add Button
	 */
	public class AddEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			String firstName = view.getFirstName();
			String lastName = view.getLastName();
			Department dpt = view.getDepartment();

			Employee employee = new Employee(firstName, lastName);
			if (dpt != null)
				employee.assign(dpt);

			mainControler.getCompany().add(employee);
			mainControler.notifyPeopleListModification();
		}
	}

	/**
	 * Class to manage Clic on Apply Button
	 */
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			String firstName = view.getFirstName();
			String lastName = view.getLastName();
			Department dpt = view.getDepartment();

			AbstractPerson person = mainControler.getCompany().findEmployee(lastSelectedID);
			if (person != null) {
				person.setfName(firstName);
				person.setlName(lastName);

				if (person instanceof Employee)
					((Employee) person).assign(dpt);

				mainControler.notifyPeopleListModification();
			}
		}
	}

	/**
	 * Class to Manage clic on Remove Button
	 */
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			AbstractPerson emp = mainControler.getCompany().findEmployee(lastSelectedID);
			if (emp != null && emp instanceof Employee) {
				mainControler.getCompany().removeEmployee((Employee) emp);
				mainControler.notifyPeopleListModification();
			}
		}
	}
}
