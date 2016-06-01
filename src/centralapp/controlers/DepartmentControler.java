package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import centralapp.model.AbstractDpt;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.model.ManagementDpt;
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
				AbstractDpt dpt = (AbstractDpt)event.getItem();
				
				view.setName(dpt.toString());
				if(dpt instanceof ManagementDpt)
					view.setManager(mainControler.getCompany().getBoss());
				else
					view.setManager(((Department)dpt).getManager());
				
				updatePeopleList(mainControler.getCompany().getEmployees());
			}
		}
	}
	
	public class SelectManagerEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				System.err.println("[Dpt] Select manager event");
			}
		}
	}
	
	public class AddEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//TODO: Promote if it's an employee
			String newDptName = view.getName();
			Company company = mainControler.getCompany();
			
			Department newDpt = new Department(newDptName, company);
			newDpt.setManager(view.getManager());
			
			//company.add(newDpt);
			mainControler.notifyDptListModification();
		}
	}
	
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//TODO: Promote if it's an employee
			System.err.println("[Dpt] Apply event");
			mainControler.notifyDptListModification();
		}
	}
	
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[Dpt] Remove event");
			mainControler.notifyDptListModification();
		}
	}
}
