package com.lnr.android.base.framework.app;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.lnr.android.base.framework.ui.base.BaseActivity;

/**
 * author:lnr
 * date:2018/5/10
 * 全局handler
 */

public final class AppHandler {

    private final Handler handler;

    private AppHandler() {
        handler = new Handler(Looper.getMainLooper());
    }

    private static final class SingleHolder {
        private static final AppHandler INSTANCE = new AppHandler();
    }

    public static AppHandler getInstance() {
        return SingleHolder.INSTANCE;
    }

    public boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public void post(HandlerRunnable runnable) {
        if(isMainThread()) {
            runnable.run();
        }else {
            handler.post(runnable);
        }
    }

    public void postDelayed(long delayMillis, HandlerRunnable runnable) {
        handler.postDelayed(runnable, delayMillis);
    }

    public void remove(HandlerRunnable runnable) {
        handler.removeCallbacks(runnable);
    }

    public void showLoading(final Class tag) {
        post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                Activity activity = ActivityStack.getInstance().currentActivity();
                if(activity != null && tag == activity.getClass() && activity instanceof BaseActivity) {
                    ((BaseActivity) activity).showLoading();
                }
            }
        });
    }

    public void hideLoading(final Class tag) {
        post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                Activity activity = ActivityStack.getInstance().getActivity(tag);
                if(activity != null && activity instanceof BaseActivity) {
                    ((BaseActivity) activity).hideLoading();
                }
            }
        });
    }

    public void updateProgress(final Class tag, final int progress) {
        post(new HandlerRunnable() {
            @Override
            protected void safeRun() {
                Activity activity = ActivityStack.getInstance().getActivity(tag);
                if(activity != null && activity instanceof BaseActivity) {
                    ((BaseActivity) activity).updateProgress(progress);
                }
            }
        });
    }
}
