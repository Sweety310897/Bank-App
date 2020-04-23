package com.shivani.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.shivani.bank.exceptions.AccountDetailsException;
import com.shivani.bank.exceptions.BalanceException;
import com.shivani.bank.interfaces.InputReader;
import com.shivani.bank.models.Bank;
import com.shivani.bank.models.BankAccount;
import com.shivani.validations.InputValidations;
public class ReadPropertiesFile implements InputReader {
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Bank shivaniBank = new Bank();
	static int accountNumber = 1000;
	InputValidations validate = new InputValidations();
	
	public void addAccount(String values) {
		try {
			values =  values.replace("\"", "");	
			String[] accDetails = values.split(",");   
			for(String each: accDetails) {
				String[] custDetails = each.split(" ");
				String custName = custDetails[0];
	            validate.checkName(custName);
				int bankAccType = Integer.parseInt(custDetails[1]);
				validate.checkAccType(bankAccType);
				String aadarNumber = custDetails[2];
				String custMobileNo = custDetails[3];
				validate.checkPhoneNum(custMobileNo);
				String custEmail = custDetails[4];
				validate.checkEmail(custEmail);
				if(shivaniBank.getSocialSecurityAccount().containsKey(aadarNumber)) {
					logger.log(Level.INFO, "Sorry Account already exists with account number: " + shivaniBank.getAccWithSSN(aadarNumber));
				} else {
					shivaniBank.createNewAccount(new BankAccount(accountNumber++, custName, bankAccType-1, custMobileNo, custEmail, aadarNumber));
					logger.log(Level.INFO," -> Account created with account number: " + (accountNumber-1)); 
				    
				}
			}
		}
		catch (AccountDetailsException message) {
			logger.log(Level.INFO, message.getMessage()); 
        }		
	}

	public void displayAll() {
		shivaniBank.getAccountMap().forEach((k, v) -> 	logger.log(Level.INFO, "Display Details\n"+ v ));
	}
	public void searchByAccount(String searchAccount) {
		searchAccount =  searchAccount.replace("\"", "");	
		String[] search = searchAccount.split(",");
		for(String each: search) {
			int searchAccountNumber = Integer.parseInt(each);
			if(shivaniBank.getAccountMap().containsKey(searchAccountNumber)) {
				logger.log(Level.INFO, "Search Details\n" + shivaniBank.getAccount(searchAccountNumber));
				
			} else {
				logger.log(Level.INFO, "Sorry Search failed Account dooesn't exist..");
			}
		}
	}
	
	public void depositAmount(String details) {
		 try {
			  details =  details.replace("\"", "");
				String[] depositDetails = details.split(",");
				for(String each: depositDetails) {
					String[] values = each.split(" ");
					int depositAccountNumber = Integer.parseInt(values[0]);
					long amount = Long.parseLong(values[1]);
					shivaniBank.getAccount(depositAccountNumber).deposit(amount);

				}

		 }catch(AccountDetailsException | BalanceException message ) {
				logger.log(Level.INFO, message.getMessage());
		 }
	}
	public void withDrawAmount(String details) {
		try {				
			  details =  details.replace("\"", "");
				String[] withdrawDetails = details.split(",");
				for(String each: withdrawDetails) {
					String[] values = each.split(" ");
					int withdrawAccountNumber = Integer.parseInt(values[0]);
					long amount = Long.parseLong(values[1]);
					shivaniBank.getAccount(withdrawAccountNumber).withDraw(amount);
				}
		}
		catch(AccountDetailsException | BalanceException message ) {
			logger.log(Level.INFO, message.getMessage());
		}
	}

	
	@SuppressWarnings("rawtypes")
	public void readInput() {
		
		  try { 
			  	FileInputStream fis = new FileInputStream("com/shivani/resources/input.properties");
				Properties prop = new Properties();
					prop.load(fis);
					Enumeration e = prop.propertyNames();
					while (e.hasMoreElements()) {
					      String key = (String) e.nextElement();
					      switch(key) {
					      	case "addAccount" : addAccount(prop.getProperty(key));
					      		break;
					      	case "displayAll": displayAll();
					      		break;
					      	case "searchAccount": searchByAccount(prop.getProperty(key));
					      		break;
					      	case "deposit": depositAmount(prop.getProperty("deposit"));
					      		break;
					      	case "withdraw": withDrawAmount(prop.getProperty("withdraw"));
					      		break;
					      	default:
								break;
							
					      }
					 } 
		  } 
		  catch (FileNotFoundException message) {
				logger.log(Level.INFO, "File Not Found");
		  } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
	}
}
