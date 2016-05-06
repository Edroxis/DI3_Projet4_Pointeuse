package centralapp.model;

import java.util.ArrayList;

public class Employee extends AbstractPerson {

	// Attribut
	private Company company;
	private int id;
	static private int nbEmployee = 0;
	protected Department dpt;
	private ArrayList<CheckInOut> checkInOut;

	// Constructeur
	public Employee(String lnParam, String fnParam, Company companyParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		company = companyParam;
		companyParam.add(this);
	}

	public Employee(String lnParam, String fnParam, Department dptParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		company = dptParam.getCompany();
		if (dptParam.getCompany() != null)
			dptParam.getCompany().add(this);
		assign(dptParam);
	}

	public Employee(String lnParam, String fnParam) {
		super(lnParam, fnParam);
		id = nbEmployee;
		nbEmployee++;
		checkInOut = new ArrayList<CheckInOut>(0);
		company = null;
		dpt = null;
	}

	// Methode
	public String getlName() {
		return super.getlName();
	}

	public String getfName() {
		return super.getfName();
	}

	public int getId() {
		return id;
	}

	public Department getDpt() {
		return dpt;
	}

	public void assign(Department dptParam) {
		if (dpt != null)
			dpt.removeEmployee(this);
		dpt = dptParam;
		if (dptParam != null && !dptParam.contains(this))
			dptParam.assign(this);
		if (company != dpt.getCompany()) {
			if (company != null)
				company.removeEmployee(this);
			company = dpt.getCompany();
			dpt.getCompany().add(this);
		}
	}

	public Company getCompany() {
		return company;
	}

	public ArrayList<CheckInOut> getCheckInOut() {
		return checkInOut;
	}

	public void addCheckInOut(CheckInOut param) {
		checkInOut.add(param);
	}

	public void modifyCheckInOut(CheckInOut oldCIO, CheckInOut newCIO) {
		int index = checkInOut.indexOf(oldCIO);
		if (index != -1)
			checkInOut.set(index, newCIO);
	}

	public void deleteCheckInOut(CheckInOut toDelete) {
		int index = checkInOut.indexOf(toDelete);
		if (index != -1)
			checkInOut.remove(index);
	}

	public void removeEmployee() {
		if (company.contains(this))
			company.removeEmployee(this);

		if (dpt != null)
			if (dpt.contains(this))
				dpt.removeEmployee(this);
	}

	public String getPrinting() {
		return "Employee : " + getfName() + " " + getlName();// + " " + id;
	}

	public void assign(Company company) {
		this.company = company;
	}
}
