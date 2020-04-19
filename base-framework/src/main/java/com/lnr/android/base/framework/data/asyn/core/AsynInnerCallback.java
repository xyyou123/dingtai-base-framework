package com.lnr.android.base.framework.data.asyn.core;


import com.lnr.android.base.framework.data.asyn.AsynException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author:lnr
 * date:2018-08-09
 */
abstract class AsynInnerCallback<T> implements Observer<T> {

    private Disposable disposable;
    private LoadingProxy loadingTarget;

    AsynInnerCallback(LoadingProxy loadingTag) {
        this.loadingTarget = loadingTag;
    }

    public void dispose() {
        if(disposable != null) disposable.dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        if(!d.isDisposed() && loadingTarget != null) {
            loadingTarget.show();
        }
    }

    private void hideLoading() {
        if(loadingTarget != null) {
            loadingTarget.hide();
            loadingTarget = null;
        }
    }


    @Override
    public final void onNext(T t) {
        try {
            onTaskResponse(t);
        } catch (Exception e) {
            onError(e);
        }
    }

    @Override
    public final void onError(Throwable t) {
        hideLoading();
        onTaskError(new AsynException(t));
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    protected abstract void onTaskResponse(T r);

    protected abstract void onTaskError(Throwable ex);

}
