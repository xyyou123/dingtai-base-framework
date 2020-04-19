package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.db.VodListModelDao;
import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-29
 */
public class GetResByTrueProgramAsynCall extends AbstractAsynCall<List<VodListModel>> {

    private static final String URL = "base";

    @Inject
    public GetResByTrueProgramAsynCall(){}

    @Override
    public Observable<List<VodListModel>> call(AsynParams params) {
        String RecType = params.get("RecType");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetResByTrueProgram(RecType, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<VodListModel>>() {
                    @Override
                    public List<VodListModel> apply(JSONObject object) {
                        List<VodListModel> list = JsonUtil.parseArray(object.getString("ByTrueProgram"), VodListModel.class);
                        if(list == null || list.isEmpty()) {
                            return list;
                        }

                        VodListModelDao dao = database().call(VodListModelDao.class);
                        dao.deleteAll();
                        dao.insertOrReplaceInTx(list);
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<VodListModel>>>() {
                    @Override
                    public ObservableSource<? extends List<VodListModel>> apply(Throwable throwable) {
                        return Observable.just(database().call(VodListModelDao.class).loadAll());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
