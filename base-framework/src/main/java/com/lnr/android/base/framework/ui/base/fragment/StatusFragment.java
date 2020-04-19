package com.lnr.android.base.framework.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.control.view.statuslayout.StatusLayoutManager;


/**
 * author:lnr
 * date:2018/6/19
 */

public abstract class StatusFragment extends BaseFragment {

    //状态管理器
    protected StatusLayoutManager mStatusLayoutManager;

    @Override
    protected final View contentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(rootLayoutResId(), container, false);
        mStatusLayoutManager = new StatusLayoutManager.Builder(getActivity()).contentResId(contentLayoutResId())
                .emptyView(R.layout.layout_empty)
                .errorView(R.layout.layout_error)
                .loadResId(R.layout.layout_loading)
                .netWorkErrorView(R.layout.layout_network_error)
                .retryResId(R.id.rerty)
                .build().setRetryListener(new StatusLayoutManager.RetryListener(){
                    @Override
                    public void retry(View v) {
                        StatusFragment.this.retry();
                    }
                }).inject((ViewGroup) root.findViewById(R.id.root_layout_content));
        return root;
    }

    protected abstract @LayoutRes int rootLayoutResId();

    protected abstract @LayoutRes int contentLayoutResId();

    protected abstract void retry();
}
