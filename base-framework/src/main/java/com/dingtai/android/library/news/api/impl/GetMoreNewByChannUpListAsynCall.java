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
 * date:2018-09-18
 */
public class GetMoreNewByChannUpListAsynCall extends AbstractAsynCall<List<NewsListModel>> {

    private static final String URL = "base";

    @Inject
    public GetMoreNewByChannUpListAsynCall(){}

    @Override
    public Observable<List<NewsListModel>> call(AsynParams params) {
        String action = params.get("action");
        String ChID = params.get("ChID");
        String DeptID = params.get("DeptID");
		String top = params.get("top");
		String dtop = params.get("dtop");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .GetMoreNewByChannUpList(action, ChID, DeptID, top, dtop, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<NewsListModel>>() {
                    @Override
                    public List<NewsListModel> apply(JSONObject object) throws Exception {
                        return JsonUtil.parseArray(object.getString("NewsListModel"), NewsListModel.class);
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
