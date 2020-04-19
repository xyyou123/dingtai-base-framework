package com.lnr.android.base.framework.common.xg;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.dingtai.android.library.model.helper.AccountHelper;
import com.lnr.android.base.framework.BuildConfig;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

/**
 * author:lnr
 * date:2018/10/18
 */
public class XGInitializer {

    static XGReceiveCallback sCallback;

    public static void init(Context context, XGReceiveCallback callback) {
        if (Build.VERSION.SDK_INT > 20) {
            XGPushConfig.enableDebug(context, BuildConfig.DEBUG);
            if (AccountHelper.getInstance().isLogin()) {
                String userGUID = AccountHelper.getInstance().getUser().getUserGUID();
                if (!TextUtils.isEmpty(userGUID)) {
                    XGPushManager.registerPush(context, userGUID);
                } else {
                    XGPushManager.registerPush(context);
                }
            } else {
                XGPushManager.registerPush(context);
            }
            sCallback = callback;
        }

        if (sCallback == null) {
            sCallback = new DefaultXGReceiveCallback();
        }
    }
}
