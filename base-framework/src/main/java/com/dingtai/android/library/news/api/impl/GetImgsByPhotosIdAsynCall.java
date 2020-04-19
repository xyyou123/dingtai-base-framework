package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.model.NewsImageModel;
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
 * date:2018-10-30
 */
public class GetImgsByPhotosIdAsynCall extends AbstractAsynCall<List<NewsImageModel>> {

    private static final String URL = "base";

    @Inject
    public GetImgsByPhotosIdAsynCall(){}

    @Override
    public Observable<List<NewsImageModel>> call(AsynParams params) {
        String PhotosID = params.get("PhotosID");
		String sign = Resource.API.SIGN;
		        return http().call(NewsApi.class, URL)
                .GetImgsByPhotosId(PhotosID, sign)
                        .map(new CallResultDecodeBase64<JSONObject>())
                        .map(new Function<JSONObject, List<NewsImageModel>>() {
                            @Override
                            public List<NewsImageModel> apply(JSONObject object) throws Exception {
                                return JsonUtil.parseArray(object.getString("photos"), NewsImageModel.class);
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
