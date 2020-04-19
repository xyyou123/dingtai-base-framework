package com.dingtai.android.library.video.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.VideoApi;
import com.dingtai.android.library.video.db.VideoChannelModelDao;
import com.dingtai.android.library.video.model.VideoChannelModel;
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
 * date:2018-11-14
 */
public class GetMediaChannelsListAsynCall extends AbstractAsynCall<List<VideoChannelModel>> {

    private static final String URL = "base";

    @Inject
    public GetMediaChannelsListAsynCall(){}

    @Override
    public Observable<List<VideoChannelModel>> call(AsynParams params) {
        String ParentID = params.get("ParentID");
        if(ParentID == null) {
            ParentID = "00000000-0000-0000-0000-000000000000";
        }
		String StID = Resource.API.STID;
		return http().call(VideoApi.class, URL)
                .getMediaChannelsList(ParentID, StID)
                .map(new CallResultDecodeBase64<JSONObject>())
                .map(new Function<JSONObject, List<VideoChannelModel>>() {
                    @Override
                    public List<VideoChannelModel> apply(JSONObject object) throws Exception {
                        List<VideoChannelModel> mediaChannels = JsonUtil.parseArray(object.getString("MediaChannels"), VideoChannelModel.class);
                        VideoChannelModelDao dao = database().call(VideoChannelModelDao.class);
                        dao.deleteAll();
                        dao.saveInTx(mediaChannels);
                        return mediaChannels;
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
