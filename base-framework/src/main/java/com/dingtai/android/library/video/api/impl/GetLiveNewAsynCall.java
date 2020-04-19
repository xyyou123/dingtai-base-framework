package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.db.LiveChannelModelDao;
import com.dingtai.android.library.video.model.LiveChannelModel;
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
 * date:2018-08-30
 */
public class GetLiveNewAsynCall extends AbstractAsynCall<List<LiveChannelModel>> {

    private static final String URL = "base";
    private static final String DefaulType = "0";

    @Inject
    public GetLiveNewAsynCall(){}

    @Override
    public Observable<List<LiveChannelModel>> call(AsynParams params) {

        final String top = params.get("top");
		final String dtop = params.get("dtop");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .GetLive( top, dtop, Resource.API.STID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<LiveChannelModel>>() {
                    @Override
                    public List<LiveChannelModel> apply(JSONObject object) {
                        LiveChannelModelDao dao = database().call(LiveChannelModelDao.class);
                        dao.queryBuilder().where(LiveChannelModelDao.Properties.ID.eq(DefaulType)).buildDelete().executeDeleteWithoutDetachingEntities();

                        List<LiveChannelModel> list = JsonUtil.parseArray(object.getString("LiveChannel"), LiveChannelModel.class);
                        if(list == null || list.isEmpty()) {
                            return new ArrayList<>();
                        }else {
                            dao.insertOrReplaceInTx(list);
                        }
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<LiveChannelModel>>>() {
                    @Override
                    public ObservableSource<? extends List<LiveChannelModel>> apply(Throwable throwable) {
                        return Observable.just(database().call(LiveChannelModelDao.class)
                                .queryBuilder()
                                .where(LiveChannelModelDao.Properties.ID.eq(DefaulType))
                                .offset(NumberUtil.parseInt(dtop, 0))
                        .limit(NumberUtil.parseInt(top, Resource.API.PAGE)).list());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
