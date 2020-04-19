package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-07
 */
public class AddLiveCommentAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public AddLiveCommentAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String ID = params.get("ID");
		String CommentContent = params.get("CommentContent");
		String UserGUID = AccountHelper.getInstance().getUserId();
		String StID = params.get("StID");
		        return http().call(VideoApi.class, URL)
                .addLiveComment(ID, CommentContent, UserGUID, StID)
                        .map(new Function<JSONObject, Boolean>() {
                            @Override
                            public Boolean apply(JSONObject object) throws Exception {
                                return object.toJSONString().contains("Success");
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
