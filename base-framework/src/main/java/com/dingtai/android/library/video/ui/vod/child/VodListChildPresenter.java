package com.dingtai.android.library.video.ui.vod.child;

import com.dingtai.android.library.video.api.impl.GetDownConetentAsynCall;
import com.dingtai.android.library.video.api.impl.GetUpContentAsynCall;
import com.dingtai.android.library.video.model.VodProgramModel;
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
public class VodListChildPresenter extends AbstractPresenter<VodListChildContract.View> implements VodListChildContract.Presenter {

    @Inject
    GetDownConetentAsynCall mGetDownConetentAsynCall;

    @Inject
    GetUpContentAsynCall mGetUpContentAsynCall;

    @Inject
    public VodListChildPresenter(){}

    //具体业务逻辑

    @Override
    public void GetDownConetent(String ID, String top) {
        excuteNoLoading(mGetDownConetentAsynCall, AsynParams.create("ID", ID).put("top", top), new AsynCallback<List<VodProgramModel>>() {
            @Override
            public void onCallResponse(List<VodProgramModel> r) {
                view().GetDownConetent(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetDownConetent(false, ex.getMessage(), null);
            }
        });
    }

    @Override
    public void GetUpContent(String ID, String top, String dtop) {
        excuteNoLoading(mGetUpContentAsynCall, AsynParams.create("ID", ID).put("top", top).put("dtop", dtop), new AsynCallback<List<VodProgramModel>>() {
            @Override
            public void onCallResponse(List<VodProgramModel> r) {
                view().GetUpContent(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetUpContent(false, ex.getMessage(), null);
            }
        });
    }
}
