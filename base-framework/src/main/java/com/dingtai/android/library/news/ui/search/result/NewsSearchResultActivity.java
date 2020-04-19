package com.dingtai.android.library.news.ui.search.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.news.DaggerNewsDagger;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.NewsListAdapter;
import com.dingtai.android.library.resource.Resource;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarRecyclerViewActivity;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/27
 */
@Route(path = "/news/search/result")
@Contract(name = "NewsSearchResult")
public class NewsSearchResultActivity extends ToolbarRecyclerViewActivity implements NewsSearchResultContract.View, BaseQuickAdapter.OnItemClickListener {

    @Inject
    protected NewsSearchResultPresenter mNewsSearchResultPresenter;

    protected BaseAdapter<NewsListModel> mNewsListAdapter;

    @Autowired
    protected String key;

    @Override
    protected void retry() {
        mNewsSearchResultPresenter.searchByKeyword(key, String.valueOf(Resource.API.PAGE), "0");
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mNewsSearchResultPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setTitle(key);

        mSmartRefreshLayout.setEnableRefresh(false);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mNewsSearchResultPresenter.searchByKeyword(key, String.valueOf(Resource.API.PAGE), String.valueOf(mNewsListAdapter.getItemCount()));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        mNewsListAdapter = adapter();
        mRecyclerView.setAdapter(mNewsListAdapter);
        mNewsListAdapter.setOnItemClickListener(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        retry();
    }

    @Override
    public void searchByKeyword(boolean result, String message, List<NewsListModel> list) {
        handlerLoadMoreResult(result, mNewsListAdapter, list, Resource.API.PAGE);
    }

    protected BaseAdapter<NewsListModel> adapter() {
        return new NewsListAdapter();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        NewsNavigation.listItemNavigation(mNewsListAdapter.getItem(position));
    }
}
