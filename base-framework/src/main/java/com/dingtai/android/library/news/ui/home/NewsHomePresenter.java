package com.dingtai.android.library.news.ui.home;

import com.dingtai.android.library.news.api.impl.GetNewsChannelListAsynCall;
import com.dingtai.android.library.news.model.ChannelModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-22
 */
@ActivityScope
public class NewsHomePresenter extends AbstractPresenter<NewsHomeContract.View> implements NewsHomeContract.Presenter {

    @Inject
    GetNewsChannelListAsynCall mGetNewsChannelListAsynCall;

    @Inject
    public NewsHomePresenter(){}

    //具体业务逻辑

    @Override
    public void GetNewsChannelList(String action, String parentID) {
        excuteNoLoading(mGetNewsChannelListAsynCall, AsynParams.create("parentID", parentID == null ? "0" : parentID).put("action", action), new AsynCallback<List<ChannelModel>>() {
            @Override
            public void onCallResponse(List<ChannelModel> r) {
                view().GetNewsChannelList(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetNewsChannelList(false, ex.getMessage(), null);
            }
        });
    }
}
