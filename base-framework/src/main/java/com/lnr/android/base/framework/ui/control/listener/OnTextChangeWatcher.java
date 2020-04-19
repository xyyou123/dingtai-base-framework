package com.lnr.android.base.framework.ui.control.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * author:lnr
 * date:2018/6/4
 */

public class OnTextChangeWatcher implements TextWatcher {

    private TextView[] views;
    private OnTextChangeListener listener;

    public OnTextChangeWatcher(TextView[] views, OnTextChangeListener listener) {
        this.views = views;
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(listener != null) {
            listener.onChange(isHasEmpty());
        }
    }

    public interface OnTextChangeListener {

        void onChange(boolean isHasEmpty);

    }

    private boolean isHasEmpty() {
        boolean isHasEmpty = false;
        for (TextView view : views) {
            if(view == null) continue;
            if(view.getText().length() <= 0) {
                isHasEmpty = true;
                break;
            }
        }
        return isHasEmpty;
    }

}
