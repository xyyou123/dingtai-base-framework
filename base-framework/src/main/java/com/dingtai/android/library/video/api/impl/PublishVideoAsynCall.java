package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-12-05
 */
public class PublishVideoAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public PublishVideoAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String ChannelID = params.get("ChannelID");
		String UserGUID = AccountHelper.getInstance().getUserId();
		String UserName = AccountHelper.getInstance().getUser().getUserName();
		String Name = params.get("Name");
		String ImageUrl = params.get("ImageUrl");
		String Detail = params.get("Detail");
		String UploadType = params.get("UploadType");

		String FileName = params.get("FileName");
		String FileExt = params.get("FileExt");

		String StID = Resource.API.STID;
		String sign = Resource.API.SIGN;

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
		String FileDate = a + b + c;

		return http().call(VideoApi.class, URL)
                .publishVideo(ChannelID, UserGUID, UserName, Name, ImageUrl, Detail, UploadType, FileDate, FileName, FileExt, StID, sign)
				.map(new Function<JSONObject, Boolean>() {
					@Override
					public Boolean apply(JSONObject object) throws Exception {
						return object.toJSONString().contains("Success");
					}
				})
                .subscribeOn(Schedulers.io());
    }
}
