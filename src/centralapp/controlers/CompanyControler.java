package centralapp.controlers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.views.CompanyView;

public class CompanyControler {
	private CentralApp mainControler;
	private CompanyView view;
	
	public CompanyControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new CompanyView(mainControler, this);
	}
	
	public CompanyView getView() {
		return view;
	}

	public class NameEvent extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			String newName = view.getName();
			Company company = mainControler.getCompany();
			
			if(company.toString() != newName) {
				//There is a modif, so update
				company.setName(newName);
			}
		}
	}
	
	public class BossEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				System.err.println("[Company] Select event");
			}
		}
	}
}
