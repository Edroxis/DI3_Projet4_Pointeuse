package centralapp.model;

import java.time.ZonedDateTime;

public class CheckInOut {
	// Attribut
	private ZonedDateTime date;

	// Constructeur
	public CheckInOut(ZonedDateTime dateParam) {
		date = dateParam;
	}

	// Methode
	public ZonedDateTime getDate() {
		return date;
	}
}
