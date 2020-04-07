package com.shivani.bank;
import com.shivani.bank.interfaces.InputReader;
import com.shivani.bank.exceptions.Validation;
import com.shivani.bank.models.Bank;
import com.shivani.bank.models.BankAccount;
import java.util.*;
import java.util.regex.Pattern; 
import java.util.logging.Logger; 
import java.util.logging.*; 
public class BankMenu implements InputReader
{  
	LogManager lgmngr = LogManager.getLogManager(); 
	Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static int accountNumber = 1000;
//	public boolean checkName(String name) {
//		try {
//			if (name == null) {
//				throw new Validation("Name is not entered");
//			}
//		} catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//			
//			return false;
//		}
//		 
//
//		try {
//			if(name.matches("[a-zA-Z]+")) {
//				return true;
//			} else {
//				throw new Validation("Check your entered name"); 
//			}
//		} catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//			return false;
//		}
//		
//	}
	
	public void checkName(String name) {
        if (name == null || !name.matches("[a-zA-Z]+")) {
            throw new Validation("Please enter your name in valid format.........");
        } 

	}
	public void checkPhnum(String phnum) {
		String[] num = phnum.split("");
		
//		try {
			if (phnum == null) {
				throw new Validation("Phone number is not entered");
			}
			if(phnum.matches("[0-9]+")) {
				if(num[0].matches("[7-9]")) {
					if(num.length != 10) {
						throw new Validation("Phone number should contain 10 digits");		
					}
				}
				 else {
						throw new Validation("Phone number should start with either 9, 8 or 7");
				}
			}
			 else {
					throw new Validation("Check your entered Phone number"); 
				}
//			catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//
//			return false;
//		}
//		 
//		String[] num = phnum.split("");
//		try {
//			if(phnum.matches("[0-9]+")) {
//				if(num[0].matches("[7-9]")) {
//					if(num.length == 10) {
//						return true;
//					} else {
//						throw new Validation("Phone number should contain 10 digits");
//					}
//					
//				} else {
//					throw new Validation("Phone number should start with either 9, 8 or 7");
//				}
//				
//			} else {
//				throw new Validation("Check your entered Phone number"); 
//			}
//		} catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//
//			return false;
//		}
	}
	public void checkAccType(int acctype) {
//		try {
			if(acctype<1 || acctype > 4) {
				throw new Validation("Select valid number greater than 1 or less than 4");
				
//				return true;
			}
//			else{
//				throw new Validation("Select valid number");
//			}
//		}
//		catch(Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//			
//			return false;
//		}
	}

	public void checkEmail(String email) {
		 
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        
//		try {
			if (email == null || !pat.matcher(email).matches()) {
				throw new Validation("Email is not entered valid");
			}
//		} catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//			
//			return false;
//		}
		 
//		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
//                            "[a-zA-Z0-9_+&*-]+)*@" + 
//                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
//                            "A-Z]{2,7}$"; 
                              
//        Pattern pat = Pattern.compile(emailRegex); 
        
//		try {
//			if(pat.matcher(email).matches()) {
//				return true;
//			} else {
//				throw new Validation("Check your entered email"); 
//			}
//		} catch (Validation ex) {
//			log.log(Level.INFO, ex.getMessage()); 
//
//			return false;
//		}
	}

	public void readInput()
	{
		Scanner scan=new Scanner(System.in);
		Bank shivaniBank = new Bank();
		int choice;
		do
		{
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Main Menu\n 1.Add Account\n 2.Display All\n 3.Search By Account\n 4.Deposit\n 5.Withdrawal\n 6.Exit");
			choice = scan.nextInt();
			System.out.println("-----------------------------------------------------------------------------------");
			switch(choice)
			{ 
				case 1:
//					System.out.print("Enter Name: ");
//					String custName = scan.next();
					try {
                        System.out.print("Enter Name: ");
                        String custName = scan.next();
                        checkName(custName);
                        //account type
                        //Rest code
                        System.out.println("Select Account Type: \n 1 for Savings \n 2 for Current \n 3 for FD \n 4 for DEMAT");
    					int bankAccType = Integer.parseInt(scan.next());
    					checkAccType(bankAccType);
//    					while(true) {
//    						if(checkAccType(bankAccType)) {
//    							break;
//    						}
//    						else {
//    							System.out.print("Select Account Type < 4 ");
//    							bankAccType = Integer.parseInt(scan.next());
//    						}
//    					}
    					
    					System.out.println("Enter your Social Security Number");
    					String ssn = scan.next();

    					System.out.print("Enter Phone Number: ");
    					String custMobileNo = scan.next();
    					checkPhnum(custMobileNo);
//    					while(true) {
//    						if(checkPhnum(custMobileNo)) {
//    							break;
//    						} else {
//    							System.out.print("Enter Phone Number: ");
//    							custMobileNo = scan.next();
//    						}
//    					}

    					System.out.print("Customer Email Id: ");
    					String custEmail = scan.next();
    					checkEmail(custEmail);
//    					while(true) {
//    						if(checkEmail(custEmail)) {
//    							break;
//    						} else {
//    							System.out.print("Customer Email Id: ");
//    							custEmail = scan.next();
//    						}
//    					}
    					
    					
    					if(shivaniBank.getSSNACC().containsKey(ssn)) {
    						System.out.println("Account already exists with account number: " + shivaniBank.getAccWithSSN(ssn));
    					} else {

    						shivaniBank.createNewAccount(new BankAccount(BankMenu.accountNumber++, custName, bankAccType-1, custMobileNo, custEmail, ssn));
    						System.out.println(" -> Account created with account number: " + (accountNumber-1));
    					}


					
					}
					
					catch (Validation ex) {
//                        System.out.println(e);
            			log.log(Level.INFO, ex.getMessage()); 

                    }

                    break;
					
//					while(true) {
//						if(checkName(custName)) {
//							break;
//							
//						} else {
//							System.out.print("Enter Name: ");
//							custName = scan.next();
//						}
//					}

				case 2:

					shivaniBank.getAccountList().forEach((k, v) -> System.out.println(v));
					break;

				case 3:
					System.out.print("Enter the account number you want to search: ");
					int acn = scan.nextInt();
					if(shivaniBank.getAccountList().containsKey(acn)) {
						System.out.println(shivaniBank.getAccount(acn));
					} else {
						System.out.println("Search failed Account dooesn't exist..");
					}
					break;

				case 4:
					System.out.print("Enter Account No : ");
					acn = scan.nextInt();
					if(shivaniBank.getAccountList().containsKey(acn)) {
						shivaniBank.getAccount(acn).deposit();
					} else {
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;

				case 5:
					System.out.print("Enter Account No : ");
					acn = scan.nextInt();
					if(shivaniBank.getAccountList().containsKey(acn)) {
						shivaniBank.getAccount(acn).withdrawal();
					} else {
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;

				case 6:
					System.out.println("Good Bye... Application has ended");
					break;
			}
		}
		while(choice != 6);
	}
}