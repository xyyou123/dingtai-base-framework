package com.lnr.android.base.framework.ui.control.web;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * author:lnr
 * date:2018/9/14
 */
@Route(path = "/framework/web/base/fragment/notoolbar")
public class BaseWebFragment extends BaseToolbarWebFragment {

    @Override
    protected View contentView() {
        toolbar().setVisibility(View.GONE);
        return super.contentView();
    }
}
