package com.benznestdeveloper.blognonestory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by benznest on 28-Aug-18.
 */

public class MyCalendarUtils {

    public static Calendar convertBlognoneTimestampToCalendar(String timestamp) {
//        String[] str1 = strDate.split("T");
//        String date = str1[0];
//
//        String[] str2 = str1[1].split(".");
//        String time = str2[0];
//
//        String[] str3 = str

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.getDefault());
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(timestamp));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDifferenceTimeString(String timestamp,boolean isAgo) {
        Calendar myDate = convertBlognoneTimestampToCalendar(timestamp);
        Calendar today = Calendar.getInstance();

        String ago = "";
        if(isAgo){
            ago = "Ago";
        }

        if (myDate != null) {
            long diff = today.getTimeInMillis() - myDate.getTimeInMillis();
            long diffMonth = diff / (30 * 24 * 60 * 60 * 1000);
            if (diffMonth > 0) {
                return diffMonth + " months "+ago;
            }

            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays > 0) {
                return diffDays + " days "+ago;
            }

            long diffHour = diff / (60 * 60 * 1000);
            if (diffHour > 0) {
                return diffHour + " Hours "+ago;
            }
        }
        return "";
    }
}
