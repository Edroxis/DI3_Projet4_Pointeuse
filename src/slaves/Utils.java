package slaves;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Utils {
	static public ZonedDateTime roundTimeMinQuarter(ZonedDateTime dateTime) {
		//Round the sec
		int unroundedSec = dateTime.getSecond();
		if(unroundedSec >= 30)
			dateTime = dateTime.plusMinutes(1);
		// We get rid of nanoseconds et seconds
		dateTime = dateTime.truncatedTo(ChronoUnit.MINUTES);

		//Round the min
		int unroundedMin = dateTime.getMinute();
		int div = unroundedMin / 15;
		int mod = unroundedMin % 15;
		if(mod >= 8)
			div++;
		if(div == 4) {
			dateTime = dateTime.plusHours(1);
			div = 0;
		}
		dateTime = dateTime.withMinute(div * 15);
		return dateTime;
	}
}
