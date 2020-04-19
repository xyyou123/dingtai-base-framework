package com.lnr.android.base.framework.data.asyn.http.retrofit;

import java.util.HashMap;

import retrofit2.Retrofit;

/**
 * author:lnr
 * date:2018-08-09
 */
public final class RetrofitProvider {
    private static final HashMap<String, Retrofit> RETROFITS = new HashMap<>();
    private RetrofitProvider() {
    }
    private static final class SingleHodler {
        static final RetrofitProvider INSTANCE = new RetrofitProvider();
    }

    public static RetrofitProvider getInstance() {
        return SingleHodler.INSTANCE;
    }

    public RetrofitProvider registe(String key, Retrofit retrofit) {
        RETROFITS.put(key, retrofit);
        return this;
    }

    public Retrofit get(String key) {
        return RETROFITS.get(key);
    }
}
