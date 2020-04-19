package com.dingtai.android.library.video.ui.live.tab.imagetext;

import com.dingtai.android.library.video.api.impl.GetLiveImageTextListAsynCall;
import com.dingtai.android.library.video.model.LiveImageTextModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-29
 */
@ActivityScope
public class LiveImageTextPresenter extends AbstractPresenter<RecyclerViewConract.View> implements LiveImageTextContract.Presenter {

    @Inject
    GetLiveImageTextListAsynCall mGetLiveImageTextListAsynCall;

    @Inject
    public LiveImageTextPresenter(){}

    //具体业务逻辑

    @Override
    public void getLiveImageTextList(String id, String top, final String dtop) {
        excuteNoLoading(mGetLiveImageTextListAsynCall, AsynParams.create("LiveID", id).put("Num", top).put("dtop", dtop), new AsynCallback<List<LiveImageTextModel>>() {
            @Override
            public void onCallResponse(List<LiveImageTextModel> r) {
                if("0".equals(dtop)) {
                    view().refresh(true, null, r);
                }else {
                    view().load(true, null, r);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                if("0".equals(dtop)) {
                    view().refresh(false, ex.getMessage(), null);
                }else {
                    view().load(false, ex.getMessage(), null);
                }
            }
        });
    }
}
