package com.lnr.android.base.framework.ui.control.web;


import android.content.Intent;

import com.tencent.smtt.sdk.WebView;

/**
 * author:lnr
 * date:2018/12/13
 */
public interface WebViewInterceptor {

    boolean shouldOverrideUrlLoading(WebView view, String url);

    void onPageFinished(WebView webView, String s);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onDestroy();
}
