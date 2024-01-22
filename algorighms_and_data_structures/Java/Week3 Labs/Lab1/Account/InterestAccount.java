
public class InterestAccount extends Account {

	private double monthlyInterestRate;
	
	public InterestAccount(double balance, double monthlyInterestRateIn) {
		super(balance);
		monthlyInterestRate = monthlyInterestRateIn;
	}
	
	public void addMonthlyInterest() {
		double balance = super.getBalance();
		balance += balance * monthlyInterestRate;
		super.setBalance(balance);
	}
	
	public void setMonthlyInterestRate(double rate) {
		monthlyInterestRate = rate;
	}
	
	public double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}
	
	public String toString() {
		return super.toString() + " Monthly interest rate: " + getMonthlyInterestRate();
	}

}
