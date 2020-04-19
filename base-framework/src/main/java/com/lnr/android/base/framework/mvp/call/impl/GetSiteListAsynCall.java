package com.lnr.android.base.framework.mvp.call.impl;

import com.alibaba.fastjson.JSONObject;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.call.Api;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-01-02
 * 获取站点信息
 */
public class GetSiteListAsynCall extends AbstractAsynCall<JSONObject> {

    private static final String URL = "base";

    @Inject
    public GetSiteListAsynCall(){}

    @Override
    public Observable<JSONObject> call(AsynParams params) {
        String sid = params.get("sid");
        return http().call(Api.class, URL)
                .getSiteList(sid)
                .map(new CallResultDecodeBase64<JSONObject>())
                .subscribeOn(Schedulers.io());
    }
}
