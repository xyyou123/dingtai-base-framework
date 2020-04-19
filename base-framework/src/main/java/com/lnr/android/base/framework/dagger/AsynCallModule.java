package com.lnr.android.base.framework.dagger;

import android.app.Activity;

import com.lnr.android.base.framework.data.asyn.LoadingImpl;
import com.lnr.android.base.framework.data.asyn.core.AsynCallExecutor;
import com.lnr.android.base.framework.data.asyn.core.LoadingProxy;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import dagger.Module;
import dagger.Provides;

/**
 * author:lnr
 * date:2018/8/1
 */
@Module
public class AsynCallModule {

    private RxAppCompatActivity activity;

    private RxFragment fragment;

    public AsynCallModule(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public AsynCallModule(RxFragment fragment) {
        this.activity = (RxAppCompatActivity) fragment.getActivity();
        this.fragment = fragment;
    }

    @Provides
    LifecycleTransformer bindLifecycle() {
        return fragment != null ? fragment.bindToLifecycle() : activity.bindToLifecycle();
    }

    @Provides
    LoadingProxy loadingTarget() {
        Activity target = activity;
        if(target == null && fragment != null) {
            target = fragment.getActivity();
        }
        return target != null ? new LoadingImpl(target.getClass()) : null;
    }

    @Provides
    AsynCallExecutor asynCallExecutor(LifecycleTransformer life, LoadingProxy loadingProxy) {
        return new AsynCallExecutor(life, loadingProxy);
    }
}
