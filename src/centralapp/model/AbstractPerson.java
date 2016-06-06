package centralapp.model;

import java.io.Serializable;

/**
 * <b>AbstractPerson : Manage Person's name</b>
 * 
 * <p>
 * Contains and manage the name of Person. Class inherited by Employee
 * and Boss
 * </p>
 * 
 * @see Employee
 * @see Boss
 * 
 */
public class AbstractPerson implements Serializable {
	/**
	 * Last Name
	 */
	private String lName;
	/**
	 * First Name
	 */
	private String fName;

	/**
	 * Constructor
	 * 
	 * @param lnParam
	 *            the last name
	 * @param fnParam
	 *            the first name
	 */
	public AbstractPerson(String lnParam, String fnParam) {
		lName = lnParam;
		fName = fnParam;
	}
	
	// Method
	/**
	 * Get the last name of a Person
	 * 
	 * @return Last name of a Person
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * Get the first name of a Person
	 * 
	 * @return First name of a Person
	 */
	public String getfName() {
		return fName;
	}
	
	/** Set the last name of the person
	 * 
	 * @param lastName 
	 */
	public void setlName(String lastName) {
		lName = lastName;
	}
	
	/**
	 * Set the first name of the person
	 * 
	 * @param firstName
	 */
	public void setfName(String firstName) {
		fName = firstName;
	}

	/**
	 * Get the name of a Person
	 * 
	 * @return Name of a Person
	 */
	public String toString() {
		return fName + " " + lName;
	}
}
