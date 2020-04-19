package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.VideoDetailsModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-06
 */
public class GetVideoDetailsAsynCall extends AbstractAsynCall<VideoDetailsModel> {

    private static final String URL = "base";

    @Inject
    public GetVideoDetailsAsynCall(){}

    @Override
    public Observable<VideoDetailsModel> call(AsynParams params) {
        String VideoID = params.get("VideoID");
		String StID = Resource.API.STID;
		        return http().call(VideoApi.class, URL)
                .getVideoDetails(VideoID, StID)
                        .map(new CallResultDecodeBase64<JSONObject>())
                        .map(new Function<JSONObject, VideoDetailsModel>() {
                            @Override
                            public VideoDetailsModel apply(JSONObject object) throws Exception {
                                return object.getJSONArray("MediaInfo").getJSONObject(0).toJavaObject(VideoDetailsModel.class);
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
