package com.dingtai.android.library.news.ui.details.web;

import com.dingtai.android.library.news.model.NewsDetailModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018-08-22
 */
public interface NewsDetailsContract {

    interface View extends IView {
        void getNewsInfo(boolean result, String message, NewsDetailModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void getNewsInfo(String guid, String sign);

 
    }
}
