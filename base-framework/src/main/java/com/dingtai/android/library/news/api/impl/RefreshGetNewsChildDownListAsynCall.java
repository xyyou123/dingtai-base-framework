package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.db.NewsListModelDao;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;

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
public class RefreshGetNewsChildDownListAsynCall extends AbstractAsynCall<List<NewsListModel>> {

    private static final String URL = "base";

    @Inject
    public RefreshGetNewsChildDownListAsynCall(){}

    @Override
    public Observable<List<NewsListModel>> call(AsynParams params) {
        final String top = params.get("top");
		String sign = Resource.API.SIGN;
		final String chid = params.get("chid");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .refreshGetNewsChildDownList(top, Resource.API.SIGN, chid, Resource.API.STID)
                .subscribeOn(Schedulers.io())
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<NewsListModel>>() {
                    @Override
                    public List<NewsListModel> apply(JSONObject object) {
                        List<NewsListModel> models = JsonUtil.parseArray(object.getString("newses"), NewsListModel.class);
                        database().call(NewsListModelDao.class).insertOrReplaceInTx(models);
                        return models;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<NewsListModel>>>() {
                    @Override
                    public ObservableSource<List<NewsListModel>> apply(Throwable throwable) {
                        return Observable.just(database().call(NewsListModelDao.class).queryBuilder()
                                .where(NewsListModelDao.Properties.ChID.eq(chid))
                                .orderDesc(NewsListModelDao.Properties.AuditTime)
                        .limit(NumberUtil.parseInt(top, Resource.API.PAGE)).list());
                    }
                })
                .map(new Function<List<NewsListModel>, List<NewsListModel>>() {
                    @Override
                    public List<NewsListModel> apply(List<NewsListModel> list) {
                        if(list == null || list.isEmpty()) {
                            return list;
                        }
                        ModelStatusDao dao = database().call(ModelStatusDao.class);
                        for (NewsListModel m : list) {
                            ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq(m.getChID() + "-" + m.getResourceGUID())).unique();
                            m.setIsRead(status != null && 1 == status.getStatus());
                        }
                        return list;
                    }
                });
    }
}
