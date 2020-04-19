package com.dingtai.android.library.news.ui.search.result;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-27
 */
public interface NewsSearchResultContract {

    interface View extends IView {
        void searchByKeyword(boolean result, String message, List<NewsListModel> list);
    }

    interface  Presenter extends IPresenter<View> {
        void searchByKeyword(String keyword, String top, String dtop);
    }
}
