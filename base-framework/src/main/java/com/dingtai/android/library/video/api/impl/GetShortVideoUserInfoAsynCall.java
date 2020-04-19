package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.ShortVideoUserInfoModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-03-14
 */
public class GetShortVideoUserInfoAsynCall extends AbstractAsynCall<ShortVideoUserInfoModel> {

    private static final String URL = "base";

    @Inject
    public GetShortVideoUserInfoAsynCall(){}

    @Override
    public Observable<ShortVideoUserInfoModel> call(AsynParams params) {
        String userGUID = params.get("userGUID");
		String StID = params.get("StID");
		        return http().call(VideoApi.class, URL)
                .getShortVideoUserInfo(userGUID, StID)
                        .map(new CallResultDecodeBase64<JSONObject>())
                        .map(new Function<JSONObject, ShortVideoUserInfoModel>() {
                            @Override
                            public ShortVideoUserInfoModel apply(JSONObject jsonObject) throws Exception {
                                ShortVideoUserInfoModel userInfo = JsonUtil.parseObject(jsonObject.getString("PersonalInfo"), ShortVideoUserInfoModel.class);
                                if (userInfo == null) {
                                    userInfo = new ShortVideoUserInfoModel();
                                }
                                
                                return userInfo;
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
