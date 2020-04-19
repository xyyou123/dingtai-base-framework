package com.dingtai.android.library.news.ui.launch;

import com.dingtai.android.library.news.api.impl.GetOpenPicByStIDAsynCall;
import com.dingtai.android.library.news.model.LaunchAdModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-26
 */
@ActivityScope
public class NewsLaunchPresenter extends AbstractPresenter<NewsLaunchContract.View> implements NewsLaunchContract.Presenter {

    @Inject
    protected GetOpenPicByStIDAsynCall mGetOpenPicByStIDAsynCall;

    @Inject
    public NewsLaunchPresenter(){}

    //具体业务逻辑

    @Override
    public void GetOpenPicByStID(String app) {
        excuteNoLoading(mGetOpenPicByStIDAsynCall, AsynParams.create("ForApp", app), new AsynCallback<LaunchAdModel>() {
            @Override
            public void onCallResponse(LaunchAdModel r) {
                view().GetOpenPicByStID(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetOpenPicByStID(null);
            }
        });
    }
}
