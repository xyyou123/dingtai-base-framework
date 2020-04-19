package com.dingtai.android.library.video.ui.vod;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.dingtai.android.library.video.ui.VideoNavigation;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/25
 * 点播类型tab
 */
@Route(path = "/video/vod/channels")
public class VodChannelsActivity extends BaseActivity {

    protected SegmentTabLayout tabLayout;
    protected ViewPager viewPager;

    protected List<Fragment> fragmentList;
    protected String[] titles;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_live_channels);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.SegmentTabLayout);
        viewPager = findViewById(R.id.ViewPager);

        ViewListen.setClick(findViewById(R.id.image_back), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }


    protected void initFragments() {
        fragmentList = new ArrayList<>();
        titles = new String[2];

        titles[0] = "栏目点播";
        titles[1] = "活动点播";

        fragmentList.add((Fragment) VideoNavigation.vodList(null));
        fragmentList.add((Fragment) VideoNavigation.vodList("2"));
    }


    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        initFragments();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        tabLayout.setTabData(titles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }
}
