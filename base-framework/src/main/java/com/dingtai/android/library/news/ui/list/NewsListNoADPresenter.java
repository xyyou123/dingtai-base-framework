package com.dingtai.android.library.news.ui.list;

import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-18
 */
@ActivityScope
public class NewsListNoADPresenter extends AbstractPresenter<NewsListNoADContract.View> implements NewsListNoADContract.Presenter {

    @Inject
    public NewsListNoADPresenter(){}

    //具体业务逻辑
}
