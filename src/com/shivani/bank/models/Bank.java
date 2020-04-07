package com.shivani.bank.models;
import java.util.*;

public class Bank
{
	private HashMap<Integer,BankAccount> accountsList = new HashMap<>();
	private HashMap<String, Integer> ssnAccmap = new HashMap<>();
//	Bank() {}

	public void createNewAccount(BankAccount account) {
		accountsList.put(account.getAccountNumber(), account);
		ssnAccmap.put(account.getSSN(), account.getAccountNumber());
	}

	public BankAccount getAccount(int accNumber) {
		return accountsList.get(accNumber);
	}

	public int getAccWithSSN(String ssn) {
		return this.ssnAccmap.get(ssn);
	}

	public Map<Integer,BankAccount> getAccountList() {
		return this.accountsList;
	}

	public Map<String, Integer> getSSNACC() {
		return this.ssnAccmap;
	}
}

