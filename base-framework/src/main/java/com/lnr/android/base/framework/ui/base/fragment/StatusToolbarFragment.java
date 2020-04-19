package com.lnr.android.base.framework.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.view.Toolbar;

public abstract class StatusToolbarFragment extends StatusFragment {

    private Toolbar mToolbar;

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout_toolbar;
    }


    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setLeftVisibility(true, true, true);
        mToolbar.setLeftListener(new OnClickListener() {
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
