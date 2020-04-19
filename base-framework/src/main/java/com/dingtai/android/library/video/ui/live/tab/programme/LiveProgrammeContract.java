package com.dingtai.android.library.video.ui.live.tab.programme;

import com.dingtai.android.library.video.model.LiveProgramModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-09-04
 */
public interface LiveProgrammeContract {

    interface View extends IView {
        void GetProgramList(boolean result, String message, String week, List<LiveProgramModel> list);
    }

    interface  Presenter extends IPresenter<View> {
        void GetProgramList(String LiveID, String week, String TabCode);
    }
}
