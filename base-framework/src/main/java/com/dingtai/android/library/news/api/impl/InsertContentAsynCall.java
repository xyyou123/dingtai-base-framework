package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Score;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.rx.RxBus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-14
 */
public class    InsertContentAsynCall extends AbstractAsynCall<Integer> {

    private static final String URL = "base";

    @Inject
    public InsertContentAsynCall(){}

    @Override
    public Observable<Integer> call(AsynParams params) {
        String userGUID = AccountHelper.getInstance().getUserId();
		String commentContent = params.get("commentContent");
		String GetGoodPoint = params.get("GetGoodPoint");
		String pid = params.get("pid");
		String rid = params.get("rid");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .InsertContent(userGUID, commentContent, GetGoodPoint, pid, rid, Resource.API.STID, Resource.API.SIGN)
                .map(new Function<JSONObject, Integer>() {
                    @Override
                    public Integer apply(JSONObject object) throws Exception {
                        String result = object.toJSONString();
                        if(result.contains("Success")) {
                            RxBus.getDefault().post(new ScoreModel(Score.SCORE_REPLY_NEWS));
                            return 1;
                        }else if(result.contains("5omL5py65Y+356CB5pyq57uR5a6aIQ==")) {
                            return -1;
                        }
                        return 0;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
