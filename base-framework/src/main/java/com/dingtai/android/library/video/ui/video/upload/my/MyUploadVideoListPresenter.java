package com.dingtai.android.library.video.ui.video.upload.my;

import com.dingtai.android.library.video.api.impl.DelMyUploadVideoListAsynCall;
import com.dingtai.android.library.video.api.impl.GetMyUploadVideoListAsynCall;
import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-12-04
 */
@ActivityScope
public class MyUploadVideoListPresenter extends AbstractPresenter<MyUploadVideoListContract.View> implements MyUploadVideoListContract.Presenter {

    @Inject
    GetMyUploadVideoListAsynCall mGetMyUploadVideoListAsynCall;

    @Inject
    DelMyUploadVideoListAsynCall mDelMyUploadVideoListAsynCall;

    @Inject
    public MyUploadVideoListPresenter(){}

    @Override
    public void getMyUploadVideoList() {
        excuteNoLoading(mGetMyUploadVideoListAsynCall, null, new AsynCallback<List<VideoModel>>() {
            @Override
            public void onCallResponse(List<VideoModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, null, null);
            }
        });
    }

    @Override
    public void delete(final VideoModel model) {
        excuteWithLoading(mDelMyUploadVideoListAsynCall, AsynParams.create("MediaGUID", model.getID2()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().delete(r, model);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().delete(false, model);
            }
        });
    }
}