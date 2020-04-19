package com.lnr.android.base.framework.ui.control.web;

import android.app.Activity;

import com.tencent.smtt.sdk.WebView;

/**
 * author:lnr
 * date:2018/12/13
 */
public abstract class AbstractWebViewInterceptor implements WebViewInterceptor {

    protected Activity activity;
    protected WebWrapper wrapper;

    public AbstractWebViewInterceptor(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onDestroy() {
        activity = null;
        wrapper = null;
    }

    @Override
    public void onPageFinished(WebView webView, String s) {

    }
}
