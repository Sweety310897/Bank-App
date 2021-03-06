package com.shivani.services;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shivani.bank.exceptions.BalanceException;
import com.shivani.bank.exceptions.AccountDetailsException;
import com.shivani.bank.interfaces.InputReader;
import com.shivani.bank.models.Bank;
import com.shivani.bank.models.BankAccount;
import com.shivani.validations.InputValidations;

public class ConsoleReader implements InputReader{
	LogManager lgmngr = LogManager.getLogManager(); 
	Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Scanner scan=new Scanner(System.in);
	Bank shivaniBank = new Bank();
	InputValidations validate = new InputValidations();
	static int accountNumber = 1000;
//	
//	public void checkName(String name) {
//        if (name == null || !name.matches("[a-zA-Z]+")) {
//            throw new AccountDetailsException("Please enter your name in valid format");
//        } 
//	}
//	public void checkPhoneNum(String phoneNum) {
//		
//		Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");		  
//        Matcher match = pattern.matcher(phoneNum); 
//        if((match.find() && match.group().equals(phoneNum))) {
//			log.log(Level.INFO, "Valid Mobile Number");
//        }
//		else {
//			throw new AccountDetailsException("Check your entered Phone number"); 
//		}
//	}
//	public void checkAccType(int accType) {
//			if(accType<1 || accType > 4) {
//				throw new AccountDetailsException("Select valid number greater than 1 or less than 4");
//			}
//	}
//
//	public void checkEmail(String email) {
//		 
//		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
//                            "[a-zA-Z0-9_+&*-]+)*@" + 
//                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
//                            "A-Z]{2,7}$"; 
//                              
//        Pattern pattern = Pattern.compile(emailRegex);
//		if (email == null || !pattern.matcher(email).matches()) {
//			throw new AccountDetailsException("Check your entered email");
//		}
//	}

	public void addAccount() {
		try {
			log.log(Level.INFO, "Please enter name");
            String custName = scan.next();
            validate.checkName(custName);
    		log.log(Level.INFO, "Select Account Type: \n 1 for Savings \n 2 for Current \n 3 for FD \n 4 for DEMAT");
			int bankAccType = Integer.parseInt(scan.next());
			validate.checkAccType(bankAccType);
			log.log(Level.INFO, "Please Enter your Aadar Card Number");
			String aadarNumber = scan.next();
			log.log(Level.INFO, "Please Enter Phone Number: ");
			String custMobileNo = scan.next();
			validate.checkPhoneNum(custMobileNo);
			log.log(Level.INFO, "Please Enter Customer Email Id: ");
			String custEmail = scan.next();
			validate.checkEmail(custEmail);
			if(shivaniBank.getSocialSecurityAccount().containsKey(aadarNumber)) {
				log.log(Level.INFO, "Sorry Account already exists with account number: " + shivaniBank.getAccWithSSN(aadarNumber));
			} else {
				shivaniBank.createNewAccount(new BankAccount(accountNumber++, custName, bankAccType-1, custMobileNo, custEmail, aadarNumber));
				log.log(Level.INFO," -> Account created with account number: " + (accountNumber-1)); 
			    
			}
		}
		catch (AccountDetailsException message) {
			log.log(Level.INFO, message.getMessage()); 
        }

	}
	public void displayAll() {
		shivaniBank.getAccountMap().forEach((k, v) -> 	log.log(Level.INFO, "Display Details\n"+ v ));
	}
	public void searchByAccount() {
		log.log(Level.INFO, "Please Enter the account number you want to search: ");
		int searchAccountNumber = scan.nextInt();
		if(shivaniBank.getAccountMap().containsKey(searchAccountNumber)) {
			log.log(Level.INFO, "Search Details\n" + shivaniBank.getAccount(searchAccountNumber));
			
		} else {
			log.log(Level.INFO, "Sorry Search failed Account dooesn't exist..");
		}

	}
	public void depositAmount() {
	 try {
		log.log(Level.INFO, "Please Enter the account number you want to deposit: ");

		int depositAccountNumber = scan.nextInt();
			long amount;
			log.log(Level.INFO, "Please Enter the amount you want to Deposit : ");
			amount = scan.nextLong();
			
			shivaniBank.getAccount(depositAccountNumber).deposit(amount);

	 }catch(AccountDetailsException | BalanceException message ) {
			log.log(Level.INFO, message.getMessage());
	 }
	}
	public void withDrawAmount() {
		try {	
		log.log(Level.INFO, "Please Enter the account number you want to withdraw: ");
		int withdrawAccountNumber = scan.nextInt();
		long amount;
		log.log(Level.INFO, "Please Enter the amount you want to withdraw : ");
		amount = scan.nextLong();
			
			shivaniBank.getAccount(withdrawAccountNumber).withDraw(amount);
		}
		catch(AccountDetailsException | BalanceException message ) {
			log.log(Level.INFO, message.getMessage());
	 }

	}
	public void readInput()
	{
		int choice;
		do
		{
			log.log(Level.INFO, "Main Menu\n 1.Add Account\n 2.Display All\n 3.Search By Account\n 4.Deposit\n 5.Withdrawal\n 6.Exit");
			choice = scan.nextInt();
			switch(choice)
			{ 
				case 1:
					addAccount();
                    break;					
				case 2:
					displayAll();
					break;

				case 3:
					searchByAccount();
					break;

				case 4:
					depositAmount();
					break;

				case 5:
					withDrawAmount();
					break;

				case 6:
					log.log(Level.INFO, "Application has ended");
					break;
				default :
					log.log(Level.INFO, "Correct choice has not been selected");
					break;
			}
		}
		while(choice != 6);
		scan.close();
	}

}
