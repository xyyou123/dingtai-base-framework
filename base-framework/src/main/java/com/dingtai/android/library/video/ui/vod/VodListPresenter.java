package com.dingtai.android.library.video.ui.vod;

import com.dingtai.android.library.video.api.impl.GetResByTrueProgramAsynCall;
import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-29
 */
@ActivityScope
public class VodListPresenter extends AbstractPresenter<VodListContract.View> implements VodListContract.Presenter {

    @Inject
    GetResByTrueProgramAsynCall mGetResByTrueProgramAsynCall;

    @Inject
    public VodListPresenter(){}

    //具体业务逻辑

    @Override
    public void GetResByTrueProgram(String RecType) {
        excuteNoLoading(mGetResByTrueProgramAsynCall, AsynParams.create("RecType", RecType), new AsynCallback<List<VodListModel>>() {
            @Override
            public void onCallResponse(List<VodListModel> r) {
                view().GetResByTrueProgram(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetResByTrueProgram(true, ex.getMessage(), null);
            }
        });
    }
}
