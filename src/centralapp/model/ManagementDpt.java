package centralapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ManagementDpt extends AbstractDpt {

	// Attribut
	private Boss boss;
	private ArrayList<Manager> managerList;

	// Constructeur
	ManagementDpt(Boss bossParam) {
		super("Management Department");
		boss = bossParam;
		managerList = new ArrayList<Manager>(0);
	}

	// Methode
	public void addManager(Manager manParam) {
		if(!contains(manParam))
			managerList.add(manParam);
	}
	
	public String toString(){
		String res = "Management Department : " + System.lineSeparator();
		
		res += "\t" + boss.toString() + System.lineSeparator();
		
		Iterator<Manager> i = managerList.iterator();
		Manager temp;
		while (i.hasNext()) {
			temp = i.next();
			res = res + "\t" + temp.toString() + System.lineSeparator();
		}
		return res;
	}

	public boolean contains(Manager manager) {
		return managerList.contains(manager);
	}
	
	public void removeManager(Manager manager) {
		if(contains(manager))
			managerList.remove(manager);
		manager.removeManager();
	}
}
