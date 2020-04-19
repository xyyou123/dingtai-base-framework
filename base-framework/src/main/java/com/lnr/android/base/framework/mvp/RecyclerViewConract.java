package com.lnr.android.base.framework.mvp;

import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018/8/31
 */
public interface RecyclerViewConract {

    interface View extends IView {

        void refresh(boolean result, String message, List list);

        void load(boolean result, String message, List list);
    }

    interface Presenter extends IPresenter<View> {

        void refresh(AsynParams params);

        void load(AsynParams params);
    }
}
