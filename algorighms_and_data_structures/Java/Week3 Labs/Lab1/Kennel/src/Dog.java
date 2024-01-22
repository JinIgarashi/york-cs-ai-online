
public class Dog extends Animal {

	private int exerciseTime;
	
	public Dog(String name, int time) {
		super(name);
		exerciseTime = time;
	}
	
	public void setExerciseTime(int time) {
		exerciseTime = time;
	}
	
	public int getExerciseTime() {
		return exerciseTime;
	}
	
	public String toString() {
		return super.toString() + " Exercise time: " + getExerciseTime();
	}

}
