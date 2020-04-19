package com.dingtai.android.library.video.ui.live.list.channel;

import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

/**
 * author:lnr
 * date:2018-08-30
 */
public interface LiveChannelContract {

    interface  Presenter extends IPresenter<RecyclerViewConract.View> {

        void refresh(AsynParams params);
        void loadMore(AsynParams params);
    }
}
