package centralapp.model;

public class Boss extends AbstractPerson {

	// Attribut

	// Constructeur
	public Boss(String lnParam, String fnParam) {
		super(lnParam, fnParam);
	}

	// Methode
	public String getlName() {
		return super.getlName();
	}

	public String getfName() {
		return super.getfName();
	}

	public String toString() {
		return "PDG : " + getfName() + " " + getlName();
	}

}
