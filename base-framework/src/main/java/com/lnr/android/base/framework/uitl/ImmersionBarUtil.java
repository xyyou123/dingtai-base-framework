package com.lnr.android.base.framework.uitl;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.dingtai.android.library.resource.GlobalConfig;
import com.gyf.barlibrary.ImmersionBar;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/8/28
 */
public class ImmersionBarUtil {
    public static boolean defaultStatusColor = false;
    /**
     * 沉浸式
     * @param activity 宿主
     * @return 沉浸式对象
     */
    public static ImmersionBar buildImmersion(Activity activity) {

        if (defaultStatusColor) {
            return ImmersionBar.with(activity).keyboardEnable(true).statusBarColor(R.color.statusbar);
        }

        return ImmersionBar.with(activity).statusBarDarkFont(true).keyboardEnable(true);
    }

    /**
     * 沉浸式
     * @param fragment 宿主
     * @return 沉浸式对象
     */
    public static ImmersionBar buildImmersion(Fragment fragment) {
        if (defaultStatusColor) {
            return ImmersionBar.with(fragment).keyboardEnable(true).statusBarColor(R.color.statusbar);
        }
        return ImmersionBar.with(fragment).statusBarDarkFont(true).keyboardEnable(true);
    }

    /**
     * 非沉浸式
     * @param activity 宿主
     * @return 非沉浸式对象
     */
    public static ImmersionBar buildNotImmersion(Activity activity) {
        return ImmersionBar.with(activity).statusBarDarkFont(true).statusBarColor(R.color.statusbar)
                .fitsSystemWindows(true).keyboardEnable(true);
    }

    /**
     * 非沉浸式
     * @param fragment 宿主
     * @return 非沉浸式对象
     */
    public static ImmersionBar buildNotImmersion(Fragment fragment) {
        return ImmersionBar.with(fragment).statusBarDarkFont(true).statusBarColor(R.color.statusbar).fitsSystemWindows(true).keyboardEnable(true);
    }

    /**
     * 沉浸式
     * @return 沉浸式对象
     */
    public static ImmersionBar buildImmersion(ImmersionBar bar) {
        return bar.reset().statusBarDarkFont(true).keyboardEnable(true);
    }

    /**
     * 非沉浸式
     * @return 非沉浸式对象
     */
    public static ImmersionBar buildNotImmersion(ImmersionBar bar) {
        return bar.reset().statusBarDarkFont(true).statusBarColor(R.color.statusbar).fitsSystemWindows(true).keyboardEnable(true);
    }

}
