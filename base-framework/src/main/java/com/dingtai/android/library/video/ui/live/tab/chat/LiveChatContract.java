package com.dingtai.android.library.video.ui.live.tab.chat;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-09-03
 */
public interface LiveChatContract {

    interface View extends RecyclerViewConract.View {

        void addComment(boolean result);
    }

    interface  Presenter extends IPresenter<View> {
        void GetNewsComment(int type, String LiveID, String Num, String dtop, String TabCode);

        void addComment(int type, boolean child, String id, String content);
    }
}
