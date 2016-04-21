package centralapp.model;

public class Test {

	public static void main(String[] args)
	{
		Boss flamby = new Boss("Hollande","Francois");
		Company france = new Company("République Française", flamby);
		
		Manager man1 = new Manager("Cazeneuve", "Bernard", france);
		Manager man2 = new Manager("Valls", "Manuel", france);
		Manager man3 = new Manager("El Komhri", "Myriam", france);
		
		Department interieur = new Department("Ministère de l'Intérieur");
		Department travail = new Department("Ministère du Travail");
		Department defense = new Department("Ministère de la Défense");
		
		interieur.setManager(man2);
		travail.setManager(man3);
		defense.setManager(man1);
		
		interieur.assign(france);
		travail.assign(france);
		defense.assign(france);
		
		Employee emp1 = new Employee("CRS1","Jack", france);
		Employee emp2 = new Employee("CRS2","Alfred", france);
		Employee emp3 = new Employee("CRS3","Jean-Claude", france);
		Employee emp4 = new Employee("Inspecteur1","Marc", france);
		Employee emp5 = new Employee("Inspecteur2","Joe", france);
		Employee emp6 = new Employee("Inspecteur3","Luc", france);
		Employee emp7 = new Employee("Sergeant","Rémi", france);
		Employee emp8 = new Employee("Capitaine","Martin", france);
		Employee emp9 = new Employee("Général","Paul", france);
		
		emp1.assign(interieur);
		emp2.assign(interieur);
		emp3.assign(interieur);
		emp4.assign(travail);
		emp5.assign(travail);
		emp6.assign(travail);
		emp7.assign(defense);
		emp8.assign(defense);
		emp9.assign(defense);
		
		emp1.assign(null);
		
		System.out.println(france.toString());
		
	}
}
