package test;

import main.HammerBarnPOSService;
import main.ToolRepository;
import main.dto.RentalAgreement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class HammerBarnPOSServiceTest {

    private final HammerBarnPOSService hammerBarnPOSService;
    private final ToolRepository toolRepository;


    HammerBarnPOSServiceTest() {
        this.hammerBarnPOSService = new HammerBarnPOSService(new ToolRepository());
        this.toolRepository = new ToolRepository();
    }
    //Extra test, gotta validate input date
//
//    @Test
//    public void throwExceptionForInvalidStartDate() {
//        RuntimeException exception = assertThrows(RuntimeException.class, () ->
//                hammerBarnPOSService.Checkout("JAKR",7,62,"09/03/15"));
//        assertEquals("Checkout Date (arg3) must be in the future",exception.getMessage());
//    }

    // Actual tests, just for you ;)
    @Test
    public void throwExceptionForInvalidCheckoutDays() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                hammerBarnPOSService.Checkout("JAKR",0,62,"09/03/15"));
        assertEquals("Day Amount (arg1) must be greater than 0",exception.getMessage());
    }

    @Test
    public void throwExceptionForInvalidDiscountTESTONE() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                hammerBarnPOSService.Checkout("JAKR",5,101,"09/03/15"));
        assertEquals("Discount Amount (arg2) must be between 0-100",exception.getMessage());
    }

    @Test
    public void testNumberTwo() {
        RentalAgreement agreement = hammerBarnPOSService.Checkout("LADW",3,10,"07/02/20");
        RentalAgreement expected = RentalAgreement.builder()
                .tool(toolRepository.getToolByToolCode("LADW"))
                .numDays(3)
                .startDate(LocalDate.of(2020,7,2))
                .dueDate(LocalDate.of(2020,7,5))
                .numChargeDays(2)
                .subtotal(2.98)
                .discountPercent(10)
                .discountDollars(0.298)
                .finalCharge(2.682)
                .build();
        assertThat(agreement).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    public void testNumberThree() {
        RentalAgreement agreement = hammerBarnPOSService.Checkout("CHNS",5,25,"07/02/15");
        RentalAgreement expected = RentalAgreement.builder()
                .tool(toolRepository.getToolByToolCode("CHNS"))
                .numDays(5)
                .startDate(LocalDate.of(2015,7,2))
                .dueDate(LocalDate.of(2015,7,7))
                .numChargeDays(3)
                .subtotal(5.97)
                .discountPercent(25)
                .discountDollars(1.4925)
                .finalCharge(4.4775)
                .build();
        assertThat(agreement).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testNumberFour() {
        RentalAgreement agreement = hammerBarnPOSService.Checkout("JAKD",6,0,"09/03/15");
        RentalAgreement expected = RentalAgreement.builder()
                .tool(toolRepository.getToolByToolCode("JAKD"))
                .numDays(6)
                .startDate(LocalDate.of(2015,9,3))
                .dueDate(LocalDate.of(2015,9,9))
                .numChargeDays(3)
                .subtotal(8.97)
                .discountPercent(0)
                .discountDollars(0.00)
                .finalCharge(8.97)
                .build();
        assertThat(agreement).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testNumberFive() {
        RentalAgreement agreement = hammerBarnPOSService.Checkout("JAKR",9,0,"07/02/15");
        RentalAgreement expected = RentalAgreement.builder()
                .tool(toolRepository.getToolByToolCode("JAKR"))
                .numDays(9)
                .startDate(LocalDate.of(2015,7,2))
                .dueDate(LocalDate.of(2015,7,11))
                .numChargeDays(5)
                .subtotal(14.950000000000001)
                .discountPercent(0)
                .discountDollars(0)
                .finalCharge(14.950000000000001)
                .build();
        assertThat(agreement).usingRecursiveComparison().isEqualTo(expected);

    }

    @Test
    public void testNumberSix() {
        RentalAgreement agreement = hammerBarnPOSService.Checkout("JAKR",4,50,"07/02/20");
        RentalAgreement expected = RentalAgreement.builder()
                .tool(toolRepository.getToolByToolCode("JAKR"))
                .numDays(4)
                .startDate(LocalDate.of(2020,7,2))
                .dueDate(LocalDate.of(2020,7,6))
                .numChargeDays(1)
                .subtotal(2.99)
                .discountPercent(50)
                .discountDollars(1.495)
                .finalCharge(1.495)
                .build();
        assertThat(agreement).usingRecursiveComparison().isEqualTo(expected);
    }

}