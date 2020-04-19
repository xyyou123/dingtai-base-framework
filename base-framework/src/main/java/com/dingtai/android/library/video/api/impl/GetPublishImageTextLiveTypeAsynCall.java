package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.PublishLiveTypeModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-09
 */
public class GetPublishImageTextLiveTypeAsynCall extends AbstractAsynCall<List<PublishLiveTypeModel>> {

    private static final String URL = "base";

    @Inject
    public GetPublishImageTextLiveTypeAsynCall(){}

    @Override
    public Observable<List<PublishLiveTypeModel>> call(AsynParams params) {
        String STid = Resource.API.STID;
        return http().call(VideoApi.class, URL)
                .getPublishImageTextLiveType(STid)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<PublishLiveTypeModel>>() {
                    @Override
                    public List<PublishLiveTypeModel> apply(JSONObject object) throws Exception {
                        return JsonUtil.parseArray(object.getString("LiveEvents"), PublishLiveTypeModel.class);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
