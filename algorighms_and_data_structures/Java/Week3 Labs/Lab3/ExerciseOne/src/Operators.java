
public class Operators {

	public static int add(int value1, int value2) {
		return value1 + value2;
	}
	
	public static int subtract(int value1, int value2) {
		return value1 - value2;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Operators add 7 9");
			return;
		}
		String operation = args[0];
		Integer value1 = Integer.parseInt(args[1]);
		Integer value2 = Integer.parseInt(args[2]);
		
		switch (operation) {
		case "add":
			Integer result = add(value1, value2);
			System.out.println(result);
			break;
		case "subtract":
			Integer result2 = subtract(value1, value2);
			System.out.println(result2);
			break;
		default:
			System.out.println("Operation command should be either 'add' or 'substract'");
			break;
		}

	}

}
