package com.lnr.android.base.framework.data.asyn.core;

/**
 * author:lnr
 * date:2018-08-09
 * 加载框代理
 */
public abstract class LoadingProxy {

    protected Class target;

    public LoadingProxy(Class target) {
        this.target = target;
    }

    public abstract void show();

    public abstract void hide();

    public abstract void updateProgress(int progress);
}
