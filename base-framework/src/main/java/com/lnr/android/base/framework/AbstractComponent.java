package com.lnr.android.base.framework;

import android.app.Application;


import com.lnr.android.base.framework.rx.RxDisposable;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/8/1
 */
public abstract class AbstractComponent {

    private Application mApplication;
    private RxDisposable mRxDisposable;

    final void init(Application application) {
        mApplication = application;
        mRxDisposable = new RxDisposable();
        initDatabase();
        initInfo();
    }

    protected abstract void initDatabase();

    protected abstract void initInfo();

    public final Application getApplication() {
        return mApplication;
    }

    protected final <T> RxDisposable registe(Class<T> tClass, Consumer<T> action) {
        return mRxDisposable.registe(tClass, action);
    }
}
