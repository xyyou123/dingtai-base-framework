package com.dingtai.android.library.news.ui.list.more;

import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-11-01
 */
@ActivityScope
public class SubjectNeoListMorePresenter extends AbstractPresenter<SubjectNeoListMoreContract.View> implements SubjectNeoListMoreContract.Presenter {

    @Inject
    public SubjectNeoListMorePresenter(){}

}