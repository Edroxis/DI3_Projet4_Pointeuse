package centralapp.model;

/**
 * <b>Boss : Manage Boss</b>
 * 
 * <p>
 * Get first and last name, and boss's String.
 * </p>
 * 
 */
public class Boss extends AbstractPerson {

	// Attribute

	// Constructor
	/**
	 * Constructor, calls mother's constructor
	 * 
	 * @param lnParam
	 *            Last name
	 * @param fnParam
	 *            First name
	 */
	public Boss(String lnParam, String fnParam) {
		super(lnParam, fnParam);
	}
	
	/**
	 * Build a boss from another person implementing AbstractPerson
	 * 
	 * @param man
	 */
	public Boss(AbstractPerson man) {
		super(man.getlName(), man.getfName());
	}

	/**
	 * Get the the string of first name + last name
	 * 
	 * @return String of first name + last name
	 */
	public String getPrinting() {
		return "PDG : " + getfName() + " " + getlName();
	}

}
