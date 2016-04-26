package centralapp.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Company {

	// Attribut
	private String name;
	private ArrayList<Employee> employeeList;
	private ArrayList<Department> dptList;
	private ManagementDpt managementDpt;
	private Boss boss;

	// Constructeur
	Company(String nameParam, Boss bossParam) {
		managementDpt = new ManagementDpt(bossParam);
		boss = bossParam;
		employeeList = new ArrayList<Employee>(0);
		dptList = new ArrayList<Department>(0);
		name = nameParam;
	}

	// Methode
	public Boss getBoss() {
		return boss;
	}

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
		//Afficher nom entrprise
		res = "Entreprise : " + name + System.lineSeparator() + System.lineSeparator();
		
		//Afficher boss
		res += boss.toString() + System.lineSeparator() + System.lineSeparator();

		//Afficher ManagementDpt et son contenu
		res += managementDpt.toString() + System.lineSeparator();

		//Afficher les départements et leurs contenus
		res = res + System.lineSeparator();
		Iterator<Department> j = dptList.iterator();
		Department temp2;
		while (j.hasNext()) {
			temp2 = j.next();
			res = res + temp2.toString() + System.lineSeparator();
		}

		//Afficher employés non assignés
		res = res + System.lineSeparator() + "Unassigned Employee :" + System.lineSeparator();
		Iterator<Employee> i = employeeList.iterator();
		Employee temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getDpt() == null && temp.getClass() != Manager.class)
				res = res + temp.toString() + System.lineSeparator();
		}

		//Afficher manager non assignés
		res = res + System.lineSeparator() + "Unassigned Manager :" + System.lineSeparator();
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
