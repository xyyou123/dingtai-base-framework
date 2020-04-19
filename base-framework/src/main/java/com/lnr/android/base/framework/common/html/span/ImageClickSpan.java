package com.lnr.android.base.framework.common.html.span;

import android.content.Context;

import com.lnr.android.base.framework.common.html.OnTagClickListener;

import java.util.List;


public class ImageClickSpan extends AbstractClickSpan {
    private Context context;
    private List<String> imageUrls;
    private int position;

    public ImageClickSpan(Context context, List<String> imageUrls, int position) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.position = position;
    }

    @Override
    protected void onSpanClick(OnTagClickListener listener) {
        if (listener != null) {
            listener.onImageClick(context, imageUrls, position);
        }
    }
}
