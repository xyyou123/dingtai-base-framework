package com.dingtai.android.library.news.ui.subject.old;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-14
 */
public interface SubjectOldListContract {

    interface View extends IView {
        void refresh(boolean result, String message, List<NewsListModel> list);
        void loadMore(boolean result, String message, List<NewsListModel> list);
    }

    interface  Presenter extends IPresenter<View> {
        void refresh(String id, int count);
        void loadMore(String id, int offset, int count);
    }
}
