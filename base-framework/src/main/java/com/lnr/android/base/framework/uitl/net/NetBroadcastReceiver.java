package com.lnr.android.base.framework.uitl.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * author:lnr
 * date:2018/10/8
 */
public class NetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkUtil.updateState();
    }
}
