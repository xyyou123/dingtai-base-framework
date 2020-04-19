package com.lnr.android.base.framework.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.control.view.Toolbar;

/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class ToolbarFragment extends BaseFragment {

    private Toolbar mToolbar;

    @Override
    protected final View contentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.root_layout_toolbar, container, false);
        mToolbar = root.findViewById(R.id.toolbar);
        root.removeViewAt(1);
        root.addView(contentView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return root;
    }

    protected abstract View contentView();

    protected Toolbar toolbar() {
        return mToolbar;
    }
}
