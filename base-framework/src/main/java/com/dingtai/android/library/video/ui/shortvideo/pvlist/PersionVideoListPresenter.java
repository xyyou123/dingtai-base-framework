package com.dingtai.android.library.video.ui.shortvideo.pvlist;

import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.api.impl.GetPersionShortVideoListAsynCall;
import com.dingtai.android.library.video.api.impl.GetShortVideoUserInfoAsynCall;
import com.dingtai.android.library.video.model.ShortVideoModel;
import com.dingtai.android.library.video.model.ShortVideoUserInfoModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2019-03-14
 */
@ActivityScope
public class PersionVideoListPresenter extends AbstractPresenter<PersionVideoListContract.View> implements PersionVideoListContract.Presenter {

    @Inject
    public GetShortVideoUserInfoAsynCall mGetShortVideoUserInfoAsynCall;

    @Inject
    public GetPersionShortVideoListAsynCall mGetPersionShortVideoListAsynCall;


    @Inject
    public PersionVideoListPresenter() {
    }

    @Override
    public void refreshUserData(String userGUID) {
        AsynParams asynParams = AsynParams.create()
                .put("UserGUID", userGUID)
                .put("StID", Resource.API.STID);
        excuteNoLoading(mGetShortVideoUserInfoAsynCall, asynParams, new AsynCallback<ShortVideoUserInfoModel>() {
            @Override
            public void onCallResponse(ShortVideoUserInfoModel r) {
                view().refreshUserData(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refreshUserData(null);
            }
        });
    }

    @Override
    public void refreshData(String UserGUID, String num, final String dtop) {
        AsynParams asynParams = AsynParams.create()
                .put("UserGUID", UserGUID)
                .put("num", num)
                .put("dtop", dtop);
        excuteNoLoading(mGetPersionShortVideoListAsynCall, asynParams, new AsynCallback<List<ShortVideoModel>>() {
            @Override
            public void onCallResponse(List<ShortVideoModel> r) {
//                if ("0".equals(dtop)) {
//                    view().refresh(true, null, r);
//                } else {
//                    view().load(true, null, r);
//                }
            }

            @Override
            public void onCallError(Throwable ex) {
//                if ("0".equals(dtop)) {
//                    view().refresh(true, null, null);
//                } else {
//                    view().load(true, null, null);
//                }
            }
        });
    }
}