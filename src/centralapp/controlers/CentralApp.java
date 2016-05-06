package centralapp.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import centralapp.model.AbstractDpt;
import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.views.MainView;

public class CentralApp {
	private CompanyControler companyControler;
	private DepartmentControler departmentControler;
	private PeopleControler peopleControler;
	private MainView mainWindow;
	private Boss boss;
	private Company company;
	
	public CentralApp() {
		boss = new Boss("Page", "Jimmy");
		company = new Company("LedZep", boss);
		
		companyControler = new CompanyControler(this);
		departmentControler = new DepartmentControler(this);
		peopleControler = new PeopleControler(this);
		mainWindow = new MainView(
				companyControler.getView(),
				departmentControler.getView(),
				peopleControler.getView());
	}
	
	public void run() {
		mainWindow.setVisible(true);

		/*  This loop allows to retry the socket client connection if it fails
		 */
		while(true) {
			try {
				CheckInOutControler instance = new CheckInOutControler(company, "127.0.0.1", 1337);
				instance.run();
			} catch (IOException e) {}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
		}
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void notifyDptListModification() {
		ArrayList<Department> list = company.getDepartments();
		
		departmentControler.updateDepartmentsList(list);
		peopleControler.updateDepartmentsList(list);
	}
	
	public void notifyPeopleListModification() {
		ArrayList<Employee> list = company.getEmployees();
		
		departmentControler.updatePeopleList(list);
		peopleControler.updatePeopleList(list);
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}
