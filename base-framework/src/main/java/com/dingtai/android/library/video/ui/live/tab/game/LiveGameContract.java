package com.dingtai.android.library.video.ui.live.tab.game;

import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-09-05
 */
public interface LiveGameContract {
    interface  Presenter extends IPresenter<RecyclerViewConract.View> {

        void refresh(AsynParams params);
    }
}
