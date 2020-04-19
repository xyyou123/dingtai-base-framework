package com.lnr.android.base.framework.rx;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/5/25
 */

public final class RxDisposable {

    private List<Disposable> mDisposables;

    @Inject
    public RxDisposable() {
        mDisposables = new ArrayList<>();
    }

    public <T> RxDisposable registe(Class<T> tClass, Consumer<T> action) {
        mDisposables.add(RxBus.getDefault().toObservable(tClass).observeOn(AndroidSchedulers.mainThread()).subscribe(action));
        return this;
    }

    public <T> RxDisposable registe(Disposable disposable) {
        mDisposables.add(disposable);
        return this;
    }

    public RxDisposable clear() {
        if (mDisposables == null) return this;
        for (Disposable disposable : mDisposables) {
            if (disposable != null) {
                disposable.dispose();
            }
        }
        mDisposables.clear();
        return this;
    }
}
