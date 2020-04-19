package com.lnr.android.base.framework.uitl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * author:lnr
 * date:2018/8/28
 */
public class IntentUtil {

    public static void openSystemBrowser(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
