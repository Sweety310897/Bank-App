package com.shivani.bank.models;
import java.util.*;

public class Bank
{
	private HashMap<Integer,BankAccount> accountsMap = new HashMap<>();
	private HashMap<String, Integer> ssnAccmap = new HashMap<>();

	public void createNewAccount(BankAccount account) {
		accountsMap.put(account.getAccountNumber(), account);
		ssnAccmap.put(account.getSSN(), account.getAccountNumber());
	}

	public BankAccount getAccount(int accNumber) {
		return accountsMap.get(accNumber);
	}

	public int getAccWithSSN(String socialSecurityNumber) {
		return this.ssnAccmap.get(socialSecurityNumber);
	}

	public Map<Integer,BankAccount> getAccountMap() {
		return this.accountsMap;
	}

	public Map<String, Integer> getSocialSecurityAccount() {
		return this.ssnAccmap;
	}
}

