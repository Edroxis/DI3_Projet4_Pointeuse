package centralapp.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import centralapp.controlers.CompanyControler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		companyNameField.addFocusListener(controler.new ChangeNameEvent());
		add(companyNameField, "4, 2, fill, default");
		companyNameField.setColumns(10);
		
		JLabel companyBossLabel = new JLabel("Boss");
		add(companyBossLabel, "2, 4");
		
		JButton companyBossButton = new JButton(controler.getBossName());
		companyBossButton.addMouseListener(controler.new ChangeBossEvent());
		add(companyBossButton, "4, 4");
	}
}
