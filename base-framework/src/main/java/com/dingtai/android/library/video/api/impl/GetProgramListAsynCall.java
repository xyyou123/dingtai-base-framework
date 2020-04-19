package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.db.LiveProgramModelDao;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-04
 */
public class GetProgramListAsynCall extends AbstractAsynCall<List<LiveProgramModel>> {

    private static final String URL = "base";

    @Inject
    public GetProgramListAsynCall(){}

    @Override
    public Observable<List<LiveProgramModel>> call(AsynParams params) {
        final String LiveID = params.get("LiveID");
		final String week = params.get("week");
		String TabCode = params.get("TabCode");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetProgramList(LiveID, week, TabCode, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<LiveProgramModel>>() {
                    @Override
                    public List<LiveProgramModel> apply(JSONObject object) throws Exception {
                        List<LiveProgramModel> list = JsonUtil.parseArray(object.getString("LiveProgramList"), LiveProgramModel.class);
                        if(list == null || list.isEmpty()) {
                            return new ArrayList<>();
                        }

                        LiveProgramModelDao dao = database().call(LiveProgramModelDao.class);
                        dao.queryBuilder().where(LiveProgramModelDao.Properties.LChID.eq(LiveID),
                                LiveProgramModelDao.Properties.Week.eq(week)).buildDelete().executeDeleteWithoutDetachingEntities();

                        dao.insertOrReplaceInTx(list);
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<LiveProgramModel>>>() {
                    @Override
                    public ObservableSource<? extends List<LiveProgramModel>> apply(Throwable throwable) throws Exception {
                        return Observable.just(database().call(LiveProgramModelDao.class)
                                .queryBuilder().where(LiveProgramModelDao.Properties.LChID.eq(LiveID),
                                        LiveProgramModelDao.Properties.Week.eq(week)).list());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
