
/**
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class boolOp
{
    //Put your data type declarations here - I have put bresult there to hold the results 
    // of your calculations (see the Boolbag example)
      boolean bresult;
      boolean finished = false;
      int  m = 3;
      int  n = 8;


/* Put the code for your calculations in this method.
 * The result of the expression should be calculated and assigned to 'bresult'
 */
   void a_boolOperation()
   {
        bresult = ( n > m) && ( m < -2);
        System.out.print("( n > m) && ( m < -2) = ");
        System.out.println(bresult);
        
        bresult = (m > 5) || ! (finished);
        System.out.print("(m > 5) || ! (finished) = ");
        System.out.println(bresult);
        
        bresult = !(n < 5) && !(finished);
        System.out.print("!(n < 5) && !(finished) = ");
        System.out.println(bresult);
   }
    
	
}
