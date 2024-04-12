package main;

import main.dto.RentalAgreement;
import main.dto.ToolDTO;

import java.time.LocalDate;

public class HammerBarnPOSService {
    private final ToolRepository toolRepository;

    public HammerBarnPOSService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public RentalAgreement Checkout(String toolCode, int dayCount, int discount, String checkoutDate) {
        if (discount < 0 || discount > 100) {
            throw new RuntimeException("Discount Amount (arg2) must be between 0-100");
        }
        if (dayCount < 1) {
            throw new RuntimeException("Day Amount (arg1) must be greater than 0");
        }
        LocalDate startDate = DateService.getDateFromString(checkoutDate);
//        if (startDate.isBefore(LocalDate.now())) {
//            throw new RuntimeException("Checkout Date (arg3) must be in the future");
//        }
        LocalDate dueDate = startDate.plusDays(dayCount);
        ToolDTO checkoutTool = toolRepository.getToolByToolCode(toolCode);
        int chargeDays = DateService.getChargeDays(startDate, checkoutTool, dayCount);
        double subtotal = chargeDays * checkoutTool.getDailyCharge();
        double discountDollars = subtotal * discount / 100;
        double finalCharge = subtotal - discountDollars;
        RentalAgreement result = RentalAgreement.builder()
                .tool(checkoutTool)
                .numDays(dayCount)
                .startDate(startDate)
                .dueDate(dueDate)
                .numChargeDays(chargeDays)
                .subtotal(subtotal)
                .discountPercent(discount)
                .discountDollars(discountDollars)
                .finalCharge(finalCharge)
                .build();
        System.out.println(result);
        return result;
    }

}
