package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.model.VideoCommentModel;
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
 * date:2018-11-06
 */
public class GetVideoCommentListAsynCall extends AbstractAsynCall<List<VideoCommentModel>> {

    private static final String URL = "base";

    @Inject
    public GetVideoCommentListAsynCall(){}

    @Override
    public Observable<List<VideoCommentModel>> call(AsynParams params) {
		String MID = params.get("MID");
        String top = params.get("top");
        String dtop = params.get("dtop");
        String action = "0".equals(dtop) ? "GetMediaCommentList" : "GetMediaCommentListShangla";
        String StID = Resource.API.STID;
		String sign = Resource.API.SIGN;
		        return http().call(VideoApi.class, URL)
                .getVideoCommentList(action, MID, top, dtop, StID, sign)
                        .map(new CallResultDecodeBase64<JSONObject>())
                        .map(new Function<JSONObject, List<VideoCommentModel>>() {
                            @Override
                            public List<VideoCommentModel> apply(JSONObject object) throws Exception {
                                return JsonUtil.parseArray(object.getString("MediaComment"), VideoCommentModel.class);
                            }
                        })
                .subscribeOn(Schedulers.io());
    }
}
