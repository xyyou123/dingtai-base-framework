package com.dingtai.android.library.video.ui.video.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VideoChannelModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.flyco.tablayout.SlidingTabLayout;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.base.avtivity.StatusActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/11/14
 */
@Route(path = "/video/video/tab")
@Contract(name = "VideoTab")
public class VideoTabActivity extends StatusActivity implements VideoTabContract.View {

    @Inject
    VideoTabPresenter mVideoTabPresenter;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private List<BaseFragment> fragmentList = new ArrayList();

    private List<VideoChannelModel> mChannelList;

    private PopupMenu mPopupMenu;

    @Override
    protected int rootLayoutResId() {
        return R.layout.activity_video_tab;
    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.layout_viewpager;
    }

    @Override
    protected void retry() {
        mVideoTabPresenter.getMediaChannelsList(null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mVideoTabPresenter);
    }

    @Override
    protected void initView() {
        mSlidingTabLayout = findViewById(R.id.TabLayout);
        mViewPager = findViewById(R.id.ViewPager);

        ViewListen.setClick(findViewById(R.id.btn_back), new OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                finishActivity();
            }
        });

        ViewListen.setClick(findViewById(R.id.btn_menu), new OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                showMenu(view);
            }
        });
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerVideoDagger.builder().applicationComponent(applicationComponent).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle bundle) {
        mVideoTabPresenter.getMediaChannelsList(null);
    }

    @Override
    public void getMediaChannelsList(List<VideoChannelModel> list) {
        if(list == null && (mChannelList == null || mChannelList.isEmpty())) {
            mStatusLayoutManager.showEmpty();
        }else if(list != null){
            mChannelList = list;
            for (VideoChannelModel model : list) {
                fragmentList.add(createFragmentWithModel(model));
            }

            if (mViewPager.getAdapter() != null) {
                mViewPager.setAdapter(this.createAdapter());
                mSlidingTabLayout.notifyDataSetChanged();
                mViewPager.setCurrentItem(mSlidingTabLayout.getCurrentTab());
            } else {
                mViewPager.setAdapter(this.createAdapter());
                mSlidingTabLayout.setViewPager(this.mViewPager);
            }

            mStatusLayoutManager.showContent();
        }
    }

    protected BaseFragment createFragmentWithModel(VideoChannelModel model) {
        return (BaseFragment) VideoNavigation.videoList(model.getID());
    }

    protected FragmentPagerAdapter createAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            public CharSequence getPageTitle(int position) {
                return mChannelList.get(position).getName();
            }

            public long getItemId(int position) {
                return fragmentList.get(position).ID;
            }
        };
    }

    private void showMenu(View view) {
        if(mPopupMenu == null) {
            mPopupMenu = new PopupMenu(this, view);
            mPopupMenu.getMenuInflater().inflate(R.menu.video_menu, mPopupMenu.getMenu());
            mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int i = item.getItemId();
                    if (i == R.id.video_upload) {
                        VideoNavigation.videoUploadPublish();
                    } else if (i == R.id.video_my) {
                        VideoNavigation.videoMyUpload();
                    } else if (i == R.id.video_uploading) {
                        VideoNavigation.videoUploading(null);
                    }
//                    else if (i == R.id.video_search) {
//
//                    }
                    return true;
                }
            });
        }

        mPopupMenu.show();
    }
}
