package com.dingtai.android.library.news.ui.details.base;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.FrameworkConfig;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.comment.CommentBottomDialog;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UMengShare;
import com.lnr.android.base.framework.common.umeng.UmengAction;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.event.RealNameAuthenticationEvent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.rx.event.AddReadingHistoryEvent;
import com.lnr.android.base.framework.ui.base.avtivity.StatusActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.NumImageView;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author:lnr"
 * date:2018/11/1
 */
public abstract class BaseNewsActivity extends StatusActivity implements BaseNewsContract.View, CommentBottomDialog.OnSubmitListener {

    @Inject
    protected BaseNewsPresenter mBaseNewsPresenter;

    protected NumImageView viewComment, viewLike, viewFavor;

    @Autowired
    protected NewsListModel model;
    @Autowired
    protected String ID;
    @Autowired
    protected String ResourceType;
    @Autowired
    protected String forapp;

    protected CommentBottomDialog mCommentBottomDialog;

    protected ShareMenu mShareMenu;

    @Override
    protected int contentLayoutResId() {
        return R.layout.activity_base_news;
    }

    protected abstract int layoutId();

    protected abstract boolean isWhiteTheme();

    protected ShareMenu createShareMenu() {
        return new ShareMenu(mActivity, new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onShareActionClick((UmengAction) adapter.getItem(position));
            }
        });
    }

    protected boolean userActionbar;

    @Override
    protected void initView() {
        if(forapp == null) forapp = "2";

        if(model == null) {
            model = new NewsListModel();
            model.setResourceGUID(ID);
            model.setResourceType(ResourceType);
        }else {
            ID = model.getResourceGUID();
            ResourceType = model.getResourceType();
        }

        LinearLayout layout = findViewById(R.id.layout_content_container);
        layout.addView(LayoutInflater.from(this).inflate(layoutId(), layout, false), 0);

        mCommentBottomDialog = new CommentBottomDialog(this, this);

        viewComment = findViewById(R.id.actionbar_comment);
        viewLike = findViewById(R.id.actionbar_like);
        viewFavor = findViewById(R.id.actionbar_favored);

        userActionbar = viewComment != null;
        if(userActionbar) {
            initActionbarView();
        }

        mShareMenu = createShareMenu();

        RxBus.getDefault().post(new AddReadingHistoryEvent(model.getResourceGUID()));

        registe(NewsListModel.class, new Consumer<NewsListModel>() {
            @Override
            public void accept(NewsListModel event) throws Exception {
                if(TextUtils.equals(model.getResourceGUID(), ID)) {
                    model = event;
                    initActionbar(model.getCommentNum(), model.getGetGoodPoint());
                }
            }
        });
    }

    private void initActionbarView() {
        ViewListen.setClick(viewComment, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                NewsNavigation.comment(model);
            }
        });

        ViewListen.setClick(viewLike, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if(viewLike.getImageView().isSelected()) {
                    return;
                }

                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                mBaseNewsPresenter.addNewsZan(model.getResourceGUID());
            }
        });


        ViewListen.setClick(viewFavor, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                if(!viewFavor.getImageView().isSelected()) {
                    mBaseNewsPresenter.addNewsCollect(model.getResourceGUID(), model.getResourceType());
                }else {
                    mBaseNewsPresenter.deleteNewsCollect(model.getResourceGUID());
                }
            }
        });

        TextView edittext = findViewById(R.id.action_bar_edittext);
        ViewListen.setClick(edittext, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                showCommentDialog(model.getResourceGUID(), "说点什么");
            }
        });


        viewLike.setIcon(R.drawable.bg_action_like);
        viewFavor.setIcon(R.drawable.bg_action_favor);

        initActionbar(model.getCommentNum(), model.getGetGoodPoint());
    }

    protected void initActionbar(String commentCount, String zanCount) {
        if(viewComment != null) {
            viewComment.setNum(NumberUtil.parseInt(commentCount));
        }

        if(viewLike != null) {
            viewLike.setNum(NumberUtil.parseInt(zanCount));
        }

        if(viewLike != null) {
            viewLike.getImageView().setSelected(mBaseNewsPresenter.isNewsZaned(model.getResourceGUID()));
        }
        if(viewFavor != null) {
            viewFavor.getImageView().setSelected(mBaseNewsPresenter.isNewsCollected(model.getResourceGUID()));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mBaseNewsPresenter == null) return;
        initActionbar(model.getCommentNum(), model.getGetGoodPoint());
    }

    protected void share() {
        if(mShareMenu != null && !mShareMenu.isShowing())
        mShareMenu.show(new ShareMenu.OnShareListener() {
            @Override
            public void onShare(SHARE_MEDIA share_media) {
                mBaseNewsPresenter.addShareNum(model.getResourceGUID());
                
            }
        });
    }

    protected void showCommentDialog(String toId, String hint) {
        if(!AccountHelper.getInstance().isLogin()) {
            navigation(Routes.Account.LOGIN).navigation();
            return;
        }
        if(FrameworkConfig.COMMENT_MUST_HAS_PHONE && !"True".equals(AccountHelper.getInstance().getUser().getIsAuthentication())) {
            RxBus.getDefault().post(new RealNameAuthenticationEvent());
            return;
        }
        mCommentBottomDialog.show(toId, hint);
    }

    protected void getNewsCommentList(String top, String dtop) {
        mBaseNewsPresenter.getNewsCommentList(model.getResourceGUID(), forapp, top, dtop);
    }


    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mBaseNewsPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    protected void onShareActionClick(UmengAction action) {

        String url = "99".equals(model.getResourceType()) ? model.getResourceUrl()
                : GlobalConfig.SHARE_URL_GUID + model.getResourceGUID();

        UMengShare.shareWeb(this, action.getType(), model.getTitle(), model.getSummary(),
                url, TextUtils.isEmpty(model.getSmallPicUrl()) ? null : new UMImage(this, model.getSmallPicUrl()));

        mBaseNewsPresenter.addShareNum(model.getResourceGUID());
    }

    @Override
    public void addNewsZan(boolean result) {
        if(result) {
            int count = NumberUtil.parseInt(model.getGetGoodPoint()) + 1;
            model.setGetGoodPoint("" + count);
            viewLike.getImageView().setSelected(true);
            viewLike.setNum(count);
        } else {
            ToastHelper.toastError("点赞失败");
        }
    }

    @Override
    public void addNewsCollect(boolean result) {
        if(result) {
            viewFavor.getImageView().setSelected(true);
            ToastHelper.toastSucceed("收藏成功");
        } else {
            ToastHelper.toastError("收藏失败");
        }
    }

    @Override
    public void deleteNewsCollect(boolean result) {
        if(result) {
            viewFavor.getImageView().setSelected(false);
        } else {
            ToastHelper.toastError("删除收藏失败");
        }
    }

    @Override
    public void addNewsComment(boolean result) {
        if(result) {
            ToastHelper.toastDefault("评论成功，请等待管理员审核");
        }else {
            ToastHelper.toastError("评论失败");
        }
    }

    @Override
    public void addCommentZan(boolean result, NewsCommentModel parent, NewsCommentModel comment) {
        if(result) {
            comment.setGetGoodPoint("" + (NumberUtil.parseInt(comment.getGetGoodPoint()) + 1));
            comment.setGoodPoint(true);
        } else {
            ToastHelper.toastError("点赞失败");
        }
    }

    @Override
    public void getNewsCommentList(boolean result, boolean refresh, List<NewsCommentModel> list) {

    }

    @Override
    public boolean onSubnit(String content) {
        if(model.getResourceGUID().equals(mCommentBottomDialog.getToId())) {
            mBaseNewsPresenter.addNewsComment(mCommentBottomDialog.getToId(), content);
        }else {
            mBaseNewsPresenter.addReplyComment(model.getResourceGUID(), mCommentBottomDialog.getToId(), content);
        }
        return true;
    }
}
