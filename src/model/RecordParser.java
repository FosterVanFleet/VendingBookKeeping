package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class RecordParser {

    public static Object parseNextRecord(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        if (firstLine == null) return null;

        firstLine = firstLine.trim();

        List<String> recordLines = new ArrayList<>();
        recordLines.add(firstLine);

        // Read until blank line
        String line;
        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
            recordLines.add(line.trim());
        }

        if (firstLine.equals("Cash Collection")) {
            return parseCashCollection(recordLines);
        } else if (firstLine.equals("Expense")) {
            return parseExpense(recordLines);
        }

        return null;
    }

    private static CashCollection parseCashCollection(List<String> lines) {
        String date = extractValue(lines.get(1));
        String location = extractValue(lines.get(2));
        BigDecimal grossProfit = new BigDecimal(extractValue(lines.get(3)));
        int mileage = Integer.parseInt(extractValue(lines.get(5)));
        
        return new CashCollection(location, date, grossProfit, mileage);
    }

    private static Expense parseExpense(List<String> lines) {
        String date = extractValue(lines.get(1));
        String location = extractValue(lines.get(2));
        BigDecimal cost = new BigDecimal(extractValue(lines.get(3)));
        String type = extractValue(lines.get(4));
        int mileage = Integer.parseInt(extractValue(lines.get(6)));

        return new Expense(location, date, type, cost, mileage);
    }

    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 2);
    }
}