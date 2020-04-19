package com.lnr.android.base.framework.ui.control.toast;

import android.content.Context;
import android.widget.Toast;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/6/4
 */

public class ToastHelper {

    private static Toast sLastToast;

    public static void toast(Context context, IToastGenerater generater) {
        cancel();
        sLastToast = generater.generater(context);
        sLastToast.show();
    }

    public static void cancel() {
        if(sLastToast != null) {
            sLastToast.cancel();
        }
    }

    public static void toastDefault(String msg) {
        toastDefault(Framework.getInstance().getApplication(), msg);
    }

    public static void toastDefault(Context context, String msg) {
        toast(context, new DefaultToastGenerater(msg));
    }

    public static void simpleToast(int res, String content) {
        simpleToast(Framework.getInstance().getApplication(), res, content);
    }

    public static void simpleToast(Context context, int res, String content) {
        toast(context, new SimpleToastGenerater(res, content));
    }

    public static void toastScore(int score) {
        toastDefault("积分 +" + score);
        //new ScoreToastGenerater(score).generater(Framework.getInstance().getApplication()).show();
    }

    public static void toastSucceed(String message) {
        simpleToast(R.drawable.icon_toast_succeed, message);
    }

    public static void toastError(String message) {
        simpleToast(R.drawable.icon_toast_error, message);
    }
}
