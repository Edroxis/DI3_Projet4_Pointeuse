package centralapp.views;

import java.awt.FlowLayout;

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

import centralapp.controlers.DepartmentControler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class DepartmentView extends JPanel {
	private DepartmentControler controler;
	private JTextField departmentNameField;
	
	public DepartmentView(DepartmentControler dptControler) {
		controler = dptControler;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JComboBox<String> departmentsComboBox = new JComboBox<String>();
		departmentsComboBox.addItemListener(controler.new SelectEvent());
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
		
		//TODO: Fill the combobox
		JComboBox<String> departmentManagerComboBox = new JComboBox<String>();
		departmentManagerComboBox.addItemListener(controler.new SelectManagerEvent());
		departmentFormPanel.add(departmentManagerComboBox, "4, 4");
		
		JPanel departmentButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		departmentFormPanel.add(departmentButtonsPanel, "4, 6, fill, fill");
		
		JButton departmentAddButton = new JButton("Add");
		departmentAddButton.addMouseListener(controler.new AddEvent());
		departmentButtonsPanel.add(departmentAddButton);
		
		JButton departmentApplyButton = new JButton("Apply");
		departmentApplyButton.addMouseListener(controler.new ApplyEvent());
		departmentButtonsPanel.add(departmentApplyButton);
		
		JButton departmentRemoveButton = new JButton("Remove");
		departmentRemoveButton.addMouseListener(controler.new RemoveEvent());
		departmentButtonsPanel.add(departmentRemoveButton);
	}
}
