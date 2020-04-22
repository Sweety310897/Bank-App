package com.shivani.validations;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shivani.bank.exceptions.AccountDetailsException;

public class InputValidations {
	LogManager lgmngr = LogManager.getLogManager(); 
	Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	
	public void checkName(String name) {
        if (name == null || !name.matches("[a-zA-Z]+")) {
            throw new AccountDetailsException("Please enter your name in valid format");
        } 
	}
	public void checkPhoneNum(String phoneNum) {
		
		Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");		  
        Matcher match = pattern.matcher(phoneNum); 
        if((match.find() && match.group().equals(phoneNum))) {
			log.log(Level.INFO, "Valid Mobile Number");
        }
		else {
			throw new AccountDetailsException("Check your entered Phone number"); 
		}
	}
	public void checkAccType(int accType) {
			if(accType<1 || accType > 4) {
				throw new AccountDetailsException("Select valid number greater than 1 or less than 4");
			}
	}

	public void checkEmail(String email) {
		 
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pattern = Pattern.compile(emailRegex);
		if (email == null || !pattern.matcher(email).matches()) {
			throw new AccountDetailsException("Check your entered email");
		}
	}

}
