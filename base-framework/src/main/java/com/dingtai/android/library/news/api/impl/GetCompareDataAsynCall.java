package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONArray;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-21
 */
public class GetCompareDataAsynCall extends AbstractAsynCall<JSONArray> {

    private static final String URL = "base";

    @Inject
    public GetCompareDataAsynCall(){}

    @Override
    public Observable<JSONArray> call(AsynParams params) {
        String Chid = params.get("Chid");
		String sign = params.get("sign");
		String chid = params.get("chid");
		//业务逻辑
        return http().call(NewsApi.class, URL).getCompareData(Chid, Resource.API.SIGN, chid, Resource.API.STID).subscribeOn(Schedulers.io())
                .map(new CallResultDecodeBase64<JSONArray>());
    }
}
