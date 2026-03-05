package model;

import java.io.FileWriter;
import java.io.IOException;

public class Food {

	/** Data Members */
	private String snack;
	private double price;
	private int amount;
	private double totalCost;

	/** Constructor */
	public Food(String snack, double price, int amount) {
		this.snack = snack;
		this.price = price;
		this.amount = amount;
		totalCost = price * amount;

	}

	/** Getters */
	public String getSnack() {
		return snack;
	}

	public double getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public double getTotalCost() {
		return totalCost;
	}

	/** Setters */
	public void setSnack(String snack) {
		this.snack = snack;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setTotalCost() {
		totalCost = price * amount;
	}

	/** Other Member Functions */

	/**
	 * method entryOutOne writes one singular item of food waste. Not in the format
	 * of an expense. Format is 
	 * Snack:   | price | amount | Total Cost | 
	 * Cheetos: | 0.15  | 10 | 1.50 |
	 * 
	 * @param fOut  the file to write to.
	 * @param date, a string in a formatted date.
	 * @throws IOException if FileWriter runs into an error.
	 */
	public void entryOut(String fOut) throws IOException {
		// Initialize variable, used to align each snack entry.
		int format = 20 - snack.length();
		String formatSpacing = " ".repeat(format);
		String formatedCost = String.format("%.2f", totalCost);
		// Write to file.
		try (FileWriter writer = new FileWriter(fOut, true)) {
			writer.write(snack + ":" + formatSpacing + "|  " + price + "  |   " + amount + "   |    " + formatedCost
					+ "    |\n");
		}
	}

	public void expenseOut(String fOut, String date, double expenseCost) throws IOException {
		try (FileWriter writer = new FileWriter(fOut, true)) {
			writer.write("Expense \n");
			writer.write("Date: " + date + "\n");
			writer.write("Location: N/A \n");
			writer.write("Expense Cost: " + expenseCost + "\n");
			writer.write("Expense Type: Food Waste \n");
			writer.write("Mileage Start - Mileage End: N/A \n");
			writer.write("Trip Mileage: 0 \n\n");
		}
	}
}
