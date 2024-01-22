
public class Cat extends Animal{

	private int livesLeft;
	
	public Cat(String name) {
		super(name);
		livesLeft = 9;
	}
	
	public int getLivesLeft() {
		return livesLeft;
	}
	
	public void loseLife() {
		if (livesLeft == 0 ) {
			System.out.println("the cat is dead");
		} else {
			livesLeft--;
		}
	}

	public String toString() {
		return super.toString() + " Lives left: " + getLivesLeft();
	}
	
}
