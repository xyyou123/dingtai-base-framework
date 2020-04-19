package com.dingtai.android.library.news.ui.relevant;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-09-28
 */
public interface NewsRelevantContract {

    interface  Presenter extends IPresenter<RecyclerViewConract.View> {
        void getLiveNews(String id);
    }
}
