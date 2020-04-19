package com.dingtai.android.library.video.api.impl;

import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-30
 */
public class AddReadNumAsynCall extends AbstractAsynCall<Object> {

    private static final String URL = "base";

    @Inject
    public AddReadNumAsynCall(){}

    @Override
    public Observable<Object> call(AsynParams params) {
        String ID = params.get("ID");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .AddReadNum(ID, Resource.API.STID, Resource.API.SIGN)
                .subscribeOn(Schedulers.io());
    }
}
