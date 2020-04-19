package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.db.VodProgramModelDao;
import com.dingtai.android.library.video.model.VodProgramModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.ArrayList;
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
public class GetUpContentAsynCall extends AbstractAsynCall<List<VodProgramModel>> {

    private static final String URL = "base";

    @Inject
    public GetUpContentAsynCall(){}

    @Override
    public Observable<List<VodProgramModel>> call(AsynParams params) {
        final String ID = params.get("ID");
        final String top = params.get("top");
        final String dtop = params.get("dtop");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetUpContent(ID, top, dtop, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<VodProgramModel>>() {
                    @Override
                    public List<VodProgramModel> apply(JSONObject object) {
                        List<VodProgramModel> list = JsonUtil.parseArray(object.getString("VODChannelsContent"), VodProgramModel.class);

                        VodProgramModelDao dao = database().call(VodProgramModelDao.class);
                        dao.queryBuilder().where(VodProgramModelDao.Properties.ID.eq(ID)).buildDelete().executeDeleteWithoutDetachingEntities();
                        if(list == null || list.isEmpty()) {
                            return new ArrayList<>();
                        }else {
                            dao.insertOrReplaceInTx(list);
                        }
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<VodProgramModel>>>() {
                    @Override
                    public ObservableSource<? extends List<VodProgramModel>> apply(Throwable throwable) {
                        return Observable.just( database().call(VodProgramModelDao.class)
                                .queryBuilder().where(VodProgramModelDao.Properties.ID.eq(ID))
                                .orderDesc(VodProgramModelDao.Properties.CreateTime)
                                .offset(NumberUtil.parseInt(dtop, 0))
                                .limit(NumberUtil.parseInt(top, Resource.API.PAGE))
                                .list());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
