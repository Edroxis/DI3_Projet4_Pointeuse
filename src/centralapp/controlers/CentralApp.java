package centralapp.controlers;

import java.io.IOException;

import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.views.MainView;

public class CentralApp {
	MainView mainWindow;
	Company company;
	
	public CentralApp() {
		Boss boss = new Boss("Page", "Jimmy");
		company = new Company("LedZep", boss);
		
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

		//Run the client socket to get data
		try {
			CheckInOutControler instance = new CheckInOutControler(company, "127.0.0.1", 1337);
			instance.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}
