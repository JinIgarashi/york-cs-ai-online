
/**
 * Class to demonstrate Arrays, using JTextArea and 
 * a review of running programs as stand-alone apps
 * 
 * 
 */

import javax.swing.*;

public class BirdTable {


   // main method begins execution of Java application
   public static void main( String args[] )
   {
      //Declare and build an array interactively - see the Temperature Project
	  int[] blueTits = new int[7];
	  
	  for (int i = 0; i < blueTits.length; i++) {
		  String answer = JOptionPane.showInputDialog( "Enter the number of blue tits at Day "+(i+1) );
         blueTits[i] = Integer.parseInt(answer );
	  }
      
      //Display Output using a histogram of '*' - see Histogram Project
      String output = "Day\tNo. Blue Tits\tHistogram";

      // for each array element, output a bar in histogram
      for ( int counter = 0; counter < blueTits.length; counter++ ) {
         output += 
            "\n" + counter+1 + "\t" + blueTits[ counter ] + "\t";

         // print bar of asterisks
         for ( int stars = 0; stars < blueTits[ counter ]; stars++ )
            output += "*";
      }
      //Create a new text area object to hold the text
      JTextArea outputArea = new JTextArea();
      outputArea.setText( output );

      JOptionPane.showMessageDialog( null, outputArea,
         "Histogram Printing Program",JOptionPane.INFORMATION_MESSAGE );

   }
}