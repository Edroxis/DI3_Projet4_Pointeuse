package centralapp.views;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.CentralApp;
import centralapp.controlers.CompanyControler;
import centralapp.model.AbstractPerson;
import centralapp.model.Boss;
import centralapp.model.Employee;

/**
 * <b>CompanyControler : Manage the view of the Company</b>
 * 
 * @author Julien
 */
@SuppressWarnings("serial")
public class CompanyView extends JPanel {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;
	
	/**
	 * Reference to the Controler
	 */
	private CompanyControler controler;
	
	/**
	 * TextField containing Company's name
	 */
	private JTextField companyNameField;
	
	/**
	 * Box containing boss
	 */
	private JComboBox<AbstractPerson> companyBossComboBox;
	
	/**
	 * Constructor of the class
	 * 
	 * @param mainControler Reference to the CentralApp
	 * @param companyControler Reference to the Controler
	 */
	public CompanyView(CentralApp mainControler, CompanyControler companyControler) {
		this.mainControler = mainControler;
		controler = companyControler;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel companyNameLabel = new JLabel("Name");
		add(companyNameLabel, "2, 2, right, default");
		
		companyNameField = new JTextField();
		add(companyNameField, "4, 2, fill, default");
		companyNameField.setColumns(10);
		
		JLabel companyBossLabel = new JLabel("Boss");
		add(companyBossLabel, "2, 4");
		
		companyBossComboBox = new JComboBox<AbstractPerson>();
		add(companyBossComboBox, "4, 4");
		
		//Set up events
		companyNameField.addFocusListener(controler.new NameEvent());
		companyBossComboBox.addItemListener(controler.new BossEvent());
	}

	/**
	 * Get TextField content
	 * 
	 * @return The String in the TextField
	 */
	public String getName() {
		return companyNameField.getText();
	}

	/**
	 * Change the Content of the TextField
	 * 
	 * @param name The String to print on the TextField
	 */
	public void updateCompanyName(String name) {
		companyNameField.setText(name);
	}

	/**
	 * Method to update the comboBox
	 * 
	 * @param employeesList List of the Employees
	 * @param boss Reference to the Boss
	 */
	public void updatePeopleList(ArrayList<Employee> employeesList, Boss boss) {
		companyBossComboBox.removeAllItems();
		
		companyBossComboBox.addItem(boss);
		for(Employee employee : employeesList) {
			companyBossComboBox.addItem(employee);
		}
	}
}
