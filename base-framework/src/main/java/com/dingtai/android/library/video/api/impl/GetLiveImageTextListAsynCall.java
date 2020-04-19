package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.LiveImageTextModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-29
 */
public class GetLiveImageTextListAsynCall extends AbstractAsynCall<List<LiveImageTextModel>> {

    private static final String URL = "base";

    @Inject
    public GetLiveImageTextListAsynCall(){}

    @Override
    public Observable<List<LiveImageTextModel>> call(AsynParams params) {
        String LiveID = params.get("LiveID");
		String Num = params.get("Num");
		String dtop = params.get("dtop");
		String TabCode = params.get("TabCode");
		//业务逻辑
        return http().call(VideoApi.class, URL)
                .getLiveImageTextList(LiveID, Num, dtop, TabCode, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<LiveImageTextModel>>() {
                    @Override
                    public List<LiveImageTextModel> apply(JSONObject object) throws Exception {
                        List<LiveImageTextModel> list = JsonUtil.parseArray(object.getString("LiveEvents"), LiveImageTextModel.class);

                        if(list.size() > 0) {
                            ModelStatusDao dao = database().call(ModelStatusDao.class,true);
                            if(dao != null) {
                                Iterator<LiveImageTextModel> iterator = list.iterator();
                                while (iterator.hasNext()) {
                                    LiveImageTextModel model = iterator.next();
                                    ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq("liveimagetext_zan_" + model.getID())).unique();
                                    model.setGoodPoint(status != null && status.getStatus() == 1);
                                }
                            }
                        }
                        return list;

                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
