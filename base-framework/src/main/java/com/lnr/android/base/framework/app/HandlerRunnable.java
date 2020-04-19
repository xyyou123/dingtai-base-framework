package com.lnr.android.base.framework.app;

/**
 * author:lnr
 * date:2018/5/10
 *
 */

public abstract class HandlerRunnable implements Runnable {

    @Override
    public final void run() {
        try {
            safeRun();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected abstract void safeRun();
}
