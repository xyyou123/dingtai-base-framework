package com.dingtai.android.library.video.ui.live.list.channel;

import com.dingtai.android.library.video.VideoComponentConstant;
import com.dingtai.android.library.video.api.impl.GetLiveByTypeAsynCall;
import com.dingtai.android.library.video.api.impl.GetLiveByTypeNewAsynCall;
import com.dingtai.android.library.video.api.impl.GetLiveNewAsynCall;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AbstractAsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynCall;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-30
 */
@ActivityScope
public class LiveChannelPresenter extends AbstractPresenter<RecyclerViewConract.View> implements LiveChannelContract.Presenter {

    @Inject
    protected GetLiveByTypeAsynCall mGetLiveByTypeAsynCall;

    @Inject
    GetLiveByTypeNewAsynCall mGetLiveByTypeNewAsynCall;

    @Inject
    GetLiveNewAsynCall mGetLiveNewAsynCall;

    @Inject
    public LiveChannelPresenter() {
    }

    //具体业务逻辑

    @Override
    public void refresh(AsynParams params) {
        AsynCall<List<LiveChannelModel>> call =
                chooseHttpCall(params);
        excuteNoLoading(call, params, new AsynCallback<List<LiveChannelModel>>() {
            @Override
            public void onCallResponse(List<LiveChannelModel> r) {
                view().refresh(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().refresh(false, ex.getMessage(), null);
            }
        });
    }


    @Override
    public void loadMore(AsynParams params) {
        AsynCall<List<LiveChannelModel>> call =
                chooseHttpCall(params);
        excuteNoLoading(call, params, new AsynCallback<List<LiveChannelModel>>() {
            @Override
            public void onCallResponse(List<LiveChannelModel> r) {
                view().load(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().load(false, ex.getMessage(), null);
            }
        });
    }

    protected AbstractAsynCall<List<LiveChannelModel>> chooseHttpCall(AsynParams params) {
        
        switch (VideoComponentConstant.Constant.LiveHttpCall) {
            case VideoComponentConstant.Constant.LiveHttpCallType.GetLiveByType:
                return mGetLiveByTypeAsynCall;
            case VideoComponentConstant.Constant.LiveHttpCallType.GetLiveByTypeNew:
                if ("3".equals(params.get("type"))) {
                    return mGetLiveByTypeNewAsynCall;

                } else {
                    return mGetLiveByTypeAsynCall;   
                }
           case  VideoComponentConstant.Constant.LiveHttpCallType.GetLiveNew:

               if ("3".equals(params.get("type"))) {
                   return mGetLiveNewAsynCall;

               } else {
                   return mGetLiveByTypeAsynCall;
               }
            default:
                return mGetLiveByTypeAsynCall;
        }


//        return (VideoComponentConstant.Constant.LIVE_CHANNEL_LIST_NEW && "3".equals(params.get("type")))
//                ? mGetLiveByTypeNewAsynCall : mGetLiveByTypeAsynCall;
    }
}
