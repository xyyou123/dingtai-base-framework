package com.lnr.android.base.framework.mvp.call.impl;

import com.alibaba.fastjson.JSONArray;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.call.Api;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-09
 */
public class UploadFileAsynCall extends AbstractAsynCall<String> {

    private static final String URL = "base";

    @Inject
    public UploadFileAsynCall(){}

    @Override
    public Observable<String> call(AsynParams params) {
        String PicUrl = params.get("PicUrl");
		String VideoUrl = params.get("VideoUrl");

        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        String a = String.valueOf(y);
        int m = cal.get(Calendar.MONTH);
        String b;
        if (m < 10) {
            b = "0" + String.valueOf(m);
        } else {
            b = String.valueOf(m);
        }

        int d = cal.get(Calendar.DAY_OF_MONTH);
        String c = String.valueOf(d);
        String f = a + b + c;
		String FileDate = f + "/";
		String StID = Resource.API.STID;
		return http().call(Api.class, URL)
                .uploadFile(PicUrl, VideoUrl, FileDate, StID)
                        .map(new CallResultDecodeBase64<JSONArray>())
                        .map(new Function<JSONArray, String>() {
                            @Override
                            public String apply(JSONArray object) throws Exception {
                                return object.getJSONObject(0).getString("Url");
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
