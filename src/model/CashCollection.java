package model;
import java.io.FileWriter;
import java.io.IOException;

/** 
 *  Class for collection of profits from machines. 
 *  Contains member function to write to a file. 
 */
public class CashCollection {

	/** Data members */
	private String location;
	private String date;
	private double profit;
	private int mileageStart;
	private int mileageEnd;
	private String filePath;
	
	/** Constructor */
	public CashCollection(String location, String date, double profit, int mileageStart, int mileageEnd, String filePath) {
		this.location = location;
		this.date = date;
		this.profit = profit;
		this.mileageStart = mileageStart;
		this.mileageEnd = mileageEnd;
		this.filePath = filePath;
	}
	
	/** Getters. */
	public String getLocation() {
		return location;
	}
	
	public String getDate() {
		return date;
	}
	
	public double getProfit() {
		return profit;
	}
	
	public int getMileageStart() {
		return mileageStart;
	}
	
	public int getMileageEnd() {
		return mileageEnd;
	}
	
	// finds total mileage.
	public int getMileageTotal() {
		return mileageEnd - mileageStart;
	}
	
	public String filePath() {
		return filePath;
	}
	
	/** Setters */
	
	/** Other Member functions */
	
	// entryOut prints object data to file. @throws IOException 
	public void entryOut() throws IOException {
		try (FileWriter writer = new FileWriter(filePath, true)) {
			writer.write("Cash Collection \n");
			writer.write("Date: " + date + "\n");
			writer.write("Location: " + location + "\n");
			writer.write("Gross Profit: " + profit + "\n");
			writer.write("Mileage Start - Mileage End: " + mileageStart + " - " + mileageEnd + "\n");
			writer.write("Trip Mileage: " + (mileageEnd - mileageStart)+ "\n\n");
		}
	}

	
	
	
}
