package centralapp.views;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

import com.jgoodies.forms.layout.FormLayout;

import centralapp.model.Employee;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JTabbedPane tabbedPane;
	
	public MainWindow() {
		//GTK theme
		/*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {	}*/
		
		//Ensure to exit the program and not only the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		setUpCompanyTab();
		setUpDepartmentsTab();
		setUpPeopleTab();
		
		//Just to test
		//openCheckInOutTab();
	}
	
	private void setUpCompanyTab() {
		JPanel companyPanel = new JPanel();
		tabbedPane.addTab("Company", companyPanel);
		
		//Create the form: name, boss, apply button
		FormLayout formLayout = new FormLayout(
				"pref, 10dlu, 100dlu",
				"default, default, default");
		companyPanel.setLayout(formLayout);
		
		JLabel companyNameLabel = new JLabel("Name");
		JTextField companyNameField = new JTextField();
		companyPanel.add(companyNameLabel, "2, 2");
	}
	
	private void setUpDepartmentsTab() {
		JPanel departmentsPanel = new JPanel();
		tabbedPane.addTab("Departments", departmentsPanel);
		
		departmentsPanel.setLayout(new BoxLayout(departmentsPanel, BoxLayout.Y_AXIS));
		
		JComboBox<String> departmentsComboBox = new JComboBox<String>();
		departmentsPanel.add(departmentsComboBox);
		
		JPanel departmentFormPanel = new JPanel();
		departmentsPanel.add(departmentFormPanel);
		
		//Create the form: name, button choose manager, button apply, add, button remove
	}
	
	//It must display the boss, manger, employees
	private void setUpPeopleTab() {
		JPanel peoplePanel = new JPanel();
		tabbedPane.addTab("People", peoplePanel);
		
		peoplePanel.setLayout(new BoxLayout(peoplePanel, BoxLayout.Y_AXIS));
		
		//The tree can change assign departments
		JTree peopleTree = new JTree();
		peoplePanel.add(peopleTree);
		
		JPanel personFormPanel = new JPanel();
		peoplePanel.add(personFormPanel);
		
		//Create the form: firstname, lastname, 
	}
	
	//It is open when there is a double click on people (except the boss)
	private void openCheckInOutTab(Employee employee) {
		JPanel checkInOutPanel = new JPanel();
		tabbedPane.addTab(employee + "'s check in/out", checkInOutPanel);
		
		JTable checkInOutTable = new JTable();
		checkInOutPanel.add(checkInOutTable);
	}
}