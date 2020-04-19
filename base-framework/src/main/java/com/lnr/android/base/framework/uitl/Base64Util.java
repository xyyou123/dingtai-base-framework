package com.lnr.android.base.framework.uitl;


import android.util.Base64;

public class Base64Util {

    public static String decodeBase64ToUTF8(String str) {
        String desStr = "";
        try {
            if (str != null && str.length() > 0) {
                desStr = new String(Base64.decode(str, Base64.NO_WRAP), "UTF-8");
            } else {
                desStr = "";
            }

            return desStr;
        } catch (Exception var5) {
            return desStr;
        }
    }

    public static String encodeBase64ToUTF8(String str) {
        String desStr = "";

        try {
            if (str != null && str.length() > 0) {
                desStr = Base64.encodeToString(str.getBytes("UTF-8"), Base64.NO_WRAP);
            } else {
                desStr = "";
            }
            return desStr;
        } catch (Exception var5) {
            return desStr;
        }
    }
}
