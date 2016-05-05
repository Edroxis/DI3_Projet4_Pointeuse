package centralapp.controlers;

import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.views.MainView;

public class CentralApp {
	MainView mainWindow;
	
	public CentralApp() {
		Boss boss = new Boss("First name", "Last name");
		Company company = new Company("Company name", boss);
		
		CompanyControler companyControler = new CompanyControler(company);
		DepartmentControler departmentControler = new DepartmentControler(company);
		PeopleControler peopleControler = new PeopleControler(company);
		mainWindow = new MainView(
				companyControler.getView(),
				departmentControler.getView(),
				peopleControler.getView());
	}
	
	public void run() {
		mainWindow.setVisible(true);
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}
