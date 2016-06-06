package centralapp.controlers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import centralapp.model.*;
import centralapp.views.MainView;

//TODO delete once test finished
import java.time.ZonedDateTime;

public class CentralApp {
	private CompanyControler companyControler;
	private DepartmentControler departmentControler;
	private PeopleControler peopleControler;
	private GeneralCIOControler generalCheckControler;
	private ArrayList<CheckInOutControler> checksControlers;
	private MainView mainWindow;
	private Company company;
	private JFileChooser companyChooser;
	
	private final String fileExtension = "ser";
	private final String fileSuffix = '.' + fileExtension;
	
	public static int nbTab;
	
	public CentralApp() {		
		companyControler = new CompanyControler(this);
		departmentControler = new DepartmentControler(this);
		peopleControler = new PeopleControler(this);
		checksControlers = new ArrayList<CheckInOutControler>();
		generalCheckControler = new GeneralCIOControler(this);
		
		mainWindow = new MainView(this);
		mainWindow.addTab("Company", companyControler.getView());
		mainWindow.addTab("Department", departmentControler.getView());
		mainWindow.addTab("People", peopleControler.getView());
		mainWindow.addTab("Checks", generalCheckControler.getView());
		
		companyChooser = new JFileChooser();
		companyChooser.setFileFilter(new FileNameExtensionFilter(
				"Company file", fileExtension));
		
	}
	
	public void run() {
		if(!openFile()) {
			Boss boss = new Boss("Last name", "First name");
			company = new Company("Company's name", boss);
			
			//TODO This is a test, REMOVE
			Department dpt1 = new Department("Dpt1", company);
			Department dpt2 = new Department("Dpt2", company);
			Employee emp4 = new Employee("Jean2", "Bon2", dpt1);
			Employee emp5 = new Employee("Jean3", "Bon3", dpt2);
			Manager man = new Manager("Machin", "Truc", company);
			dpt1.setManager(man);
			//emp3.addCheckInOut(new CheckInOut(ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]")));
			emp4.addCheckInOut(new CheckInOut(ZonedDateTime.parse("2010-11-03T10:15:30+01:00[Europe/Paris]")));
			emp5.addCheckInOut(new CheckInOut(ZonedDateTime.parse("1995-11-03T10:15:30+01:00[Europe/Paris]")));
			//openCheckTab(emp3);
			openCheckTab(emp4);
			openCheckTab(emp5);
			//closeCheckTab(checksControlers.get(0));
			//closeCheckTab(checksControlers.get(0));
			//closeCheckTab(checksControlers.get(0));
			//emp3.addCheckInOut(new CheckInOut(ZonedDateTime.parse("2007-10-03T10:15:30+01:00[Europe/Paris]")));
			//checkControler = new CheckInOutControler(this, emp3);
			//mainWindow.addTab(emp3.getfName()+" "+emp3.getlName(), checkControler.getView());
			//////////////////////
		}
		
		//Update the first infos
		companyControler.getView().updateCompanyName(company.toString());
		notifyPeopleListModification();
		notifyDptListModification();
		
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
	
	public void openCheckTab(Employee emp){	//Fonction pour ouvrir un onglet de Checks In Out
		boolean alreadyOpened = false;
		for(CheckInOutControler cioc : checksControlers)
			if(emp == cioc.getEmployee())
				alreadyOpened = true;
		
		if(alreadyOpened == false)
		{
			CheckInOutControler newCIOC = new CheckInOutControler(this, emp, nbTab);
			checksControlers.add(newCIOC);
			mainWindow.addTab(emp.getfName()+" "+emp.getlName(), newCIOC.getView());
		}
		else
			System.err.println("[CentrallApp#openCheckTab] Tab already opened");
	}
	
	public void closeCheckTab(CheckInOutControler controler){
		int indexInAL = checksControlers.indexOf(controler);
		
		if(indexInAL != -1){	//if tab is contained on the window
			for(int i = indexInAL+1; i < checksControlers.size(); i++)	//Reduce tabNb of following tabs (avoid NullPointerException)
				checksControlers.get(i).tabNb--;
			mainWindow.closeTab(controler.getTabNb());
			checksControlers.remove(controler);
			nbTab--;
		}
		else
			System.err.println("[CentralApp#closeCheckTab] this CheckInOutControler doesn't exist");
	}
	
	private boolean openFile() {
		boolean openFileSucceed = false;
	    
	    if(companyChooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
	    	String companyFileLocation = companyChooser.getSelectedFile().getAbsolutePath();
	 
	    	try {
	    		company = Company.unserialize(companyFileLocation);
	    		openFileSucceed = true;
	    	}
    		catch(ClassNotFoundException e) {
    			JOptionPane.showMessageDialog(mainWindow,
    					"Bad version file: " + companyFileLocation,
	    				"Error", JOptionPane.ERROR_MESSAGE);
    		}
	    	catch(FileNotFoundException e) {
	    		JOptionPane.showMessageDialog(mainWindow,
	    				"Cannot find the following file: " + companyFileLocation,
	    				"Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    	catch(IOException e) {
	    		JOptionPane.showMessageDialog(mainWindow,
	    				"File corrupted: " + companyFileLocation,
	    				"Error", JOptionPane.ERROR_MESSAGE);
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
		
		companyControler.updatePeopleList(list, company.getBoss());
		departmentControler.updatePeopleList(list);
		peopleControler.updatePeopleList(list);
		generalCheckControler.updateTable();
	}
	
	public class ExitEvent extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {		    
		    if(companyChooser.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
		    	String fileLocation = companyChooser.getSelectedFile().getAbsolutePath();
		    	if(!fileLocation.endsWith(fileSuffix))
		    		fileLocation = fileLocation + fileSuffix;
		    	
		    	try {
					company.serialize(fileLocation);
					
				} catch (FileNotFoundException e) {
					System.err.println("Cannot save in " + fileLocation
							+ ". File not found!");
					
				} catch (IOException e) {
					System.err.println("Cannot save in " + fileLocation +
							". Unknown exception " + e);
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
