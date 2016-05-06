package centralapp.model;

/**
 * <b>Boss : Manage Boss</b>
 * 
 * <p>
 * Get first and last name, and boss's String.
 * </p>
 * 
 * @author Julien
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

	// Method
	public String getlName() {
		return super.getlName();
	}

	public String getfName() {
		return super.getfName();
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
