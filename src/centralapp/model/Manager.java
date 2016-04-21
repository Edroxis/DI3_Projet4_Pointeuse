package centralapp.model;

public class Manager extends Employee {

	// Attribut
	private ManagementDpt managementDpt;

	// Constructeur
	public Manager(String lnParam, String fnParam, Company companyParam) {
		super(lnParam, fnParam, companyParam);
		managementDpt = super.getCompany().getManagementDpt();
		managementDpt.addManager(this);
	}

	// Methode
	public String getlName() {
		return super.getlName();
	}

	public String getfName() {
		return super.getfName();
	}

	public int getId() {
		return super.getId();
	}

	public Department getDpt() {
		return super.getDpt();
	}

	public void setDpt(Department dptParam) {
		Department nullDpt = null;
		super.dpt = dptParam;
		if (dptParam != null)
			dptParam.getManager().setDpt(nullDpt);
		if (dptParam != null && dptParam.getManager() != this)
			dptParam.setManager(this);
	}
	
	public String toString() {
		return "Manager : " + getfName() + " " + getlName();
	}
}
