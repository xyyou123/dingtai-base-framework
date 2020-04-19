package com.lnr.android.base.framework.ui.control.web;

import android.graphics.Bitmap;

import com.just.agentwebX5.ChromeClientCallbackManager;
import com.tencent.smtt.sdk.WebView;

/**
 * author:lnr
 * date:2018/12/13
 */
public interface WebCallback extends ChromeClientCallbackManager.ReceivedTitleCallback {

    void onReceivedIcon(WebView view, Bitmap icon);

    void onResizeHeight(float height);
}
