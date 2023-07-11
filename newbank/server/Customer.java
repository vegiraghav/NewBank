package newbank.server;

import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	private String password;
	private String address;
	private String legalName;
	public Customer(String pass) {
		accounts = new ArrayList<>();
		this.password=pass;
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	public Boolean checkPassword(String pass){
		if(this.password==pass)
		{return true;}
		else
		{return false;}
	}

	public void addAddress(String address){
		this.address=address;
	}
	public void addName(String name){
		this.legalName = name;
	}
	public String getAddress(){
		return this.address;
	}
	public String getName(){
		return this.legalName;
	}
	public void changePassword(String pass){
		this.password = pass;
	}
	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
