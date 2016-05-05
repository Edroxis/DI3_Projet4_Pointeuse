package centralapp.views;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.PeopleControler;

@SuppressWarnings("serial")
public class PeopleView extends JPanel {
	private PeopleControler controler;
	private JTextField personFirstNameField;
	private JTextField personLastNameField;
	
	public PeopleView(PeopleControler peopleControler) {
		controler = peopleControler;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel peopleTreeIndication = new JLabel("Double-click to open check in/out:");
		add(peopleTreeIndication);
		
		JTree peopleTree = new JTree();
		peopleTree.setRootVisible(false);
		add(peopleTree);
		
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
		
		JButton personDepartmentButton = new JButton("Click to choose the department");
		personFormPanel.add(personDepartmentButton, "4, 6");
		
		JLabel personIsManagerLabel = new JLabel("Is a manager?");
		personFormPanel.add(personIsManagerLabel, "2, 8");
		
		JToggleButton personIsManagerButton = new JToggleButton("Press to promote as manager");
		personFormPanel.add(personIsManagerButton, "4, 8");
		
		JPanel personButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		personFormPanel.add(personButtonsPanel, "4, 10, fill, fill");
	
		JButton personAddButton = new JButton("Add");
		personButtonsPanel.add(personAddButton);
		
		JButton personApplyButton = new JButton("Apply");
		personButtonsPanel.add(personApplyButton);
		
		JButton personRemoveButton = new JButton("Remove");
		personButtonsPanel.add(personRemoveButton);
	}
}
