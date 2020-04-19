package com.dingtai.android.library.video.ui.live.tab.game;

import com.dingtai.android.library.video.api.impl.GetGameAsynCall;
import com.dingtai.android.library.video.model.LiveGameModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-05
 */
@ActivityScope
public class LiveGamePresenter extends AbstractPresenter<RecyclerViewConract.View> implements LiveGameContract.Presenter {

    @Inject
    GetGameAsynCall mGetGameAsynCall;

    @Inject
    public LiveGamePresenter(){}

    //具体业务逻辑

    @Override
    public void refresh(AsynParams params) {
        excuteNoLoading(mGetGameAsynCall, params, new AsynCallback<List<LiveGameModel>>() {
            @Override
            public void onCallResponse(List<LiveGameModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, ex.getMessage(), null);
            }
        });
    }
}
