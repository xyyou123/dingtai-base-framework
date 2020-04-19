package com.dingtai.android.library.news.ui.details.base;

import com.dingtai.android.library.database.DB;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.db.NewsCollectModelDao;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.news.api.impl.AddGoodPointAsynCall;
import com.dingtai.android.library.news.api.impl.AddNewsGoodPointAsynCall;
import com.dingtai.android.library.news.api.impl.AddShareNumAsynCall;
import com.dingtai.android.library.news.api.impl.AddSubGoodPointAsynCall;
import com.dingtai.android.library.news.api.impl.DelUserCollectAsynCall;
import com.dingtai.android.library.news.api.impl.GetNewsCommentListAsynCall;
import com.dingtai.android.library.news.api.impl.InsertContentAsynCall;
import com.dingtai.android.library.news.api.impl.InsertUserCollectAsynCall;
import com.dingtai.android.library.news.model.NewsCommentModel;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterProvider;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapterType;
import com.lnr.android.base.framework.data.asyn.core.AsynCallback;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.AbstractPresenter;
import com.lnr.android.base.framework.uitl.MaiDianUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018-08-22
 */
@ActivityScope
public class BaseNewsPresenter extends AbstractPresenter<BaseNewsContract.View> implements BaseNewsContract.Presenter {

    @Inject
    protected InsertContentAsynCall mInsertContentAsynCall;

    @Inject
    protected InsertUserCollectAsynCall mInsertUserCollectAsynCall;

    @Inject
    protected DelUserCollectAsynCall mDelUserCollectAsynCall;

    @Inject
    protected AddNewsGoodPointAsynCall mAddNewsGoodPointAsynCall;

    @Inject
    protected AddGoodPointAsynCall mAddGoodPointAsynCall;
    @Inject
    protected AddSubGoodPointAsynCall mAddSubGoodPointAsynCall;

    @Inject
    protected GetNewsCommentListAsynCall mGetNewsCommentListAsynCall;

    @Inject
    protected AddShareNumAsynCall mAddShareNumAsynCall;

    @Inject
    public BaseNewsPresenter() {
    }

    //具体业务逻辑


    @Override
    public boolean isNewsZaned(String newsId) {
        if (AccountHelper.getInstance().isLogin()) {
            ModelStatus status = (ModelStatus) DB.getInstance().getUser().getDao(ModelStatusDao.class)
                    .queryBuilder().where(ModelStatusDao.Properties.Key.eq("News_Zan_" + newsId))
                    .unique();
            return status != null && status.getStatus() == 1;
        }
        return false;
    }

    @Override
    public boolean isNewsCollected(String newsId) {
        return AccountHelper.getInstance().isLogin() && AsynCallAdapterProvider.getInstance().get(AsynCallAdapterType.DATABASE).call(NewsCollectModelDao.class, true).queryBuilder()
                .where(NewsCollectModelDao.Properties.ResourceGUID.eq(newsId)).list().size() > 0;
    }

    @Override
    public void addNewsZan(final String newsId) {
        excuteNoLoading(mAddNewsGoodPointAsynCall, AsynParams.create("Cid", newsId), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().addNewsZan(r);
                MaiDianUtils.praise(newsId);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addNewsZan(false);
            }
        });
    }

    @Override
    public void addNewsCollect(String newsId, String type) {
        excuteNoLoading(mInsertUserCollectAsynCall, AsynParams.create("CollectID", newsId).put("CollectType", type), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().addNewsCollect(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addNewsCollect(false);
            }
        });
    }

    @Override
    public void deleteNewsCollect(String newsId) {
        excuteNoLoading(mDelUserCollectAsynCall, AsynParams.create("id", newsId), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().deleteNewsCollect(r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().deleteNewsCollect(false);
            }
        });
    }

    @Override
    public void addNewsComment(final String newsId, final String content) {
        excuteWithLoading(mInsertContentAsynCall, AsynParams.create("commentContent", content).put("rid", newsId), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
                if (r >= 0) {
                    view().addNewsComment(r == 1);
                    MaiDianUtils.comment(newsId, content);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addNewsComment(false);
            }
        });
    }

    @Override
    public void addCommentZan(final NewsCommentModel parent, final NewsCommentModel comment) {
        if (comment.isGoodPoint()) {
            return;
        }
        excuteNoLoading(parent == null ? mAddGoodPointAsynCall : mAddSubGoodPointAsynCall, AsynParams.create("Cid", comment.getID()), new AsynCallback<Boolean>() {
            @Override
            public void onCallResponse(Boolean r) {
                view().addCommentZan(r, parent, comment);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addCommentZan(false, parent, comment);
            }
        });
    }

    @Override
    public void addReplyComment(String newsId, String commentId, String content) {
        excuteWithLoading(mInsertContentAsynCall, AsynParams.create("commentContent", content).put("rid", newsId).put("pid", commentId), new AsynCallback<Integer>() {
            @Override
            public void onCallResponse(Integer r) {
                if (r >= 0) {
                    view().addNewsComment(r == 1);
                }
            }

            @Override
            public void onCallError(Throwable ex) {
                view().addNewsComment(false);
            }
        });
    }

    @Override
    public void getNewsCommentList(String newsId, String forapp, String top, final String dtop) {
        AsynParams params = AsynParams.create("rid", newsId).put("FORAPP", forapp).put("num", top).put("drop", dtop);
        excuteNoLoading(mGetNewsCommentListAsynCall, params, new AsynCallback<List<NewsCommentModel>>() {
            @Override
            public void onCallResponse(List<NewsCommentModel> r) {
                view().getNewsCommentList(true, "0".equals(dtop), r);
            }

            @Override
            public void onCallError(Throwable ex) {
                view().getNewsCommentList(false, "0".equals(dtop), null);
            }
        });
    }

    @Override
    public void addShareNum(String id) {
        excuteNoLoading(mAddShareNumAsynCall, AsynParams.create("guid", id), null);
    }
}
