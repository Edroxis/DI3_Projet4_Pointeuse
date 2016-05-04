package centralapp.views;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JPanel;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTree;
import net.miginfocom.swing.MigLayout;
import java.awt.Insets;
import java.awt.CardLayout;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JTextField companyNameField;
	private JTextField departmentNameField;
	public MainWindow() {
		//GTK theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {	}
		
		//Ensure to exit the program and not only the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel companyPanel = new JPanel();
		tabbedPane.addTab("Company", null, companyPanel, null);
		companyPanel.setLayout(new BoxLayout(companyPanel, BoxLayout.Y_AXIS));
		
		JPanel companyFormPanel = new JPanel();
		companyPanel.add(companyFormPanel);
		companyFormPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel companyNameLabel = new JLabel("Name");
		companyFormPanel.add(companyNameLabel, "2, 2, right, default");
		
		companyNameField = new JTextField();
		companyFormPanel.add(companyNameField, "4, 2, fill, default");
		companyNameField.setColumns(10);
		
		JLabel companyBossLabel = new JLabel("Boss");
		companyFormPanel.add(companyBossLabel, "2, 4");
		
		JButton companyBossButton = new JButton("Click to choose boss");
		companyFormPanel.add(companyBossButton, "4, 4");
		
		JPanel departmentsPanel = new JPanel();
		tabbedPane.addTab("Departments", null, departmentsPanel, null);
		departmentsPanel.setLayout(new BoxLayout(departmentsPanel, BoxLayout.Y_AXIS));
		
		JList departmentsList = new JList();
		departmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		departmentsList.setModel(new AbstractListModel() {
			String[] values = new String[] {"dpt1", "dpt2", "dpt3", "dpt4", "dpt5", "dpt6", "dpt7", "dpt8", "dpt9", "dpt10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		departmentsPanel.add(departmentsList);
		
		JPanel departmentFormPanel = new JPanel();
		departmentsPanel.add(departmentFormPanel);
		departmentFormPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel departmentNameLabel = new JLabel("Name");
		departmentFormPanel.add(departmentNameLabel, "2, 2, right, default");
		
		departmentNameField = new JTextField();
		departmentFormPanel.add(departmentNameField, "4, 2, fill, default");
		departmentNameField.setColumns(10);
		
		JLabel departmentManagerLabel = new JLabel("Manager");
		departmentFormPanel.add(departmentManagerLabel, "2, 4");
		
		JButton departmentManagerButton = new JButton("Click to choose the manager");
		departmentFormPanel.add(departmentManagerButton, "4, 4");
		
		JPanel departmentButtonsForm = new JPanel();
		FlowLayout flowLayout = (FlowLayout) departmentButtonsForm.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		departmentFormPanel.add(departmentButtonsForm, "4, 8, fill, fill");
		
		JButton departmentAddButton = new JButton("Add");
		departmentButtonsForm.add(departmentAddButton);
		departmentAddButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton departmentRemoveButton = new JButton("Remove");
		departmentButtonsForm.add(departmentRemoveButton);
		departmentRemoveButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JPanel employeesPanel = new JPanel();
		tabbedPane.addTab("Employees", null, employeesPanel, null);
		GridBagLayout gbl_employeesPanel = new GridBagLayout();
		gbl_employeesPanel.columnWidths = new int[]{63, 592, 0};
		gbl_employeesPanel.rowHeights = new int[]{315, 0};
		gbl_employeesPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_employeesPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		employeesPanel.setLayout(gbl_employeesPanel);
		
		JTree employeesListTree = new JTree();
		employeesListTree.setEditable(true);
		GridBagConstraints gbc_employeesListTree = new GridBagConstraints();
		gbc_employeesListTree.fill = GridBagConstraints.BOTH;
		gbc_employeesListTree.insets = new Insets(0, 0, 0, 5);
		gbc_employeesListTree.gridx = 0;
		gbc_employeesListTree.gridy = 0;
		employeesPanel.add(employeesListTree, gbc_employeesListTree);
		
		JPanel employeeFormPanel = new JPanel();
		employeeFormPanel.setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));
		GridBagConstraints gbc_employeeFormPanel = new GridBagConstraints();
		gbc_employeeFormPanel.gridx = 1;
		gbc_employeeFormPanel.gridy = 0;
		employeesPanel.add(employeeFormPanel, gbc_employeeFormPanel);
	}
}