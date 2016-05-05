package centralapp.views;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.CompanyControler;

@SuppressWarnings("serial")
public class CompanyView extends JPanel {
	private CompanyControler controler;
	private JTextField companyNameField;
	
	public CompanyView(CompanyControler companyControler) {
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
		
		companyNameField = new JTextField(controler.getCompanyName());
		companyNameField.addFocusListener(controler.new NameEvent());
		add(companyNameField, "4, 2, fill, default");
		companyNameField.setColumns(10);
		
		JLabel companyBossLabel = new JLabel("Boss");
		add(companyBossLabel, "2, 4");
		
		//TODO: Fill the combobox
		JComboBox<String> companyBossComboBox = new JComboBox<String>();
		companyBossComboBox.addItemListener(controler.new BossEvent());
		add(companyBossComboBox, "4, 4");
	}
	
	public String getName() {
		return companyNameField.getText();
	}
}
