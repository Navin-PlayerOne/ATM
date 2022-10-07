package ATM;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8054141826860457394L;
	public int accountNumber;
	public String name;
	public int balance;
	public int pin;
	public Customer(int accountNumber, String name, int balance, int pin) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.pin = pin;
	}
	
}
