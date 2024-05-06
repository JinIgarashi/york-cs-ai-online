public class Student {

	private String name;
	private int rollNumber;
	
	public Student(String nameIn, int rollNumberIn) {
		name = nameIn;
		rollNumber = rollNumberIn;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRnumber() {
		return rollNumber;
	}
	
	public void display() {
		System.out.print("name: ");
		System.out.println(name);
		System.out.print("roll number: ");
		System.out.println(rollNumber);
	}

}
