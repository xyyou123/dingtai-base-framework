package com.dingtai.android.library.news.ui.list.more;

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
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/18
 */
@Contract(name = "SubjectNeoListMore")
@Route(path = "/news/list/more")
public class NewsListMoreActivity extends ToolbarRecyclerViewActivity implements NewsListMoreContract.View {

    @Inject
    protected NewsListMorePresenter mSubjectNeoListMorePresenter;

    private NewsListAdapter mNewsListAdapter;

    public static final String ACTION_SUBJECT = "GetMoreNewByChannUpList";
    public static final String ACTION_ZHENGWU = "GetMoreNewByDeptUpList";

    @Autowired
    protected String action = ACTION_SUBJECT;

    @Autowired
    protected String ChID;

    @Autowired
    protected String DeptID;

    @Autowired
    protected String title;

    @Override
    protected void retry() {
        mSubjectNeoListMorePresenter.refresh(action, ChID, DeptID, String.valueOf(Resource.API.PAGE));
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mSubjectNeoListMorePresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setTitle(title);

        if(action == null) {
            action = ACTION_SUBJECT;
        }

        mNewsListAdapter = new NewsListAdapter();
        mRecyclerView.setAdapter(mNewsListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mNewsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsNavigation.subjectItemNavigation(mNewsListAdapter.getItem(position));
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mSubjectNeoListMorePresenter.load(action, ChID, DeptID, String.valueOf(Resource.API.PAGE), String.valueOf(mNewsListAdapter.getItemCount()));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSubjectNeoListMorePresenter.refresh(action, ChID, DeptID, String.valueOf(Resource.API.PAGE));
            }
        });

        retry();
    }

    @Override
    public void refresh(boolean result, String message, List<NewsListModel> models) {
        handlerRefreshResult(result, mNewsListAdapter, models, Resource.API.PAGE);
    }

    @Override
    public void load(boolean result, String message, List<NewsListModel> models) {
        handlerLoadMoreResult(result, mNewsListAdapter, models, Resource.API.PAGE);
    }
}
