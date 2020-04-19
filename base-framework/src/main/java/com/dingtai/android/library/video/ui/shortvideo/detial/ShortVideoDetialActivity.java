package com.dingtai.android.library.video.ui.shortvideo.detial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.ShortVideoModel;
import com.dingtai.android.library.video.ui.player.DTVedioPlayer;
import com.dingtai.android.library.video.ui.player.controller.douyin.DouYinController;
import com.dingtai.android.library.video.ui.player.douyin.TikTokController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/3/12 0012.
 */
@Route(path = "/video/shortvideo/detial")
@Contract(name = "ShortVideoDetial")
public class ShortVideoDetialActivity extends DefaultRecyclerviewActivity implements ShortVideoDetialContract.View {
    @Inject
    protected ShortVideoDetialPresenter mShortVideoDetialPresenter;

    @Autowired(name = "list")
    protected Object object;
    @Autowired(name = "position")
    protected int position;

    private DTVedioPlayer mIjkVideoView;
    private TikTokController mTikTokController;
    private int mCurrentPosition;
    private List<ShortVideoModel> dataList;
    private DouYinController controler;

    @Override
    protected void initView() {
        mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mSmartRefreshLayout.setEnableLoadMore(false);
        mAdapter = adapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this));
        mIjkVideoView = new DTVedioPlayer(this);
        controler = new DouYinController(mIjkVideoView);

        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mTikTokController = new TikTokController(this);
        mIjkVideoView.setVideoController(mTikTokController);
        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                startPlay(mCurrentPosition);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    mIjkVideoView.release();
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) return;
                startPlay(position);
                mCurrentPosition = position;
            }
        });

        RecyclerView.ItemDecoration itemDecoration = itemDecoration();
        if (itemDecoration != null) {
            mRecyclerView.addItemDecoration(itemDecoration);
        }

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

        dataList = JSONObject.parseArray(object.toString(), ShortVideoModel.class);

        if (dataList != null && dataList.size() > position) {
            mAdapter.setNewData(dataList);
            mRecyclerView.scrollToPosition(position);
            mCurrentPosition = position;
        }
        mStatusLayoutManager.showContent();
    }

    private void startPlay(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        FrameLayout frameLayout = itemView.findViewById(R.id.container);
        GlideHelper.load(mTikTokController.getThumb(), dataList.get(position).getImgUrl());
        ViewParent parent = mIjkVideoView.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(mIjkVideoView);
        }
        frameLayout.addView(mIjkVideoView);
        ShortVideoModel model = dataList.get(position);
        PlayerModel playerModel = PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD).
                addUrls(model.getVideoUrl()).setTitle(model.getUserName()).setThumb(model.getImgUrl()).build();

        controler.init(playerModel, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        },null);
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
        mIjkVideoView.start();
    }


    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mShortVideoDetialPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void refresh(int page) {

    }

    @Override
    protected void loadMore(int page, int current) {

    }

    @Override
    protected BaseAdapter adapter() {
        return new ShortVideoDetialAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mIjkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
       
    }
}
