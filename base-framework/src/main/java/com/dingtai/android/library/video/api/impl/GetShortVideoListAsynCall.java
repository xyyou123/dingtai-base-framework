package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-03-11
 */
public class GetShortVideoListAsynCall extends AbstractAsynCall<List<ShortVideoModel>> {

    private static final String URL = "base";

    @Inject
    public GetShortVideoListAsynCall() {
    }

    @Override
    public Observable<List<ShortVideoModel>> call(AsynParams params) {
        String action = params.get("action");
        String num = params.get("num");
        String dtop = params.get("dtop");
        String StID = params.get("StID");
        return http().call(VideoApi.class, URL)
                .getShortVideoList( num, dtop, StID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<ShortVideoModel>>() {
                    @Override
                    public List<ShortVideoModel> apply(JSONObject jsonObject) throws Exception {
                        List<ShortVideoModel> list = JsonUtil.parseArray(jsonObject.getString("VideoList"), ShortVideoModel.class);
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
