package com.dingtai.android.library.video.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;

import com.dingtai.android.library.video.ui.VideoNavigation;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseActivity;

import java.util.List;

/**
 * author :  lnr
 * date : 2018/8/19.
 * 视频播放器  仅包含一个视频组件
 */
@Route(path = "/video/player/simple")
public class SimpleVidioPlayerActivity extends BaseActivity {

    @Autowired
    PlayerModel model;

    protected VideoPlayerFragment fragment;

    @Override
    public String digest() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void setContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
    }

    @Override
    protected void initView() {
//        mImmersionBar = ImmersionBarUtil.buildNotImmersion(this)
//                .hideBar(BarHide.FLAG_HIDE_BAR)
//                .statusBarDarkFont(false).flymeOSStatusBarFontColor(R.color.white);
//        mImmersionBar.init();
    }

    @Override
    protected void initImmersionBar(boolean immersion) {
        //super.initImmersionBar(immersion);
    }

    @Override
    protected void inject(ApplicationComponent component) {
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        fragment = VideoNavigation.player(model);

        replaceFragment(R.id.video_container, fragment);
    }

    @Override
    public void onBackPressed() {
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
