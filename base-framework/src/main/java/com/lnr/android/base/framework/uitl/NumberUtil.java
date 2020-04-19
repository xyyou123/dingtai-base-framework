package com.lnr.android.base.framework.uitl;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class NumberUtil {

    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    public static int parseInt(String value, int def) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static long parseLong(String value) {
        return parseLong(value, 0);
    }

    public static long parseLong(String value, long def) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static double parseDouble(String value, double def) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static double parseDouble(String value) {
        return parseDouble(value, 0);
    }
}
