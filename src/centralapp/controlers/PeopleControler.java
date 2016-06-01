package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.views.PeopleView;
import centralapp.views.PeopleView.MyDefaultMutableTreeNode;

public class PeopleControler {
	private CentralApp mainControler;
	private PeopleView view;
	
	private int lastSelectedID;
	
	public PeopleControler(CentralApp controler) {
		mainControler = controler;
		view = new PeopleView(controler, this);
	}
	
	public PeopleView getView() {
		return view;
	}
	
	public void updateDepartmentsList(ArrayList<Department> list) {
		view.updateDepartmentsList(list);
	}
	
	public void updatePeopleList(ArrayList<Employee> list) {
		view.updatePeopleList(list);
	}
	
	public class TreeSelectEvent implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent event) {
			MyDefaultMutableTreeNode actualNode = ((MyDefaultMutableTreeNode) event.getNewLeadSelectionPath().getLastPathComponent());
			lastSelectedID = actualNode.getId();
			if(lastSelectedID>=0){
				//Open tab of the employee
				mainControler.openCheckTab(mainControler.getCompany().findEmployee(lastSelectedID));
				
				//Change selected item on comboBox to Dpt of selected Employee
				view.selectDepartmentBoxId(-((MyDefaultMutableTreeNode)actualNode.getParent()).getId());	
			}
			else
				view.selectDepartmentBoxId(-lastSelectedID);	//Change selected item on comboBox to selected Dpt
		}
	}
	
	public class SelectDepartmentEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				System.err.println("[People] Select department event");
			}
		}
	}
	
	public class AddEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//TODO: Assign dpt too
			System.err.println("[People] Add event");
			
			String firstName = view.getFirstName();
			String lastName = view.getLastName();
			Department dpt = view.getDepartment();
			
			Employee employee = new Employee(firstName, lastName);
			if(dpt != null)
				employee.assign(dpt);
				
			mainControler.getCompany().add(employee);
			mainControler.notifyPeopleListModification();
		}
	}
	
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[People] Apply event");
		}
	}
	
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[People] Remove event");
		}
	}
}
