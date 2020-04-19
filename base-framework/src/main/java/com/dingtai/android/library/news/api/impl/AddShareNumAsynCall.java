package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-02
 */
public class AddShareNumAsynCall extends AbstractAsynCall<JSONObject> {

    private static final String URL = "base";

    @Inject
    public AddShareNumAsynCall(){}

    @Override
    public Observable<JSONObject> call(AsynParams params) {
        String guid = params.get("guid");
		String STid = Resource.API.STID;
		        return http().call(NewsApi.class, URL)
                .AddShareNum(guid, STid)
                .subscribeOn(Schedulers.io());
    }
}
