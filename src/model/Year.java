package model;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Year {

	/** Data Members */
	private int year;
	private BigDecimal expense;
	private BigDecimal grossProfit;
	private BigDecimal netProfit;
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
	public Year(int year, BigDecimal expense, BigDecimal grossProfit, int mileage) {
		this.year = year;
		this.expense = expense;
		this.grossProfit = grossProfit;
		netProfit = grossProfit.subtract(expense);
		this.mileage = mileage;
	}

	/** Getters */

	public int getYear() {
		return year;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public BigDecimal getNetProfit() {
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