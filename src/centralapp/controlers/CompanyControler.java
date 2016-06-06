package centralapp.controlers;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import centralapp.model.AbstractDpt;
import centralapp.model.AbstractPerson;
import centralapp.model.Boss;
import centralapp.model.Company;
import centralapp.model.Employee;
import centralapp.views.CompanyView;

/**
 * <b>CompanyControler : Manage Controler of the Company</b>
 * 
 * @author Julien
 */
public class CompanyControler {
	/**
	 * Reference to the CentralApp
	 */
	private CentralApp mainControler;

	/**
	 * Reference to the view
	 */
	private CompanyView view;

	/**
	 * Constructor of the class
	 * 
	 * @param mainControler
	 *            Reference to the CentralApp
	 */
	public CompanyControler(CentralApp mainControler) {
		this.mainControler = mainControler;
		view = new CompanyView(mainControler, this);
	}

	/**
	 * Return the reference to the view
	 * 
	 * @return Reference to the view
	 */
	public CompanyView getView() {
		return view;
	}

	/**
	 * Calls the mthod to update People List in the view
	 * 
	 * @param list
	 *            List of Employees
	 * @param boss
	 *            Reference to the boss
	 */
	public void updatePeopleList(ArrayList<Employee> list, Boss boss) {
		view.updatePeopleList(list, boss);
	}

	/**
	 * Class to Manage modification of company's name
	 */
	public class NameEvent extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			String newName = view.getName();
			Company company = mainControler.getCompany();

			if (company.toString() != newName) {
				// There is a modif, so update
				company.setName(newName);
			}
		}
	}

	/**
	 * Class to manage boss modification
	 */
	public class BossEvent implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				AbstractPerson luckyGuy = (AbstractPerson) event.getItem();
				mainControler.getCompany().setBoss(luckyGuy);
			}
		}
	}
}
