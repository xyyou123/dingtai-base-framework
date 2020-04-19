package com.dingtai.android.library.video.ui.live.tab.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.VideoComponentConstant;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.model.LiveCommentModel;
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
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.StringUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/3
 * 直播聊天页面
 */
@Contract(name = "LiveChat")
@Route(path = "/video/live/chat")
public class LiveChatFragment extends DefaultRecyclerviewFragment implements LiveChatContract.View, CommentBottomDialog.OnSubmitListener {

    /**
     * 直播类型
     * @see VideoComponentConstant.LiveType
     */
    @Autowired
    protected int type = 1;

    /**
     * 直播id
     */
    @Autowired
    protected String liveId;

    @Autowired
    protected String tabCode;

    @Autowired
    protected String name;

    @Autowired
    protected LiveChannelModel model;

    @Inject
    protected LiveChatPresenter mLiveChatPresenter;

    protected TextView textComment;

    protected CommentBottomDialog mCommentBottomDialog;

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_live_chat;
    }

    @Override
    protected void refresh(int i) {
        mLiveChatPresenter.GetNewsComment(type, liveId, String.valueOf(i), "0", tabCode);
    }

    @Override
    protected void loadMore(int i, int i1) {
        mLiveChatPresenter.GetNewsComment(type, liveId, String.valueOf(i), String.valueOf(i1), tabCode);
    }

    @Override
    protected BaseQuickAdapter adapter() {
        return new LiveChatCommentAdapter();
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mLiveChatPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        textComment = findViewById(R.id.edit_content);

        mCommentBottomDialog = new CommentBottomDialog(getContext(), this);

        ViewListen.setClick(textComment, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                if(FrameworkConfig.COMMENT_MUST_HAS_PHONE && !"True".equals(AccountHelper.getInstance().getUser().getIsAuthentication())) {
                    RxBus.getDefault().post(new RealNameAuthenticationEvent());
                    return;
                }
                mCommentBottomDialog.show(liveId, "说点什么...");
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);

        LiveCommentModel item = (LiveCommentModel) adapter.getItem(position);
        if(item == null) return;
        if(!AccountHelper.getInstance().isLogin()) {
            navigation(Routes.Account.LOGIN).navigation();
            return;
        }

        if(FrameworkConfig.COMMENT_MUST_HAS_PHONE && !"True".equals(AccountHelper.getInstance().getUser().getIsAuthentication())) {
            RxBus.getDefault().post(new RealNameAuthenticationEvent());
            return;
        }

        String name = TextUtils.isEmpty(item.getUserNickName()) ?  StringUtil.formatPhone(item.getUserName()) : item.getUserNickName();
        mCommentBottomDialog.show(item.getID(), "回复：" + name);
    }

    @Override
    public void addComment(boolean result) {
        if(result) {
            ToastHelper.toastDefault("评论成功，请等待管理员审核");
        }else {
            ToastHelper.toastError("评论失败");
        }
    }

    @Override
    public boolean onSubnit(String content) {
        if(!AccountHelper.getInstance().isLogin()) {
            navigation(Routes.Account.LOGIN).navigation();
            return false;
        }
        if(mCommentBottomDialog.getToId() == null) return false;
        mLiveChatPresenter.addComment(type, !TextUtils.equals(model.getID(), mCommentBottomDialog.getToId()), mCommentBottomDialog.getToId(), content);
        return false;
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }
}
