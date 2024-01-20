// File: Test_YorDate.java
/**
 * Test harness for YorDate class
 *
 * @author	Tommy Yuan
 * @version	24 January 2019 */
 
public class Test_YorDate
{
	public static void main(String args[])
	{
		//Create a reference to a YorDate object
		YorDate date1;
			
	    // This creates an object which sets the date to the current date
		 date1 = new YorDate();
		
		//Using the appropriate methods set, set date1 to your birthdate
		 date1.setDay(23);
		 date1.setMonth(6);
		 date1.setYear(1985);
		
		//Using appropriate date1 method(s) display your birthday
		System.out.println("Your date is " + date1.toString());
		
		YorDate date2 = new YorDate(23, 6, 1985);;
		System.out.println("Day of the week is " + date2.getDayOfWeek());
	}
}