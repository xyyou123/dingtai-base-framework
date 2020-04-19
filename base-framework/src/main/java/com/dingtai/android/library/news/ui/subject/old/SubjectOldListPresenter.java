package com.dingtai.android.library.news.ui.subject.old;

import com.dingtai.android.library.news.api.impl.GetSubjectListLoadMoreAsynCall;
import com.dingtai.android.library.news.api.impl.GetSubjectListRefreshAsynCall;
import com.dingtai.android.library.news.api.impl.LoadGetNewsChildUpListAsynCall;
import com.dingtai.android.library.news.api.impl.RefreshGetNewsChildDownListAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-14
 */
@ActivityScope
public class SubjectOldListPresenter extends AbstractPresenter<SubjectOldListContract.View> implements SubjectOldListContract.Presenter {

    @Inject
    protected GetSubjectListRefreshAsynCall mGetSubjectListRefreshAsynCall;

    @Inject
    protected GetSubjectListLoadMoreAsynCall mGetSubjectListLoadMoreAsynCall;

    @Inject
    RefreshGetNewsChildDownListAsynCall mRefreshGetNewsChildDownListAsynCall;
    @Inject
    LoadGetNewsChildUpListAsynCall mLoadGetNewsChildUpListAsynCall;

    protected boolean useType2;

    @Inject
    public SubjectOldListPresenter(){}

    //具体业务逻辑

    @Override
    public void refresh(String id, int count) {
        AsynParams params = AsynParams.create().put("top", String.valueOf(count)).put("sign", Resource.API.SIGN).put("chid", id);
        excuteNoLoading(useType2 ? mRefreshGetNewsChildDownListAsynCall : mGetSubjectListRefreshAsynCall, params, new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, ex.getMessage(), null);
            }
        });
    }

    @Override
    public void loadMore(String id, int offset, int count) {
        AsynParams params = AsynParams.create().put("top", String.valueOf(offset)).put("dtop", String.valueOf(count)).put("chid", id);
        excuteNoLoading(useType2 ? mLoadGetNewsChildUpListAsynCall : mGetSubjectListLoadMoreAsynCall, params, new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> r) {
                view().loadMore(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().loadMore(false, ex.getMessage(), null);
            }
        });
    }
}
