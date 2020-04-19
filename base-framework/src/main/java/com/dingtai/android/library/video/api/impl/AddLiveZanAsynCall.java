package com.dingtai.android.library.video.api.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.AccountModel;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.StringUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-11-07
 */
public class AddLiveZanAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public AddLiveZanAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String ID = params.get("ID");
        AccountModel model = AccountHelper.getInstance().getUser();
		String UserGUID = model.getUserGUID();
		String NickName = TextUtils.isEmpty(model.getUserNickName()) ? StringUtil.formatPhone(model.getUserPhone()) : model.getUserNickName();
		return http().call(VideoApi.class, URL)
                .addLiveZan(ID, UserGUID, NickName)
                .map(new Function<JSONObject, Boolean>() {
                    @Override
                    public Boolean apply(JSONObject object) throws Exception {
                        if(object.toJSONString().contains("Success")) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
