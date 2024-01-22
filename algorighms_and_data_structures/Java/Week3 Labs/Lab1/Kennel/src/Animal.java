
public class Animal {

	private String name;
	
	public Animal(String nameIn) {
		name = nameIn;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return super.toString() + " name: "+ getName();
	}

}
