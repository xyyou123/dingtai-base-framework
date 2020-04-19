package com.dingtai.android.library.news.ui.search;

import com.dingtai.android.library.news.api.impl.GetNewsKeyWordAsynCall;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-27
 */
@ActivityScope
public class NewsSearchPresenter extends AbstractPresenter<NewsSearchContract.View> implements NewsSearchContract.Presenter {

    @Inject
    protected GetNewsKeyWordAsynCall mGetNewsKeyWordAsynCall;

    @Inject
    public NewsSearchPresenter(){}

    //具体业务逻辑

    @Override
    public void getNewsKeyWord() {
        excuteNoLoading(mGetNewsKeyWordAsynCall, null, new AsynCallback<String>() {
            @Override
            public void onCallResponse(String r) {
                view().getNewsKeyWord(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().getNewsKeyWord(null);
            }
        });
    }
}
