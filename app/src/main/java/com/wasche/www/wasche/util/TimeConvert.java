package com.wasche.www.wasche.util;

import android.text.format.DateFormat;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeConvert {
    public static String getDate(int time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

}
