package com.shivani.bank.models;
import java.util.*;

public class Bank
{
	private HashMap<Integer,BankAccount> accountsMap = new HashMap<>();
	private HashMap<String, Integer> ssnAccmap = new HashMap<>();
//	Bank() {}

	public void createNewAccount(BankAccount account) {
		accountsMap.put(account.getAccountNumber(), account);
		ssnAccmap.put(account.getSSN(), account.getAccountNumber());
	}

	public BankAccount getAccount(int accNumber) {
		return accountsMap.get(accNumber);
	}

	public int getAccWithSSN(String ssn) {
		return this.ssnAccmap.get(ssn);
	}

	public Map<Integer,BankAccount> getAccountMap() {
		return this.accountsMap;
	}

	public Map<String, Integer> getSSNACC() {
		return this.ssnAccmap;
	}
}

