package com.lnr.android.base.framework.uitl.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Author:  lnr
 * Date:    2018/2/24
 */

public class Time {

    public static final long Second = 1000;

    public static final long Minutes = Second * 60;

    public static final long Hour = Minutes * 60;

    public static final long Day = Hour * 24;

    public static final long Week = Day * 7;

    public static final long Month = Day * 30;

    public static boolean isDaytime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour <= 19;
    }

    public static String weekday(int dayOfWeek) {
        String str;
        switch (dayOfWeek) {
            case 0:
                str = "星期日";
                break;
            case 1:
                str = "星期一";
                break;
            case 2:
                str = "星期二";
                break;
            case 3:
                str = "星期三";
                break;
            case 4:
                str = "星期四";
                break;
            case 5:
                str = "星期五";
                break;
            case 6:
                str = "星期六";
                break;
            default:
                str = null;
        }
        return str;
    }

    public static String weekday(long time) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date(time));
        return weekday(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    public static boolean isYesterday(long time) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date(time));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (time < calendar.getTime().getTime()) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            return time >= calendar.getTime().getTime();
        }

        return false;
    }

    public static long yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime().getTime();
    }

    public static long today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long thisweek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long thisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long thisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static int dayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
