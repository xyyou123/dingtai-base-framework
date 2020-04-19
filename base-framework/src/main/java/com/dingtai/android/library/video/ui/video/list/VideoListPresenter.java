package com.dingtai.android.library.video.ui.video.list;

import com.dingtai.android.library.video.api.impl.AddVideoZanAsynCall;
import com.dingtai.android.library.video.api.impl.DelVideoZanAsynCall;
import com.dingtai.android.library.video.api.impl.GetVideoListAsynCall;
import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-06
 */
@ActivityScope
public class VideoListPresenter extends AbstractPresenter<VideoListContract.View> implements VideoListContract.Presenter {

    @Inject
    GetVideoListAsynCall mGetHotVideoListAsynCall;

    @Inject
    AddVideoZanAsynCall mAddHotVideoZanAsynCall;

    @Inject
    DelVideoZanAsynCall mDelHotVideoZanAsynCall;

    @Inject
    public VideoListPresenter(){}

    @Override
    public void getHotVideoList(String chid, String top, final String dtop) {
        AsynParams params = AsynParams.create("ChannelID", chid).put("top", top).put("dtop", dtop);
        excuteNoLoading(mGetHotVideoListAsynCall, params, new AsynCallback<List<VideoModel>>() {
            @Override
            public void onCallResponse(List<VideoModel> videoModels) {
                if("0".equals(dtop)) {
                    view().refresh(true, null, videoModels);
                }else {
                    view().load(true, null, videoModels);
                }
            }

            @Override
            public void onCallError(Throwable throwable) {
                if("0".equals(dtop)) {
                    view().refresh(false, null, null);
                }else {
                    view().load(false, null, null);
                }
            }
        });
    }

    @Override
    public void addZan(final VideoModel model) {
        excuteWithLoading(mAddHotVideoZanAsynCall, AsynParams.create("ID", model.getID()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean aBoolean) {
                view().addZan(aBoolean, model);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().addZan(false, model);
            }
        });
    }

    @Override
    public void deleteZan(final VideoModel model) {
        excuteWithLoading(mDelHotVideoZanAsynCall, AsynParams.create("ID", model.getID()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean aBoolean) {
                view().deleteZan(aBoolean, model);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().deleteZan(false, model);
            }
        });
    }
}