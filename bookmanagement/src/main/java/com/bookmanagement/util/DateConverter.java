package com.bookmanagement.util;

import java.time.LocalDate;

public class DateConverter {

    // Convert Gregorian LocalDate to Buddhist Date format
    public static String convertGregorianToBuddhist(LocalDate gregorianDate) {
        int buddhistYear = gregorianDate.getYear() + 543;
        int month = gregorianDate.getMonthValue();
        int day = gregorianDate.getDayOfMonth();
        return buddhistYear + "-" + month + "-" + day;
    }

    // Convert Buddhist Date string to Gregorian LocalDate
    public static String convertBuddhistToGregorian(String buddhistDate) {
        String[] dateParts = buddhistDate.split("-");
        int buddhistYear = Integer.parseInt(dateParts[0]);
        int gregorianYear = buddhistYear - 543; // Convert to Gregorian year
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        // Convert to LocalDate object and return the string format
        LocalDate gregorianDate = LocalDate.of(gregorianYear, month, day);
        return gregorianDate.toString();  // Return as a String
    }
}
