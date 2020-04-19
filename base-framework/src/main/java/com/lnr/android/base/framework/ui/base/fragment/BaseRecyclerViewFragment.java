package com.lnr.android.base.framework.ui.base.fragment;


import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class BaseRecyclerViewFragment extends AbstractRecyclerViewFragment {

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout;
    }
}
