package com.dingtai.android.library.news.ui.search.result;

import com.dingtai.android.library.news.api.impl.GetNewsKeyWordShangLaAsynCall;
import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-27
 */
@ActivityScope
public class NewsSearchResultPresenter extends AbstractPresenter<NewsSearchResultContract.View> implements NewsSearchResultContract.Presenter {

    @Inject
    protected GetNewsKeyWordShangLaAsynCall mGetNewsKeyWordShangLaAsynCall;

    @Inject
    public NewsSearchResultPresenter(){}

    //具体业务逻辑


    @Override
    public void searchByKeyword(String keyword, String top, String dtop) {
        excuteNoLoading(mGetNewsKeyWordShangLaAsynCall, AsynParams.create("keywords", keyword).put("top", top).put("dtop", dtop), new AsynCallback<List<NewsListModel>>() {
            @Override
            public void onCallResponse(List<NewsListModel> r) {
                view().searchByKeyword(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().searchByKeyword(false, ex.getMessage(), null);
            }
        });
    }
}
