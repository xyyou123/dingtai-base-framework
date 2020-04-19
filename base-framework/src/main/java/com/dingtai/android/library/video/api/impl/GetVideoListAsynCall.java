package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.VideoModel;
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
 * date:2018-11-06
 */
public class GetVideoListAsynCall extends AbstractAsynCall<List<VideoModel>> {

    private static final String URL = "base";

    @Inject
    public GetVideoListAsynCall(){}

    @Override
    public Observable<List<VideoModel>> call(AsynParams params) {
		String ChannelID = params.get("ChannelID");
		String top = params.get("top");
		String dtop = params.get("dtop");
		String UserGUID = AccountHelper.getInstance().getUserId();
		String StID = Resource.API.STID;
		        return http().call(VideoApi.class, URL)
                .getVideoList("0".equals(dtop) ? "GetDataByChannelIDXiaLa" : "GetDataByChannelIDShangLa", ChannelID, top, dtop, UserGUID, StID)
                        .map(new CallResultDecodeBase64<JSONObject>())
                        .map(new Function<JSONObject, List<VideoModel>>() {
                            @Override
                            public List<VideoModel> apply(JSONObject object) throws Exception {
                                return JsonUtil.parseArray(object.getString("MediaList"), VideoModel.class);
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
