package com.dingtai.android.library.video.ui.vod;

import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-08-29
 */
public interface VodListContract {

    interface View extends IView {
        void GetResByTrueProgram(boolean result, String message, List<VodListModel> list);
    }

    interface  Presenter extends IPresenter<View> {
        void GetResByTrueProgram(String RecType);
    }
}
