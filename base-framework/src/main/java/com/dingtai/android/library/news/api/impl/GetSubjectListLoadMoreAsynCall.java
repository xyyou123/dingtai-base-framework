package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.model.NewsListModel;
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
 * date:2018-09-14
 */
public class GetSubjectListLoadMoreAsynCall extends AbstractAsynCall<List<NewsListModel>> {

    private static final String URL = "base";

    @Inject
    public GetSubjectListLoadMoreAsynCall(){}

    @Override
    public Observable<List<NewsListModel>> call(AsynParams params) {
        String chid = params.get("chid");
		String top = params.get("top");
		String dtop = params.get("dtop");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .getSubjectListLoadMore(chid, top, dtop, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<NewsListModel>>() {
                    @Override
                    public List<NewsListModel> apply(JSONObject object) throws Exception {
                        List<NewsListModel> list = JsonUtil.parseArray(object.getString("newses"), NewsListModel.class);
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
