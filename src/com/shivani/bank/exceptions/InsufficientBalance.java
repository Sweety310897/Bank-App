package com.shivani.bank.exceptions;

public class InsufficientBalance extends RuntimeException {
	public InsufficientBalance(String s) {
		super(s);
	}
}
