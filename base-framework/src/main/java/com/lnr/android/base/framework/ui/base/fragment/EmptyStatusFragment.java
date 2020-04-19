package com.lnr.android.base.framework.ui.base.fragment;

import com.lnr.android.base.framework.R;

public abstract class EmptyStatusFragment extends StatusFragment {

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout;
    }
}
