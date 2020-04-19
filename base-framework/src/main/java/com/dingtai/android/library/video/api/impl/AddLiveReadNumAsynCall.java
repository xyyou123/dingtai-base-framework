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
 * date:2018-11-07
 */
public class AddLiveReadNumAsynCall extends AbstractAsynCall<Object> {

    private static final String URL = "base";

    @Inject
    public AddLiveReadNumAsynCall(){}

    @Override
    public Observable<Object> call(AsynParams params) {
        String ID = params.get("ID");
		String STid = Resource.API.STID;
		String sign = Resource.API.SIGN;
		        return http().call(VideoApi.class, URL)
                .AddLiveReadNum(ID, STid, sign)
                .subscribeOn(Schedulers.io());
    }
}
