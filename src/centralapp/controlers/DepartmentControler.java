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

public class DepartmentControler {
	private CentralApp mainControler;
	private DepartmentView view;
	
	public DepartmentControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new DepartmentView(mainControler, this);
	}
	
	public DepartmentView getView() {
		return view;
	}
	
	public void updateDepartmentsList(ArrayList<Department> list) {
		view.updateDepartmentsList(list);
	}
	
	public void updatePeopleList(ArrayList<Employee> list) {
		view.updatePeopleList(list);
	}
	
	public class SelectEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				view.updateFromChoosenDpt(view.getDpt());
			}
		}
	}
	
	public class AddEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			String newDptName = view.getName();
			Company company = mainControler.getCompany();
			Department newDpt = new Department(newDptName, company);
			Employee futureManager = view.getManager();
			if(futureManager != null) {
				newDpt.setManager(new Manager(futureManager));
				futureManager.removeEmployee();
			}
			mainControler.notifyDptListModification();
		}
	}
	
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Department dpt = view.getDpt();
			dpt.setName(view.getName());
			Employee futureManager = view.getManager();
			if(futureManager != null) {
				dpt.setManager(new Manager(futureManager));
				futureManager.removeEmployee();
			}
			mainControler.notifyDptListModification();
		}
	}
	
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			mainControler.getCompany().removeDepartment(view.getDpt());
			mainControler.notifyDptListModification();
		}
	}
}
