package centralapp.model;

import java.time.ZonedDateTime;

/**
 * <b>Test : Test class of model</b>
 * 
 * @see Company
 * 
 * @author Julien
 */
public class Test {

	/**
	 * Test Function
	 * 
	 * @param args
	 *            arguments of the test, useless
	 * @see Company
	 */
	public static void main(String[] args) {
		Boss flamby = new Boss("Hollande", "Francois");
		Company france = new Company("République Française", flamby);

		Manager man1 = new Manager("Cazeneuve", "Bernard", france);
		Manager man2 = new Manager("Valls", "Manuel", france);
		Manager man3 = new Manager("El Komhri", "Myriam", france);

		Department interieur = new Department("Ministère de l'Intérieur",
				france);

		Department travail = new Department("Ministère du Travail");
		travail.assign(france);

		Department defense = new Department("Ministère de la Défense");
		defense.assign(france);

		interieur.setManager(man2);
		travail.setManager(man3);
		defense.setManager(man1);

		defense.removeManager();

		Employee emp1 = new Employee("CRS1", "Jack", interieur);
		Employee emp2 = new Employee("CRS2", "Alfred", interieur);
		Employee emp3 = new Employee("CRS3", "Jean-Claude", interieur);
		Employee emp4 = new Employee("Inspecteur1", "Marc", travail);
		Employee emp5 = new Employee("Inspecteur2", "Joe", travail);
		Employee emp6 = new Employee("Inspecteur3", "Luc", france);
		Employee emp7 = new Employee("Sergeant", "Rémi", defense);
		Employee emp8 = new Employee("Capitaine", "Martin", defense);
		Employee emp9 = new Employee("Général", "Paul");

		// emp1.removeEmployee();

		// emp2.assign(travail);

		// travail.removeDpt();

		System.out.println(france);

		/*
		 * ZonedDateTime date1 = null, date2 = null; //TEST pointages employés
		 * date1 =
		 * ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"); date2
		 * = ZonedDateTime.parse("2016-12-03T10:15:30+01:00[Europe/Paris]");
		 * CheckInOut pointage = (new CheckInOut(date1)); CheckInOut newPointage
		 * = (new CheckInOut(date2));
		 * 
		 * emp1.addCheckInOut(pointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.modifyCheckInOut(pointage, newPointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.deleteCheckInOut(pointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.deleteCheckInOut(newPointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().size());
		 */
	}
}
