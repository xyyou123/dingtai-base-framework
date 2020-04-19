package com.dingtai.android.library.news.ui.home.more;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-12-18
 */
public interface NewsFirstMoreContract {

    interface  Presenter extends IPresenter<RecyclerViewConract.View> {
        void getNewsFirstMoreNews(String type, String top, String dtop);
    }
}