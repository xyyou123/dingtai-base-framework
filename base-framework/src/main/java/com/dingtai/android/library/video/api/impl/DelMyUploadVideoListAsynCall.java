package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-12-04
 */
public class DelMyUploadVideoListAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public DelMyUploadVideoListAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String MediaGUID = params.get("MediaGUID");
		String sign = Resource.API.SIGN;
		return http().call(VideoApi.class, URL)
                .delMyUploadVideoList(MediaGUID, sign)
                .map(new Function<JSONObject, Boolean>() {
                    @Override
                    public Boolean apply(JSONObject object) throws Exception {
                        return object.toJSONString().contains("Success");
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
