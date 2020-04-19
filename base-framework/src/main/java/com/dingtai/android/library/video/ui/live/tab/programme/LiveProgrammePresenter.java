package com.dingtai.android.library.video.ui.live.tab.programme;

import com.dingtai.android.library.video.api.impl.GetProgramListAsynCall;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-04
 */
@ActivityScope
public class LiveProgrammePresenter extends AbstractPresenter<LiveProgrammeContract.View> implements LiveProgrammeContract.Presenter {

    @Inject
    GetProgramListAsynCall mGetProgramListAsynCall;

    @Inject
    public LiveProgrammePresenter(){}

    //具体业务逻辑


    @Override
    public void GetProgramList(String LiveID, final String week, String TabCode) {
        excuteNoLoading(mGetProgramListAsynCall, AsynParams.create("LiveID", LiveID)
                .put("week", week).put("TabCode", TabCode), new AsynCallback<List<LiveProgramModel>>() {
            @Override
            public void onCallResponse(List<LiveProgramModel> r) {
                view().GetProgramList(true, null, week, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().GetProgramList(false, ex.getMessage(), week, null);
            }
        });
    }
}
