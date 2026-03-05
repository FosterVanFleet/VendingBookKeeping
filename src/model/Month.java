package model;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Month {
	private String month;
	private String year;
	private BigDecimal expense;
	private BigDecimal grossProfit;
	private int mileage;
	private BigDecimal netProfit;

	/** Constructor */
	public Month(String month, String year, BigDecimal expense, BigDecimal grossProfit, int mileage) {
		this.month = month;
		this.year = year;
		this.expense = expense;
		this.grossProfit = grossProfit;
		this.mileage = mileage;
		netProfit = grossProfit.subtract(expense);
	}

	/** Getters */

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public BigDecimal getGrossProfit() {
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
