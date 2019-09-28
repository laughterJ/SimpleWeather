package com.wiker.framework.util;

import java.text.SimpleDateFormat;
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
}
