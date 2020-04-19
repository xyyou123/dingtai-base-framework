package com.lnr.android.base.framework.uitl.date;

import com.dingtai.android.library.resource.Utils;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author:lnr
 * date:2018/9/3
 */
public class DateUtil {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINESE);

    public static long format(String time) {
       return format(time, pattern);
    }

    public static long format(String time, String pattern) {
        return  Utils.format(time, pattern);
    }

    public static String formatFromString(String time) {
        return format(format(time));
    }

    public static String format(long time) {
        return Utils.format(time);
    }

    public static String formatDefult(long time) {
        return format(time, pattern);
    }

    public static String format(long time, String pattern) {
        sSimpleDateFormat.applyPattern(pattern);
        return sSimpleDateFormat.format(time);
    }
}
