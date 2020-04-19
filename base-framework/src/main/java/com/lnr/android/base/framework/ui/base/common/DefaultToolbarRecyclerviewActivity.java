package com.lnr.android.base.framework.ui.base.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.BaseRecyclerViewActivity;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarRecyclerViewActivity;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/28
 */
public abstract class DefaultToolbarRecyclerviewActivity extends ToolbarRecyclerViewActivity implements RecyclerViewConract.View, OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    protected BaseAdapter mAdapter;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    protected void retry() {
        onRefresh(null);
    }

    @Override
    protected void initView() {
        super.initView();
        mAdapter = adapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this));

        RecyclerView.ItemDecoration itemDecoration = itemDecoration();
        if(itemDecoration != null) {
            mRecyclerView.addItemDecoration(itemDecoration);
        }

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        onRefresh(null);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        loadMore(page(), mAdapter.getData().size());
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refresh(page());
    }

    protected abstract void refresh(int page);

    protected abstract void loadMore(int page, int current);


    @Override
    public void refresh(boolean result, String message, List list) {
        handlerRefreshResult(result, mAdapter, list, page());
    }

    @Override
    public void load(boolean result, String message, List list) {
        handlerLoadMoreResult(result, mAdapter, list, page());
    }

    protected abstract BaseAdapter adapter();

    protected abstract RecyclerView.ItemDecoration itemDecoration();

    protected int page() {
        return Resource.API.PAGE;
    }

    public void remove(Object ooject) {
        int position = mAdapter.getData().indexOf(ooject);
        if(position >= 0) {
            mAdapter.remove(position);
            if(mAdapter.getItemCount() == 0) {
                mStatusLayoutManager.showEmpty();
                mSmartRefreshLayout.setEnableLoadMore(false);
            }
        }
    }
}
