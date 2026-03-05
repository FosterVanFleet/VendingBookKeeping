package model;

import java.io.FileWriter;
import java.io.IOException;

public class Year {

	/** Data Members */
	private int year;
	private double expense;
	private double grossProfit;
	private double netProfit;
	private int mileage;

	// TODO could add these to get specifics at later date.
	// private double foodLoss;
	// private double inventory;
	// private double repair;
	// private double refund;
	// private double commissionFee;

	// private double profitLSI;
	// private double profitGRS;

	/** Constructor */
	public Year(int year, double expense, double grossProfit, int mileage) {
		this.year = year;
		this.expense = expense;
		this.grossProfit = grossProfit;
		netProfit = Math.round(grossProfit - expense);
		this.mileage = mileage;
	}

	/** Getters */

	public int getYear() {
		return year;
	}

	public double getExpense() {
		return expense;
	}

	public double getGrossProfit() {
		return grossProfit;
	}

	public double getNetProfit() {
		return netProfit;
	}

	/** Setters */

	/** Other Member Functions */

	public void yearOut(String fOut) throws IOException {
		try (FileWriter writer = new FileWriter(fOut, true)) {
			writer.write("End of Year " + year + "\n");
			writer.write("Gross Profit: " + grossProfit + "\n");
			writer.write("Expense: " + expense + "\n");
			writer.write("Net Profit: " + netProfit + "\n");
			writer.write("Mileage Total: " + mileage + "\n \n");
		}
	}
}