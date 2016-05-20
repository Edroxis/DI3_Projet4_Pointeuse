package centralapp.controlers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private JFileChooser companyChooser;
	private String companyFileLocation;
	
	public CentralApp() {		
		companyControler = new CompanyControler(this);
		departmentControler = new DepartmentControler(this);
		peopleControler = new PeopleControler(this);
		
		mainWindow = new MainView(this);
		mainWindow.addTab("Company", companyControler.getView());
		mainWindow.addTab("Department", departmentControler.getView());
		mainWindow.addTab("People", peopleControler.getView());
		
		companyChooser = new JFileChooser();
		companyChooser.setFileFilter(new FileNameExtensionFilter(
				"Company file", "ser"));
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
				ComControler instance = new ComControler(company, "127.0.0.1", 1337);
				instance.run();
			} catch (IOException e) {}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
		}
	}
	
	private boolean openFile() {
		boolean openFileSucceed = false;
	    
	    if(companyChooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
	    	companyFileLocation = companyChooser.getSelectedFile().getAbsolutePath();
	 
	    	try {
	    		company = Company.unserialize(companyFileLocation);
	    		openFileSucceed = true;
	    	}
    		catch(ClassNotFoundException e) {
    			System.err.println("Bad version file: " + companyFileLocation);
    		}
	    	catch(FileNotFoundException e) {
	    		System.err.println("Cannot find the following file: " + companyFileLocation);
	    	}
	    	catch(IOException e) {
	    		System.err.println("File corrputed: " + companyFileLocation);
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
		
		companyControler.updatePeopleList(list);
		departmentControler.updatePeopleList(list);
		peopleControler.updatePeopleList(list);
	}
	
	public class ExitEvent extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			if(companyFileLocation == null) {			    
			    if(companyChooser.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
			    	companyFileLocation = companyChooser.getSelectedFile().getAbsolutePath();
			    	
			    	try {
						company.serialize(companyFileLocation);
						
					} catch (FileNotFoundException e) {
						System.err.println("Cannot save in " + companyFileLocation
								+ ". File not found!");
						
					} catch (IOException e) {
						System.err.println("Cannot save in " + companyFileLocation +
								". Unknown error!");
					}
			    }
			}
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		CentralApp app = new CentralApp();
		app.run();
	}

}
