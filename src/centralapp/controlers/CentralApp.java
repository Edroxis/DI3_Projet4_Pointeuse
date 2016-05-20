package centralapp.controlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private Company company;
	
	public CentralApp() {		
		companyControler = new CompanyControler(this);
		departmentControler = new DepartmentControler(this);
		peopleControler = new PeopleControler(this);
		mainWindow = new MainView(
				companyControler.getView(),
				departmentControler.getView(),
				peopleControler.getView());
	}
	
	public void run() {
		if(!openFile()) {
			Boss boss = new Boss("Last name", "First name");
			company = new Company("Company's name", boss);
		}
		
		//Update the first infos
		companyControler.getView().updateCompanyName(company.toString());
		notifyDptListModification();
		notifyPeopleListModification();
		
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
	
	private boolean openFile() {
		boolean openFileSucceed = false;
		
	    JFileChooser companyChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Company file", "ser");
	    companyChooser.setFileFilter(filter);
	    
	    if(companyChooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
	    	String absolutePath = companyChooser.getSelectedFile().getAbsolutePath();
	 
	    	try {
	    		company = Company.unserialize(absolutePath);
	    		openFileSucceed = true;
	    	}
    		catch(ClassNotFoundException e) {
    			System.err.println("Bad version file: " + absolutePath);
    		}
	    	catch(FileNotFoundException e) {
	    		System.err.println("Cannot find the following file: " + absolutePath);
	    	}
	    	catch(IOException e) {
	    		System.err.println("File corrputed: " + absolutePath);
	    	}
	    }
	    
	    return openFileSucceed;
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
