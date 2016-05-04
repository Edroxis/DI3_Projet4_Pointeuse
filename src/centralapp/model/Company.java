package centralapp.model;

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
public class Company {

	// Attribut
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
	private Employee mrX;

	// Constructeur
	/**
	* Constructor of a company
	* 
	* @param nameParam Name of the Company
	* @param bossParam Reference to the boss of the Company
	* 
	* @see ManagementDpt
	*/
	public Company(String nameParam, Boss bossParam) {
		managementDpt = new ManagementDpt(bossParam);
		boss = bossParam;
		employeeList = new ArrayList<Employee>(0);
		dptList = new ArrayList<Department>(0);
		name = nameParam;
		
		//The goal is to handle unknown employee's id
		mrX = new Employee("X", "Mr");
	}

	// Methode
	/**
	* Get the reference to the boss of the company
	* 
	* @return Reference to the boss of the Company
	* 
	* @see Boss
	*/
	public Boss getBoss() {
		return boss;
	}
	
	public Employee getMrX() {
		return mrX;
	}

	/**
	* Constructor of a company
	* 
	* @param empParam Employee you want to add to the Company
	* 
	* @see ArrayList#add(Object)
	* @see ArrayList#contains(Object)
	* @see Employee#getCompany()
	* @see Employee#assign(Company)
	*/
	public void add(Employee empParam) {
		if (!employeeList.contains(empParam))
			employeeList.add(empParam);

		if (!(empParam.getCompany() == this))
			empParam.assign(this);

	}

	public void add(Department dptParam) {
		if (!dptList.contains(dptParam))
			dptList.add(dptParam);
		if (!(dptParam.getCompany() == this))
			dptParam.assign(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManagementDpt getManagementDpt() {
		return managementDpt;
	}

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

	public void removeEmployee(Employee empParam) {
		if (employeeList.contains(empParam))
			employeeList.remove(empParam);
		empParam.removeEmployee();
	}

	public void removeDepartment(Department dptParam) {
		if (dptList.contains(dptParam))
			dptList.remove(dptParam);
		dptParam.removeDpt();
	}

	public boolean contains(Employee employee) {
		return employeeList.contains(employee);
	}

	public boolean containsDpt(Department department) {
		return dptList.contains(department);
	}

	public String toString() {
		String res;
		// Afficher nom entrprise
		res = "Entreprise : " + name + System.lineSeparator()
				+ System.lineSeparator();

		// Afficher boss
		res += boss.toString() + System.lineSeparator()
				+ System.lineSeparator();

		// Afficher ManagementDpt et son contenu
		res += managementDpt.toString() + System.lineSeparator();

		// Afficher les d�partements et leurs contenus
		res = res + System.lineSeparator();
		Iterator<Department> j = dptList.iterator();
		Department temp2;
		while (j.hasNext()) {
			temp2 = j.next();
			res = res + temp2.toString() + System.lineSeparator();
		}

		// Afficher employ�s non assign�s
		res = res + System.lineSeparator() + "Unassigned Employee :"
				+ System.lineSeparator();
		Iterator<Employee> i = employeeList.iterator();
		Employee temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getDpt() == null && temp.getClass() != Manager.class)
				res = res + temp.toString() + System.lineSeparator();
		}

		// Afficher manager non assign�s
		res = res + System.lineSeparator() + "Unassigned Manager :"
				+ System.lineSeparator();
		Iterator<Employee> k = employeeList.iterator();
		Employee temp3;
		while (k.hasNext()) {
			temp3 = k.next();
			if (temp3.getDpt() == null && temp3.getClass() == Manager.class)
				res = res + temp3.toString() + System.lineSeparator();
		}

		return res;
	}
}
