package com.dingtai.android.library.news.ui.details.comment;

import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.news.api.impl.AddGoodPointAsynCall;
import com.dingtai.android.library.news.api.impl.GetNewsCommentListAsynCall;
import com.dingtai.android.library.news.api.impl.InsertContentAsynCall;
import com.dingtai.android.library.news.model.NewsCommentModel;
import com.lnr.android.base.framework.FrameworkConfig;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.event.RealNameAuthenticationEvent;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;
import com.lnr.android.base.framework.rx.RxBus;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-09-26
 */
@ActivityScope
public class NewsDetailsCommentPresenter extends AbstractPresenter<NewsDetailsCommentContract.View> implements NewsDetailsCommentContract.Presenter {

    @Inject
    protected GetNewsCommentListAsynCall mGetNewsCommentListAsynCall;
    @Inject
    protected InsertContentAsynCall mInsertContentAsynCall;

    @Inject
    protected AddGoodPointAsynCall mAddGoodPointAsynCall;

    @Inject
    public NewsDetailsCommentPresenter(){}

    //具体业务逻辑

    @Override
    public void getNewsCommentList(String id, String forapp, String top, String dtop) {
        AsynParams params = AsynParams.create("rid", id).put("FORAPP", forapp).put("num", top).put("drop", dtop);
        excuteNoLoading(mGetNewsCommentListAsynCall, params, new AsynCallback<List<NewsCommentModel>>() {
            @Override
            public void onCallResponse(List<NewsCommentModel> r) {
                view().getNewsCommentList(true, null, r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().getNewsCommentList(false, ex.getMessage(), null);
            }
        });
    }

    @Override
    public void InsertContent(String content, String newsID) {
        if(FrameworkConfig.COMMENT_MUST_HAS_PHONE && !"True".equals(AccountHelper.getInstance().getUser().getIsAuthentication())) {
            RxBus.getDefault().post(new RealNameAuthenticationEvent());
            return;
        }
        excuteWithLoading(mInsertContentAsynCall, AsynParams.create("commentContent", content).put("rid", newsID), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
                if(r >= 0) {
                    view().InsertContent(r == 1);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                view().InsertContent(false);
            }
        });
    }

    @Override
    public void addGoodPoint(final NewsCommentModel commentModel) {
        excuteNoLoading(mAddGoodPointAsynCall, AsynParams.create("Cid", commentModel.getID()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().addGoodPoint(r, commentModel);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addGoodPoint(false, commentModel);
            }
        });
    }
}
