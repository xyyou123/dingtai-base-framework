package com.dingtai.android.library.video.ui.video.tab;

import com.dingtai.android.library.video.api.impl.GetMediaChannelsListAsynCall;
import com.dingtai.android.library.video.model.VideoChannelModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-14
 */
@ActivityScope
public class VideoTabPresenter extends AbstractPresenter<VideoTabContract.View> implements VideoTabContract.Presenter {

    @Inject
    GetMediaChannelsListAsynCall mGetMediaChannelsListAsynCall;

    @Inject
    public VideoTabPresenter(){}

    @Override
    public void getMediaChannelsList(String parentId) {
        excuteNoLoading(mGetMediaChannelsListAsynCall, AsynParams.create("ParentID", parentId), new AsynCallback<List<VideoChannelModel>>() {
            @Override
            public void onCallResponse(List<VideoChannelModel> list) {
                view().getMediaChannelsList(list);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().getMediaChannelsList(null);
            }
        });
    }
}