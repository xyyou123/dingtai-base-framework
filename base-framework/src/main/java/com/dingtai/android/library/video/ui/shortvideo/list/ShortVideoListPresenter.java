package com.dingtai.android.library.video.ui.shortvideo.list;

import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.impl.GetShortVideoListAsynCall;
import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2019-03-11
 */
@ActivityScope
public class ShortVideoListPresenter extends AbstractPresenter<ShortVideoListContract.View> implements ShortVideoListContract.Presenter {

    @Inject
    protected GetShortVideoListAsynCall mGetShortVideoListAsynCall;

    @Inject
    public ShortVideoListPresenter() {
    }


    @Override
    public void getData( String num, final String dtop, String StID) {
        AsynParams asynParams = AsynParams.create()
                .put("num", num)
                .put("dtop", dtop)
                .put("StID", Resource.API.STID);
        excuteNoLoading(mGetShortVideoListAsynCall, asynParams, new AsynCallback<List<ShortVideoModel>>() {
            @Override
            public void onCallResponse(List<ShortVideoModel> models) {
                if ("0".equals(dtop)) {
                    view().refresh(true, null, models);
                } else {
                    view().load(true, null, models);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                if ("0".equals(dtop)) {
                    view().refresh(true, null, null);
                } else {
                    view().load(true, null, null);
                }
            }
        });

    }
}