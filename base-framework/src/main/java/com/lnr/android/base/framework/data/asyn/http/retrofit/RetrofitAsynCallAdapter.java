package com.lnr.android.base.framework.data.asyn.http.retrofit;


import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapter;

public class RetrofitAsynCallAdapter implements AsynCallAdapter {

    @Override
    public final <T> T call(Class<T> tClass, Object... rs) {
        return RetrofitProvider.getInstance().get((String) rs[0]).create(tClass);
    }
}
