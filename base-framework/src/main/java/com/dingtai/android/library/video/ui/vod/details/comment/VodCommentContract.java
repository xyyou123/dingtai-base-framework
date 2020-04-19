package com.dingtai.android.library.video.ui.vod.details.comment;

import com.dingtai.android.library.video.model.VodCommentModel;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2019-01-09
 */
public interface VodCommentContract {

    interface View extends RecyclerViewConract.View {
        void addCommentZan(boolean result, VodCommentModel item);

        void addVodComment(boolean result);

        void addVodReplayComment(boolean result);
    }

    interface Presenter extends IPresenter<View> {
        void getVodCommentList(String vodId);

        void addCommentZan(VodCommentModel item);

        void addVodComment(String toId, String content);

        void addVodReplayComment(String toId, String content);
    }
}