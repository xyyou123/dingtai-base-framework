package com.dingtai.android.library.news.api.impl;

import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * author:lnr
 * date:2018-09-27
 */
public class GetNewsKeyWordAsynCall extends AbstractAsynCall<String> {

    private static final String URL = "base";

    @Inject
    public GetNewsKeyWordAsynCall(){}

    @Override
    public Observable<String> call(AsynParams params) {
        //业务逻辑
        return http().call(NewsApi.class, URL)
                .GetNewsKeyWord(Resource.API.STID, Resource.API.SIGN)
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody object) throws Exception {
                        return object.string();
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
