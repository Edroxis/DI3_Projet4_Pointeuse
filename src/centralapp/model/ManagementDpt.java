package centralapp.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <b>ManagementDpt : Manage the ManagementDpt Object</b>
 * <p>
 * Attributes:
 * <ul>
 * <li>boss : reference to the boss of the Company</li>
 * <li>managerList : ArrayList containing all Managers</li>
 * </ul>
 * 
 * <p>
 * Extends AbstractDpt.
 * </p>
 * 
 * @see AbstractDpt
 * 
 */
public class ManagementDpt extends AbstractDpt {

	// Attribute
	/**
	 * Reference to the Boss
	 */
	private Boss boss;
	
	/**
	 * ArrayList containing all the managers
	 */
	private ArrayList<Manager> managerList;

	// Constructor
	/**
	 * Constructor of a the ManagementDpt
	 * 
	 * @param bossParam reference to the boss, can't exist without one
	 */
	ManagementDpt(Boss bossParam) {
		super("Management Department");
		boss = bossParam;
		managerList = new ArrayList<Manager>(0);
	}

	// Method
	/**
	 * Add a Manager to the database
	 * 
	 * @param manParam Reference to a manager
	 */
	public void addManager(Manager manParam) {
		if (!contains(manParam))
			managerList.add(manParam);
	}

	/**
	 * Print informations about this Dpt such as list of managers and Boss
	 * 
	 * @return String containing those informations
	 */
	public String getPrinting() {
		String res = "Management Department : " + System.lineSeparator();

		res += "\t" + boss.toString() + System.lineSeparator();

		Iterator<Manager> i = managerList.iterator();
		Manager temp;
		while (i.hasNext()) {
			temp = i.next();
			res = res + "\t" + temp.toString() + System.lineSeparator();
		}
		return res;
	}

	/**
	 * Test if database contains specified manager
	 * 
	 * @param manager Reference to manager you want to test
	 * 
	 * @return True if contained, False otherwise
	 */
	public boolean contains(Manager manager) {
		return managerList.contains(manager);
	}

	/**
	 * Remove Manager from database
	 * 
	 * @param manager Reference to Manager you want to remove
	 */
	public void removeManager(Manager manager) {
		if (contains(manager))
			managerList.remove(manager);
		manager.removeManager();
	}
}
