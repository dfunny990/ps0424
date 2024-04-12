package test;

import main.DateService;
import main.dto.ToolDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {


    @Test
    public void shouldCreateProperDateFromString() {
        LocalDate actual = DateService.getDateFromString("02/16/25");
        LocalDate expected= LocalDate.of(2025, 2, 16);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGiveTrueForWeekend() {
        LocalDate saturday = LocalDate.of(2024,4,13);
        LocalDate sunday = saturday.plusDays(1);
        assertTrue(DateService.isWeekend(saturday));
        assertTrue(DateService.isWeekend(sunday));
    }

    @Test
    public void shouldGiveFalseForWeekend() {
        LocalDate monday = LocalDate.of(2024,4,15);
        LocalDate tuesday = monday.plusDays(1);
        LocalDate wednesday = monday.plusDays(2);
        LocalDate thursday = monday.plusDays(3);
        LocalDate friday = monday.plusDays(4);
        assertFalse(DateService.isWeekend(monday));
        assertFalse(DateService.isWeekend(tuesday));
        assertFalse(DateService.isWeekend(wednesday));
        assertFalse(DateService.isWeekend(thursday));
        assertFalse(DateService.isWeekend(friday));
    }

    @Test
    public void shouldPassFourthJuly() {
        LocalDate fourth = LocalDate.of(2024,7, 4);
        LocalDate fifth = fourth.plusDays(1);

        assertTrue(DateService.isHoliday(fourth));
        assertFalse(DateService.isHoliday(fifth));
    }

    @Test
    public void shouldPassLaborDay() {
        LocalDate labor = LocalDate.of(2024,9,2);
        LocalDate notLabor = LocalDate.of(2025,9,2);
        assertTrue(DateService.isHoliday(labor));
        assertFalse(DateService.isHoliday(notLabor));
    }

    @Test
    public void returnsNumberOfCheckoutDays() {
        ToolDTO tool = ToolDTO.builder().build();
        LocalDate sunday = LocalDate.of(2024,4,14);
        int dayCount = 3;
        int actual = DateService.getChargeDays(sunday, tool, dayCount);
        int expected = 3;
        assertEquals(expected,actual);
    }


    @Test
    public void discountsWeekends() {
        ToolDTO tool = ToolDTO.builder()
                .weekendCharge(false).build();
        LocalDate wednesday = LocalDate.of(2024,4,18);
        int dayCount = 7;
        int actual = DateService.getChargeDays(wednesday, tool, dayCount);
        int expected = 5;
        assertEquals(expected,actual);
    }

    //now the tricky one
    @Test
    public void skipHolidayChargeOnFriday() {
        ToolDTO tool = ToolDTO.builder()
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        LocalDate checkout = LocalDate.of(2026,7,2);

        LocalDate secondCheckout = LocalDate.of(2026,7,3);
        int dayCount = 4;
        int actualLabor = DateService.getChargeDays(checkout, tool, dayCount);
        int actualNoLabor = DateService.getChargeDays(secondCheckout, tool, dayCount);

        int expected = 4;
        assertEquals(1, actualLabor);
        assertEquals(2, actualNoLabor);
    }

    @Test
    public void skipHolidayChargeOnMonday() {
        ToolDTO tool = ToolDTO.builder()
                .weekendCharge(false)
                .holidayCharge(false)
                .build();
        LocalDate checkout = LocalDate.of(2027,7,1);

        int dayCount = 3;
        int actualLabor = DateService.getChargeDays(checkout, tool, dayCount);
        int actualNoLabor = DateService.getChargeDays(checkout, tool, dayCount+1);

        assertEquals(1, actualLabor);
        assertEquals(1, actualNoLabor);
    }
}