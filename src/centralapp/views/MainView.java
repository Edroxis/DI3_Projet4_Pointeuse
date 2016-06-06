package centralapp.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import centralapp.controlers.CentralApp;

/**
 * The main window containing all the sub-views
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	private JTabbedPane tabbedPane;
	
	/**
	 * Constructor of MainView
	 * 
	 * @param controler The controlers associated to this view
	 */
	public MainView(CentralApp controler) {
		//GTK theme
		/*try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {	}*/
		
		//Ensure to exit the program and not only the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(800, 600);
		
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane);
		
		//Just to test
		//openCheckInOutTab();
		
		//Set up events
		addWindowListener(controler.new ExitEvent());
	}
	
	/**
	 * Add the panel in parameter as a tab of the main window
	 * 
	 * @param name The tab's name
	 * @param tab The panel to add
	 */
	public void addTab(String name, JPanel tab){
		tabbedPane.addTab(name, tab);
		CentralApp.nbTab++;
	}

	/**
	 * Close a tab
	 * 
	 * @param tabNb The tab id
	 */
	public void closeTab(int tabNb) {
		tabbedPane.remove(tabNb);
	}
}