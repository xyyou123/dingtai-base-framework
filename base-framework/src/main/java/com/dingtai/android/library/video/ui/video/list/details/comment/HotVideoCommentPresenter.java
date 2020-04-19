package com.dingtai.android.library.video.ui.video.list.details.comment;

import com.dingtai.android.library.video.api.impl.AddVideoCommentAsynCall;
import com.dingtai.android.library.video.api.impl.GetVideoCommentListAsynCall;
import com.dingtai.android.library.video.model.VideoCommentModel;
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
public class HotVideoCommentPresenter extends AbstractPresenter<HotVideoCommentContract.View> implements HotVideoCommentContract.Presenter {

    @Inject
    GetVideoCommentListAsynCall mGetHotVideoCommentListAsynCall;

    @Inject
    AddVideoCommentAsynCall mAddHotVideoCommentAsynCall;

    @Inject
    public HotVideoCommentPresenter(){}

    @Override
    public void getHotVideoCommentList(String id, String top, final String dtop) {
        excuteNoLoading(mGetHotVideoCommentListAsynCall, AsynParams.create("MID", id).put("top", top).put("dtop", dtop), new AsynCallback<List<VideoCommentModel>>() {
            @Override
            public void onCallResponse(List<VideoCommentModel> videoCommentModels) {
                if("0".equals(dtop)) {
                    view().refresh(true, null, videoCommentModels);
                }else {
                    view().load(true, null, videoCommentModels);
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
    public void addComment(String id, String content) {
        excuteWithLoading(mAddHotVideoCommentAsynCall, AsynParams.create("MID", id).put("", content), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer aBoolean) {
                if(aBoolean >= 0) {
                    view().addComment(aBoolean == 1);
                }
            }

            @Override
            public void onCallError(Throwable throwable) {
                view().addComment(false);
            }
        });
    }
}