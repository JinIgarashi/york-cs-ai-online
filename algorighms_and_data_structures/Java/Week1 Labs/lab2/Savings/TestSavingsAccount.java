
public class TestSavingsAccount {

	public static void main(String[] args) {
		/* Create SavingsAccount object and 
		 * call the methods
		 */
		SavingsAccount s = new SavingsAccount();
		s.initialiseAccountDetails();
		s.getBalance();
		s.deposit();
		s.getBalance();
		s.withdraw();
		s.getBalance();
		s.printStatement();
	}
}