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
import centralapp.model.Employee;

@SuppressWarnings("serial")
public class CompanyView extends JPanel {
	private CentralApp mainControler;
	private CompanyControler controler;
	
	private JTextField companyNameField;
	private JComboBox<AbstractPerson> companyBossComboBox;
	
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
		
		//Update infos
		companyNameField.setText(mainControler.getCompany().toString());
		updatePeopleList(mainControler.getCompany().getEmployees());
		
		//Set up events
		companyNameField.addFocusListener(controler.new NameEvent());
		companyBossComboBox.addItemListener(controler.new BossEvent());
	}
	
	public String getName() {
		return companyNameField.getText();
	}
	
	public void updatePeopleList(ArrayList<Employee> employeesList) {
		companyBossComboBox.removeAll();
		
		companyBossComboBox.addItem(mainControler.getCompany().getBoss());
		for(Employee employee : employeesList) {
			companyBossComboBox.addItem(employee);
		}
	}
}
