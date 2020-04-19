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
 * date:2018-11-09
 */
public class InserNewsContentAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public InserNewsContentAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String UserGUID = AccountHelper.getInstance().getUserId();
		String UserPhone = AccountHelper.getInstance().getUser().getUserPhone();
		String EventID = params.get("EventID");
		String LiveType = params.get("LiveType");
		String Title = params.get("Title");
		String Content = params.get("Content");
		String PicUrl = params.get("PicUrl");
		String VideoUrl = params.get("VideoUrl");
		String FileDate = params.get("FileDate");
		String StID = Resource.API.STID;
		String Sign = Resource.API.SIGN;
		        return http().call(VideoApi.class, URL)
                .InserNewsContent(UserGUID, UserPhone, EventID, LiveType, Title, Content, PicUrl, VideoUrl, FileDate, StID, Sign)
					.map(new Function<JSONObject, Boolean>() {
						@Override
						public Boolean apply(JSONObject object) throws Exception {
							return object.toJSONString().contains("Success");
						}
					})
                .subscribeOn(Schedulers.io());
    }
}
