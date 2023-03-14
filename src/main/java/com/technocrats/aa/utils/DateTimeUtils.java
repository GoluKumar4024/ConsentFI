package com.technocrats.aa.utils;


import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static Date dateXMonthsLater(int interval,  Date fromDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.MONTH, interval);
        return calendar.getTime();
    }
}
