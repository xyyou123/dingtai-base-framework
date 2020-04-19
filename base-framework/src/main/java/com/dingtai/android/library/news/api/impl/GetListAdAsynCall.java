package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ADModelDao;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-21
 */
public class GetListAdAsynCall extends AbstractAsynCall<List<ADModel>> {

    private static final String URL = "base";

    @Inject
    public GetListAdAsynCall(){}

    @Override
    public Observable<List<ADModel>> call(AsynParams params) {
        final String Chid = params.get("Chid");
		String sign = params.get("sign");
		final String ADType = params.get("ADType");
		final String ADFor = params.get("ADFor");
		final String IsIndexAD = "4".equals(ADFor) ? "True" : (String) params.get("IsIndexAD");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .getListAd(Chid, sign, ADType, ADFor, IsIndexAD, Resource.API.STID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<ADModel>>() {
                    @Override
                    public List<ADModel> apply(JSONObject object) {
                        List<ADModel> list = JsonUtil.parseArray(object.getString("ads"), ADModel.class);
                        int hash = MessageFormat.format("{0}-{1}-{2}-{3}", Chid, ADType, ADFor, IsIndexAD).hashCode();
                        database().call(ADModelDao.class).queryBuilder().where(ADModelDao.Properties.GenID.eq(hash)).buildDelete().executeDeleteWithoutDetachingEntities();

                        if(list == null) {
                            return new ArrayList<>();
                        }
                        for (ADModel model : list) {
                            model.setGenID(hash);
                        }

                        database().call(ADModelDao.class).insertOrReplaceInTx(list);
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<ADModel>>>() {
                    @Override
                    public ObservableSource<List<ADModel>> apply(Throwable throwable) {
                        int hash = MessageFormat.format("{0}-{1}-{2}-{3}", Chid, ADType, ADFor, IsIndexAD).hashCode();
                        return Observable.just(database().call(ADModelDao.class).queryBuilder().where(ADModelDao.Properties.GenID.eq(hash)).list());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
