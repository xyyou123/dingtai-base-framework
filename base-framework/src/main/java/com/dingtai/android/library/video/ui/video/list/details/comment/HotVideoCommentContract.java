package com.dingtai.android.library.video.ui.video.list.details.comment;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-11-06
 */
public interface HotVideoCommentContract {

    interface View extends RecyclerViewConract.View {
        void addComment(boolean result);
    }

    interface  Presenter extends IPresenter<View> {
        void getHotVideoCommentList(String id, String top, String dtop);

        void addComment(String id, String content);
    }
}