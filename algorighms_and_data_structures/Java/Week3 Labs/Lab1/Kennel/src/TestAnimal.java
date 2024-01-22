
public class TestAnimal {

	public static void main(String[] args) {
		Dog dog = new Dog("Taro", 30);
		System.out.println(dog.toString());
		dog.setExerciseTime(50);
		System.out.println(dog.toString());
		
		Cat cat = new Cat("Jiji");
		
		do {
			System.out.println(cat.toString());
			cat.loseLife();
		}while(cat.getLivesLeft() > 0);
		
		cat.loseLife();
		System.out.println(cat.toString());
	}

}
