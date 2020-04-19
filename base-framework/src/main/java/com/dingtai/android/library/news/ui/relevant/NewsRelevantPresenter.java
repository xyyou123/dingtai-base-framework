package com.dingtai.android.library.news.ui.relevant;

import com.dingtai.android.library.news.api.impl.GetLiveNewsAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-28
 */
@ActivityScope
public class NewsRelevantPresenter extends AbstractPresenter<RecyclerViewConract.View> implements NewsRelevantContract.Presenter {

    @Inject
    protected GetLiveNewsAsynCall mGetLiveNewsAsynCall;

    @Inject
    public NewsRelevantPresenter(){}

    //具体业务逻辑

    @Override
    public void getLiveNews(String id) {
        excuteNoLoading(mGetLiveNewsAsynCall, AsynParams.create("LiveID", id), new AsynCallback<List<NewsListModel>>() {
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
}
