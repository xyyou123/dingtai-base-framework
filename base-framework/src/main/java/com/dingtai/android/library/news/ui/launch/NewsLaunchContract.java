package com.dingtai.android.library.news.ui.launch;

import com.dingtai.android.library.news.model.LaunchAdModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018-09-26
 */
public interface NewsLaunchContract {

    interface View extends IView {
        void GetOpenPicByStID(LaunchAdModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void GetOpenPicByStID(String app);
    }
}
