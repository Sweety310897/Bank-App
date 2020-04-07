package com.shivani.bank;
import com.shivani.bank.interfaces.InputReader;
public class BankApplication {
	public static void main(String[] args) {
		InputReader input = new BankMenu();
		input.readInput();

	}
}

