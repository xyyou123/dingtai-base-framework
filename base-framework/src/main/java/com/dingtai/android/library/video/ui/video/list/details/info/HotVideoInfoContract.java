package com.dingtai.android.library.video.ui.video.list.details.info;

import com.dingtai.android.library.video.model.VideoDetailsModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018-11-06
 */
public interface HotVideoInfoContract {

    interface View extends IView {
        void getHotVideoDetails(VideoDetailsModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void getHotVideoDetails(String id);
    }
}