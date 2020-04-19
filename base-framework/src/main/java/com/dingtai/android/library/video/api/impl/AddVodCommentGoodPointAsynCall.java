package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Score;
import com.dingtai.android.library.video.api.VideoApi;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.rx.RxBus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-01-09
 */
public class AddVodCommentGoodPointAsynCall extends AbstractAsynCall<Boolean> {

    private static final String URL = "base";

    @Inject
    public AddVodCommentGoodPointAsynCall(){}

    @Override
    public Observable<Boolean> call(AsynParams params) {
        final String ID = params.get("ID");
        String STid = Resource.API.STID;
		return http().call(VideoApi.class, URL)
                .addVodCommentGoodPoint(ID, STid)
                .map(new Function<JSONObject, Boolean>() {
                    @Override
                    public Boolean apply(JSONObject object) throws Exception {
                        boolean result = object.toJSONString().contains("Success");
                        if(result) {
                            database().call(ModelStatusDao.class, true)
                                    .insertOrReplaceInTx(new ModelStatus("Vod_comment_" + ID, 1));
                            RxBus.getDefault().post(new ScoreModel(Score.SCORE_DIG_PAI));
                        }
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
