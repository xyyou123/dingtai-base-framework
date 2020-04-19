package com.dingtai.android.library.video.ui.video.list.details.info;

import com.dingtai.android.library.video.api.impl.GetVideoDetailsAsynCall;
import com.dingtai.android.library.video.model.VideoDetailsModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-06
 */
@ActivityScope
public class HotVideoInfoPresenter extends AbstractPresenter<HotVideoInfoContract.View> implements HotVideoInfoContract.Presenter {

    @Inject
    GetVideoDetailsAsynCall mGetHotVideoDetailsAsynCall;

    @Inject
    public HotVideoInfoPresenter(){}

    @Override
    public void getHotVideoDetails(String id) {
        excuteNoLoading(mGetHotVideoDetailsAsynCall, AsynParams.create("VideoID", id), new AsynCallback<VideoDetailsModel>() {
            @Override
            public void onCallResponse(VideoDetailsModel model) {
                view().getHotVideoDetails(model);
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().getHotVideoDetails(null);
            }
        });
    }
}