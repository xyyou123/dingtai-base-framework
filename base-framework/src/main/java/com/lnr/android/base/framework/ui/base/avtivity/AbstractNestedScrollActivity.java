package com.lnr.android.base.framework.ui.base.avtivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;

import java.util.List;

/**
 * author:lnr
 * date:2018/10/31
 */
public abstract class AbstractNestedScrollActivity extends StatusActivity {

    @Override
    protected int contentLayoutResId() {
        return R.layout.layout_nestedscroll;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

    }
}
