package centralapp.model;

public abstract class AbstractDpt {

	// Attribut
	private String name;

	// Constructeur
	public AbstractDpt(String nameParam) {
		name = nameParam;
	}

	// Methode
	public String getName() {
		return name;
	}
}
