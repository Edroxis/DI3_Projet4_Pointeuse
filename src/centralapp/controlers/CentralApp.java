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

/**
 * The main class representing the central application
 */
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
	
	/**
	 * The count of tabs opened in the main view
	 */
	public static int nbTab;
	
	/**
	 * The constructor
	 */
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
	
	/**
	 * Execute the central app
	 */
	public void run() {
		if(!openFile()) {
			Boss boss = new Boss("Last name", "First name");
			company = new Company("Company's name", boss);
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
	
	/** 
	 * Open a check in/out tab for the employee in argument
	 * 
	 * @param emp The employee
	 */
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
	
	/**
	 * Close a check in/out tab associated to the controler
	 * 
	 * @param controler The controler associated to the sub-view (tab) to close
	 */
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
	
	/**
	 * Load a company file
	 * 
	 * @return true if the loading has succeed, false otherwise
	 */
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
	
	/**
	 * @return The company associated to the central application
	 */
	public Company getCompany() {
		return company;
	}
	
	/**
	 * Notify all the sub-controlers that the list of departments has changed
	 */
	public void notifyDptListModification() {
		ArrayList<Department> list = company.getDepartments();
		
		departmentControler.updateDepartmentsList(list);
		peopleControler.updateDepartmentsList(list);
	}
	
	/**
	 *  Notify all the sub-controlers that the list of people has changed
	 */
	public void notifyPeopleListModification() {
		ArrayList<Employee> list = company.getEmployees();
		
		companyControler.updatePeopleList(list, company.getBoss());
		departmentControler.updatePeopleList(list);
		peopleControler.updatePeopleList(list);
		generalCheckControler.updateTable();
	}
	
	/**
	 * On exit, open a save dialog
	 */
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
