package com.lnr.android.base.framework.data.asyn.core;

/**
 * author:lnr
 * @param <T>
 */
public abstract class AbstractAsynCall<T> implements AsynCall<T> {

    protected final AsynCallAdapter http() {
        return AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.HTTP);
    }

    protected final AsynCallAdapter database() {

        return AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE);
    }

    protected AsynCallAdapter callAdapter(String key) {
        return AsynCallAdapterProvider.getInstance().get(key);
    }

}
