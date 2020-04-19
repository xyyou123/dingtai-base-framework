package com.dingtai.android.library.news.api.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.db.ChannelModelDao;
import com.dingtai.android.library.news.model.ChannelModel;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.SP;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-22
 */
public class GetNewsChannelListAsynCall extends AbstractAsynCall<List<ChannelModel>> {

    private static final String URL = "base";

    @Inject
    public GetNewsChannelListAsynCall(){}

    @Override
    public Observable<List<ChannelModel>> call(AsynParams params) {
        final String parentID = params.get("parentID");
        String action = params.get("action");
        if(action == null) {
            action = "GetNewsChannelList";
        }
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .GetNewsChannelList(action, parentID, Resource.API.STID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<ChannelModel>>() {
                    @Override
                    public List<ChannelModel> apply(JSONObject jsonObject) {
                        List<ChannelModel> modelList = JsonUtil.parseArray(jsonObject.getString("newsChannel"), ChannelModel.class);
                        database().call(ChannelModelDao.class).queryBuilder()
                                .where(ChannelModelDao.Properties.ParentId.eq(parentID)).buildDelete().executeDeleteWithoutDetachingEntities();

                        if(modelList == null) {
                            return new ArrayList<>();
                        }

                        if(modelList.size() == 1 && TextUtils.isEmpty(modelList.get(0).getID())) {
                            return modelList;
                        }

                        Iterator<ChannelModel> iterator = modelList.iterator();

                        if(GlobalConfig.HOME_CHANNEL_FILTER != null) {
                            while (iterator.hasNext()) {
                                ChannelModel model = iterator.next();
                                if(!TextUtils.isEmpty(model.getType()) && GlobalConfig.HOME_CHANNEL_FILTER.contains(model.getType())) {
                                    model.setParentId(parentID);
                                }else {
                                    iterator.remove();
                                }
                            }
                        }

                        database().call(ChannelModelDao.class).insertOrReplaceInTx(modelList);
                        List<ChannelModel> list = new ArrayList<>();
                        String sort = SP.getDefault().getString("NewsSubscription_sort_" + parentID, null);
                        if(sort == null) {
                            list = modelList;
                        }else {
                            ChannelModel m = new ChannelModel();

                            String[] strings = sort.split("@");
                            for (String id : strings) {
                                m.setID(id);
                                int i = modelList.indexOf(m);
                                if (i >= 0) {
                                    list.add(modelList.get(i));
                                }
                            }
                        }
                        return list;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<ChannelModel>>>() {
                    @Override
                    public ObservableSource<? extends List<ChannelModel>> apply(Throwable throwable) {
                        return Observable.just(database().call(ChannelModelDao.class).queryBuilder()
                                .where(ChannelModelDao.Properties.ParentId.eq(parentID)).list());
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
