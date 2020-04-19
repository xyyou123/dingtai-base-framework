package com.lnr.android.base.framework;

import android.app.Application;

import com.dingtai.android.library.model.ModuleCommponent;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.app.ActivityLifecycleCallback;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.DaggerApplicationComponent;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterProvider;
import com.lnr.android.base.framework.data.asyn.db.greendao.GreendaoCallAdapter;
import com.lnr.android.base.framework.data.asyn.http.retrofit.RetrofitAsynCallAdapter;
import com.lnr.android.base.framework.data.asyn.http.retrofit.RetrofitDefaultFactory;
import com.lnr.android.base.framework.data.asyn.http.retrofit.RetrofitProvider;
import com.lnr.android.base.framework.rx.RxDisposable;

import io.reactivex.disposables.Disposable;

/**
 * author:lnr
 * date:2018/5/10
 * 框架入口 必须在Application中初始化
 */

public class Framework {

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final Framework INSTANCE = new Framework();
    }

    /**
     * 对外唯一实例的接口
     */
    public static Framework getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    private RxDisposable mRxDisposable;

    private Framework() {
    }

    private Application mApplication;
    private ApplicationComponent mApplicationComponent;

    public void initModule(Application application, AbstractComponent... modules) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());
        mRxDisposable = new RxDisposable();
        mApplicationComponent = DaggerApplicationComponent.builder().build();
        mApplicationComponent.inject(this);

        RetrofitProvider.getInstance().registe(Resource.API.BASE, RetrofitDefaultFactory.defaultRetrofit(Resource.API.URL));
        AsynCallAdapterProvider.getInstance().addHttpAsynCallAdapter(new RetrofitAsynCallAdapter());
        AsynCallAdapterProvider.getInstance().addDatabaseAsynCallAdapter(new GreendaoCallAdapter());

        ModuleCommponent.init(mApplication);
        if(modules != null) {
            for (AbstractComponent module : modules) {
                module.init(mApplication);
            }
        }

    }

    public Application getApplication() {
        return mApplication;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public void registe(Disposable disposable) {
        mRxDisposable.registe(disposable);
    }
}
