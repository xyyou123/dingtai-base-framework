package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-21
 */
public class InsertADStatisticsAsynCall extends AbstractAsynCall<JSONObject> {

    private static final String URL = "base";

    @Inject
    public InsertADStatisticsAsynCall(){}

    @Override
    public Observable<JSONObject> call(AsynParams params) {
        String OPID = params.get("OPID");
        String OPType = params.get("OPType");
		String sign = params.get("sign");
		String UserGUID = params.get("UserGUID");
		String System = params.get("System");
		String Device = params.get("Device");
		String Resolution = params.get("Resolution");
		String Network = params.get("Network");
		String CarrierOperator = params.get("CarrierOperator");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .InsertADStatistics(OPID, OPType, sign, UserGUID, System, Device, Resolution, Network, CarrierOperator, Resource.API.STID)
                .subscribeOn(Schedulers.io());
    }
}
