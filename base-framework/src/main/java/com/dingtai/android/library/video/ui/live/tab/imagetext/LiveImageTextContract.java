package com.dingtai.android.library.video.ui.live.tab.imagetext;

import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-09-29
 */
public interface LiveImageTextContract {

    interface  Presenter extends IPresenter<RecyclerViewConract.View> {
        void getLiveImageTextList(String id, String top, String dtop);
    }
}
