package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.model.SubjectNeoRootModel;
import com.dingtai.android.library.resource.Resource;
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
 * date:2018-09-17
 */
public class GetChannAndNewsAsynCall extends AbstractAsynCall<SubjectNeoRootModel> {

    private static final String URL = "base";

    @Inject
    public GetChannAndNewsAsynCall(){}

    @Override
    public Observable<SubjectNeoRootModel> call(AsynParams params) {
        String ParentID = params.get("ParentID");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .GetChannAndNews(ParentID, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, SubjectNeoRootModel>() {
                    @Override
                    public SubjectNeoRootModel apply(JSONObject object) throws Exception {
                        return JsonUtil.parseObject(object.toJSONString(), SubjectNeoRootModel.class);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
