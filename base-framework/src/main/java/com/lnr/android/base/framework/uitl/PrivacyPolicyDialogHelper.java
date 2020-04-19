package com.lnr.android.base.framework.uitl;

import android.app.Activity;
import android.view.View;

import com.lnr.android.base.framework.ui.control.dialog.PrivacyPolicyDialog;

/**
 * author:lnr
 * date:2018/3/21
 */

public class PrivacyPolicyDialogHelper {

    public static PrivacyPolicyDialog builder(Activity activity) {
        return new PrivacyPolicyDialog(activity).builder();
    }


    public static void show(Activity activity) {
        if (!SP.getDefault().getBoolean("privacy-" + ContextUtil.getVersionName(), false)) {
            builder(activity)
                    .setPositiveButton("同意",null)
                    .setNegativeButton("暂不使用", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.exit(0);
                        }
                    })
                    .show();
            SP.getDefault().edit().putBoolean("privacy-" + ContextUtil.getVersionName(), true).apply();
        }
    }

    public static void show(Activity activity, String title, String message, String positiveButton) {
        if (!SP.getDefault().getBoolean("privacy-" + ContextUtil.getVersionName(), false)) {
            builder(activity).setTitle(title)
                    .setMsg(message)
                    .setPositiveButton(positiveButton, null)
                    .show();
            SP.getDefault().edit().putBoolean("privacy-" + ContextUtil.getVersionName(), true).apply();
        }
    }


}
