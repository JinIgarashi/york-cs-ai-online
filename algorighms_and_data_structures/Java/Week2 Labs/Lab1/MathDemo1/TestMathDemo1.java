
public class TestMathDemo1 {

	public static void main(String[] args) {
		// Create MathDemo1 object and test the methods
		MathDemo m = new MathDemo();
		int lottoNumber = m.generateLottoNumber();
		System.out.println("lottoNumber = " + lottoNumber);
		
		int maxValue = m.max(5,2, -5);
		System.out.println("maxValue = " + maxValue);
		
		m.bestOfThree();
	}

}
