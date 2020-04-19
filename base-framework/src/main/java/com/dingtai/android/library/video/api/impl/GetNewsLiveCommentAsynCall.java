package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.LiveCommentModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-03
 */
public class GetNewsLiveCommentAsynCall extends AbstractAsynCall<List<LiveCommentModel>> {

    private static final String URL = "base";

    @Inject
    public GetNewsLiveCommentAsynCall(){}

    @Override
    public Observable<List<LiveCommentModel>> call(AsynParams params) {
        String LiveID = params.get("LiveID");
		String Num = params.get("Num");
		String dtop = params.get("dtop");
		String TabCode = params.get("TabCode");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetNewsLiveComment(LiveID, Num, dtop, TabCode, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<LiveCommentModel>>() {
                    @Override
                    public List<LiveCommentModel> apply(JSONObject object) throws Exception {
                        return JsonUtil.parseArray(object.getString("Comment"), LiveCommentModel.class);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<LiveCommentModel>>>() {
                    @Override
                    public ObservableSource<? extends List<LiveCommentModel>> apply(Throwable throwable) throws Exception {
                        return Observable.just(new ArrayList<LiveCommentModel>());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
