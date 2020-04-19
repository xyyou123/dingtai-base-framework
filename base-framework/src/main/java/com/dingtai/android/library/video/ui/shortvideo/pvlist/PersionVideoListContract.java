package com.dingtai.android.library.video.ui.shortvideo.pvlist;

import com.dingtai.android.library.video.model.ShortVideoModel;
import com.dingtai.android.library.video.model.ShortVideoUserInfoModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2019-03-14
 */
public interface PersionVideoListContract {

    interface View extends IView {
        void refreshUserData(ShortVideoUserInfoModel r);

        void refreshData(List<ShortVideoModel> list);
    }

    interface Presenter extends IPresenter<View> {
        void refreshUserData(String userGUID);

        void refreshData(String UserGUID, String num, String dtop);
    }
}