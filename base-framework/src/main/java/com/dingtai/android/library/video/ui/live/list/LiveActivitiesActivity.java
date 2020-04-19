package com.dingtai.android.library.video.ui.live.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.dingtai.android.library.video.ui.VideoNavigation;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;

import java.util.List;

/**
 * author:lnr
 * date:2018/10/9
 * 直播间
 */
@Route(path = "/video/live/activities")
public class LiveActivitiesActivity extends ToolbarActivity {
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
        toolbar().setTitle("活动直播");
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        replaceFragment(R.id.frame, (Fragment) VideoNavigation.liveList("3"));
    }
}
