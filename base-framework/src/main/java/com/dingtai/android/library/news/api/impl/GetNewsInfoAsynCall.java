package com.dingtai.android.library.news.api.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.db.NewsDetailModelDao;
import com.dingtai.android.library.news.model.NewsDetailModel;
import com.dingtai.android.library.news.model.RelatedReaderModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-22
 */
public class GetNewsInfoAsynCall extends AbstractAsynCall<NewsDetailModel> {

    private static final String URL = "base";

    @Inject
    public GetNewsInfoAsynCall(){}

    @Override
    public Observable<NewsDetailModel> call(AsynParams params) {
        final String guid = params.get("guid");
		String sign = params.get("sign");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .getNewsInfo(guid, Resource.API.SIGN, Resource.API.STID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, NewsDetailModel>() {
                    @Override
                    public NewsDetailModel apply(JSONObject jsonObject) {
                        NewsDetailModel newsDetailModel = JsonUtil.parseObject(jsonObject.toJSONString(), NewsDetailModel.class);
                        if(newsDetailModel == null || TextUtils.isEmpty(newsDetailModel.getResourceGUID())) return null;

                        JSONObject relatedNews = jsonObject.getJSONObject("RelatedNews");
                        if(relatedNews != null) {
                            newsDetailModel.setRelatedNews(JsonUtil.parseArray(relatedNews.getString("NewsList"), RelatedReaderModel.class));
                        }
                        database().call(NewsDetailModelDao.class).insertOrReplace(newsDetailModel);
                        return newsDetailModel;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends NewsDetailModel>>() {
                    @Override
                    public ObservableSource<? extends NewsDetailModel> apply(Throwable throwable) {
                        return Observable.just(database().call(NewsDetailModelDao.class).queryBuilder()
                                .where(NewsDetailModelDao.Properties.ResourceGUID.eq(guid)).unique());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
