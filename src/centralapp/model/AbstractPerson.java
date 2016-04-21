package centralapp.model;

public abstract class AbstractPerson {
	// Attributs
	private String lName; // Nom
	private String fName; // Prenom

	// Constructeur
	AbstractPerson(String lnParam, String fnParam) {
		lName = lnParam;
		fName = fnParam;
	}

	// Methode
	public String getlName() {
		return lName;
	}

	public String getfName() {
		return fName;
	}
}
