package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;

	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
	}
	
	public void deposit( double amount){
		this.openingBalance += amount;
	}
	public void withdraw(double amount){
		this.openingBalance -= amount;
	}
	public void move(double amount, Account a){
		this.withdraw(amount);
		a.deposit(amount);
	}
	public String toString() {
		return (accountName + ": " + openingBalance);
	}

}
