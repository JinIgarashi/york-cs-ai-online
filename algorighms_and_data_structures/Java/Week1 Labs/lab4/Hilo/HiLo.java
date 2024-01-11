/*
 * @author Tommy Yuan
 * The purpose is to practise the use of if statement.
 * 
 * */


import java.util.*;

public class HiLo {
    
     int random;
    
    public void generateNumber() 
    {
       //The following lines generate and output a random number between 1 and 10
        random = (int)(Math.random()*100)+1;
    }
    
   //Write the guess() method below 
   public String guess()
   {
	   //Use scanner to accept a user input 
	   //Create a new scanner object to receive user input
	      Scanner sc=new Scanner(System.in);
	    
	      System.out.println("Enter you guess ");
	      int guess = sc.nextInt();
	      
	      //write your code below
	      String message;
	      if (random < guess) {
	    	  message = "Low";
	      } else if (random > guess) {
	    	  message = "High";
	      } else {
	    	  message = "Hit";
	      }
    	  return message;
	   
   }
   
   public void startGuessing() {
	   this.generateNumber();

	   String message;
	   int count = 0;
	   do {
		   count++;
		   message = this.guess();
		   if (message == "Hit") {
			   System.out.println(String.format("You have guessed %d times. %s! Congraturations!", count, message));
		   } else {
			   System.out.println(String.format("You have guessed %d times. Correct number is %s! Try again!", count, message));
		   }

	   } while (message != "Hit");
   }
   
    
}
 