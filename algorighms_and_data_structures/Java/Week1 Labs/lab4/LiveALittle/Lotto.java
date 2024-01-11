
/**
 * Write a description of class Lotto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lotto
{
	
	//Put the luckyDip() method here
	public void luckyDip() {
		for (int i = 1; i <=6; i++) {
			int number = this.getRandomNumber();
			System.out.println(String.format("Ball %d = %d", i, number));
		}
	}
	
	public int getRandomNumber() {
		int random;
	   random = (int)(Math.random()*49)+1;
	   return random;
	}





        //Test
	public static void main (String args[]) {
		
		Lotto l=new Lotto();
		l.luckyDip();
	}

	
}
