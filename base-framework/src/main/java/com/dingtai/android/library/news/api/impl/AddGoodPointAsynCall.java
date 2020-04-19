package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
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
public class AddGoodPointAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public AddGoodPointAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        final String Cid = params.get("Cid");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .addGoodPoint(Cid, Resource.API.STID, Resource.API.SIGN)
                .map(new Function<JSONObject, Boolean>() {
                    @Override
                    public Boolean apply(JSONObject object) throws Exception {
                        if(object.toJSONString().contains("Success")) {
                            database().call(ModelStatusDao.class, true).insertOrReplace(new ModelStatus("News_comment_" + Cid, 1));
                            RxBus.getDefault().post(new ScoreModel(Score.SCORE_DIG_PAI));
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
