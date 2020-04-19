package com.lnr.android.base.framework.mvp.presenter;

import com.lnr.android.base.framework.mvp.view.IView;

/**
 * author:lnr
 * date:2018/5/10
 */

public interface IPresenter<V extends IView> {

    void bindView(V view);

    void unBindView();
}
