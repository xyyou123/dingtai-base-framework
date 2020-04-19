package com.dingtai.android.library.news.ui.details.web;

import com.dingtai.android.library.news.api.impl.GetNewsInfoAsynCall;
import com.dingtai.android.library.news.model.NewsDetailModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-22
 */
@ActivityScope
public class NewsDetailsPresenter extends AbstractPresenter<NewsDetailsContract.View> implements NewsDetailsContract.Presenter {
    public String newGuid;
    @Inject
    protected GetNewsInfoAsynCall mGetNewsInfoAsynCall;

    @Inject
    public NewsDetailsPresenter() {
    }

    //具体业务逻辑


    @Override
    public void getNewsInfo(String guid, String sign) {
        AsynParams params = AsynParams.create("guid", guid).put("sign", sign);
        excuteNoLoading(mGetNewsInfoAsynCall, params, new AsynCallback<NewsDetailModel>() {
            @Override
            public void onCallResponse(NewsDetailModel r) {
                view().getNewsInfo(true, null, r);
              
            }

            @Override
            public void onCallError(Throwable ex) {
                view().getNewsInfo(false, null, null);
            }
        });
    }

   
}
