package com.dingtai.android.library.video.ui.vod.child;

import com.dingtai.android.library.video.model.VodProgramModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-08-29
 */
public interface VodListChildContract {

    interface View extends IView {

        void GetDownConetent(boolean result, String message, List<VodProgramModel> list);

        void GetUpContent(boolean result, String message, List<VodProgramModel> list);
    }

    interface  Presenter extends IPresenter<View> {

        void GetDownConetent(String ID, String top);

        void GetUpContent(String ID, String top, String dtop);

    }
}
