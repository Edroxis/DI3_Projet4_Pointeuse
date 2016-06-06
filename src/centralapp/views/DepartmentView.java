package centralapp.views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.CentralApp;
import centralapp.controlers.DepartmentControler;
import centralapp.model.AbstractDpt;
import centralapp.model.AbstractPerson;
import centralapp.model.Boss;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.model.ManagementDpt;
import centralapp.model.Manager;

@SuppressWarnings("serial")
public class DepartmentView extends JPanel {
	private CentralApp mainControler;
	private DepartmentControler controler;
	
	private JComboBox<Department> departmentsComboBox;
	private JTextField departmentNameField;
	private JComboBox<Employee> departmentManagerComboBox;
	private Employee nullPerson;
	private JButton departmentRemoveButton;
	
	public DepartmentView(CentralApp mainControler, DepartmentControler dptControler) {
		this.mainControler = mainControler;
		controler = dptControler;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		departmentsComboBox = new JComboBox<Department>();
		add(departmentsComboBox);
		
		//Create the form: name, button choose manager, button add, apply, button remove
		JPanel departmentFormPanel = new JPanel();
		add(departmentFormPanel);
		FormLayout fl_departmentFormPanel = new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),});
		departmentFormPanel.setLayout(fl_departmentFormPanel);
		
		JLabel departmentNameLabel = new JLabel("Name");
		departmentFormPanel.add(departmentNameLabel, "2, 2, right, default");
		
		departmentNameField = new JTextField();
		departmentFormPanel.add(departmentNameField, "4, 2, fill, default");
		departmentNameField.setColumns(10);
		
		JLabel departmentManagerLabel = new JLabel("Manager");
		departmentFormPanel.add(departmentManagerLabel, "2, 4");
		
		nullPerson = new Employee("none", "");
		departmentManagerComboBox = new JComboBox<Employee>();
		departmentFormPanel.add(departmentManagerComboBox, "4, 4");
		
		JPanel departmentButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		departmentFormPanel.add(departmentButtonsPanel, "4, 6, fill, fill");
		
		JButton departmentAddButton = new JButton("Add");
		departmentButtonsPanel.add(departmentAddButton);
		
		JButton departmentApplyButton = new JButton("Apply");
		departmentButtonsPanel.add(departmentApplyButton);
		
		departmentRemoveButton = new JButton("Remove");
		departmentButtonsPanel.add(departmentRemoveButton);
		
		//Set up events
		departmentsComboBox.addItemListener(controler.new SelectEvent());
		departmentAddButton.addMouseListener(controler.new AddEvent());
		departmentApplyButton.addMouseListener(controler.new ApplyEvent());
		departmentRemoveButton.addMouseListener(controler.new RemoveEvent());
	}
	
	public String getName() {
		return departmentNameField.getText();
	}
	
	public Employee getManager() {
		Employee manager = (Employee)departmentManagerComboBox.getSelectedItem();
		if(manager == nullPerson)
			manager = null;
			
		return manager;
	}
	
	public Department getDpt() {
		return (Department)departmentsComboBox.getSelectedItem();
	}
	
	public void updateDepartmentsList(ArrayList<Department> dptsList) {
		Department selectedDpt = getDpt();
		
		departmentsComboBox.removeAllItems();
		for(Department dpt : dptsList)
			departmentsComboBox.addItem(dpt);
		
		departmentsComboBox.setSelectedItem(selectedDpt);
	}
	
	public void updatePeopleList(ArrayList<Employee> list) {
		Employee selectedManager = getManager();
		
		departmentManagerComboBox.removeAllItems();
		departmentManagerComboBox.addItem(nullPerson);
		for(Employee emp : list)
			departmentManagerComboBox.addItem(emp);
		
		departmentManagerComboBox.setSelectedItem(selectedManager);
	}
	
	public void updateFromChoosenDpt(Department dpt) {		
		departmentNameField.setText(dpt.toString());
		if(dpt.getManager() == null)
			departmentManagerComboBox.setSelectedItem(nullPerson);
		else
			departmentManagerComboBox.setSelectedItem(dpt.getManager());
	}
}
