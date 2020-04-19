package com.dingtai.android.library.news.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.custom.ScrollTopHelper;
import com.dingtai.android.library.news.event.HomeSelectedTabEvent;
import com.dingtai.android.library.news.event.SubscriptionUpdateEvent;
import com.dingtai.android.library.news.model.ChannelModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.base.fragment.EmptyStatusFragment;
import com.lnr.android.base.framework.ui.control.view.NoScrollViewPager;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

@Route(path = "/news/home")
@Contract(name = "NewsHome")
public class NewsHomeFragment extends EmptyStatusFragment implements NewsHomeContract.View, ScrollTopHelper.ScrollTopScrollerParentCallback, OnTabSelectListener, ViewPager.OnPageChangeListener {

    @Inject
    protected NewsHomePresenter mNewsHomePresenter;


    protected LinearLayout mHeaderLayout;
    protected LinearLayout mTabLayout;
    protected SlidingTabLayout mTab;
    protected NoScrollViewPager mViewPager;

    protected List<BaseFragment> fragmentList = new ArrayList<>();
    protected List<ChannelModel> channelModelList;
    protected HashMap<String, BaseFragment> fragmentHashMap = new HashMap<>();

    @Autowired
    protected String parentId;

    @Autowired
    protected String action;

    protected int getCaptureScrollTopChild() {
        return -1;
    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.fragment_news_home;
    }

    @Override
    protected void retry() {
        mNewsHomePresenter.GetNewsChannelList(action, parentId);
    }

    protected BaseFragment getCurrentFragment() {
        return fragmentList != null && mTab != null ? fragmentList.get(mTab.getCurrentTab()) : null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mNewsHomePresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        if (getCaptureScrollTopChild() >= 0) {
            ScrollTopHelper.HELPER.register(this);
        }


        mHeaderLayout = findViewById(R.id.home_header_layout);
        mTabLayout = findViewById(R.id.home_tab_layout);
        mTab = findViewById(R.id.TabLayout);
        mViewPager = findViewById(R.id.home_pager1);
        mViewPager.setScroll(true);
        mViewPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        registe(SubscriptionUpdateEvent.class, new Consumer<SubscriptionUpdateEvent>() {
            @Override
            public void accept(SubscriptionUpdateEvent event) throws Exception {
                channelModelList = event.getList();
                updateTab();
            }
        });

        registe(HomeSelectedTabEvent.class, new Consumer<HomeSelectedTabEvent>() {
            @Override
            public void accept(HomeSelectedTabEvent event) throws Exception {
                BaseFragment fragment = fragmentHashMap.get(event.getChid());
                if (fragment != null) {
                    int i = fragmentList.indexOf(fragment);
                    if (i >= 0 && i != mTab.getCurrentTab()) {
                        mTab.setCurrentTab(i);
                    }
                }
            }
        });

        mTab.setOnTabSelectListener(this);
        mViewPager.addOnPageChangeListener(this);
        retry();
    }

    @Override
    public void GetNewsChannelList(boolean result, String message, List<ChannelModel> models) {
        if (result) {
            if (models.size() == 1 && TextUtils.isEmpty(models.get(0).getID())) {
                mTabLayout.setVisibility(View.GONE);

                ChannelModel model = new ChannelModel();
                model.setID(parentId);
                model.setChannelName("新闻");
                channelModelList = new ArrayList<>();
                channelModelList.add(model);
                updateTab();
                mStatusLayoutManager.showContent();
                return;
            }

            if (models.size() > 0) {
                channelModelList = models;
                mStatusLayoutManager.showContent();
                updateTab();
            } else {
                if (fragmentList != null) fragmentList.clear();
                mStatusLayoutManager.showEmpty();
            }
        } else {
            if (fragmentList == null || fragmentList.isEmpty()) {
                mStatusLayoutManager.showError();
            }
        }
    }

    protected BaseFragment createFirstFragment() {
        return (BaseFragment) NewsNavigation.listFirst();
    }

    protected String createFirstName() {
        return "推荐";
    }

    protected BaseFragment createFragmentWithModel(ChannelModel model) {
        return (BaseFragment) NewsNavigation.listHasAd(model.getID());
    }

    protected void updateTab() {
        fragmentList.clear();

        Iterator<ChannelModel> iterator = channelModelList.iterator();
        while (iterator.hasNext()) {
            ChannelModel model = iterator.next();
            BaseFragment fragment = fragmentHashMap.get(model.getID());
            if (fragment == null) {
                fragment = createFragmentWithModel(model);
            }
            if (fragment == null) {
                iterator.remove();
            } else {
                fragmentHashMap.put(model.getID(), fragment);
                fragmentList.add(fragment);
            }
        }

        BaseFragment first = fragmentHashMap.get("0");
        if (first == null) {
            first = createFirstFragment();
        }

        if (first != null) {
            fragmentHashMap.put("0", first);
            fragmentList.add(0, first);
        }

        if (first != null) {
            ChannelModel firstChannel = channelModelList.get(0);
            if (!TextUtils.isEmpty(firstChannel.getID()) && !"0".equals(firstChannel.getID())) {
                ChannelModel model = new ChannelModel();
                model.setChannelName(createFirstName());
                channelModelList.add(0, model);
            }
        }

        if (mViewPager.getAdapter() != null) {
            mViewPager.setAdapter(createAdapter());
            mTab.notifyDataSetChanged();
            mViewPager.setCurrentItem(mTab.getCurrentTab());
        } else {
            mViewPager.setAdapter(createAdapter());
            mTab.setViewPager(mViewPager);
        }
    }

    protected FragmentPagerAdapter createAdapter() {
        return new FragmentPagerAdapter(getChildFragmentManager()) {
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
                return channelModelList.get(position).getChannelName();
            }

            @Override
            public long getItemId(int position) {
                return fragmentList.get(position).ID;
            }
        };
    }

    @Override
    public void onDestroy() {
        ScrollTopHelper.HELPER.unRegister(this);
        super.onDestroy();
    }

    @Override
    public void scroll(int scrollY) {

    }

    @Override
    public void scrollToTop() {
    }

    @Override
    public void onTabSelect(int position) {
        int index = getCaptureScrollTopChild();
        if (index >= 0) {
            ScrollTopHelper.HELPER.capture(position == index);
        }
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int index = getCaptureScrollTopChild();
        if (index >= 0) {
            ScrollTopHelper.HELPER.capture(position == index);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
