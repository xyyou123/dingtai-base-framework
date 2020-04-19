package com.lnr.android.base.framework.ui.base.avtivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.Toolbar;


/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class ToolbarActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected final void setContentView() {
        setContentView(R.layout.root_layout_toolbar);
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setLeftVisibility(true, true, true);
        mToolbar.setTitle(digest());
        ViewListen.setClick(mToolbar.getLeftLayout(), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                onBackPressed();
            }
        });

        ViewGroup root = findViewById(R.id.root_layout);
        root.removeViewAt(1);
        root.addView(contentView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected abstract View contentView();

    protected Toolbar toolbar() {
        return mToolbar;
    }
}
