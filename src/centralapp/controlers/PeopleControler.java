package centralapp.controlers;

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
}
