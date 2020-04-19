package com.lnr.android.base.framework.ui.base.avtivity;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.ui.control.view.statuslayout.StatusLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;


/**
 * author:lnr
 * date:2018/5/28
 */

public abstract class AbstractRecyclerViewActivity extends StatusActivity {

    //状态管理器
    protected SmartRefreshLayout mSmartRefreshLayout;
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
    protected void initView() {
        mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this));
        mSmartRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void autoRefresh() {
        if(mStatusLayoutManager.getCurrentLayoutId() != StatusLayout.LAYOUT_CONTENT_ID) {
            retry();
        }else {
            mSmartRefreshLayout.autoRefresh();
        }
    }

    protected <T> void handlerRefreshResult(boolean result, BaseAdapter<T> adapter, List list, int page) {
        mSmartRefreshLayout.finishRefresh();
        if(result) {
            if(list == null || list.isEmpty()) {
                mSmartRefreshLayout.setEnableLoadMore(false);
                mStatusLayoutManager.showEmpty();
            }else {
                mStatusLayoutManager.showContent();
                adapter.setNewData(list);

                mSmartRefreshLayout.setEnableLoadMore(list.size() >= page);
            }
        }else {
            if(adapter.getItemCount() == 0) {
                mStatusLayoutManager.showEmpty();
            }
            mSmartRefreshLayout.setEnableLoadMore(false);
        }
    }

    protected <T> void handlerLoadMoreResult(boolean result, BaseAdapter<T> adapter, List<T> list, int page) {
        mSmartRefreshLayout.finishLoadMore();
        if(result) {
            if(list == null || list.isEmpty()) {
                mSmartRefreshLayout.setEnableLoadMore(false);
                if(adapter.getItemCount() == 0) {
                    mStatusLayoutManager.showEmpty();
                }
            }else {
                if(adapter.getItemCount() == 0) {
                    mStatusLayoutManager.showContent();
                }
                adapter.addData(list);
                mSmartRefreshLayout.setEnableLoadMore(list.size() >= page);
            }
        }else {
            if(adapter.getItemCount() == 0) {
                mStatusLayoutManager.showError();
            }
            mSmartRefreshLayout.setEnableLoadMore(false);
        }
    }
}
