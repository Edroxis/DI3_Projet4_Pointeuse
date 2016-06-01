package centralapp.controlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Employee;
import centralapp.views.CheckInOutView;
import centralapp.views.CompanyView;

public class CheckInOutControler {
	private CentralApp mainControler;
	private CheckInOutView view;
	private Employee employee;
	public int tabNb;	//Numero de l'onglet
	
	public CheckInOutControler(CentralApp mainControler, Employee emp, int tabNb) {
		this.mainControler = mainControler;
		employee = emp;
		view = new CheckInOutView(mainControler, this, employee);
		this.tabNb = tabNb;
	}
	
	public Employee getEmployee(){
		return employee;
	}
	
	public int getTabNb() {
		return tabNb;
	}
	
	public CheckInOutView getView() {
		return view;
	}
	
	public CheckInOutControler getHimself(){
		return this;
	}
	
	public class CloseEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.err.println("[CIO] Close event");
			mainControler.closeCheckTab(getHimself());
		}
	}
}
