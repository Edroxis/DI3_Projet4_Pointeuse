package centralapp.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Department : Manage Department Objects</b>
 * <p>
 * Attributes:
 * <ul>
 * <li>Company : Reference to the Company</li>
 * <li>employeeMap : Contains all the employees, indexed by their id</li>
 * <li>nbEmployee : Number of employee in this Department</li>
 * <li>manager : Contains reference to the manager</li>
 * </ul>
 * 
 * <p>
 * Add, delete, get Employee, get Company, add, modify, delete, get Manager,
 * delete Department.
 * </p>
 * 
 * @see Department
 * @see Employee
 * @see ManagementDpt
 * @see Boss
 * @see Company
 * 
 * @author Julien
 */
@SuppressWarnings("serial")
public class Department extends AbstractDpt {

	// Attribute
	/**
	 * Reference to the Company
	 */
	private Company company;

	/**
	 * HashMap containing Employees indexed by id
	 */
	private ConcurrentHashMap<Integer, Employee> employeeMap;

	/**
	 * Contains number of Employee in this Department
	 */
	private int nbEmployee;

	/**
	 * Reference to the manager
	 */
	private Manager manager;

	// Constructor
	/**
	 * Constructor of Department with only the name
	 * 
	 * @param nameParam
	 *            the name of the Department
	 */
	public Department(String nameParam) {
		super(nameParam);
		employeeMap = new ConcurrentHashMap<Integer, Employee>(0);
		manager = null;
		nbEmployee = 0;
	}

	/**
	 * Constructor of Department with the name and the company.
	 * 
	 * @param nameParam
	 *            The name of the Department
	 * @param company
	 *            The reference to the company
	 */
	public Department(String nameParam, Company company) {
		this(nameParam);
		assign(company);
	}

	// Method
	/**
	 * Test if Department contains a specified employee
	 * 
	 * @param empParam
	 *            The reference to the employee
	 * 
	 * @return True if contained, false otherwise
	 */
	public boolean contains(Employee empParam) {
		return employeeMap.containsValue(empParam);
	}

	/**
	 * Assign an Employee to the Department
	 * 
	 * @param eParam
	 *            The reference to the Employee you want to add to the
	 *            Department
	 * 
	 * @see Employee#assign(Department)
	 */
	public void assign(Employee eParam) {
		if (eParam.getDpt() != this)
			eParam.assign(this);

		if (!employeeMap.containsKey(eParam.getId())) {
			employeeMap.put(eParam.getId(), eParam);
			nbEmployee++;
		}
	}

	/**
	 * Get the reference to the manager
	 * 
	 * @return Reference to the manager
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Define the Department's manager, calls appropriate functions to unassign
	 * the old one
	 * 
	 * @param manParam
	 *            The reference the new Manager.
	 * 
	 * @see Manager#setDpt(Department)
	 */
	public void setManager(Manager manParam) {
		if (manager != null && manager != manParam)
			manager.setDpt(null);

		if (manParam != null)
			if (manParam.getDpt() != null && manParam.getDpt() != this)
				manParam.getDpt().setManager(null);

		if (manager != manParam)
			manager = manParam;

		if (manParam != null)
			if (manParam.getDpt() != this)
				manParam.setDpt(this);
	}

	/**
	 * Get the company our Department is in
	 * 
	 * @return Reference to the Company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Assign the department to the specified Company
	 * 
	 * @param companyParam
	 *            Reference to the Company where will be current Department.
	 * 
	 * @see Company#add(Department)
	 */
	public void assign(Company companyParam) {
		company = companyParam;
		companyParam.add(this);
		for (Employee e : employeeMap.values())
			e.assign(companyParam);
	}

	/**
	 * Remove an Employee from the current Department, will still be assign to
	 * the company
	 * 
	 * @param empParam Reference to the Employee you want to add
	 * 
	 * @see Employee#removeEmployee()
	 */
	public void removeEmployee(Employee empParam) {
		if (employeeMap.containsValue(empParam)) {
			employeeMap.remove(empParam.getId());
			nbEmployee--;
		}
	}

	/**
	 * Remove the manager from the current Department, will still be assign to the Company
	 */
	public void removeManager() {
		if (manager != null)
		{	
			manager.setDpt(null);
			manager = null;
		}
		
	}

	/**
	 * Remove the Department from the whole database
	 * 
	 * @see Company#removeDepartment(Department)
	 */
	public void removeDpt() {
		Department nullDpt = null;
		for (Employee e : employeeMap.values())
			e.assign(nullDpt);

		employeeMap.clear();

		if (company.containsDpt(this))
			company.removeDepartment(this);

		if (manager.getDpt() == this)
			manager.setDpt(null);
	}

	/**
	 * Get informations such as name of Department, manager and list of Employees
	 * 
	 * @return String you will have to print.FS
	 */
	public String getPrinting() {
		String res = "Department : " + toString() + System.lineSeparator();
		if (manager != null)
			res = res + "\t" + manager.toString() + System.lineSeparator();
		else
			res = res + "\t" + "Manager : " + System.lineSeparator();
		for (Employee e : employeeMap.values())
			res += "\t" + e.toString() + System.lineSeparator();
		return res;
	}

	/**
	 * Get EployeeMap
	 * 
	 * @return ConcurrentHashMap of Employees, indexed by their ID
	 */
	public ConcurrentHashMap<Integer, Employee> getEmployees() {
		return employeeMap;
	}
}
