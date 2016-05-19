package centralapp.model;

/**
 * <b>Test : Test class of model</b>
 * 
 * @see Company
 * 
 * @author Julien
 */
public class Test {

	/**
	 * Test Function
	 * 
	 * @param args
	 *            arguments of the test, useless
	 * @see Company
	 */
	public static void main(String[] args) {
		Company myCompany = Company.unserialize("Company.ser");
		System.out.println(myCompany.print());

		/*
		 * ZonedDateTime date1 = null, date2 = null; //TEST pointages employ�s
		 * date1 =
		 * ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"); date2
		 * = ZonedDateTime.parse("2016-12-03T10:15:30+01:00[Europe/Paris]");
		 * CheckInOut pointage = (new CheckInOut(date1)); CheckInOut newPointage
		 * = (new CheckInOut(date2));
		 * 
		 * emp1.addCheckInOut(pointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.modifyCheckInOut(pointage, newPointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.deleteCheckInOut(pointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().get(0).getDate().toString());
		 * 
		 * emp1.deleteCheckInOut(newPointage);
		 * 
		 * System.out.println(emp1.getCheckInOut().size());
		 */
	}
}
