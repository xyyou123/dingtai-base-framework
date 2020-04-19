package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-21
 */
public class RefreshGetNewsListAsynCall extends AbstractAsynCall<JSONObject> {

    private static final String URL = "base";

    @Inject
    public RefreshGetNewsListAsynCall(){}

    @Override
    public Observable<JSONObject> call(AsynParams params) {
        String num = params.get("num");
		String sign = params.get("sign");
		String chid = params.get("chid");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .refreshGetNewsList(num, Resource.API.SIGN, chid, Resource.API.STID)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends JSONObject>>() {
                    @Override
                    public ObservableSource<JSONObject> apply(Throwable throwable) {



                        return null;
                    }
                });
    }
}
