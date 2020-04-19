package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.LiveGameModel;
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
 * date:2018-09-05
 */
public class GetGameAsynCall extends AbstractAsynCall<List<LiveGameModel>> {

    private static final String URL = "base";

    @Inject
    public GetGameAsynCall(){}

    @Override
    public Observable<List<LiveGameModel>> call(AsynParams params) {
        String LiveID = params.get("LiveID");
		String GameType = params.get("GameType");
		String TabCode = params.get("TabCode");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetGame(LiveID, GameType, TabCode, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<LiveGameModel>>() {
                    @Override
                    public List<LiveGameModel> apply(JSONObject object) throws Exception {
                        return JsonUtil.parseArray(object.getString("GameList"), LiveGameModel.class);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
