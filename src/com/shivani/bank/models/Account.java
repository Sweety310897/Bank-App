package com.shivani.bank.models;

class Account {
	String[] accTypes = {"Savings", "Current", "Fixed", "DEMAT"};
	private final int accountNumber;
	private final String customerName;
	private String accountType;
	private long accountBalance;
	private final String socialSecurityNumber;

	Account(int accountNumber, String custName, int accType, String ssn) {
		this.accountNumber = accountNumber;
		this.customerName = custName;
		this.accountType = accTypes[accType];
		this.accountBalance = 0;
		this.socialSecurityNumber = ssn;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public long getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountType(int type) {
		this.accountType = accTypes[type];
		
	}

	public void updateAccountBalance(long amount) {
		this.accountBalance = amount;
	}

	public String getSSN() {
		return this.socialSecurityNumber;
	}
}
