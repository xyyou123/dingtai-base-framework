package com.lnr.android.base.framework.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.Toolbar;

/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class ToolbarRecyclerViewFragment extends AbstractRecyclerViewFragment {

    private Toolbar mToolbar;

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout_toolbar;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setLeftVisibility(true, true, true);
        mToolbar.setTitle(digest());
        ViewListen.setClick(mToolbar.getLeftLayout(), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                onBackPressed();
            }
        });
    }

    protected Toolbar toolbar() {
        return mToolbar;
    }
}
