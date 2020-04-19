package com.lnr.android.base.framework.ui.base.avtivity;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.view.statuslayout.StatusLayoutManager;


/**
 * author:lnr
 * date:2018/6/19
 */

public abstract class StatusActivity extends BaseActivity {

    //状态管理器
    protected StatusLayoutManager mStatusLayoutManager;

    @Override
    protected final void setContentView() {
        ViewGroup root = (ViewGroup) LayoutInflater.from(this).inflate(rootLayoutResId(), null, false);
        beforeInjectStatusLayout(root);
        mStatusLayoutManager = new StatusLayoutManager.Builder(this).contentResId(contentLayoutResId())
                .emptyView(R.layout.layout_empty)
                .errorView(R.layout.layout_error)
                .loadResId(R.layout.layout_loading)
                .netWorkErrorView(R.layout.layout_network_error)
                .retryResId(R.id.rerty)
                .build().setRetryListener(new StatusLayoutManager.RetryListener(){
                    @Override
                    public void retry(View v) {
                        StatusActivity.this.retry();
                    }
                }).inject((ViewGroup) root.findViewById(R.id.root_layout_content));
        setContentView(root);
    }

    protected abstract @LayoutRes int rootLayoutResId();

    protected abstract @LayoutRes int contentLayoutResId();

    protected void beforeInjectStatusLayout(ViewGroup root) {

    }

    protected abstract void retry();
}
