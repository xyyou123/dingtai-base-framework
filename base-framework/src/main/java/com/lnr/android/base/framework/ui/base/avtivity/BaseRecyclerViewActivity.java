package com.lnr.android.base.framework.ui.base.avtivity;


import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class BaseRecyclerViewActivity extends AbstractRecyclerViewActivity {

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout;
    }
}
