package com.dingtai.android.library.news.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.AccountModelDao;
import com.dingtai.android.library.model.db.NewsCollectModelDao;
import com.dingtai.android.library.model.models.AccountModel;
import com.dingtai.android.library.model.models.NewsCollectModel;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-14
 */
public class InsertUserCollectAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public InsertUserCollectAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        String CollectType = params.get("CollectType");
		final String CollectID = params.get("CollectID");

        AccountModel model = database().call(AccountModelDao.class).queryBuilder().unique();
        String UserGUID = model.getUserGUID();
		String userName = model.getUserName();
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .InsertUserCollect(CollectType, CollectID, UserGUID, userName, Resource.API.STID, Resource.API.SIGN)
                .map(new Function<JSONObject, Boolean>() {
                    @Override
                    public Boolean apply(JSONObject object) throws Exception {
                        object = object.getJSONArray("RegisterUserCollect").getJSONObject(0);
                        if("Success".equals(object.getString("Result")) || "5bey5a2Y5Zyo5pS26JeP6K6w5b2V".equals(object.getString("ErrorMessage"))) {
                            NewsCollectModel model = new NewsCollectModel();
                            model.setID(object.getString("ID"));
                            model.setResourceGUID(CollectID);
                            database().call(NewsCollectModelDao.class, true).insertOrReplace(model);
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
