package com.dingtai.android.library.news.ui.list.more;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-18
 */
public interface NewsListMoreContract {

    interface View extends IView {
        void refresh(boolean result, String message, List<NewsListModel> models);
        void load(boolean result, String message, List<NewsListModel> models);
    }

    interface  Presenter extends IPresenter<View> {

        void refresh(String action, String CHID, String DeptID, String top);

        void load(String action, String CHID, String DeptID, String top, String dtop);
    }
}
