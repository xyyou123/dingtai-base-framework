package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
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
 * date:2018-11-06
 */
public class AddVideoCommentAsynCall extends AbstractAsynCall<Integer> {

    private static final String URL = "base";

    @Inject
    public AddVideoCommentAsynCall(){}

    @Override
    public Observable<Integer> call(AsynParams params) {
        String MID = params.get("MID");
		String userGUID = AccountHelper.getInstance().getUserId();
		String commentContent = params.get("commentContent");
		String StID = Resource.API.STID;
		        return http().call(VideoApi.class, URL)
                .addVideoComment(MID, userGUID, commentContent, StID)
                        .map(new Function<JSONObject, Integer>() {
                            @Override
                            public Integer apply(JSONObject object) throws Exception {
                                String result = object.toJSONString();
                                if(result.contains("Success")) {
                                    return 1;
                                }else if(result.contains("5omL5py65Y+356CB5pyq57uR5a6aIQ==")) {
                                    return -1;
                                }
                                return 0;
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
