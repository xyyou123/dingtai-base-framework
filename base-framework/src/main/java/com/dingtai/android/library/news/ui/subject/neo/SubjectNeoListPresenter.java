package com.dingtai.android.library.news.ui.subject.neo;

import com.dingtai.android.library.news.api.impl.GetChannAndNewsAsynCall;
import com.dingtai.android.library.news.model.SubjectNeoRootModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-17
 */
@ActivityScope
public class SubjectNeoListPresenter extends AbstractPresenter<SubjectNeoListContract.View> implements SubjectNeoListContract.Presenter {

    @Inject
    protected GetChannAndNewsAsynCall mGetChannAndNewsAsynCall;

    @Inject
    public SubjectNeoListPresenter(){}

    //具体业务逻辑

    @Override
    public void GetChannAndNews(String ParentID) {
        excuteNoLoading(mGetChannAndNewsAsynCall, AsynParams.create("ParentID", ParentID), new AsynCallback<SubjectNeoRootModel>() {
            @Override
            public void onCallResponse(SubjectNeoRootModel r) {
                view().GetChannAndNews(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetChannAndNews(false, ex.getMessage(), null);
            }
        });
    }
}
