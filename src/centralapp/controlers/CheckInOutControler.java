package centralapp.controlers;

import centralapp.model.Employee;
import centralapp.views.CheckInOutView;
import centralapp.views.CompanyView;

public class CheckInOutControler {
	private CentralApp mainControler;
	private CheckInOutView view;
	public int tabNb;	//Numero de l'onglet
	
	public CheckInOutControler(CentralApp mainControler, Employee emp, int tabNb) {
		this.mainControler = mainControler;
		view = new CheckInOutView(mainControler, this, emp);
		this.tabNb = tabNb;
	}
	
	public int getTabNb() {
		return tabNb;
	}
	
	public CheckInOutView getView() {
		return view;
	}
}
