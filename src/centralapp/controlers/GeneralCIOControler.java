package centralapp.controlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import centralapp.model.Employee;
import centralapp.views.GeneralCIOView;

public class GeneralCIOControler {
	private CentralApp mainControler;
	private GeneralCIOView view;
	
	public GeneralCIOControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new GeneralCIOView(mainControler, this);
	}
	
	public GeneralCIOView getView() {
		return view;
	}
	
	public void updateTable(){
		view.updateTable();
	}
}
