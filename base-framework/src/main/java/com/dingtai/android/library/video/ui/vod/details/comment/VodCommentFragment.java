package com.dingtai.android.library.video.ui.vod.details.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VodCommentModel;
import com.dingtai.android.library.video.model.VodProgramModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.FrameworkConfig;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.comment.CommentBottomDialog;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.event.RealNameAuthenticationEvent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2019/1/9
 */
@Route(path = "/video/vod/details/comment")
@Contract(name = "VodComment")
public class VodCommentFragment extends DefaultRecyclerviewFragment implements VodCommentContract.View, CommentBottomDialog.OnSubmitListener {

    @Inject
    VodCommentPresenter mVodCommentPresenter;

    @Autowired
    protected VodProgramModel model;

    protected CommentBottomDialog mCommentBottomDialog;

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_live_chat;
    }

    @Override
    protected void refresh(int page) {
        mVodCommentPresenter.getVodCommentList(model.getID());
    }

    @Override
    protected int page() {
        return 10000;
    }

    @Override
    protected void loadMore(int page, int current) {
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    protected BaseQuickAdapter adapter() {
        return new VodCommentAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return new DividerItemDecoration(getContext());
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mVodCommentPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        super.afterInitView(view, savedInstanceState);

        mCommentBottomDialog = new CommentBottomDialog(getContext(), this);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                VodCommentModel item = (VodCommentModel) adapter.getItem(position);
                if(item == null) return;
                if(view.getId() == R.id.item_zan_image) {
                    mVodCommentPresenter.addCommentZan(item);
                }else if(view.getId() == R.id.item_edit) {
                    String name = TextUtils.isEmpty(item.getUserNickName()) ? item.getUserName() : item.getUserNickName();
                    if(TextUtils.isEmpty(name)) {
                        name = "匿名用户";
                    }
                    showCommentDialog(item.getID(), "回复 " + name);
                }
            }
        });

        ViewListen.setClick(findViewById(R.id.edit_content), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                showCommentDialog(model.getID(), "说点什么...");
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

    @Override
    public boolean onSubnit(String content) {
        if(model.getID().equals(mCommentBottomDialog.getToId())) {
            mVodCommentPresenter.addVodComment(mCommentBottomDialog.getToId(), content);
        }else {
            mVodCommentPresenter.addVodReplayComment(mCommentBottomDialog.getToId(), null);
        }
        return true;
    }

    @Override
    public void addCommentZan(boolean result, VodCommentModel item) {
        if(result) {
            item.setGetGoodPoint("" + (NumberUtil.parseInt(item.getGetGoodPoint()) + 1));
            item.setGoodPoint(true);
        } else {
            ToastHelper.toastError("点赞失败");
        }
    }

    @Override
    public void addVodComment(boolean result) {
        if(result) {
            ToastHelper.toastDefault("评论成功，请等待管理员审核");
        }else {
            ToastHelper.toastError("评论失败");
        }
    }

    @Override
    public void addVodReplayComment(boolean result) {
        if(result) {
            ToastHelper.toastDefault("评论成功，请等待管理员审核");
        }else {
            ToastHelper.toastError("评论失败");
        }
    }
}
