package com.lnr.android.base.framework.data.provider;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * author:lnr
 * date:2018/9/30
 */
public class SimpleDataProvider implements IDataProvider {

    @Override
    public HashMap<String, Object> get(HashMap<String, Object> params) {
        return params;
    }

    @Override
    public Observable<Object> getAsync(HashMap<String, Object> params) {
        return null;
    }

    @Override
    public void put(HashMap<String, Object> params) {

    }

    @Override
    public void delete(HashMap<String, Object> params) {

    }
}
