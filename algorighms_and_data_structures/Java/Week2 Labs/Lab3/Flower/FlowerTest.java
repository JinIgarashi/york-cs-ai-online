import java.util.ArrayList;

public class FlowerTest {

	public static void main(String[] args) {
		// Create 4 different flowers using the different constructors
		Flower sakura = new Flower("Cherry blossoms");
		Flower ume = new Flower("Japanese apricot");
		Flower hasu = new Flower("Lotus");
		Flower botan = new Flower("Peony");
		
		ArrayList<Flower> flowers = new ArrayList<Flower>();
		flowers.add(sakura);
		flowers.add(ume);
		flowers.add(hasu);
		flowers.add(botan);
		
		for (int i = 0; i < flowers.size(); i++) {
			String name = flowers.get(i).getFlowerName();
			System.out.println(name);
		}
	}

}
