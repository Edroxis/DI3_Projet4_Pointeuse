package centralapp.model;

/**
 * <b>Manager : Manage Manager Objects</b>
 * <p>
 * Attributes:
 * <ul>
 * <li>managementDpt : Reference to management Department</li>
 * </ul>
 * 
 * <p>
 * Extends Employee.
 * </p>
 * 
 * @see Employee
 * @see ManagementDpt
 * 
 */
public class Manager extends Employee {

	// Attribute
	/**
	 * Reference to the managementDpt
	 */
	private ManagementDpt managementDpt;

	// Constructor
	/**
	 * Constructor of a Manager
	 * 
	 * @param lnParam Last name of the Manager
	 * @param fnParam First name of the Manager
	 * @param companyParam Company Manager is in
	 */
	public Manager(String lnParam, String fnParam, Company companyParam) {
		super(lnParam, fnParam, companyParam);
		managementDpt = super.getCompany().getManagementDpt();
		managementDpt.addManager(this);
	}
	
	/** 
	 * Build a manager from an employee
	 * 
	 * @param emp The employee to promote
	 */
	public Manager(Employee emp) {
		super(emp);
		managementDpt = super.getCompany().getManagementDpt();
		managementDpt.addManager(this);
		for(CheckInOut cio : emp.getCheckInOut())
			this.addCheckInOut(cio);
		emp.removeEmployee();
	}

	/**
	 * Modify Department of a Manager
	 * 
	 * @param dptParam New Department of the Manager, null to unassign
	 * 
	 * @see Department#setManager(Manager)
	 */
	public void setDpt(Department dptParam) {
		if (super.dpt != null && super.getDpt() != dptParam && dptParam != null) // Retirer instance actuelle de l'ancien dpt
			if (super.dpt.getManager() != null)
				super.dpt.setManager(null);

		if(super.dpt != dptParam)
			super.dpt = dptParam;

		if (dptParam != null)// retirer dpt de son ancien manager
			if (dptParam.getManager() != null && dptParam.getManager() != this)
				dptParam.getManager().setDpt(null);

		if (dptParam != null)// Changer manager dans nouveau dpt
			if (dptParam.getManager() != this)
				dptParam.setManager(this);
	}
	
	/**
	 * Remove totally manager from Database
	 * 
	 * @see Department#removeManager()
	 * @see ManagementDpt#removeManager(Manager)
	 */
	public void removeManager() {
		if(managementDpt.contains(this))
			managementDpt.removeManager(this);
		if(dpt != null)
			if(dpt.getManager() == this)
				dpt.setManager(null);
		super.dpt = null;
		
		super.removeEmployee();
	}

	/**
	 * Get information about manager
	 * 
	 * @return String containing first and last name
	 */
	public String getPrinting() {
		return "Manager : " + getfName() + " " + getlName();
	}
}
