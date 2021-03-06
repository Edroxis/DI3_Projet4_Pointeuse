package centralapp.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * <b>CheckInOut : Manage Check In and Check out</b>
 * 
 */

@SuppressWarnings("serial")
public class CheckInOut implements Serializable {
	// Attribute
	/**
	 * Date and hour of the check
	 */
	private ZonedDateTime date;

	// Constructor
	/**
	 * Constructor of a check
	 * 
	 * @param dateParam Date of current check
	 */
	public CheckInOut(ZonedDateTime dateParam) {
		date = dateParam;
	}

	// Method
	/**
	 * Get the date and hour of current check
	 * 
	 * @return Date of current check
	 */
	public ZonedDateTime getDate() {
		return date;
	}
}
