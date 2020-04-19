package com.dingtai.android.library.video.ui.shortvideo.list;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2019-03-11
 */
public interface ShortVideoListContract {

    interface View extends RecyclerViewConract.View  {

    }

    interface  Presenter extends IPresenter<View> {
        void getData(String num, String dtop, String StID);
    }
}