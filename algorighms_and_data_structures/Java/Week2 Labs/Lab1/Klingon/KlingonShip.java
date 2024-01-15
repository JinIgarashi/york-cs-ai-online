import java.util.Scanner;

/**
 * AlienShip - Unit 4 exercise.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KlingonShip
{
	double photonPower = 45;
	int threatFactor = 7;
	double cosmicProximity = 20.6;
	
	//Put the displayKlingonDetails()and getDangerLeve() methods below.
	public void displayKlingonDetails() {
		System.out.print("photonPower\t");
		System.out.println(photonPower);
		
		System.out.print("threatFactor\t");
		System.out.println(threatFactor);
		
		System.out.print("cosmicProximity\t");
		System.out.println(cosmicProximity);
		
		double dangerLevel = getDangerLevel();
		System.out.print("dangerLevel\t");
		System.out.println(dangerLevel);
	}
	
	public double getDangerLevel() {
		double dangerLevel =  (photonPower * threatFactor)/cosmicProximity;
		return dangerLevel;
	}
	
	public void promptSettings() {
		 Scanner sc=new Scanner(System.in);
		 System.out.println("********  Initialising KlingonShip settings *******");
		System.out.print("Please enter photon power => ");
		photonPower =sc.nextDouble();
		  
		System.out.print("Please enter thread factor => ");
		threatFactor =sc.nextInt();
		  
		System.out.print("Please enter cosmic proximity => ");
		cosmicProximity =sc.nextDouble();
	}
	
}
