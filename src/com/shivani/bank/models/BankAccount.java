package com.shivani.bank.models;
import com.shivani.bank.interfaces.AccountOperations;
import com.shivani.bank.exceptions.InsufficientBalance;
import java.util.*;
import java.util.logging.*; 
public class BankAccount extends Account implements AccountOperations {
	private String custPhoneNo;
	private String custEmailId;

	LogManager lgmngr = LogManager.getLogManager(); 
	Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Scanner scan2 = new Scanner(System.in);
	public BankAccount(int accNumber, String custName, int accType, String custPhoneNo, String custEmailId, String ssn) {
		super(accNumber, custName, accType, ssn);
		this.custPhoneNo = custPhoneNo;
		this.custEmailId = custEmailId;
	}

	public String getCustEmail() {
		return this.custEmailId;
	}

	public String getCustPhone() {
		return this.custPhoneNo;
	}

	public void deposit(long amount) {
	try {
//		long amount;
//		System.out.println("Enter the amount you want to Deposit : ");
//		amount = scan2.nextLong();
		if(amount > 0 ) {
			updateAccountBalance(getAccountBalance() + amount);
			log.log(Level.INFO, "Amount deposited successfully");
//			System.out.println("Amount deposited successfully");
		} else {
			throw new InsufficientBalance("You cannot deposit as the number entered by you is less than 0");
		}
		}
		catch(InsufficientBalance e) {
			log.log(Level.INFO, e.getMessage()); 

		}
	}

	public void withdrawal(long amount) {
		try {
//		long amount;
//		System.out.println("Enter the amount you want to withdraw : ");
//		amount = scan2.nextLong();
		if(amount < getAccountBalance() ) {
			updateAccountBalance(getAccountBalance() - amount);
			log.log(Level.INFO, "Amount Withdrawn successfully");
			log.log(Level.INFO, "Remaining Balance is " + getAccountBalance());
//			System.out.println("Amount Withdrawn successfully");
//			System.out.println("Remaining Balance is " + getAccountBalance());
		}else {
			throw new InsufficientBalance("Balance is less. U cannot withdraw");
		}
		}
		catch(InsufficientBalance e) {
			log.log(Level.INFO, e.getMessage()); 
			
		}
	}


	public String toString() {
		String details = "Name: " + getCustomerName() + "\n" + "Account Number: " + getAccountNumber() + "\n" + "Account Type: " + getAccountType() + "\n" + "Available Balance: " + getAccountBalance() + "\n" + "Mobile Number: " + getCustPhone() + "\n" + "Email id: " + getCustEmail() + "\n";
		return details;
	}

	boolean search(int acn)
	{ 
		if(getAccountNumber() == acn)
		{ 
			
			System.out.println(this);
			return(true);
		}
		return(false);
	}
}
