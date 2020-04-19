package com.lnr.android.base.framework.uitl;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class ClipboardUtil {

    public static boolean copy(Context context, String data) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if(cm == null) {
            return false;
        }
        ClipData mClipData = ClipData.newPlainText("data", data);
        cm.setPrimaryClip(mClipData);
        return true;
    }
}
