package com.lnr.android.base.framework.common.html.span;

import android.content.Context;

import com.lnr.android.base.framework.common.html.OnTagClickListener;


public class VideoClickSpan extends AbstractClickSpan {
    private Context context;
    private String path;

    public VideoClickSpan(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    @Override
    protected void onSpanClick(OnTagClickListener listener) {
        if (listener != null) {
            listener.onVideoClick(context, path);
        }
    }
}
