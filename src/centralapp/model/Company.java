package centralapp.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <b>Company : Manage company Objects</b>
 * <p>
 * Attributes:
 * <ul>
 * <li>name : Company name</li>
 * <li>employeeList : Contains all the employees</li>
 * <li>dptList : Contains all the departments</li>
 * <li>managementDpt : Contains the Management Departments</li>
 * <li>boss : Contains Boss</li>
 * </ul>
 * 
 * <p>
 * Add an Employee or a Department, set Company name, find Employee, remove
 * Employee or Department, test if Company contains an Employee or a Department
 * </p>
 * 
 * @see Department
 * @see Employee
 * @see ManagementDpt
 * @see Boss
 * 
 * @author Julien
 */

public class Company implements Serializable {

	// Attribute
	/**
	 * Name of the Company
	 */
	private String name;

	/**
	 * List of employees
	 */
	private ArrayList<Employee> employeeList;

	/**
	 * List of departments
	 */
	private ArrayList<Department> dptList;

	/**
	 * Reference to management department
	 */
	private ManagementDpt managementDpt;

	/**
	 * Reference to the boss
	 */
	private Boss boss;

	/**
	 * Entity which gets all checkInOut referring to unknown employee
	 */
	private Employee mrX;

	// Constructor
	/**
	 * Constructor of a company
	 * 
	 * @param nameParam
	 *            Name of the Company
	 * @param bossParam
	 *            Reference to the boss of the Company
	 * 
	 * @see ManagementDpt
	 */
	public Company(String nameParam, Boss bossParam) {
		managementDpt = new ManagementDpt(bossParam);
		boss = bossParam;
		employeeList = new ArrayList<Employee>(0);
		dptList = new ArrayList<Department>(0);
		name = nameParam;

		// The goal is to handle unknown employee's id
		mrX = new Employee("X", "Mr");
	}

	// Method
	/**
	 * Get the reference to the boss of the company
	 * 
	 * @return Reference to the boss of the Company
	 */
	public Boss getBoss() {
		return boss;
	}

	public void setBoss(AbstractPerson luckyGuy) {
		//If the lucky guy is already the boss, no need to promote him
		if(luckyGuy != boss) {
			//Here, we create new instances to prevent lost attributes (mem leaks)
			
			//The current boss become an unassigned employee
			add(new Employee(boss));
			
			//Set the new boss
			boss = new Boss(luckyGuy);
		}
	}

	/**
	 * Get the reference to MrX
	 * 
	 * @return Reference to MrX
	 */
	public Employee getMrX() {
		return mrX;
	}

	/**
	 * Get the name of the Company
	 * 
	 * @return name of the company
	 */
	public String toString() {
		return name;
	}

	/**
	 * Set the name of the Company
	 * 
	 * @param name
	 *            new name of the company
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the reference of the ManagementDpt
	 * 
	 * @return managementDpt
	 */
	public ManagementDpt getManagementDpt() {
		return managementDpt;
	}

	/**
	 * Get a list of all the departments
	 * 
	 * @return dptList
	 */
	public ArrayList<Department> getDepartments() {
		return dptList;
	}

	/**
	 * Add an Employee to the company, won't be assigned to any Department
	 * 
	 * @param empParam
	 *            Employee you want to add to the Company
	 * 
	 * @see Employee#assign(Company)
	 */
	public void add(Employee empParam) {
		if (!employeeList.contains(empParam))
			employeeList.add(empParam);

		if (!(empParam.getCompany() == this))
			empParam.assign(this);

	}

	/**
	 * Add a Department to the Company
	 * 
	 * @param dptParam
	 *            Department you want to add to the Company
	 * 
	 * @see Department#assign(Company)
	 */
	public void add(Department dptParam) {
		if (!dptList.contains(dptParam))
			dptList.add(dptParam);
		if (!(dptParam.getCompany() == this))
			dptParam.assign(this);
	}

	/**
	 * Get all the employees (managers included, boss excluded)
	 * 
	 * @return reference an ArrayList to all the employees
	 */
	public ArrayList<Employee> getEmployees() {
		return employeeList;
	}

