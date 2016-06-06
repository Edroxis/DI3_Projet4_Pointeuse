package centralapp.views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.CentralApp;
import centralapp.controlers.PeopleControler;
import centralapp.model.AbstractPerson;
import centralapp.model.Company;
import centralapp.model.Department;
import centralapp.model.Employee;
import centralapp.model.Manager;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PeopleView extends JPanel {
	private CentralApp mainControler;
	private PeopleControler controler;
	
	private JTextField personFirstNameField;
	private JTextField personLastNameField;
	private JComboBox<Department> personDepartmentComboBox;
	private Department unassignedDpt;
	
	private JTree peopleTree;
	
	public PeopleView(CentralApp mainControler, PeopleControler peopleControler) {
		this.mainControler = mainControler;
		controler = peopleControler;
		setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel peopleTreeIndication = new JLabel("Double-click to open check in/out:");
		leftPanel.add(peopleTreeIndication, BorderLayout.NORTH);
		
		MyDefaultMutableTreeNode racine = new MyDefaultMutableTreeNode("The Root",-1);
		DefaultTreeModel arbreModele = new DefaultTreeModel(racine);
		peopleTree = new JTree(arbreModele);
		//updateTree();
		peopleTree.setRootVisible(false);
		leftPanel.add(peopleTree, BorderLayout.CENTER);
		
		//Create the form: firstname, lastname, 
		JPanel personFormPanel = new JPanel();
		add(personFormPanel);
		personFormPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel personFirstNameLabel = new JLabel("First name");
		personFormPanel.add(personFirstNameLabel, "2, 2, right, default");
		
		personFirstNameField = new JTextField();
		personFormPanel.add(personFirstNameField, "4, 2, fill, default");
		personFirstNameField.setColumns(10);
		
		JLabel personLastNameLabel = new JLabel("Last name");
		personFormPanel.add(personLastNameLabel, "2, 4, right, default");
		
		personLastNameField = new JTextField();
		personFormPanel.add(personLastNameField, "4, 4, fill, default");
		personLastNameField.setColumns(10);
		
		JLabel personDepartmentLabel = new JLabel("Department");
		personFormPanel.add(personDepartmentLabel, "2, 6");
		
		unassignedDpt = new Department("unassigned");
		personDepartmentComboBox = new JComboBox<Department>();
		personFormPanel.add(personDepartmentComboBox, "4, 6");
		
		JPanel personButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		personFormPanel.add(personButtonsPanel, "4, 8, fill, fill");
	
		JButton personAddButton = new JButton("Add");
		personButtonsPanel.add(personAddButton);
		
		JButton personApplyButton = new JButton("Apply");
		personButtonsPanel.add(personApplyButton);
		
		JButton personRemoveButton = new JButton("Remove");
		personButtonsPanel.add(personRemoveButton);
		
		//Set up events
		peopleTree.addTreeSelectionListener(controler.new TreeSelectEvent());
		personDepartmentComboBox.addItemListener(controler.new SelectDepartmentEvent());
		personAddButton.addMouseListener(controler.new AddEvent());
		personApplyButton.addMouseListener(controler.new ApplyEvent());
		personRemoveButton.addMouseListener(controler.new RemoveEvent());
	}
	
	public String getFirstName() {
		return personFirstNameField.getText();
	}
	
	public String getLastName() {
		return personLastNameField.getText();
	}
	
	public Department getDepartment() {
		Department returnedDpt = (Department)personDepartmentComboBox.getSelectedItem();
		
		if(returnedDpt == unassignedDpt)
			returnedDpt = null;
			
		return returnedDpt;
	}
	
	public void updateDepartmentsList(ArrayList<Department> dptsList) {
		personDepartmentComboBox.removeAll();
		for(Department dpt : dptsList) {
			personDepartmentComboBox.addItem(dpt);
		}
		personDepartmentComboBox.addItem(unassignedDpt);
		updateTree();
	}
	
	public void updatePeopleList(ArrayList<Employee> peopleList) {
		updateTree();
	}
	
	public void updateTree(){
		Company cmp = mainControler.getCompany();
		int i = 1;
		DefaultMutableTreeNode racine = new MyDefaultMutableTreeNode("The Root",-1);
		
		for(Department dpt : cmp.getDepartments()){
			MyDefaultMutableTreeNode x = new MyDefaultMutableTreeNode(dpt.toString(),-i);
			Manager man = dpt.getManager();
			if(man != null)
				x.add( new MyDefaultMutableTreeNode("Manager: "+man.getfName()+" "+man.getlName(), man.getId()));
		    for(Employee emp : dpt.getEmployees().values()){
		    	x.add( new MyDefaultMutableTreeNode(emp.getfName()+" "+emp.getlName(), emp.getId()));
		    }
		    racine.add(x);
		    i++;
		}
		
		DefaultTreeModel newModel = new DefaultTreeModel(racine);
		
		peopleTree.setModel(newModel);
	}
	
	public void selectDepartmentBoxId(int i){
		personDepartmentComboBox.setSelectedIndex(i-1);
	}
	
	public class MyDefaultMutableTreeNode extends DefaultMutableTreeNode{
		private int id;
		MyDefaultMutableTreeNode(String nodeName, int id){
			super(nodeName);
			this.id = id;
		}
		public int getId(){
			return id;
		}
	}
}
