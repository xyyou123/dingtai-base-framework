package com.dingtai.android.library.news.ui.details.comment;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-26
 */
public interface NewsDetailsCommentContract {

    interface View extends IView {
        void getNewsCommentList(boolean result, String message, List<NewsCommentModel> list);

        void InsertContent(boolean result);

        void addGoodPoint(boolean result, NewsCommentModel model);
    }

    interface  Presenter extends IPresenter<View> {
        void getNewsCommentList(String id, String forapp, String top, String dtop);

        void InsertContent(String content, String newsID);

        void addGoodPoint(NewsCommentModel comment);
    }
}
