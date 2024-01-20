import java.util.Scanner;

/**
 * Favourite class to test a string object and it's methods.
 * 
 * @version (a version number or a date)
 */

public class WrappedUp
{
	
	public void guessMyFavouriteThing()
	{
	   //Create a string object with your favourite animal, car ..whatever
	   //e.g String favMotorBikeManufacturer = "bmw"
	   String myFavourite = "Tea";
		
	   /* Now prompt for a guess - using the Scanner class
	   ** Using String methods, you should give the length 
	   ** and first letter of your favourite thing in the prompt.
	   */
	  Scanner input = new Scanner(System.in);
	  String answer = "";
	  Boolean isCorrect = false;
	  do {
		  System.out.println("Guess my favourite. First letter is t. It is traditionally popular in UK and Ireland. it is often knoan as Afternoon...");
		  answer = input.nextLine();
		  
		  isCorrect = myFavourite.toLowerCase().equals(answer.toLowerCase());
		  if (!isCorrect) {
			  System.out.println("Try aggain!");
		  }
	  } while (!isCorrect);
	  
	  System.out.println("Correct!");
	  input.close();
	  
	  // Now test if the guess was correct - ignore case 
	
	
	}//end guessing


	public static void main(String args[]) {
		//Create WrappedUp object and test the methods
		WrappedUp w = new WrappedUp();
		w.guessMyFavouriteThing();
	}
}
