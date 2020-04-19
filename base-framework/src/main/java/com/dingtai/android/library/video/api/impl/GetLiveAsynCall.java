package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-03-11
 */
public class GetLiveAsynCall extends AbstractAsynCall<JSONObject> {

    private static final String URL = "base";

    @Inject
    public GetLiveAsynCall(){}

    @Override
    public Observable<JSONObject> call(AsynParams params) {
        String top = params.get("top");
		String dtop = params.get("dtop");
		String STid = params.get("STid");
		        return http().call(VideoApi.class, URL)
                .GetLive(top, dtop, STid)
                .subscribeOn(Schedulers.io());
    }
}
