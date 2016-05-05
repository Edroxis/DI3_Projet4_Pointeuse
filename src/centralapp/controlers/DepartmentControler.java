package centralapp.controlers;

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
}
