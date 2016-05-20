package centralapp.controlers;

import centralapp.model.Employee;
import centralapp.views.CheckInOutView;
import centralapp.views.CompanyView;

public class CheckInOutControler {
	private CentralApp mainControler;
	private CheckInOutView view;
	
	public CheckInOutControler(CentralApp mainControler, Employee emp) {
		this.mainControler = mainControler;
		view = new CheckInOutView(mainControler, this, emp);
	}
	
	public CheckInOutView getView() {
		return view;
	}
}
