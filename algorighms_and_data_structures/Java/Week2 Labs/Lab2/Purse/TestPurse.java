
public class TestPurse {

	public static void main(String[] args) {
		//Create a purse and some coins, then add the coins to the purse, 
		//and finally work out the total in the purse
		Coin twentyPenny = new Coin(20);
		Purse p = new Purse();
		p.addCoins(8, twentyPenny);
		System.out.println("Total=" + p.getTotal());
	}

}
