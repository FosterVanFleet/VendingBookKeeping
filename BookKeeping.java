
/**
 * @author Foster VanFleet
 * Last modified 11/1/2025
 * 
 * A program to manage the finances of my vending machine business.
 * Including Expenses, Profits, Food Waste, calculating End of Month, 
 * End of Quarter, and End of Year. Writes everything to a file 
 * and reads from a file for all endOf functions.
 */
import model.CashCollection;
import model.Expense;
import model.Food;
import model.Month;
import model.Year;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class BookKeeping {
	// Shared Scanner.
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws ParseException, IOException {
		// Run through the program at least once.
		System.out.println("Welcome to your personalized Book Keeping Program.");
		userOptions();

		// Loop to run as many times as desired.
		while (true) {
			System.out.println("Would you like to complete another action? Yes/No");
			String yesOrNo = input.nextLine().trim();

			// Check for valid response.
			boolean yesOrNoIsVal = isYNvalid(yesOrNo);
			// If not valid loop until valid response.
			while (!yesOrNoIsVal) {
				System.out.println("Invalid input, answer must be Yes/No.");
				System.out.println("Would you like to make complete another action? Yes/No");
				// Update Response and if its valid.
				yesOrNo = input.nextLine();
				yesOrNoIsVal = isYNvalid(yesOrNo);
			}
			// If yes run userOptions else exit Loop and program.
			if (yesOrNo.equalsIgnoreCase("Yes")) {
				userOptions();
			} else {
				System.out.println("Thank you for using your personalized Book Keeping Program.");
				break;
			}
		}
		input.close();
	}

	/**
	 * Method userOptions helps control the flow of the program. Takes input from a
	 * user and calls the method they desire.
	 * 
	 * @throws ParseExxception, IOException.
	 */
	public static void userOptions() throws ParseException, IOException {
		System.out.println(
				"Please select one of the following options, \n" + "type in the number associated with that option. ");
		System.out.println("1 - Write a new entry \n" + "2 - Write a new Expense \n" + "3 - End of Month \n"
				+ "4 - End of Year \n" + "5 - Food Waste \n");

		// Parse for int to consume new line character.
		int select = Integer.parseInt(input.nextLine().trim());

		// Catch user selection error.
		if (select > 5 || 0 > select) {
			do {
				System.out.println("please select one of the following options, \n "
						+ "type in the number associated with that option. \n");
				System.out.println("1 - Write a new entry \n" + "2 - Write a new expense \n" + "3 - End of Month \n"
						+ "4 - End of Year \n" + "5 - Food Waste \n");

				// Update the user selection.
				select = Integer.parseInt(input.nextLine().trim());
			} while (select > 5 || 0 > select);
		}
		// Use selection to control where we go in the program.
		if (select == 1) {
			newEntry();
		} else if (select == 2) {
			newExpense();
		} else if (select == 3) {
			endOfMonth();
		} else if (select == 4) {
			endOfYear();
		} else {
			foodWaste();
		}
	}

	/*
	 * method newEntry collects all the input to make a entry object calls the
	 * method entryOut, to write the entry to a file.
	 * 
	 * @throws ParseException, IOException
	 */
	public static void newEntry() throws ParseException, IOException {
		System.out.println("You chose: Write a new entry.");
		// Get File path
		System.out.println("File name? ex. vendingReport25.txt");
		String fP = input.nextLine();

		// Get Date.
		String formDate = getDate();

		// Get location input.
		System.out.println("Vendor Location, LSI, GRS, or Coin Deposit");
		String loc = input.nextLine();
		loc = loc.toUpperCase();

		// Check for valid location.
		boolean locIsValid = isLocValid(loc);
		if (!(locIsValid)) {
			do {
				// Get location input.
				System.out.println("Invalid Vendor Location. Please enter a valid location LSI or GRS.");
				loc = input.nextLine();
				// Update locIsVailid.
				locIsValid = (loc.equalsIgnoreCase("GRS") || loc.equalsIgnoreCase("LSI"));
				// Repeat loop until valid entry.
			} while (!(locIsValid));
		}

		// Get mileage start and end.
		System.out.println("Milage Start");
		int mS = Integer.parseInt(input.nextLine().trim());

		System.out.println("Milage End");
		int mE = Integer.parseInt(input.nextLine().trim());

		// Check for valid mileage.
		boolean mIsValid = isMileVal(mS, mE);
		if (!mIsValid) {
			do {
				// Get a valid mileage start and end.
				System.out.println(
						"Milage Start must be less than Milage End, and Mileage Total must be less than 200. \n "
								+ "please enter valid milage.");
				System.out.println("Milage Start");
				mS = Integer.parseInt(input.nextLine().trim());

				System.out.println("Mileage End");
				mE = Integer.parseInt(input.nextLine().trim());

				// Update mIsValid.
				mIsValid = isMileVal(mS, mE);
			} while (!(mIsValid));
		}

		// get gP
		System.out.println("Gross Profit");
		double gP = Double.parseDouble(input.nextLine().trim());

		// Confirm the data and confirm the action of entry out.
		System.out.println("The data you entered is");
		System.out.println("Date: " + formDate);
		System.out.println("Location: " + loc);
		System.out.println("Gross Profit: " + gP);
		System.out.println("Mileage Start - Mileage End: " + mS + " - " + mE);
		System.out.println("File to write to: " + fP);

		System.out.println("Is this information correct? (Yes/No) ");
		String confirm = input.nextLine();

		boolean confirmIsVal = isYNvalid(confirm);
		while (!(confirmIsVal)) {
			System.out.println("Invalid response. input must be Yes or No");
			confirm = input.nextLine();
			confirmIsVal = isYNvalid(confirm);
		}

		if (confirm.equalsIgnoreCase("Yes")) {
			CashCollection collectionEntry = new CashCollection(loc, formDate, gP, mS, mE, fP);
			collectionEntry.entryOut();
		} else if (confirm.equalsIgnoreCase("No")) {
			System.out.println("You selected no. Please put in the correct data. ");
			newEntry();
		}
	}

	/**
	 * Method newExpense collects data to make a expense object and calls the
	 * expenseOut method.
	 * 
	 * @throws IOException, ParseException
	 */
	public static void newExpense() throws IOException, ParseException {
		System.out.println("You selected new Expense.");
		while (true) {
			// Get filePath.
			System.out.println("File name? ex. vendingReport25.txt");
			String fP = input.nextLine();

			// expense Date.
			String formDate = getDate();

			// expense location.
			System.out.println("Location of expense? ");
			String eLoc = input.nextLine();

			// expense cost.
			System.out.println("Cost of expense? ");
			double eCost = Double.parseDouble(input.nextLine().trim());

			// initialize variables.
			String yesOrNo = "";
			boolean yesOrNoIsValid = false;

			// Loop to get valid Yes or No.
			while (!(yesOrNoIsValid)) {
				System.out.println("Was there mileage? (Yes/No)");
				yesOrNo = input.nextLine().trim();

				// Update yesOrNoIsValid.
				yesOrNoIsValid = isYNvalid(yesOrNo);
				if (!(yesOrNoIsValid)) {
					System.out.println("Invalid input. Please enter Yes or No.");
				}
				// Repeat loop until valid entry.
			}

			int mS = 0;
			int mE = 0;
			// Conditional control of mileage or no
			if (yesOrNo.equalsIgnoreCase("Yes")) {

				System.out.println("Mileage Start");
				mS = Integer.parseInt(input.nextLine().trim());

				System.out.println("Mileage End");
				mE = Integer.parseInt(input.nextLine().trim());

				boolean mIsValid = isMileVal(mS, mE);
				while (!(mIsValid)) {

					// Get a valid mileage start and end.
					System.out.println("Milage start must be less than Milage End, please enter valid milage.");
					System.out.println("Milage Start");
					mS = Integer.parseInt(input.nextLine().trim());

					System.out.println("Mileage End");
					mE = Integer.parseInt(input.nextLine().trim());

					// Update mIsVal.
					mIsValid = isMileVal(mS, mE);
				}
			}

			// Type of expense.
			System.out.println("Type of expense? (Inventory, Repair, Commision, Refund)");
			String eType = input.nextLine();

			// Confirm the data and confirm the action of entry out.
			System.out.println("The data you entered is: ");

			System.out.println("Date: " + formDate);
			System.out.println("Location: " + eLoc);
			System.out.println("Expense Cost: " + eCost);
			System.out.println("Expense Type: " + eType);
			System.out.println("Mileage: " + mS + " - " + mE);
			System.out.println("File Path: " + fP);

			System.out.println("Is this information correct? (Yes/No) ");
			String confirm = input.nextLine();

			// Check if confirm is valid input.
			boolean confirmIsVal = isYNvalid(confirm);
			while (!(confirmIsVal)) {
				System.out.println("Invalid response. input must be Yes or No.");
				confirm = input.nextLine();
				confirmIsVal = isYNvalid(confirm);
			}
			// If info is confirmed output data to file and break.
			if (confirm.equalsIgnoreCase("Yes")) {
				Expense expenseEntry = new Expense(eLoc, formDate, eType, eCost, mS, mE, fP);
				expenseEntry.expenseOut();
				break;
			} else if (confirm.equalsIgnoreCase("No")) {
				// Rerun loop to get the correct data.
				System.out.println("You selected no. Please put in the correct data. ");
			}
		}
		return;
	}

	/**
	 * Method endOfMonth reads from a file and writes to a different file. It
	 * calculates and write; gross profit, expenses, net profit, and mileage to a
	 * file.
	 * 
	 * @throws IOException
	 */
	public static void endOfMonth() throws IOException {
		System.out.println("You selected End of Month.");

		// Variable declaration.
		String fIn;
		String fOut;
		int month;
		String monthStr;
		String year;

		// loop until break from user confirms correct information.
		while (true) {
			// Prompt for user input and collect input.
			System.out.println(
					"What is the file name containing the data you are intrested in? ex. VendingReport2025.txt ");
			fIn = input.nextLine();

			System.out.println("What year? (2025)");
			year = input.nextLine();

			System.out.println("What Month? (01, 02, 03, ...)");
			month = input.nextInt();
			// Format for proper file reading
			monthStr = String.format("%02d", month);

			// Consume new line character.
			input.nextLine();

			System.out.println("What file name would you like to write to? (endOfJan.txt)");
			fOut = input.nextLine().trim();

			// Confirm information provided.
			System.out.println("The information you provided is...");
			System.out.println("File to read from: " + fIn);
			System.out.println("Month: " + monthStr);
			System.out.println("File to write to: " + fOut);
			System.out.println("Is this information correct? (Yes/No)");
			String confirm = input.nextLine().trim();
			boolean confirmIsVal = isYNvalid(confirm);

			// if not valid loop until valid yes or no.
			while (!(confirmIsVal)) {
				System.out.println("Invalid response. input must be Yes or No");
				confirm = input.nextLine();
				confirmIsVal = isYNvalid(confirm);
			}

			if (confirm.equalsIgnoreCase("Yes")) {
				// break out of input loop.
				break;
			} else if (confirm.equalsIgnoreCase("No")) {
				// Loop continues.
				System.out.println("You selected no. Please put in the correct data. ");
			}
		}

		// Create a file reader.
		BufferedReader reader = new BufferedReader(new FileReader(fIn));

		// Variable initialization/ declaration.
		double totalProfit = 0;
		double totalExpense = 0;
		int mileage;
		int totalMileage = 0;
		String collectOrExpense;

		// Iterate down through the file until end is reached.
		while ((collectOrExpense = reader.readLine()) != null) {
			collectOrExpense = collectOrExpense.trim();

			// Loop through file and add Profits.
			if (collectOrExpense.equals("Cash Collection")) {
				String dateLine = reader.readLine();
				// Null check before trim.
				if (dateLine != null)
					dateLine = dateLine.trim();

				// Check if the line is from the correct year and month.
				if (dateLine.startsWith("Date: " + year + "-" + monthStr)) {
					// Skip Location line.
					reader.readLine();

					// Store the profit line
					String profitLine = reader.readLine();

					// null check before trim
					if (profitLine != null) {
						profitLine = profitLine.trim();

						// Separate the string and profit amount.
						if (profitLine.startsWith("Gross Profit: ")) {
							double profit = Double.parseDouble(profitLine.substring("Gross Profit: ".length()));
							totalProfit += profit;
						}
					}
					// Skip to mileageLine.
					reader.readLine();
					// Get the mileage line separate String and mileage, add to total mileage.
					String mileageLine = reader.readLine();
					// null check before trim.
					if (mileageLine != null) {
						mileageLine = mileageLine.trim();
						mileage = Integer.parseInt(mileageLine.substring("Trip Mileage: ".length()));
						totalMileage += mileage;
					}
					// else this record is not of the correct month and year.
				} else {
					// Skip the rest of record (6 lines).
					for (int i = 0; i < 6; i++) {
						reader.readLine();
					}
				}
				// else its an Expense.
			} else if (collectOrExpense.equals("Expense")) {
				String dateLine = reader.readLine();
				if (dateLine.startsWith("Date: " + year + "-" + month)) {
					// Skip location line.
					reader.readLine();

					// Store the expense Line.
					String expenseLine = reader.readLine();
					// null check before trim.
					if (expenseLine != null)
						expenseLine = expenseLine.trim();

					// Separate the string and the cost.
					if (expenseLine != null && expenseLine.startsWith("Expense Cost: ")) {
						double expense = Double.parseDouble(expenseLine.substring("Expense Cost: ".length()));
						// Add the expense to the running total.
						totalExpense += expense;
					}

					// Skip to the mileage line.
					reader.readLine();
					reader.readLine();

					// Get mileage line separate String and mileage, add to total mileage.
					String mileageLine = reader.readLine();
					mileageLine = mileageLine.trim();
					mileage = Integer.parseInt(mileageLine.substring("Trip Mileage: ".length()));
					totalMileage += mileage;
					// else this record is not of the correct month and year.
				} else {
					// Skip the rest of the record (7 lines).
					for (int i = 0; i < 7; i++) {
						reader.readLine();
					}
				}

			}
		}
		// Create month object, call member function monthOut to print out the totals.
		Month endOfMonth = new Month(monthStr, year, totalExpense, totalProfit, totalMileage);
		endOfMonth.monthOut(fOut);
		// Close the reader.
		reader.close();

	}

	/**
	 * Method endOfYear reads from a file and writes to a different file. Calculates
	 * and writes the total; expense's profits, and mileage from a single year.
	 * Similar to endOfMonth.
	 * 
	 * @throws IOException
	 */
	public static void endOfYear() throws IOException {
		System.out.println("You selected End of Year. ");

		// variable declarations.
		String fIn;
		String fOut;
		String yearStr;
		String confirm;
		int year;

		// loop until we break upon having correct information.
		while (true) {

			// Prompt and collect input for file in, file out, and year.
			System.out.println("What is the file which contains the desired data? ex. vendingReport2025.txt ");
			fIn = input.nextLine();

			System.out.println("What is the file which you wish to write to? ex. vendEndOfYear2025.txt");
			fOut = input.nextLine();

			System.out.println("What is the year this is for? ex. 2025 ");
			yearStr = input.nextLine().trim();
			year = Integer.parseInt(yearStr);

			// Return the provided info and ask user to confirm.
			System.out.println("The information you provided is: ");
			System.out.println("File to read from: " + fIn);
			System.out.println("File to write to: " + fOut);
			System.out.println("Year:" + year);

			System.out.println("Is all of the information correct? (Yes/No)");
			confirm = input.nextLine();
			boolean confirmIsVal = isYNvalid(confirm);

			// check if confirmation was a valid yes or no. loop until we get a valid
			// confirmation.
			while (!(confirmIsVal)) {
				System.out.println("Invalid response. input must be Yes or No.");
				confirm = input.nextLine();
				confirmIsVal = isYNvalid(confirm);
			}

			// if confirm is yes break loop.
			if (confirm.equalsIgnoreCase("Yes")) {
				break;
				// else loop again.
			} else {
				System.out.println("You selected no. Please input the correct data.");
			}
		}

		// Create a file reader.
		BufferedReader reader = new BufferedReader(new FileReader(fIn));

		// Variable declaration/ initialization.
		String collectOrExpense;
		double totalProfit = 0;
		double totalExpense = 0;
		int mileage;
		int totalMileage = 0;

		// Iterate down through the file until end is reached.
		while ((collectOrExpense = reader.readLine()) != null) {
			collectOrExpense = collectOrExpense.trim();
			// Loop through file and add Profits.
			if (collectOrExpense.equals("Cash Collection")) {
				String dateLine = reader.readLine();
				dateLine = dateLine.trim();

				// Check if entry is of the correct year.
				if (dateLine.startsWith("Date: " + year)) {
					// Skip Location line.
					reader.readLine();

					// Store the profit line.
					String profitLine = reader.readLine();
					profitLine = profitLine.trim();

					// Separate the string and profit amount.
					if (profitLine != null && profitLine.startsWith("Gross Profit: ")) {
						double profit = Double.parseDouble(profitLine.substring("Gross Profit: ".length()));
						totalProfit += profit;
					}
					// Skip to mileageLine.
					reader.readLine();

					// Get the mileage line separate String and mileage, add to total mileage.
					String mileageLine = reader.readLine();
					mileageLine = mileageLine.trim();
					mileage = Integer.parseInt(mileageLine.substring("Trip Mileage: ".length()));
					totalMileage += mileage;
				}

				// else its an Expense.
			} else if (collectOrExpense.equals("Expense")) {
				// Read date and check if its the write year
				String dateLine = reader.readLine();
				dateLine = dateLine.trim();
				if (dateLine.startsWith("Date: " + year)) {
					// Skip location line.
					reader.readLine();
					// Store the expense Line.
					String expenseLine = reader.readLine().trim();
					// Separate the string and the cost.
					if (expenseLine != null && expenseLine.startsWith("Expense Cost: ")) {
						double expense = Double.parseDouble(expenseLine.substring("Expense Cost: ".length()));
						// add the expense.
						totalExpense += expense;
					}

					// Skip to the mileage line.
					reader.readLine();
					reader.readLine();
					// Get mileage line separate String and mileage, add to total mileage.
					String mileageLine = reader.readLine().trim();
					mileage = Integer.parseInt(mileageLine.substring("Trip Mileage: ".length()));
					totalMileage += mileage;
				}
			}
		}

		// Create the endOfYear object. call the Member function yearOut.
		Year endOfYear = new Year(year, totalExpense, totalProfit, totalMileage);
		endOfYear.yearOut(fOut);

		// Close the reader.
		reader.close();
	}

	public static void foodWaste() throws IOException, ParseException {
		System.out.println("You selected Food Waste.");

		// Variable initialization/ declarations.
		String fOut;
		String foodWasteFile;
		int numOfEntries;
		String date;
		double totalExpense = 0;
		String confirm;

		while (true) {
			// Prompt for and get user input for fOut.
			System.out.println("What file would you like to write the expense to? ex. vendingReport2025.txt");
			fOut = input.nextLine();
			if (fOut != null)
				fOut = fOut.trim();

			// Prompt for and get user input for foodWasteFile.
			System.out.println("What file would you like to write the food waste entries to? ex. foodWasteDec2025.txt");
			foodWasteFile = input.nextLine();
			if (foodWasteFile != null)
				foodWasteFile = foodWasteFile.trim();

			// Prompt for and get date.
			date = getDate();

			// Prompt for and get number of entries.
			System.out.println("How many entries do you have? ");
			numOfEntries = input.nextInt();
			// Move scanner ahead to prevent null entries.
			input.nextLine();

			// Confirm correct information.
			System.out.println("The information you provided is: ");
			System.out.println("File to write the expense to: " + fOut);
			System.out.println("File to write the food waste to: " + foodWasteFile);
			System.out.println("The date of entry: " + date);
			System.out.println("The number of food waste entries: " + numOfEntries);

			System.out.println("Is the information above correct? (Yes/No)");
			confirm = input.nextLine();
			boolean confirmIsVal = isYNvalid(confirm);
			// check if confirmation was a valid yes or no. loop until we get a valid
			// confirmation.
			while (!(confirmIsVal)) {
				System.out.println("Invalid response. input must be Yes or No.");
				confirm = input.nextLine();
				confirmIsVal = isYNvalid(confirm);
			}

			// if confirm is yes break loop.
			if (confirm.equalsIgnoreCase("Yes")) {
				break;
				// else loop again.
			} else {
				System.out.println("You selected no. Please input the correct data.");
			}
		} // End of first while loop.

		// initialize an array to the size of the number of entries.
		Food entries[] = new Food[numOfEntries];

		// Labels for the food waste
		try (FileWriter writer = new FileWriter(foodWasteFile, true)) {
			writer.write("\n" + "Food Waste Entry: " + date + "\n");
			writer.write("Snack:              | Price | Amount | Total Cost | \n");
			writer.write("---------------------------------------------------");
		}

		System.out.println("Input each entry as prompted. ");
		// for loop get every entry and create an object for each.
		for (int i = 0; i < numOfEntries; i++) {
			// Prompt and get snack from user.
			System.out.println("What sanck? ex. Cheetos. ");
			String snack = input.nextLine();

			// Prompt and get price from user.
			System.out.println("What is the cost of a single unit of the snack? ex. a bag of cheetos = 0.40. ");
			double price = input.nextDouble();
			input.nextLine();

			// Prompt and get amount from user.
			System.out.println("How many is there?");
			int amount = input.nextInt();
			input.nextLine();

			// Create an object of each food and add it to the array.
			Food entry = new Food(snack, price, amount);

			entry.entryOut(foodWasteFile);
			entries[i] = entry;
		}

		// Iterate through the array and find the total expense of the food waste.
		for (Food entry : entries) {
			totalExpense = totalExpense + entry.getTotalCost();
		}

		// Print out the information in the format of an expense so it is used when
		// calculating in endOfMonth and endOfYear.
		try (FileWriter writer = new FileWriter(fOut, true)) {
			// Separate with another line
			writer.write("\n");

			// Write the larger accumulative expense.
			writer.write("Expense \n");
			writer.write("Date: " + date + "\n");
			writer.write("Location: N/A \n");
			writer.write("Expense Cost: " + totalExpense + "\n");
			writer.write("Expense Type: Food Waste \n");
			writer.write("Mileage Start - Mileage End: N/A \n");
			writer.write("Trip Mileage: 0 \n\n");
		}
	}

	/**
	 * Method getDate is a helper method to get the date from user input. Check if
	 * the date is valid, check for exceptions, and format the date. Returns a
	 * String of the properly formated date
	 * 
	 * @throws ParseException
	 */
	public static String getDate() throws ParseException {
		String formDate = "";

		// Create date format.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);

		// Check if we have not received input. loop to get input.
		while (true) {
			System.out.println("Date of Entry, formatted yyyy-MM-dd");
			String date = input.nextLine().trim();

			try {
				Date dE = sdf.parse(date);
				formDate = sdf.format(dE);
				break; // valid date, exit loop
				// if invalid date format try again.
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please try again date must be yyyy-MM-dd");
			}
		}
		return formDate;
	}

	/**
	 * Method isYNvalid, checks a string for a valid Yes or no response.
	 * 
	 * @param String yesOrNo
	 * @returns true if the string param is either yes or no.
	 */
	public static boolean isYNvalid(String yesOrNo) {
		return (yesOrNo.equalsIgnoreCase("Yes") || yesOrNo.equalsIgnoreCase("No"));
	}

	/**
	 * Method isLocValid, checks if a String is any of the acceptable options.
	 * 
	 * @param String loc
	 * @returns true if the String param is GRS or LSI.
	 */
	public static boolean isLocValid(String loc) {
		return (loc.equalsIgnoreCase("GRS") || loc.equalsIgnoreCase("LSI")) || loc.equalsIgnoreCase("Coin Deposit");
	}

	/**
	 * Method isMileVal, checks if start < end and end - start is < 200
	 * 
	 * @param mS, mE. Mileage Start, Mileage End.
	 * @returns true if mileage is valid.
	 */
	public static boolean isMileVal(int mS, int mE) {
		// Check if mileage is valid. if not do while loop.
		return (mS < mE && mE - mS < 200);
	}

}