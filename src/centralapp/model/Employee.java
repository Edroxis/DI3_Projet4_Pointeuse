package centralapp.model;

import java.util.ArrayList;

/**
 * <b>Employee : Manage Employee Objects</b>
 * <p>
 * Attributes:
 * <ul>
 * <li>Company : Reference to the Company</li>
 * <li>id : Unique id of the employee</li>
 * <li>nbEmployee : Contains all the departments</li>
 * <li>dpt : Reference to the Management Departments</li>
 * <li>checkInOut : ArrayList of all checks</li>
 * </ul>
 * 
 * <p>
 * Get, add, modify Department, get, add, modify, delete CheckInOut, add and get
 * Company, delete Employee
 * </p>
 * 
 * @see Department
 * @see Company
 * @see CheckInOut
 * @see AbstractPerson
 * 
 */
@SuppressWarnings("serial")
public class Employee extends AbstractPerson {

	// Attribute
	/**
	 * Reference to the Company
	 */
	private Company company;

	/**
	 * Unique id of current Employee
	 */
	private int id;

	/**
	 * Total number of created Employees
	 */
	static private int nbEmployee = 0;

	/**
	 * Reference to the Department
	 */
	protected Department dpt;

	/**
	 * List of all CheckInOut
	 */
	private ArrayList<CheckInOut> checkInOut;

	// Constructor
	/**
	 * Constructor of Employee
	 * 
	 * @param lnParam
	 *            Last name of Employee
	 * @param fnParam
	 *            First name of Employee
	 * @param companyParam
	 *            Reference to the Company Employee will be assigned in
	 */
	public Employee(String lnParam, String fnParam, Company companyParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		company = companyParam;
		companyParam.add(this);
	}

	/**
	 * Constructor of Employee
	 * 
	 * @param lnParam
	 *            Last name of Employee
	 * @param fnParam
	 *            First name of Employee
	 * @param dptParam
	 *            Department where Employee will be assigned
	 */
	public Employee(String lnParam, String fnParam, Department dptParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		assign(dptParam);
		company = dptParam.getCompany();
		if (dptParam.getCompany() != null)
			dptParam.getCompany().add(this);
	}

	/**
	 * Constructor of Employee
	 * 
	 * @param lnParam
	 *            Last name of Employee
	 * @param fnParam
	 *            First name of Employee
	 */
	public Employee(String lnParam, String fnParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		company = null;
		dpt = null;
	}
	
	/**
	 * Copy constructor of Employee
	 * 
	 * @param man the employee to copy
	 */
	public Employee(Employee man) {
		this(man.getlName(), man.getfName(), man.getDpt());
	}
	
	/**
	 * Constructor of Employee
	 * 
	 * @param man person
	 */
	public Employee(AbstractPerson man) {
		this(man.getlName(), man.getfName());
	}

	/**
	 * Get id of the current Employee
	 * 
	 * @return id of the Employee
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the reference to the Department current Employee is assigned in
	 * 
	 * @return Reference to the Department of Employee
	 */
	public Department getDpt() {
		return dpt;
	}

	/**
	 * Assign an Employee to a Department, calls appropriate functions to keep
	 * structure coherent
	 * 
	 * @param dptParam
	 *            New Department Employee will be assign to
	 * 
	 * @see Department#assign(Employee)
	 */
	public void assign(Department dptParam) {
		if (dpt != null)
			dpt.removeEmployee(this);
		
		dpt = dptParam;
		
		if(dptParam != null) {
			if(!dptParam.contains(this))
				dptParam.assign(this);

			if(company != dpt.getCompany()) {
				if(company != null)
					company.removeEmployee(this);
				
				company = dpt.getCompany();
				dpt.getCompany().add(this);
			}
		}
	}

	/**
	 * Get the reference to the Company Employee is assigned to
	 * 
	 * @return Reference to the Company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Get the ArrayList of all Check
	 * 
	 * @return ArrayList of all Checks
	 */
	public ArrayList<CheckInOut> getCheckInOut() {
		return checkInOut;
	}

	/**
	 * Add a Check to Employee's list, add it properly, in order to keep increasing orders
	 * 
	 * @param param
	 *            CheckInOut you want to add
	 */
	public void addCheckInOut(CheckInOut param) {
		int i;
		boolean contained = false;
		
		for(i = 0; i<checkInOut.size(); i++){	//Find where to insert check to keep increasing order
			if(param.getDate().compareTo(checkInOut.get(i).getDate())<0)
				break;
		}
		
		for(CheckInOut ch : checkInOut){	//Verify if Check is not already in database
			if(ch.getDate().compareTo(param.getDate())==0)
				contained = true;
		}
		if(!contained)
			checkInOut.add(i, param);
	}

	/**
	 * Modify a specified CheckInOut to another one, if old isn't found, do
	 * nothing
	 * 
	 * @param oldCIO
	 *            The former CheckInOut you want to replace
	 * @param newCIO
	 *            The new one
	 */
	public void modifyCheckInOut(CheckInOut oldCIO, CheckInOut newCIO) {
		int index = checkInOut.indexOf(oldCIO);
		if (index != -1)
			checkInOut.set(index, newCIO);
	}

	/**
	 * Delete a specified CheckInOut
	 * 
	 * @param toDelete
	 *            CheckInOut you want to delete
	 */
	public void deleteCheckInOut(CheckInOut toDelete) {
		int index = checkInOut.indexOf(toDelete);
		if (index != -1)
			checkInOut.remove(index);
	}

	/**
	 * Totally erase an Employee from the database
	 * 
	 * @see Department#removeEmployee(Employee)
	 * @see Company#removeEmployee(Employee)
	 */
	public void removeEmployee() {
		if (company.contains(this))
			company.removeEmployee(this);

		if (dpt != null)
			if (dpt.contains(this))
				dpt.removeEmployee(this);
	}

	/**
	 * Get String with First and last name of an Employee
	 * 
	 * @return String containing those informations
	 */
	public String getPrinting() {
		return "Employee : " + getfName() + " " + getlName();// + " " + id;
	}

	/**
	 * Assign Employee to the specified company
	 * 
	 * @param company
	 *            Reference to Company you want to assign Employee to
	 * 
	 * @see Company#add(Employee)
	 */
	public void assign(Company company) {
		this.company = company;
	}

	/**
	 * Get nb of checks for this Employee
	 * 
	 * @return Number of checks
	 */
	public int getTotalCIO() {
		return checkInOut.size();
	}
}
