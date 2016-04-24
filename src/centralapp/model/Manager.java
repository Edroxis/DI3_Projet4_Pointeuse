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
		if (super.dpt != null && super.dpt != dptParam) // Retirer instance actuelle de l'ancien dpt
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

	public String toString() {
		return "Manager : " + getfName() + " " + getlName();
	}
}
