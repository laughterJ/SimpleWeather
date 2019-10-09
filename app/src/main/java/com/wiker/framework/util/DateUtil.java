package com.wiker.framework.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.util
 */
public class DateUtil {

    public static String date2String(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return mSimpleDateFormat.format(date);
    }

    public static Date string2Date(String strDate) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return mSimpleDateFormat.parse(strDate, new ParsePosition(0));
    }

    public static String getHour(String strDate) {
        Date mDate = string2Date(strDate);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
        return mCalendar.get(Calendar.HOUR_OF_DAY) + " 时";
    }
}
