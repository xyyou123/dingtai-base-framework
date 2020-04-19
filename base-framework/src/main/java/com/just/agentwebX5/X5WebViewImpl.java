package com.just.agentwebX5;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import com.tencent.smtt.sdk.WebView;

import java.util.Map;

/**
 * author:lnr
 * date:2018/11/1
 */
public class X5WebViewImpl extends WebView {

    public X5WebViewImpl(Context context) {
        super(context);
    }

    public X5WebViewImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public X5WebViewImpl(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public X5WebViewImpl(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
    }

    public X5WebViewImpl(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("X5WebViewImpl", "onDraw");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("X5WebViewImpl", "onMeasure");
    }
}
