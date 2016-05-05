package centralapp.model;

import java.util.concurrent.ConcurrentHashMap;

public class Department extends AbstractDpt {

	// Attribut
	private Company company;
	private ConcurrentHashMap<Integer, Employee> employeeMap;
	private int nbEmployee;
	private Manager manager;

	// Constructeur
	public Department(String nameParam) {
		super(nameParam);
		employeeMap = new ConcurrentHashMap<Integer, Employee>(0);
		manager = null;
		nbEmployee = 0;
	}

	public Department(String nameParam, Company company) {
		super(nameParam);
		employeeMap = new ConcurrentHashMap<Integer, Employee>(0);
		manager = null;
		nbEmployee = 0;
		assign(company);
	}

	// Methode
	public String getName() {
		return super.getName();
	}

	public boolean contains(Employee empParam) {
		return employeeMap.containsValue(empParam);
	}

	public void assign(Employee eParam) {
		if (eParam.getDpt() != this)
			eParam.assign(this);

		if (!employeeMap.containsKey(eParam.getId())) {
			employeeMap.put(eParam.getId(), eParam);
			nbEmployee++;
		}
	}

	public Manager getManager() {
		return manager;
	}

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

	public Company getCompany() {
		return company;
	}

	public void assign(Company companyParam) {
		company = companyParam;
		companyParam.add(this);
		for (Employee e : employeeMap.values())
			e.assign(companyParam);
	}

	public void removeEmployee(Employee empParam) {
		if (employeeMap.containsValue(empParam)) {
			employeeMap.remove(empParam.getId());
			nbEmployee--;
		}
	}
	
	public void removeManager() {
		if(manager != null)
			manager.removeManager();
	}

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

	public String toString() {
		String res = "Department : " + getName() + System.lineSeparator();
		if(manager != null)
			res = res + "\t" + manager.toString() + System.lineSeparator();
		else
			res = res + "\t" + "Manager : " + System.lineSeparator();
		for (Employee e : employeeMap.values())
			res += "\t" + e.toString() + System.lineSeparator();
		return res;
	}
}
