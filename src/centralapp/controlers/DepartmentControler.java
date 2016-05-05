package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Company;
import centralapp.views.DepartmentView;

public class DepartmentControler {
	private Company company;
	private DepartmentView view;
	
	public DepartmentControler(Company companyToModify) {
		company = companyToModify;
		view = new DepartmentView(this);
	}
	
	public DepartmentView getView() {
		return view;
	}
	
	public class SelectEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				System.err.println("[Dpt] Select event");
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
			System.err.println("[Dpt] Add event");
		}
	}
	
	public class ApplyEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[Dpt] Apply event");
		}
	}
	
	public class RemoveEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[Dpt] Remove event");
		}
	}
}
