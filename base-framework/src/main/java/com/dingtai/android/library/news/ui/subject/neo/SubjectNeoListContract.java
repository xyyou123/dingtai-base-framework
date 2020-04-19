package com.dingtai.android.library.news.ui.subject.neo;

import com.dingtai.android.library.news.model.SubjectNeoRootModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018-09-17
 */
public interface SubjectNeoListContract {

    interface View extends IView {
        void GetChannAndNews(boolean result, String message, SubjectNeoRootModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void GetChannAndNews(String ParentID);
    }
}
