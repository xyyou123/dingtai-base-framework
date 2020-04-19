package com.dingtai.android.library.video.ui.video.upload.my;

import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-12-04
 */
public interface MyUploadVideoListContract {

    interface View extends RecyclerViewConract.View {
        void delete(boolean result, VideoModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void getMyUploadVideoList();

        void delete(VideoModel model);
    }
}