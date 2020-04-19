package com.lnr.android.base.framework.mvp.view;

/**
 * author:lnr
 * date:2018/5/10
 */

public interface IView {

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    void updateProgress(int progress);
}
