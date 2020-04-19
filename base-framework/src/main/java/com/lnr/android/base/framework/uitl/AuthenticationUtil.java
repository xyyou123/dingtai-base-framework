package com.lnr.android.base.framework.uitl;

import android.net.Uri;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 服务器 鉴权的方法
 * Created by Administrator on 2019/10/25.
 */

public class AuthenticationUtil {

    public static String key = "pEhNjHccmS";
    public static String houjian_key = "adbvekjwdklfj";
    public static String rand = "0";
    public static String uid = "0";
    public static final String TYPE_TIANYI = "tianyi";
    public static final String TYPE_ALI = "ali";
    public static final String TYPE_HOUJIAN = "houjian";

    private static int TIME_INTERVAL = 10;

    public static String getAuthenticationUrl(String url, String flag) {
        if (TextUtils.isEmpty(flag) || TextUtils.isEmpty(url)) {
            return url;
        }

        switch (flag) {
            case TYPE_TIANYI:
                break;


            case TYPE_HOUJIAN:
                Uri uri1 = Uri.parse(url);


                int time1 = (int) (System.currentTimeMillis() / 1000) + TIME_INTERVAL;
                String format2 = String.format("%s%s%s%s", houjian_key, time1 + "", uri1.getEncodedPath());

                String decode323 = md5Decode32(format2);
                String substring = decode323.substring(12, 20);
                url = url + "?_upt=" + String.format("%s%s", substring, time1);
                break;
            case TYPE_ALI:
            default:
                Uri uri = Uri.parse(url);


                int time = (int) (System.currentTimeMillis() / 1000) + TIME_INTERVAL;
                String format = String.format("%s-%s-%s-%s-%s", uri.getEncodedPath(), time + "", rand, uid, key);
                String decode32 = md5Decode32(format);
                url = url + "?auth_key=" + String.format("%s-%s-%s-%s", time, rand, uid, decode32);


        }
        return url;

    }


    public static String md5Decode32(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
