package model;

import java.io.FileWriter;
import java.io.IOException;

public class Month {
	private String month;
	private String year;
	private double expense;
	private double grossProfit;
	private int mileage;
	private double netProfit;

	/** Constructor */
	public Month(String month, String year, double expense, double grossProfit, int mileage) {
		this.month = month;
		this.year = year;
		this.expense = expense;
		this.grossProfit = grossProfit;
		this.mileage = mileage;
		netProfit = grossProfit - expense;
	}

	/** Getters */

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public double getExpense() {
		return expense;
	}

	public double getGrossProfit() {
		return grossProfit;
	}

	public int getMileage() {
		return mileage;
	}

	/** Setters */

	/** Other Member Functions */

	public void monthOut(String fOut) throws IOException {
		try (FileWriter writer = new FileWriter(fOut, true)) {
			writer.write("End of Month Report \nMonth of " + month + "\n");
			writer.write("Gross Profit: " + grossProfit + "\n");
			writer.write("Expense: " + expense + "\n");
			writer.write("Net Profit: " + netProfit + "\n");
			writer.write("Mileage Total: " + mileage + "\n \n");
		}
	}
}
