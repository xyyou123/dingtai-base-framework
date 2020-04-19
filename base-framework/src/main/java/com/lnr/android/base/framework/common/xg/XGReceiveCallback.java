package com.lnr.android.base.framework.common.xg;

import android.content.Context;
import android.content.Intent;

import com.dingtai.android.library.model.models.XGNotificationModel;

/**
 * author:lnr
 * date:2018/12/26
 */
public interface XGReceiveCallback {

    Intent getIntent(Context context, XGNotificationModel model);
    Intent getIntent(Context context, String  str);
}
