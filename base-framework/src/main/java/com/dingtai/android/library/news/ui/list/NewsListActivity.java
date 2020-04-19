package com.dingtai.android.library.news.ui.list;

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

/**
 * author:lnr
 * date:2018/10/9
 */
@Route(path = "/news/list/activity")
public class NewsListActivity extends ToolbarActivity {

    @Autowired
    protected boolean hasAd;
    @Autowired
    protected String CHID;

    @Autowired
    protected String title;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.layout_frame, null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {
        if(title == null || title.trim().length() == 0) {
            title = "新闻列表";
        }
        toolbar().setTitle(title);
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

        replaceFragment(R.id.frame, (Fragment) (hasAd ? NewsNavigation.listHasAd(CHID) : NewsNavigation.listNoAd(CHID)));
    }
}
