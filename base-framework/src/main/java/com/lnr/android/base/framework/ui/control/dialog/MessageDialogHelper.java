package com.lnr.android.base.framework.ui.control.dialog;

import android.app.Activity;
import android.view.View;

/**
 * author:lnr
 * date:2018/3/21
 */

public class MessageDialogHelper {

    public static MessageDialog builder(Activity activity) {
        return new MessageDialog(activity).builder();
    }

    public static void show(Activity activity, String message) {
        builder(activity).setTitle(message)
                .setPositiveButton("确定", null)
                .show();
    }

    public static void show(Activity activity, String message, String positiveButton) {
        builder(activity).setTitle(message)
                .setPositiveButton(positiveButton, null)
                .show();
    }

    public static void show(Activity activity, String message, String positiveButton, View.OnClickListener positiveButtonListener) {
        builder(activity).setTitle(message)
                .setPositiveButton(positiveButton, positiveButtonListener)
                .show();
    }

    public static void show(Activity activity, String message, View.OnClickListener positiveButtonListener) {
       show(activity, message, "确定", positiveButtonListener);
    }


    public static void showHasCancel(Activity activity, String message, String positiveButton, View.OnClickListener positiveButtonListener) {
        showHasCancel(activity, message, positiveButton, positiveButtonListener, "取消", null);
    }

    public static void showHasCancel(Activity activity, String message, String positiveButton, View.OnClickListener positiveButtonListener
            , String negativeButton, View.OnClickListener negativeButtonListener) {
        builder(activity).setTitle(message)
                .setPositiveButton(positiveButton, positiveButtonListener)
                .setNegativeButton(negativeButton, negativeButtonListener)
                .show();
    }
}
