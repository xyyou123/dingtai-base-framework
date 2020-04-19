package com.dingtai.android.library.resource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static final long Second = 1000;
    private static final long Minutes = Second * 60;
    private static final long Hour = Minutes * 60;
    private static final long Day = Hour * 24;
    private static final long Week = Day * 7;
    private static final long Month = Day * 30;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINESE);

    public static long format(String time, String pattern) {
        long res;
        try {
            time = time.replaceAll("/", "-");
            if (time.length() < pattern.length()) {
                String[] split = time.split(" ");
                String[] datePattern;
                if (split.length == 2) {
                    pattern = "yyyy-";
                    datePattern = split[0].split("-");
                    if (datePattern[1].length() < 2) {
                        pattern = pattern + "M-";
                    } else {
                        pattern = pattern + "MM-";
                    }

                    if (datePattern[2].length() < 2) {
                        pattern = pattern + "d ";
                    } else {
                        pattern = pattern + "dd ";
                    }

                    pattern = pattern + "HH:mm:ss";
                } else if (time.contains("-")) {
                    pattern = "yyyy-";
                    datePattern = split[0].split("-");
                    if (datePattern[1].length() < 2) {
                        pattern = pattern + "M-";
                    } else {
                        pattern = pattern + "MM-";
                    }

                    if (datePattern[2].length() < 2) {
                        pattern = pattern + "d";
                    } else {
                        pattern = pattern + "dd";
                    }
                }
            }

            sSimpleDateFormat.applyPattern(pattern);
            Date date = sSimpleDateFormat.parse(time);
            res = date.getTime();
        } catch (Exception var6) {
            res = 0L;
        }

        return res;
    }

    public static String format(long time) {
        Date date = new Date(time);
        String result;
        if (time > System.currentTimeMillis() - 60000L) {
            result = "刚刚";
        } else if (time > today()) {
            sSimpleDateFormat.applyPattern("HH:mm");
            result = sSimpleDateFormat.format(date);
        } else if (time > yesterday()) {
            sSimpleDateFormat.applyPattern("HH:mm");
            result = "昨天 " + sSimpleDateFormat.format(date);
        } else if (time > thisweek()) {
            sSimpleDateFormat.applyPattern("HH:mm");
            result = weekday(time) + " " + sSimpleDateFormat.format(date);
        } else if (time > thisYear()) {
            sSimpleDateFormat.applyPattern("MM-dd HH:mm");
            result = sSimpleDateFormat.format(date);
        } else {
            sSimpleDateFormat.applyPattern("yyyy-MM-dd");
            result = sSimpleDateFormat.format(date);
        }

        return result;
    }

    private static boolean isDaytime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 6 && hour <= 19;
    }

    private static String weekday(int dayOfWeek) {
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

    private static String weekday(long time) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date(time));
        return weekday(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    private static boolean isYesterday(long time) {
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

    private static long yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime().getTime();
    }

    private static long today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    private static long thisweek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    private static long thisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    private static long thisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    private static int dayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
