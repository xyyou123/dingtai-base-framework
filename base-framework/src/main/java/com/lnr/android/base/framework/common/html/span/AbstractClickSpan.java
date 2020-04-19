package com.lnr.android.base.framework.common.html.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.lnr.android.base.framework.common.html.OnTagClickListener;


/**
 * author:lnr
 * date:2018/10/24
 */
public abstract class AbstractClickSpan extends ClickableSpan {

    private OnTagClickListener listener;

    @Override
    public final void onClick(View widget) {
        onSpanClick(listener);
    }

    public void setListener(OnTagClickListener listener) {
        this.listener = listener;
    }

    protected abstract void onSpanClick(OnTagClickListener listener);

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
