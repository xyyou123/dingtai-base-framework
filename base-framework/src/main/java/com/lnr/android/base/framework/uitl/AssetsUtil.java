package com.lnr.android.base.framework.uitl;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class AssetsUtil {

    public static InputStream open(Context context, String fileName) {
        try {
            return context.getAssets().open(fileName);
        } catch (IOException e) {
            return null;
        }
    }

    public static String readAll(Context context, String fileName) {
        return FileUtil.file2String(open(context, fileName));
    }
}
