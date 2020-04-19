package com.dingtai.android.library.video.ui.live.tab.chat;

import com.dingtai.android.library.video.VideoComponentConstant;
import com.dingtai.android.library.video.api.impl.AddCommentType12AsynCall;
import com.dingtai.android.library.video.api.impl.AddCommentType12ChildAsynCall;
import com.dingtai.android.library.video.api.impl.AddCommentType34AsynCall;
import com.dingtai.android.library.video.api.impl.AddCommentType34ChildAsynCall;
import com.dingtai.android.library.video.api.impl.GetNewsCommentAsynCall;
import com.dingtai.android.library.video.api.impl.GetNewsLiveCommentAsynCall;
import com.dingtai.android.library.video.model.LiveCommentModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-03
 */
@ActivityScope
public class LiveChatPresenter extends AbstractPresenter<LiveChatContract.View> implements LiveChatContract.Presenter {

    @Inject
    GetNewsCommentAsynCall mGetNewsCommentAsynCall;
    @Inject
    GetNewsLiveCommentAsynCall mGetNewsLiveCommentAsynCall;

    @Inject
    AddCommentType12AsynCall mAddCommentType12AsynCall;
    @Inject
    AddCommentType34AsynCall mAddCommentType34AsynCall;

    @Inject
    AddCommentType12ChildAsynCall mAddCommentType12ChildAsynCall;
    @Inject
    AddCommentType34ChildAsynCall mAddCommentType34ChildAsynCall;



    @Inject
    public LiveChatPresenter(){}

    //具体业务逻辑

    @Override
    public void GetNewsComment(int type, String LiveID, String Num, final String dtop, String TabCode) {
        AsynCallback<List<LiveCommentModel>> callback = new AsynCallback<List<LiveCommentModel>>() {
            @Override
            public void onCallResponse(List<LiveCommentModel> r) {
                if(NumberUtil.parseInt(dtop) == 0) {
                    view().refresh(true, null, r);
                }else {
                    view().load(true, null, r);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                if(NumberUtil.parseInt(dtop) == 0) {
                    view().refresh(false, ex.getMessage(), null);
                }else {
                    view().load(false, ex.getMessage(), null);
                }
            }
        };

        switch (type) {
            case VideoComponentConstant.LiveType.VEDIO:
            case VideoComponentConstant.LiveType.AUDIO:
                excuteNoLoading(mGetNewsCommentAsynCall,
                        AsynParams.create("LiveID", LiveID).put("Num", Num).put("dtop", dtop).put("TabCode", TabCode), callback);
                break;
            case VideoComponentConstant.LiveType.IMAGE_AND_TEXT:
            case VideoComponentConstant.LiveType.ACTIVITY:
                excuteNoLoading(mGetNewsLiveCommentAsynCall,
                        AsynParams.create("LiveID", LiveID).put("Num", Num).put("dtop", dtop).put("TabCode", TabCode), callback);
                break;

        }
    }

    @Override
    public void addComment(int type, boolean child, String id, String content) {
        AsynCallback<Integer> callback = new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
               if(r >= 0) {
                   view().addComment(r == 1);
               }
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addComment(false);
            }
        };

        AsynParams params = AsynParams.create("id", id).put("content", content);

        if(type == 1 || type == 2) {
            excuteWithLoading(child ? mAddCommentType12ChildAsynCall : mAddCommentType12AsynCall, params, callback);
        }else {
            excuteWithLoading(child ? mAddCommentType34ChildAsynCall : mAddCommentType34AsynCall, params, callback);
        }
    }
}
