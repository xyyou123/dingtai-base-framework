package com.dingtai.android.library.video.ui.video.upload.uploading;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.dingtai.android.library.video.ui.VideoNavigation;
import com.dingtai.android.library.video.ui.video.upload.UploadVideoManager;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * author:lnr
 * date:2018/12/3
 */
@Route(path = "/video/video/upload/uploading")
public class VideoUploadingListActivity extends ToolbarActivity implements UploadVideoManager.OnUploadStateUpdateListener, BaseQuickAdapter.OnItemChildClickListener {

    protected SmartRefreshLayout mSmartRefreshLayout;
    protected RecyclerView mRecyclerView;

    private UploadVideoStateAdapter mUploadVideoStateAdapter;

    @Override
    protected View contentView() {
        return View.inflate(this, R.layout.layout_recyclerview, null);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void initView() {
        mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        mSmartRefreshLayout.setEnableOverScrollDrag(true);
        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setEnableLoadMore(false);
        mRecyclerView = findViewById(R.id.RecyclerView);

        mUploadVideoStateAdapter = new UploadVideoStateAdapter();
        mUploadVideoStateAdapter.setNewData(UploadVideoManager.getInstance().getUploadList());

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setAdapter(mUploadVideoStateAdapter);

        mUploadVideoStateAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setTitle("正在上传");

        toolbar().setRightText("上传");
        toolbar().setRightListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                VideoNavigation.videoUploadPublish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        UploadVideoManager.getInstance().registeUploadListener(this);
    }

    @Override
    protected void onPause() {
        UploadVideoManager.getInstance().unRegisteUploadListener();
        super.onPause();
    }

    @Override
    public void onUpdate(UploadVideoManager.UploadState model) {
        mUploadVideoStateAdapter.notifyItemChanged(mUploadVideoStateAdapter.getData().indexOf(model), new Object());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mUploadVideoStateAdapter.setNewData(UploadVideoManager.getInstance().getUploadList());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        final UploadVideoManager.UploadState item = (UploadVideoManager.UploadState) adapter.getItem(position);
        if(item == null) return;

        if(view.getId() == R.id.item_delete) {
            UploadVideoManager.getInstance().delete(item);
            mUploadVideoStateAdapter.remove(position);
        }else if(view.getId() == R.id.item_retry) {
            UploadVideoManager.getInstance().retry(item);
        }
    }
}
