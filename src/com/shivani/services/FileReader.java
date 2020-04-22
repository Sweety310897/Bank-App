package com.shivani.services;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.shivani.bank.exceptions.AccountDetailsException;
import com.shivani.bank.exceptions.BalanceException;
import com.shivani.bank.interfaces.InputReader;
import com.shivani.bank.models.Bank;
import com.shivani.bank.models.BankAccount;
import com.shivani.validations.InputValidations;
 
public class FileReader implements InputReader {
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Bank shivaniBank = new Bank();
	static int accountNumber = 1000;
	InputValidations validate = new InputValidations();
	public void addAccount(String[] custDetails) {
		
		try {
			String custName = custDetails[1];
            validate.checkName(custName);
			int bankAccType = Integer.parseInt(custDetails[2]);
			validate.checkAccType(bankAccType);
			String aadarNumber = custDetails[3];
			String custMobileNo = custDetails[4];
			validate.checkPhoneNum(custMobileNo);
			String custEmail = custDetails[5];
			validate.checkEmail(custEmail);
			if(shivaniBank.getSocialSecurityAccount().containsKey(aadarNumber)) {
				logger.log(Level.INFO, "Sorry Account already exists with account number: " + shivaniBank.getAccWithSSN(aadarNumber));
			} else {
				shivaniBank.createNewAccount(new BankAccount(accountNumber++, custName, bankAccType-1, custMobileNo, custEmail, aadarNumber));
				logger.log(Level.INFO," -> Account created with account number: " + (accountNumber-1)); 
			    
			}
		}
		catch (AccountDetailsException message) {
			logger.log(Level.INFO, message.getMessage()); 
        }		
	}
	
	public void displayAll() {
		shivaniBank.getAccountMap().forEach((k, v) -> 	logger.log(Level.INFO, "Display Details\n"+ v ));
	}
	public void searchByAccount(int searchAccountNumber) {
		if(shivaniBank.getAccountMap().containsKey(searchAccountNumber)) {
			logger.log(Level.INFO, "Search Details\n" + shivaniBank.getAccount(searchAccountNumber));
			
		} else {
			logger.log(Level.INFO, "Sorry Search failed Account dooesn't exist..");
		}

	}
	public void depositAmount(int depositAccountNumber, long amount) {
	 try {
			
			shivaniBank.getAccount(depositAccountNumber).deposit(amount);

	 }catch(AccountDetailsException | BalanceException message ) {
			logger.log(Level.INFO, message.getMessage());
	 }
	}
	public void withDrawAmount(int withdrawAccountNumber, long amount) {
		try {				
			shivaniBank.getAccount(withdrawAccountNumber).withDraw(amount);
		}
		catch(AccountDetailsException | BalanceException message ) {
			logger.log(Level.INFO, message.getMessage());
	 }
	}

		@Override
		public void readInput() {
			  try {
				  File file = new File("F:\\Office\\Bank\\Bank\\Bank-App\\input.txt"); 
				  Scanner myReader = new Scanner(file);					
				  while (myReader.hasNextLine()) {
					      String fileData = myReader.nextLine();
      
					      String[] data = fileData.split(" ");
				
					      switch (data[0]) {
							case "addAccount":
								addAccount(data);
								break;
							case "displayAll":
								displayAll();
								break;
							case "searchByAccount":
								searchByAccount(Integer.parseInt(data[1]));
								break;
							case "depositAmount":
								depositAmount(Integer.parseInt(data[1]),Long.parseLong(data[2]));
								break;
							case "withDrawAmount":
								withDrawAmount(Integer.parseInt(data[1]),Long.parseLong(data[2]));
								
								break;
							default:
								break;
							}
					      
				  }
				} catch (FileNotFoundException message) {
					logger.log(Level.INFO, "File Not Found");
				}
		}

}