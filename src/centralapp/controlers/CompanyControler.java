package centralapp.controlers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.views.CompanyView;

public class CompanyControler {
	private Company company;
	private CompanyView view;
	
	public CompanyControler(Company companyModel) {
		company = companyModel;
		view = new CompanyView(this);
	}
	
	public CompanyView getView() {
		return view;
	}
	
	public String getCompanyName() {
		return company.getName();
	}
	
	public String getBossName() {
		Boss boss = company.getBoss();
		return boss.getfName() + " " + boss.getlName();
	}

	public class ChangeNameEvent extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			String newName = view.getName();
			if(company.getName() != newName) {
				//There is a modif, so update
				company.setName(newName);
			}
		}
	}
	
	public class ChangeBossEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("Change company boss");
		}
	}
}
