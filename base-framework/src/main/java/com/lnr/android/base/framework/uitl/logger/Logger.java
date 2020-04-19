package com.lnr.android.base.framework.uitl.logger;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.uitl.cache.FileCache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * author:lnr
 * date:2018/6/6
 */

public final class Logger {

    private static boolean debug = false;

    public static void openDebug() {
        debug = true;
    }


    public static void log(String TAG, String content) {
        if(!debug) return;

        if(content.length() > 2000) {
            do {
                StringBuilder info = new StringBuilder();
                info.append(content.substring(0, 2000));
                Log.e(TAG, info.toString());
                content = content.substring(2000);
            }
            while (content.length() > 2000);
        }
        StringBuilder info = new StringBuilder();
        info.append(content);
        Log.e(TAG, info.toString());
    }


    public static void log(String TAG, Throwable ex) {
        if(!debug) return;
        StringBuilder info = new StringBuilder();
        info.append(ex.getMessage());
        Log.e(TAG, info.toString(), ex);
    }

    public static void log(String TAG, String className, Throwable ex) {
        if(!debug) return;
        Log.e(TAG, className, ex);
    }

    public static void save(String name, Throwable ex) {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            String date = sDateFormat.format(new Date());
            sb.append("\r\n").append(date).append("\n");

            Map<String, String> infos = new HashMap<>();

            try {
                PackageManager pm = Framework.getInstance().getApplication().getPackageManager();
                PackageInfo pi = pm.getPackageInfo(Framework.getInstance().getApplication().getPackageName(),
                        PackageManager.GET_ACTIVITIES);
                if (pi != null) {
                    String versionName = pi.versionName + "";
                    String versionCode = pi.versionCode + "";
                    infos.put("versionName", versionName);
                    infos.put("versionCode", versionCode);
                }
            } catch (PackageManager.NameNotFoundException e) {
                log("Logger-save", e);
            }
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    infos.put(field.getName(), field.get(null).toString());
                } catch (Exception e) {
                    log("Logger-save", e);
                }
            }

            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key).append("=").append(value).append("\n");
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            String result = writer.toString();
            sb.append(result);

            if(name == null) {
                String time = sDateFormat.format(new Date());
                name= "throwable-" + time + ".log";
            }

            File path = new File(FileCache.getLogDir(), name);
            FileOutputStream fos = new FileOutputStream(path, true);
            fos.write(sb.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            log("Logger-save", "save failed");
        }
    }


    public static void save(Throwable ex) {
        save(null, ex);
    }
}
