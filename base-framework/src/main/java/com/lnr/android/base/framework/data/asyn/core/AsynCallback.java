package com.lnr.android.base.framework.data.asyn.core;

public interface AsynCallback<T> {

    void onCallResponse(T r);

    void onCallError(Throwable ex);
}
