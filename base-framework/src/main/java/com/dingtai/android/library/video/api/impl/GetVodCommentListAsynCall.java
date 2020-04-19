package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.VodCommentModel;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2019-01-09
 */
public class GetVodCommentListAsynCall extends AbstractAsynCall<List<VodCommentModel>> {

    private static final String URL = "base";

    @Inject
    public GetVodCommentListAsynCall(){}

    @Override
    public Observable<List<VodCommentModel>> call(AsynParams params) {
        String VodID = params.get("VodID");
		String STid = Resource.API.STID;
		return http().call(VideoApi.class, URL)
                .getVodCommentList(VodID, STid)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<VodCommentModel>>() {
                    @Override
                    public List<VodCommentModel> apply(JSONObject jsonObject) throws Exception {
                        return JsonUtil.parseArray(jsonObject.getString("Comments"), VodCommentModel.class);
                    }
                })
                .map(new Function<List<VodCommentModel>, List<VodCommentModel>>() {
                    @Override
                    public List<VodCommentModel> apply(List<VodCommentModel> list) throws Exception {
                        ModelStatusDao dao = database().call(ModelStatusDao.class, true);
                        if(dao != null) {
                            for (VodCommentModel model : list) {
                                ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq("Vod_comment_" + model.getID())).unique();
                                model.setGoodPoint(status != null && status.getStatus() == 1);
                            }
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
