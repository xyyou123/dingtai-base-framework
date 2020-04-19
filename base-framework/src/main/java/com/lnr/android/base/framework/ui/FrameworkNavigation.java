package com.lnr.android.base.framework.ui;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.SP;

/**
 * author:lnr
 * date:2018/9/5
 */
public class FrameworkNavigation {

    /**
     * web
     * @param url 地址
     * @param title 标题
     * @see com.lnr.android.base.framework.ui.control.web.BaseToolbarWebActivity
     */
    public static void web(String url, String title) {
        ARouter.getInstance().build(Routes.Framework.WEB_BASE)
                .withString("url", url)
                .withString("title", title).navigation();
    }


    /**
     * web
     * @param url 地址
     * @see com.lnr.android.base.framework.ui.control.web.BaseToolbarWebActivity
     */
    public static void webNoToolbar(String url) {
        ARouter.getInstance().build("/framework/web/base/notoolbar")
                .withString("url", url).navigation();
    }

    public static Object webFragment(String url) {
        return ARouter.getInstance().build(Routes.Framework.WEB_BASE_FRAGMENT)
                .withString("url", url).navigation();
    }

    public static Object webFragment(String url, String title) {
        return ARouter.getInstance().build(Routes.Framework.WEB_BASE_FRAGMENT)
                .withString("url", url)
                .withString("title", title).navigation();
    }


    public static Object webFragment(String url, String title,boolean showHome) {
        return ARouter.getInstance().build(Routes.Framework.WEB_BASE_FRAGMENT)
                .withString("url", url)
                .withBoolean("showHome",showHome)
                .withString("title", title).navigation();
    }

    public static Object webFragmentNoToolbar(String url) {
        return webFragmentNoToolbar(url, false);
    }


    public static Object webFragmentNoToolbar(String url,boolean showHome,int headerHeight) {
        return ARouter.getInstance().build("/framework/web/base/fragment/notoolbar")
                .withBoolean("showHome",showHome)
                .withInt("headerHeight",headerHeight)
                .withString("url", url).navigation();
    }

    public static Object webFragmentNoToolbar(String url,boolean showHome) {
        return ARouter.getInstance().build("/framework/web/base/fragment/notoolbar")
                .withBoolean("showHome",showHome)
                .withString("url", url).navigation();
    }

    public static void home(final BaseActivity activity) {
        if(!SP.getDefault().getBoolean("guide-" + ContextUtil.getVersionName(), false)) {
            ARouter.getInstance().build(Routes.Setting.GUIDE)
                    .withBoolean("next", true).navigation(activity, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    activity.finishActivity();
                }
            });
        }else {
            ARouter.getInstance().build(Routes.Framework.HOME).navigation(activity, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    activity.finishActivity();
                }
            });
        }
    }
}
