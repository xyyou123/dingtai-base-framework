package com.lnr.android.base.framework.mvp;

import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

public interface UpdateModelContract {

    interface  Presenter<V extends IView> extends IPresenter<V> {
        void update(String key, int status);
    }
}
