package main.dto;

import lombok.Builder;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Builder
public class RentalAgreement {
    private ToolDTO tool;
    private int numDays;
    private LocalDate startDate;
    private LocalDate dueDate;
    private int numChargeDays;
    private double subtotal;
    private int discountPercent;
    private double discountDollars;
    private double finalCharge;

    @Override
    public String toString() {
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
        String dCharge = numberFormatter.format(tool.getDailyCharge());
        String sTotal = numberFormatter.format(subtotal);
        String dDollars = numberFormatter.format(discountDollars);
        String total = numberFormatter.format(finalCharge);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String sDate = startDate.format(dateTimeFormatter);
        String dDate = dueDate.format(dateTimeFormatter);

        return "Tool code: " + tool.getToolCode() + "\n" +
                "Tool type: " + tool.getToolType() + "\n" +
                "Tool type: " + tool.getBrand() + "\n" +
                "Rental days: " + numDays + "\n" +
                "Check out date: " + sDate + "\n" +
                "Due date: " + dDate + "\n" +
                "Daily rental charge: " + dCharge + "\n" +
                "Charge days: " + numChargeDays + "\n" +
                "Pre-discount charge: " + sTotal + "\n" +
                "Discount percent: " + discountPercent + "%\n" +
                "Discount amount: " + dDollars + "\n" +
                "Final charge: " + total;
    }
}
