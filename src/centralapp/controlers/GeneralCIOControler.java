package centralapp.controlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import centralapp.model.Employee;
import centralapp.views.GeneralCIOView;

public class GeneralCIOControler {
	private CentralApp mainControler;
	private GeneralCIOView view;
	private String formatStr = "yyyy-MM-dd|HH:mm"+"-Europe/Paris";
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm-VV");
	
	public GeneralCIOControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new GeneralCIOView(mainControler, this);
		view.setMinTextField(formatStr);
		view.setMaxTextField(formatStr);
	}
	
	public GeneralCIOView getView() {
		return view;
	}
	
	public void updateTable(){
		view.updateTable();
	}
	
	public DateTimeFormatter getDateFormatter(){
		return dateFormatter;
	}
	
	public ZonedDateTime strToDate(String str){
		ZonedDateTime result = null;
		try{
			result = ZonedDateTime.parse(str, dateFormatter);
		}catch(DateTimeParseException e){
			System.err.println("Invalid Date Format");
		}
		
		return result;
	}
	
	public class FilterEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//System.err.println("[GCIOC] Filter event");
			String strDateMin = view.getMinTextField();
			String strDateMax = view.getMaxTextField();
			ZonedDateTime dateMin = strToDate(strDateMin);
			ZonedDateTime dateMax = strToDate(strDateMax);
			
			view.setFilterMin(dateMin);
			view.setFilterMax(dateMax);
			
			updateTable();
		}
	}
	
	public class ResetEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//System.err.println("[GCIOC] Reset event");
			view.setFilterMin(null);
			view.setFilterMax(null);
			view.setMinTextField(formatStr);
			view.setMaxTextField(formatStr);
			
			updateTable();
		}
	}
}
