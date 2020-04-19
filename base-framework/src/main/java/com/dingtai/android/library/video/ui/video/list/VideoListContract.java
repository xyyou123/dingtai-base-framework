package com.dingtai.android.library.video.ui.video.list;

import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-11-06
 */
public interface VideoListContract {

    interface View extends RecyclerViewConract.View {
        void addZan(boolean result, VideoModel model);

        void deleteZan(boolean result, VideoModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void getHotVideoList(String chid, String top, String dtop);

        void addZan(VideoModel model);

        void deleteZan(VideoModel model);
    }
}