
/**
 * To demonstrate boolean.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person
{
	double cmHeight=174;
	double kgWeight=80;
	boolean smoker = false;
	int weeklyAlcholUnits = 0;
	final int RWAL = 21;   //RWAL - Recommended Weekly Alcohol Limit
	int restPulse = 260;
	char gender = 'm'; // 'm' or 'f'

	
	public void checkPulse()
	{
	   boolean healthyPulse;
	   healthyPulse = ((restPulse > 30) && (restPulse < 180));
	   System.out.print("Healthy pulse = ");
	   System.out.println(healthyPulse);
	}
	
	public void checkForAbuse()
	{
	      boolean abuser ;
	      abuser = ((weeklyAlcholUnits > RWAL) || (smoker) );
	      System.out.print("Checking for abuse -- ");
	      System.out.println(abuser);
	
	}
	
	public void checkDesirability()
	{
		boolean desirability;
		desirability = cmHeight < 150 && kgWeight > 100 && smoker == true && weeklyAlcholUnits > RWAL;
		System.out.print("Checking for desirability -- ");
		System.out.println(desirability);
	}
	
	public void displayDetails()
	{
		if (gender == 'm') {
		   System.out.println(String.format("gender: %s", "Male"));
	   } else if (gender == 'f') {
		   System.out.println(String.format("gender: %s", "Female"));
	   }
		
	   System.out.println(String.format("Height: %f cm", cmHeight));
	   System.out.println(String.format("Weight: %f kg", kgWeight));
	   
	   if (smoker == true) {
		   System.out.println(String.format("smoker: %s", "Yes"));
	   } else {
		   System.out.println(String.format("smoker: %s", "No"));
	   }
	   
	   System.out.println(String.format("weeklyAlcholUnits: %d", weeklyAlcholUnits));
	   System.out.println(String.format("Recommended Weekly Alcohol Limit: %d", RWAL));
	   System.out.println(String.format("restPulse: %d", restPulse));
	}
	
}
