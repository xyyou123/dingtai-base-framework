package com.dingtai.android.library.news.ui.web;

import com.dingtai.android.library.news.model.NewsListModel;

/**
 * author:lnr
 * date:2019/1/17
 */
public interface NewsNavigationCallback {

    boolean intercept(NewsListModel model);
}
