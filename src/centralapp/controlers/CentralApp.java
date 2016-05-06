package centralapp.controlers;

import java.io.IOException;
import java.util.ArrayList;

import centralapp.model.AbstractDpt;
import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.views.MainView;

public class CentralApp {
	CompanyControler companyControler;
	DepartmentControler departmentControler;
	PeopleControler peopleControler;
	MainView mainWindow;
	Company company;
	
	public CentralApp() {
		Boss boss = new Boss("Page", "Jimmy");
		company = new Company("LedZep", boss);
		
		CompanyControler companyControler = new CompanyControler(this);
		DepartmentControler departmentControler = new DepartmentControler(this);
		PeopleControler peopleControler = new PeopleControler(this);
		mainWindow = new MainView(
				companyControler.getView(),
				departmentControler.getView(),
				peopleControler.getView());
	}
	
	public void run() {
		mainWindow.setVisible(true);

		//Run the client socket to get data
		try {
			CheckInOutControler instance = new CheckInOutControler(company, "127.0.0.1", 1337);
			instance.run();
		} catch (IOException e) {
			System.err.println("The client listening the TimeClocking has not been launched");
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
		
		//TODO: BIG ISSUE HOW CAN DEPARTMENTCONTROLLER BE NULL????
		departmentControler.updatePeopleList(list);
		peopleControler.updatePeopleList(list);
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}
