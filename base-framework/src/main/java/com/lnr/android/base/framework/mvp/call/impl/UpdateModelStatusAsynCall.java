package com.lnr.android.base.framework.mvp.call.impl;

import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-08-23
 */
public class UpdateModelStatusAsynCall extends AbstractAsynCall<String> {

    @Inject
    public UpdateModelStatusAsynCall(){}

    @Override
    public Observable<String> call(AsynParams params) {
        //业务逻辑
        return Observable.just(params)
                .map(new Function<AsynParams, String>() {
                    @Override
                    public String apply(AsynParams asynParams) {
                        ModelStatusDao dao = database().call(ModelStatusDao.class);
                        String key = asynParams.get("key");
                        int status = asynParams.get("status");
                        ModelStatus modelStatus = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq(key)).unique();
                        if(modelStatus == null) {
                            modelStatus = new ModelStatus();
                            modelStatus.setKey(key);
                            modelStatus.setStatus(status);
                        }else {
                            modelStatus.setStatus(status);
                        }
                        dao.insertOrReplace(modelStatus);
                        return "";
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
