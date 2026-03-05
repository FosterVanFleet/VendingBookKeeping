package model;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Class for Expenses
 * Contains Member function to write to a file.
 */
public class Expense {

	/** Data Members */
	private String location;
	private String date;
	private String type;
	private BigDecimal expenseAmount;
	private int mileageStart;
	private int mileageEnd;
	private int mileage;
	private String filePath;

	/** Constructor */
	public Expense(String location, String date, String type, BigDecimal expenseAmount, int mileageStart, int mileageEnd, String filePath) {
		this.location = location;
		this.date = date;
		this.type = type;
		this.expenseAmount = expenseAmount;
		this.mileageStart = mileageStart;
		this.mileageEnd = mileageEnd;
		this.mileage = mileageEnd - mileageStart;
		this.filePath = filePath;
	}
	
	public Expense(String location, String date, String type, BigDecimal expenseAmount, int mileage) {
		this.location = location;
		this.date = date;
		this.type = type;
		this.expenseAmount = expenseAmount;
		this.mileage = mileage;
	}
	
	/** Getters */
	public String getLocation() {
		return location;
	}
	
	public String getDate() {
		return date;
	}
	
	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}
	
	public int getMileageStart() {
		return mileageStart;
	}
	
	public int getMileageEnd() {
		return mileageEnd;
	}
	
	public int getMileageTotal() {
		return mileage;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	/** Setters */
	
	/** Other Member functions */
	
	
	//Method expenseOut, writes the data from newExpense to a file.	  
	// @throws IOException
	public void expenseOut() throws IOException {
		try (FileWriter writer = new FileWriter(filePath, true)) {
			writer.write("Expense \n");
			writer.write("Date: " + date + "\n");
			writer.write("Location: " + location + "\n");
			writer.write("Expense Cost: " + expenseAmount + "\n");
			writer.write("Expense Type: " + type + "\n");
			writer.write("Mileage Start - Mileage End: " + mileageStart + " - " + mileageEnd + "\n");
			writer.write("Trip Mileage: " + (mileageEnd - mileageStart) + "\n\n");
		}
	}

}
