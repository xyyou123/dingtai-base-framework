package com.lnr.android.base.framework.ui.base.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.Resource;
import com.lnr.android.base.framework.mvp.RecyclerViewConract;
import com.lnr.android.base.framework.ui.base.fragment.BaseRecyclerViewFragment;
import com.lnr.android.base.framework.ui.base.fragment.ToolbarRecyclerViewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * author:lnr
 * date:2018/8/31
 */
public abstract class DefaultToolbarRecyclerviewFragment extends ToolbarRecyclerViewFragment implements RecyclerViewConract.View, OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    protected BaseQuickAdapter mAdapter;

    @Override
    protected void retry() {
        onRefresh(null);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        mAdapter = adapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));

        RecyclerView.ItemDecoration itemDecoration = itemDecoration();
        if(itemDecoration != null) {
            mRecyclerView.addItemDecoration(itemDecoration);
        }

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        retry();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        loadMore(page(), mAdapter.getData().size());
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
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

    protected abstract BaseQuickAdapter adapter();

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

    @Override
    public void autoRefresh() {
        if(isShowing() && mSmartRefreshLayout != null) {
            mSmartRefreshLayout.autoRefresh();
        }
    }
}
