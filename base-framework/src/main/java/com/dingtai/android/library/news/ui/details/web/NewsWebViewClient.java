package com.dingtai.android.library.news.ui.details.web;

import com.lnr.android.base.framework.uitl.logger.Logger;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * author:lnr
 * date:2018/11/1
 */
public class NewsWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String s) {
        Logger.log("NewsWebViewClient", s);
        return super.shouldOverrideUrlLoading(webView, s);
    }
}
