package centralapp.controlers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Company;
import centralapp.views.PeopleView;

public class PeopleControler {
	private Company company;
	private PeopleView view;
	
	public PeopleControler(Company companyToModify) {
		company = companyToModify;
		view = new PeopleView(this);
	}
	
	public PeopleView getView() {
		return view;
	}
	
	public class SelectEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			int countClicks = event.getClickCount();
			
			if(countClicks == 1) {
				System.err.println("[People] Select person");
			}
			else if(countClicks == 2) {
				System.err.println("[People] Open check in/out person");
			}
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
			System.err.println("[People] Add event");
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
