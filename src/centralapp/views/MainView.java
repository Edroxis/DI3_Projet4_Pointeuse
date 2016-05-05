package centralapp.views;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	public MainView(CompanyView company, DepartmentView dpt, PeopleView people) {
		//GTK theme
		/*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {	}*/
		
		//Ensure to exit the program and not only the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(800, 600);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("Company", company);
		tabbedPane.addTab("Department", dpt);
		tabbedPane.addTab("People", people);
		tabbedPane.setSelectedComponent(people);
		
		//Just to test
		//openCheckInOutTab();
	}
}