package centralapp.model;

import java.io.Serializable;

/**
 * <b>AbstractDpt : Manage Department's name</b>
 * 
 * <p>
 * Contains and manage the name of Departments.Inherited by Departments and
 * ManagementDpt
 * </p>
 * 
 * @see Department
 * @see ManagementDpt
 * 
 * @author Julien
 */
public abstract class AbstractDpt implements Serializable {

	// Attribute
	/**
	 * Name of the Department
	 */
	private String name;

	// Constructor
	/**
	 * Constructor
	 * 
	 * @param nameParam
	 *            name of the Department
	 */
	public AbstractDpt(String nameParam) {
		name = nameParam;
	}

	// Method
	/**
	 * Get the name of the Department
	 * 
	 * @return name of the Department
	 */
	public String toString() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
