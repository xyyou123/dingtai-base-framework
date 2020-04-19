package com.dingtai.android.library.news.ui.search;

import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018-09-27
 */
public interface NewsSearchContract {

    interface View extends IView {
        void getNewsKeyWord(String key);
    }

    interface  Presenter extends IPresenter<View> {
        void getNewsKeyWord();
    }
}
