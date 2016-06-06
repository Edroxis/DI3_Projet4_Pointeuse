package centralapp.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;

import centralapp.controlers.CentralApp;
import centralapp.controlers.GeneralCIOControler;
import centralapp.model.*;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GeneralCIOView extends JPanel {

	private CentralApp mainControler;
	private GeneralCIOControler controler;
	private JTable table;
	private DefaultTableModel model;
	private JTextField dateMinTextField;
	private JTextField dateMaxTextField;
	
	private ZonedDateTime filterMin;
	private ZonedDateTime filterMax;

	/**
	 * Create the panel.
	 */
	public GeneralCIOView(CentralApp mainControler, GeneralCIOControler localControler) {
		this.mainControler = mainControler;
		controler = localControler;

		// updateTable();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);
        
        JPanel panel = new JPanel();
        add(panel);
        
        JLabel lblNewLabel = new JLabel("Print checks ");
        panel.add(lblNewLabel);
        
        JLabel lblDatemin = new JLabel("after :");
        panel.add(lblDatemin);
        
        dateMinTextField = new JTextField();
        panel.add(dateMinTextField);
        dateMinTextField.setColumns(17);
        
        JLabel lblDatemax = new JLabel("and before :");
        panel.add(lblDatemax);
        
        dateMaxTextField = new JTextField();
        panel.add(dateMaxTextField);
        dateMaxTextField.setColumns(17);
        
        JButton btnFilter = new JButton("Filter");
        panel.add(btnFilter);
        btnFilter.addMouseListener(controler.new FilterEvent());
        
        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);
        btnReset.addMouseListener(controler.new ResetEvent());
	}
	
	public void setMinTextField(String str){
		dateMinTextField.setText(str);
	}
	
	public String getMinTextField(){
		return dateMinTextField.getText();
	}
	
	public void setMaxTextField(String str){
		dateMaxTextField.setText(str);
	}
	
	public String getMaxTextField(){
		return dateMaxTextField.getText();
	}
	
	public void setFilterMin(ZonedDateTime date){
		filterMin = date;
	}
	
	public void setFilterMax(ZonedDateTime date){
		filterMax = date;
	}

	public void updateTable() {// update the model of the JTable
		int i = 0;
		String[][] data = new String[mainControler.getCompany().getTotalCIO()][5];
		DateTimeFormatter dateFormatter = controler.getDateFormatter();

		for (Employee emp : mainControler.getCompany().getEmployees()) {
			for (CheckInOut cio : emp.getCheckInOut()) {
				if((filterMin != null && cio.getDate().compareTo(filterMin)>=0) || filterMin == null){
					if((filterMax != null && cio.getDate().compareTo(filterMax)<=0) || filterMax == null){
						data[i][0] = Integer.toString(emp.getId());
						data[i][1] = emp.getfName();
						data[i][2] = emp.getlName();
						data[i][3] = emp.getDpt().toString();
						data[i][4] = cio.getDate().format(dateFormatter);
						i++;
					}
				}
			}
		}
		
		model = new DefaultTableModel(data,
				new String[] { "Id", "First Name", "Last Name", "Department", "Hour of check" }
		);

		table.setModel(model);
	}

}
