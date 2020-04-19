package com.dingtai.android.library.news.ui.home;

import com.dingtai.android.library.news.model.ChannelModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-08-22
 */
public interface NewsHomeContract {

    interface View extends IView {

        void GetNewsChannelList(boolean result, String message, List<ChannelModel> models);
    }

    interface  Presenter extends IPresenter<View> {

        void GetNewsChannelList(String action, String parentID);

    }
}
