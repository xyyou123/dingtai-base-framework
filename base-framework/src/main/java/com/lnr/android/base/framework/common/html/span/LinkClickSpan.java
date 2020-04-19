package com.lnr.android.base.framework.common.html.span;

import android.content.Context;

import com.lnr.android.base.framework.common.html.OnTagClickListener;


public class LinkClickSpan extends AbstractClickSpan {
    private Context context;
    private String url;

    public LinkClickSpan(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    protected void onSpanClick(OnTagClickListener listener) {
        if (listener != null) {
            listener.onLinkClick(context, url);
        }
    }
}
