package com.lnr.android.base.framework.ui.control.listener;

/**
 * author:lnr
 * date:2018/6/4
 */

import android.view.View;

public abstract class OnClickListener implements View.OnClickListener {

    public static final long INTERVAL = 500L;
    private long lastClick;

    @Override
    public void onClick(View v) {
        long current = System.currentTimeMillis();
        if(current - lastClick < INTERVAL) {
            return;
        }

        lastClick = current;
        onSafeClick(v);
    }

    protected abstract void onSafeClick(View v);
}
