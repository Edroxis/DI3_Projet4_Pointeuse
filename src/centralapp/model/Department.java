package centralapp.model;

import java.util.HashMap;
import java.util.Iterator;

public class Department extends AbstractDpt {

	// Attribut
	private Company company;
	private HashMap<Integer, Employee> employeeMap;
	private int nbEmployee;
	private Manager manager;

	// Constructeur
	public Department(String nameParam) {
		super(nameParam);
		employeeMap = new HashMap<Integer, Employee>(0);
		manager = null;
		nbEmployee = 0;
	}

	// Methode
	public String getName() {
		return super.getName();
	}

	public boolean contains(Employee empParam) {
		return employeeMap.containsValue(empParam);
	}

	public void assign(Employee eParam) {
		if(eParam.getDpt() != this)
			eParam.assign(this);
		
		if (!employeeMap.containsKey(eParam.getId()))
		{
			employeeMap.put(eParam.getId(), eParam);
			nbEmployee++;
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manParam) {
		manager = manParam;
		if (manParam.getDpt() != this)
			manParam.setDpt(this);
	}

	public Company getCompany() {
		return company;
	}

	public void assign(Company companyParam) {
		company = companyParam;
		companyParam.add(this);
	}

	public void removeEmployee(Employee empParam) {
		if(employeeMap.containsValue(empParam))
		{
			employeeMap.remove(empParam.getId());
			nbEmployee--;
		}
	}
	
	public void removeDpt()
	{
		Department nullDpt = null;
		if(!employeeMap.isEmpty())
			employeeMap.forEach((k,v) -> {if(v.getDpt() == this) v.assign(nullDpt);});
		employeeMap.clear();
		if(company.containsDpt(this))
			company.removeDepartment(this);
		if(manager.getDpt() == this)
			manager.setDpt(nullDpt);
	}

	public String toString() {
		String res =  "Department : " + getName() + System.lineSeparator();
		res = res + "\t" + manager.toString() + System.lineSeparator();
		for(Employee e : employeeMap.values())
			res+= "\t" + e.toString() + System.lineSeparator();
		return res;
	}
}
