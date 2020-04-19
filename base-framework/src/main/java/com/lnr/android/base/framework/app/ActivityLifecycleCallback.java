package com.lnr.android.base.framework.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

/**
 * author:lnr
 * Date: 2017/5/9
 * app activity生命周期监测
 */

public class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    // 0的时候说明没有再前台
    private static int count = 0;

    /**
     * 是否在前台显示
     */
    public static boolean isForegoing() {
        return count > 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityStack.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        count++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        MobclickAgent.onResume(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        MobclickAgent.onPause(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        count--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityStack.getInstance().removeActivity(activity);
        System.gc();
    }
}
