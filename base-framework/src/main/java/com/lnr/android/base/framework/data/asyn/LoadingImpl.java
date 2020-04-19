package com.lnr.android.base.framework.data.asyn;

import com.lnr.android.base.framework.app.AppHandler;
import com.lnr.android.base.framework.data.asyn.core.LoadingProxy;

/**
 * 加载框代理实现
 */
public class LoadingImpl extends LoadingProxy {

    public LoadingImpl(Class target) {
        super(target);
    }

    @Override
    public void show() {
        AppHandler.getInstance().showLoading(target);
    }

    @Override
    public void hide() {
        AppHandler.getInstance().hideLoading(target);
    }

    @Override
    public void updateProgress(int progress) {
        AppHandler.getInstance().updateProgress(target, progress);
    }
}
