package com.lnr.android.base.framework.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class AbstractRecyclerViewFragment extends StatusFragment {

    protected SmartRefreshLayout mSmartRefreshLayout;
    //状态管理器
    protected RecyclerView mRecyclerView;

    @Override
    protected int contentLayoutResId() {
        return isAddSearchLayout() ? R.layout.layout_recyclerview_with_search : R.layout.layout_recyclerview;
    }

    protected abstract @LayoutRes int rootLayoutResId();

    protected boolean isAddSearchLayout() {
        return false;
    }

    protected abstract void retry();

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(getContext()));
        mSmartRefreshLayout.setEnableLoadMore(false);
    }

    protected <T> void handlerRefreshResult(boolean result, BaseQuickAdapter adapter, List list, int page) {
        mSmartRefreshLayout.finishRefresh();
        if(result) {
            if(list == null || list.isEmpty()) {
                mSmartRefreshLayout.setEnableLoadMore(false);
                mStatusLayoutManager.showEmpty();
            }else {
                mStatusLayoutManager.showContent();
                adapter.setNewData(list);
                mSmartRefreshLayout.setEnableLoadMore(true);
            }
        }else {
            if(adapter.getItemCount() == 0) {
                mStatusLayoutManager.showEmpty();
            }
        }
    }

    protected <T> void handlerLoadMoreResult(boolean result, BaseQuickAdapter adapter, List<T> list, int page) {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
        if(result) {
            if(list == null || list.isEmpty()) {
                //mSmartRefreshLayout.setEnableLoadMore(false);
            }else {
                adapter.addData(list);
                //mSmartRefreshLayout.setEnableLoadMore(list.size() >= page);
            }
        }
    }
}
