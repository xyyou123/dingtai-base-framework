package com.dingtai.android.library.news.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import com.dingtai.android.library.news.ui.NewsNavigation;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;

import java.util.List;

@Route(path = "/news/home/activity")
public class NewsHomeActivity extends ToolbarActivity {

    @Autowired
    protected String parentId;

    @Autowired
    protected String title;

    @Autowired
    protected String action;

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.layout_frame, null);
    }

    @Override
    protected void initView() {
        toolbar().setTitle(title);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle bundle) {
        replaceFragment(R.id.frame, (Fragment) NewsNavigation.tab(action, parentId));
    }
}
