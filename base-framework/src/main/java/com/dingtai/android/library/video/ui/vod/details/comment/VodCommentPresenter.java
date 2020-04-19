package com.dingtai.android.library.video.ui.vod.details.comment;

import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.video.api.impl.AddVodCommentAsynCall;
import com.dingtai.android.library.video.api.impl.AddVodCommentGoodPointAsynCall;
import com.dingtai.android.library.video.api.impl.AddVodReplyCommentAsynCall;
import com.dingtai.android.library.video.api.impl.GetVodCommentListAsynCall;
import com.dingtai.android.library.video.model.VodCommentModel;
import com.lnr.android.base.framework.FrameworkConfig;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.event.RealNameAuthenticationEvent;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;
import com.lnr.android.base.framework.rx.RxBus;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2019-01-09
 */
@ActivityScope
public class VodCommentPresenter extends AbstractPresenter<VodCommentContract.View> implements VodCommentContract.Presenter {

    @Inject
    GetVodCommentListAsynCall mGetVodCommentListAsynCall;

    @Inject
    AddVodCommentGoodPointAsynCall mAddVodCommentGoodPointAsynCall;

    @Inject
    AddVodCommentAsynCall mAddVodCommentAsynCall;

    @Inject
    AddVodReplyCommentAsynCall mAddVodReplyCommentAsynCall;

    @Inject
    public VodCommentPresenter(){}

    @Override
    public void getVodCommentList(String vodId) {
        excuteNoLoading(mGetVodCommentListAsynCall, AsynParams.create("VodID", vodId), new AsynCallback<List<VodCommentModel>>() {
            @Override
            public void onCallResponse(List<VodCommentModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, null, null);
            }
        });
    }

    @Override
    public void addCommentZan(final VodCommentModel item) {
        excuteWithLoading(mAddVodCommentGoodPointAsynCall, AsynParams.create("ID", item.getID()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().addCommentZan(r, item);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addCommentZan(false, item);
            }
        });
    }

    @Override
    public void addVodComment(String toId, String content) {
        if(FrameworkConfig.COMMENT_MUST_HAS_PHONE && !"True".equals(AccountHelper.getInstance().getUser().getIsAuthentication())) {
            RxBus.getDefault().post(new RealNameAuthenticationEvent());
            return;
        }
        excuteWithLoading(mAddVodCommentAsynCall, AsynParams.create("VodID", toId).put("CommentContent", content), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
                if(r < 0) return;
                view().addVodComment(r == 1);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addVodComment(false);
            }
        });
    }

    @Override
    public void addVodReplayComment(String toId, String content) {
        excuteWithLoading(mAddVodReplyCommentAsynCall, AsynParams.create("LCID", toId).put("CommentContent", content), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
                if(r < 0) return;
                view().addVodReplayComment(r == 1);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addVodReplayComment(false);
            }
        });
    }
}