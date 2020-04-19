package com.lnr.android.base.framework;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.dingtai.android.library.database.DB;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.iflytek.cloud.SpeechUtility;
import com.lnr.android.base.framework.app.ActivityStack;
import com.lnr.android.base.framework.common.umeng.UMengConfig;
import com.lnr.android.base.framework.common.xg.DefaultXGReceiveCallback;
import com.lnr.android.base.framework.common.xg.XGInitializer;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.event.RealNameAuthenticationEvent;
import com.lnr.android.base.framework.event.UpdateStatusEvent;
import com.lnr.android.base.framework.mvp.call.impl.UpdateModelStatusAsynCall;
import com.lnr.android.base.framework.rx.RxDisposable;
import com.lnr.android.base.framework.ui.control.dialog.MessageDialogHelper;
import com.lnr.android.base.framework.ui.control.error.ErrorActivity;
import com.lnr.android.base.framework.uitl.MaiDianUtils;
import com.lnr.android.base.framework.uitl.net.NetworkUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.smtt.sdk.QbSdk;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/10/18
 */
public class FrameworkApplication extends Application {

    public static float MAX_FONT = 1.1f;

    protected RxDisposable mRxDisposable;

    protected UpdateModelStatusAsynCall mUpdateModelStatusAsynCall;

    @Override
    public final void onCreate() {
        super.onCreate();

        initSmartRefreshLayout();

        mRxDisposable = new RxDisposable();

        mUpdateModelStatusAsynCall = new UpdateModelStatusAsynCall();

        mRxDisposable.registe(UpdateStatusEvent.class, new Consumer<UpdateStatusEvent>() {
            @Override
            public void accept(UpdateStatusEvent event) throws Exception {
                mUpdateModelStatusAsynCall.call(
                        AsynParams.create("key", event.getKey()).put("status", event.getStatus()))
                        .subscribe();
            }
        });

        mRxDisposable.registe(RealNameAuthenticationEvent.class, new Consumer<RealNameAuthenticationEvent>() {
            @Override
            public void accept(RealNameAuthenticationEvent realNameAuthenticationEvent) throws Exception {
                Activity activity = ActivityStack.getInstance().currentActivity();
                if (activity == null) return;
                MessageDialogHelper.showHasCancel(activity, "按照互联网服务要求，使用该功能需要实名制，是否现在进行实名制？",
                        "是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build("/account/authentication").navigation();
                            }
                        }, "否", null);
            }
        });

        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {
                if (level == TRIM_MEMORY_UI_HIDDEN) {
                    Glide.get(FrameworkApplication.this).clearMemory();
                }
                Glide.get(FrameworkApplication.this).trimMemory(level);
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {
                Glide.get(FrameworkApplication.this).onLowMemory();
            }
        });

        initFramework();

        initSDK();

        initCrashConfig();
    }

    protected void initFramework() {

    }

    protected void initSDK() {
        UMengConfig.init(getApplicationContext());
        Glide.init(this, new GlideBuilder());

        DB.getInstance().initConmon(this);

        QbSdk.initX5Environment(this, null);

        SDKInitializer.initialize(this);

        ARouter.init(this);

        NetworkUtil.listen(this);

        AccountHelper.getInstance().init(this);

        XGInitializer.init(this, new DefaultXGReceiveCallback());

        SpeechUtility.createUtility(this, "appid=5ba46870");
        MaiDianUtils.init(this, true);
    }

    /**
     * 初始化crash
     */
    protected void initCrashConfig() {
        CaocConfig.Builder.create()
                .showErrorDetails(false)
                .errorActivity(ErrorActivity.class)
                .apply();
    }

    protected void initSmartRefreshLayout() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static class CrushEventListener implements CustomActivityOnCrash.EventListener {

        @Override
        public void onLaunchErrorActivity() {

//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//        Logger.save("crash-" + sDateFormat.format(new Date()) + ".log", e);
//        Logger.log("crash", e);
        }

        @Override
        public void onRestartAppFromErrorActivity() {

        }

        @Override
        public void onCloseAppFromErrorActivity() {

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale > MAX_FONT)
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale > MAX_FONT) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            newConfig.fontScale = MAX_FONT;
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
