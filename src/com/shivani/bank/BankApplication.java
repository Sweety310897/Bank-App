package com.shivani.bank;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.shivani.bank.interfaces.InputReader;
import com.shivani.services.ConsoleReader;
import com.shivani.services.FileReader;
import com.shivani.services.ReadPropertiesFile;
public class BankApplication {
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		logger.log(Level.INFO, "Please Enter the choice 1 for reading from console\n 2.Reading from file\n 3. Reading from Properties file");
		
		int choice = scan.nextInt();
		switch(choice) {
		
		case 1:
			InputReader consoleReader = new ConsoleReader();
			consoleReader.readInput();
			break;
		case 2:
			InputReader fileReader = new FileReader();
			fileReader.readInput();
			break;
		case 3:

			InputReader propertyReader = new ReadPropertiesFile();
			propertyReader.readInput();
			break;
		default:
			break;
		}
		
		
//		InputReader inputReader = new FileReader();
//		inputReader.readInput();

	}
}

