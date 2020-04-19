package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.NewsCollectModelDao;
import com.dingtai.android.library.model.models.NewsCollectModel;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/**
 * author:lnr
 * date:2018-09-12
 */
public class DelUserCollectAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public DelUserCollectAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        final String id = params.get("id");
        //业务逻辑
        return Observable.just(database().call(NewsCollectModelDao.class, true).queryBuilder()
        .where(NewsCollectModelDao.Properties.ResourceGUID.eq(id)).list())
                .flatMap(new Function<List<NewsCollectModel>, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(List<NewsCollectModel> newsCollectModels) throws Exception {
                        if(newsCollectModels.size() <= 0) {
                            return Observable.just(true);
                        }
                        return http().call(NewsApi.class, URL)
                                .DelUserCollect(newsCollectModels.get(0).getID(), Resource.API.STID, Resource.API.SIGN)
                                .map(new Function<JSONObject, Boolean>() {
                                    @Override
                                    public Boolean apply(JSONObject object) throws Exception {
                                        if(object.toJSONString().contains("Success")) {
                                            database().call(NewsCollectModelDao.class, true).queryBuilder()
                                                    .where(NewsCollectModelDao.Properties.ResourceGUID.eq(id)).buildDelete().executeDeleteWithoutDetachingEntities();
                                            return true;
                                        }
                                        return false;
                                    }
                                });
                    }
                });
    }
}
