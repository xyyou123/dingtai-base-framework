package com.dingtai.android.library.video.ui.video.list.details.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VideoModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.OnTextChangeWatcher;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/11/6
 */
@Contract(name = "HotVideoComment")
@Route(path = "/video/video/list/details/comment")
public class HotVideoCommentFragment extends DefaultRecyclerviewFragment implements HotVideoCommentContract.View {

    @Inject
    protected HotVideoCommentPresenter mHotVideoCommentPresenter;

    @Autowired
    protected VideoModel model;

    private EditText editComment;
    private Button btnComment;

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_hotvideo_comment;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mHotVideoCommentPresenter);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerVideoDagger.builder().applicationComponent(applicationComponent)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void refresh(int i) {
        mHotVideoCommentPresenter.getHotVideoCommentList(model.getID2(), "" + i, "0");
    }

    @Override
    protected void loadMore(int i, int i1) {
        mHotVideoCommentPresenter.getHotVideoCommentList(model.getID2(), "" + i, "" + i1);
    }

    @Override
    protected BaseQuickAdapter adapter() {
        return new HotVideoCommentAdapter();
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        super.afterInitView(view, savedInstanceState);
        editComment = findViewById(R.id.edit_content);

        if("true".equalsIgnoreCase(model.getIsApproved())) {
            editComment.setHint("评论功能已关闭，暂时无法使用");
            editComment.setFocusableInTouchMode(false);
            editComment.setFocusable(false);
        }else {
            btnComment = findViewById(R.id.btn_comment);
            ViewListen.addTextChangeWatcher(new OnTextChangeWatcher.OnTextChangeListener() {
                @Override
                public void onChange(boolean b) {
                    btnComment.setEnabled(!b);
                }
            }, editComment);

            ViewListen.setClick(btnComment, new OnClickListener() {
                @Override
                protected void onSafeClick(View view) {
                    if(!AccountHelper.getInstance().isLogin()) {
                        navigation(Routes.Account.LOGIN).navigation();
                        return;
                    }
                    ContextUtil.hideSoftInput(editComment);
                    mHotVideoCommentPresenter.addComment(model.getID2(), editComment.getText().toString());
                }
            });
        }
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    public void addComment(boolean result) {
        if(result) {
            editComment.setText("");
            ToastHelper.toastDefault("评论成功，请等待管理员审核");
        }else {
            ToastHelper.toastError("评论失败");
        }
    }
}
