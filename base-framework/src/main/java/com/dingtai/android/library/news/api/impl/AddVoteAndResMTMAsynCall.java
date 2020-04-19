package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.news.api.NewsApi;
import com.lnr.android.base.framework.data.asyn.AsynCode;
import com.lnr.android.base.framework.data.asyn.AsynException;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-12-15
 */
public class AddVoteAndResMTMAsynCall extends AbstractAsynCall<Integer> {

    private static final String URL = "base";

    @Inject
    public AddVoteAndResMTMAsynCall(){}

    @Override
    public Observable<Integer> call(AsynParams params) {
        String UserGUID = AccountHelper.getInstance().getUserId();
		final String ResGUID = params.get("ResGUID");
		final int type = params.get("type");
		return http().call(NewsApi.class, URL)
                .AddVoteAndResMTM(UserGUID, ResGUID, type < 0 ? "1" : "2")
                .flatMap(new Function<JSONObject, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(JSONObject jsonObject) throws Exception {
                        String result = jsonObject.getJSONArray("Vote").getJSONObject(0).getString("Result");
                        if("Success".equals(result)) {
                            database().call(ModelStatusDao.class, true).insertOrReplace(new ModelStatus("vote_" + ResGUID, type));
                            return Observable.just(0);
                        }else if("exist".equals(result)) {
                            return Observable.just(1);
                        }
                        return Observable.error(new AsynException(AsynCode.CODE_ERROR, "错误"));
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
