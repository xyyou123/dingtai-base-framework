package com.dingtai.android.library.video.ui.video.upload.publish;

import com.dingtai.android.library.video.model.VideoChannelModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-12-04
 */
public interface UploadVideoContract {

    interface View extends IView {
        void getMediaChannelsList(List<VideoChannelModel> list);
    }

    interface  Presenter extends IPresenter<View> {
        void getMediaChannelsList(String parentId);
    }
}