	/**
	 * Get the reference to an Employee from his id
	 * 
	 * @param id
	 *            the id of the Employee you are looking for
	 * 
	 * @return reference to the employee with the id in parameter, null if not
	 *         found
	 */
	public Employee findEmployee(int id) {
		Iterator<Employee> i = employeeList.iterator();
		Employee temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getId() == id)
				return temp;
		}
		return null;
	}

	/**
	 * Remove an Employee from the company and the Department he's in
	 * 
	 * @param empParam
	 *            reference to the Employee you want to delete
	 * 
	 * @see Employee#removeEmployee()
	 */
	public void removeEmployee(Employee empParam) {
		if (employeeList.contains(empParam))
			employeeList.remove(empParam);
		empParam.removeEmployee();
	}

	/**
	 * Remove a Department from the Company and all its employees
	 * 
	 * @param dptParam
	 *            reference to the Department you want to delete
	 * @see Department#removeDpt()
	 */
	public void removeDepartment(Department dptParam) {
		if (dptList.contains(dptParam))
			dptList.remove(dptParam);
		dptParam.removeDpt();
	}

	/**
	 * Test the presence of an Employee
	 * 
	 * @param employee
	 *            reference to the employee you're looking for
	 * 
	 * @return true if contained, false otherwise
	 */
	public boolean contains(Employee employee) {
		return employeeList.contains(employee);
	}

	/**
	 * Test the presence of a Department
	 * 
	 * @param department
	 *            reference to the department you're looking for
	 * 
	 * @return true if contained, false otherwise
	 */
	public boolean containsDpt(Department department) {
		return dptList.contains(department);
	}

	/**
	 * Print name, boss, departments, unassigned employees and unassigned
	 * managers
	 * 
	 * @return String giving details about company
	 */
	public String print() {
		String res;
		// Add Company name to result String
		res = "Entreprise : " + name + System.lineSeparator()
				+ System.lineSeparator();

		// Add boss to result String
		res += boss.getPrinting() + System.lineSeparator()
				+ System.lineSeparator();

		// Add managementDpt to result String
		res += managementDpt.getPrinting() + System.lineSeparator();

		// Add Departments to result String
		res = res + System.lineSeparator();
		Iterator<Department> j = dptList.iterator();
		Department temp2;
		while (j.hasNext()) {
			temp2 = j.next();
			res = res + temp2.getPrinting() + System.lineSeparator();
		}

		// Add List of unassigned Employees to result String
		res = res + System.lineSeparator() + "Unassigned Employee :"
				+ System.lineSeparator();
		Iterator<Employee> i = employeeList.iterator();
		Employee temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getDpt() == null && temp.getClass() != Manager.class)
				res = res + temp.getPrinting() + System.lineSeparator();
		}

		// Add List of unassigned managers to result String
		res = res + System.lineSeparator() + "Unassigned Manager :"
				+ System.lineSeparator();
		Iterator<Employee> k = employeeList.iterator();
		Employee temp3;
		while (k.hasNext()) {
			temp3 = k.next();
			if (temp3.getDpt() == null && temp3.getClass() == Manager.class)
				res = res + temp3.getPrinting() + System.lineSeparator();
		}

		return res;
	}

	public void serialize(String nomFichier)
	throws FileNotFoundException, IOException {
		FileOutputStream fichierOut = new FileOutputStream(nomFichier);
		ObjectOutputStream oos = new ObjectOutputStream(fichierOut);

		oos.writeObject(this);
		oos.flush();
		if (oos != null)
			oos.close();
	}

	public static Company unserialize(String nomFichier)
	throws ClassNotFoundException, FileNotFoundException, IOException {
		ObjectInputStream ois = null;
		Company res = null;

		FileInputStream fichierIn;
		fichierIn = new FileInputStream(nomFichier);
		ois = new ObjectInputStream(fichierIn);
		res = (Company) ois.readObject();
		if(ois != null)
			ois.close();
		
		return res;
	}
}
