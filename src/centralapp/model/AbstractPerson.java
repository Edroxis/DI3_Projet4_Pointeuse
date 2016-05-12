package centralapp.model;

import java.io.Serializable;

/**
 * <b>AbstractPerson : Manage Person's name</b>
 * 
 * <p>
 * Contains and manage the name of Person. Virtual class inherited by Employee
 * and Boss
 * </p>
 * 
 * @see Employee
 * @see Boss
 * 
 * @author Julien
 */
public abstract class AbstractPerson implements Serializable{
	// Attribute
	/**
	 * Last Name
	 */
	private String lName;
	/**
	 * First Name
	 */
	private String fName;

	// Constructor
	/**
	 * Constructor
	 * 
	 * @param lnParam
	 *            the last name
	 * @param fnParam
	 *            the first name
	 */
	AbstractPerson(String lnParam, String fnParam) {
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

	/**
	 * Get the name of a Person
	 * 
	 * @return Name of a Person
	 */
	public String toString() {
		return fName + " " + lName;
	}
}
