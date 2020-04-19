package com.dingtai.android.library.video.ui.video.list.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Routes;

import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.ui.player.VideoPlayerFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.uitl.AuthenticationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/11/6
 */
@Route(path = "/video/video/list/details")
public class VideoDetailsActivity extends BaseActivity {

    protected VideoPlayerFragment fragment;

    @Autowired
    protected VideoModel model;

    protected SlidingTabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected List<BaseFragment> fragmentList;
    protected List<String> titles;

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initImmersionBar(boolean immersion) {

    }

    @Override
    protected void setContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hotvideo_details);
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.TabLayout);
        mViewPager = findViewById(R.id.ViewPager);


    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle bundle) {

        String mediaUrl = model.getMediaUrl();
        mediaUrl = AuthenticationUtil.getAuthenticationUrl(mediaUrl, model.getAuthenticationflag());
        PlayerModel playerModel = PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                .setTitle(model.getName())
                .setThumb(model.getImageUrl())
                .setSize(PlayerModel.SIZE_16_9)
                .addUrls(mediaUrl)
                .build();

        fragment = (VideoPlayerFragment) navigation(Routes.Video.PLAYER).withParcelable("model", playerModel).navigation();
        replaceFragment(R.id.layout_player, fragment);

        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();

        titles.add("详情");
        titles.add("评论");

        fragmentList.add((BaseFragment) navigation("/video/video/list/details/info").withParcelable("model", model).navigation());
        fragmentList.add((BaseFragment) navigation("/video/video/list/details/comment").withParcelable("model", model).navigation());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
