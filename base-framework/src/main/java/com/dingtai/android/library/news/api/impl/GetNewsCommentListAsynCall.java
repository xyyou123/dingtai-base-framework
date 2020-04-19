package com.dingtai.android.library.news.api.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.news.api.NewsApi;
import com.dingtai.android.library.news.model.NewsCommentModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.data.asyn.CallResultDecodeBase64;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.uitl.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author:lnr
 * date:2018-09-26
 */
public class GetNewsCommentListAsynCall extends AbstractAsynCall<List<NewsCommentModel>> {

    private static final String URL = "base";

    @Inject
    public GetNewsCommentListAsynCall(){}

    @Override
    public Observable<List<NewsCommentModel>> call(AsynParams params) {
        String rid = params.get("rid");
		String num = params.get("num");
		String drop = params.get("drop");
		String FORAPP = params.get("FORAPP");
		//业务逻辑
        return http().call(NewsApi.class, URL)
                .getNewsCommentList(rid, num, drop, FORAPP, Resource.API.STID, Resource.API.SIGN)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<NewsCommentModel>>() {
                    @Override
                    public List<NewsCommentModel> apply(JSONObject object) throws Exception {
                        String result = object.getString("comments");
                        List<NewsCommentModel> list = JsonUtil.parseArray(result, NewsCommentModel.class);
                        if(list.size() == 1) {
                            NewsCommentModel obj = list.get(0);
                            if(obj == null || TextUtils.isEmpty(obj.getUserGUID())) {
                                return new ArrayList<>();
                            }
                        }

                        ModelStatusDao dao = database().call(ModelStatusDao.class, true);
                        if(dao != null) {
                            for (NewsCommentModel model : list) {
                                if(model.getSubCommentList() != null) {
                                    model.setSubList(JsonUtil.parseArray(model.getSubCommentList().getString("comments"), NewsCommentModel.class));
                                    if(model.getSubList() != null) {
                                        for (NewsCommentModel subModel : model.getSubList()) {
                                            ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq("News_comment_" + subModel.getID())).unique();
                                            subModel.setGoodPoint(status != null && status.getStatus() == 1);
                                        }
                                    }
                                }
                                ModelStatus status = dao.queryBuilder().where(ModelStatusDao.Properties.Key.eq("News_comment_" + model.getID())).unique();
                                model.setGoodPoint(status != null && status.getStatus() == 1);
                            }
                        }else {
                            for (NewsCommentModel model : list) {
                                if(model.getSubCommentList() != null) {
                                    model.setSubList(JsonUtil.parseArray(model.getSubCommentList().getString("comments"), NewsCommentModel.class));
                                }
                            }
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io());
    }

}
