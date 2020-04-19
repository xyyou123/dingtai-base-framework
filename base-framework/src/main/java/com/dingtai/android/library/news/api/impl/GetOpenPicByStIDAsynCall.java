package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.db.LaunchAdModelDao;
import com.dingtai.android.library.news.model.LaunchAdModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-26
 */
public class GetOpenPicByStIDAsynCall extends AbstractAsynCall<LaunchAdModel> {

    private static final String URL = "base";

    @Inject
    public GetOpenPicByStIDAsynCall(){}

    @Override
    public Observable<LaunchAdModel> call(AsynParams params) {
        String ForApp = params.get("ForApp");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .GetOpenPicByStID(ForApp, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, LaunchAdModel>() {
                    @Override
                    public LaunchAdModel apply(JSONObject object) throws Exception {
                        List<LaunchAdModel> models = JsonUtil.parseArray(object.getString("OpenPic"), LaunchAdModel.class);
                        if(models == null || models.isEmpty()) {
                            return null;
                        }
                        database().call(LaunchAdModelDao.class).deleteAll();
                        database().call(LaunchAdModelDao.class).insertOrReplace(models.get(0));
                        return models.get(0);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
