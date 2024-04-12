package main;

import main.dto.ToolDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateService {

    public static LocalDate getDateFromString(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return LocalDate.parse(input, dateTimeFormatter);
    }

    public static boolean isWeekend(LocalDate dayOfWeek) {
        return (dayOfWeek.getDayOfWeek() == DayOfWeek.SATURDAY ||
                dayOfWeek.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public static boolean isHoliday(LocalDate day) {
        boolean laborDay = day.getMonth() == Month.SEPTEMBER &&
            day.getDayOfWeek() == DayOfWeek.MONDAY &&
            day.getDayOfMonth() < 8;
        boolean fourth = (day.getMonth() == Month.JULY &&
                day.getDayOfMonth() == 4);
        return laborDay || fourth;
    }

    //This Still Isn't Right..... Holiday on weekends is broken
    public static int getChargeDays(LocalDate startDate, ToolDTO tool, int dayCount) {
        int countDays = 0;
        LocalDate currentDate = startDate.plusDays(1);
        for (int i = 0; i < dayCount; i++) {

            if (!isWeekend(currentDate) && !isHoliday(currentDate)) {
                countDays++;
            }
            if (isWeekend(currentDate) && tool.isWeekendCharge()) {
                countDays++;
            }

            if (isHoliday(currentDate) && !tool.isHolidayCharge() &&
                    ((countDays > 0 && currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) ||
                    (dayCount - i > 1 && currentDate.getDayOfWeek() == DayOfWeek.SUNDAY))){
                countDays--;
            }
            currentDate = currentDate.plusDays(1);

        }
        return countDays;
    }
}
