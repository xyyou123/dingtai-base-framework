package com.dingtai.android.library.news.ui.details.comment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.dingtai.android.library.news.ui.details.base.BaseNewsActivity;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.Toolbar;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/26
 * 新闻评论
 */
@Route(path = "/news/details/comment")
public class NewsCommentActivity extends BaseNewsActivity {

    protected SmartRefreshLayout mSmartRefreshLayout;
    protected RecyclerView mRecyclerView;

    protected NewsCommentAdapter mNewsCommentAdapter;

    protected TextView textTitle, textDes;

    @Override
    protected int layoutId() {
        return R.layout.layout_news_comment;
    }

    @Override
    protected int contentLayoutResId() {
        return R.layout.activity_news_details_comment;
    }

    @Override
    protected boolean isWhiteTheme() {
        return true;
    }

    @Override
    protected int rootLayoutResId() {
        return R.layout.root_layout_toolbar;
    }

    @Override
    protected void retry() {
        getNewsCommentList(String.valueOf(Resource.API.PAGE), "0");
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("评论(" + NumberUtil.parseInt(model.getCommentNum()) + ")");

        mToolbar.setLeftImage(Resource.Drawable.TOOLBAR_BACK);
        mToolbar.setLeftListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });

        textTitle = findViewById(R.id.text_title);
        textDes = findViewById(R.id.text_des);

        textTitle.setText(model.getTitle());
        textDes.setText(model.getAuditTime());

        mSmartRefreshLayout = findViewById(R.id.SmartRefreshLayout);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setNestedScrollingEnabled(false);
        mSmartRefreshLayout.setEnableLoadMore(false);

        mNewsCommentAdapter = new NewsCommentAdapter(new NewsCommentAdapter.OnSubChildZanClickListener() {
            @Override
            public void onSubChildZanClick(NewsCommentModel parent, NewsCommentModel model) {
                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                mBaseNewsPresenter.addCommentZan(parent, model);
            }
        });
        mNewsCommentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(!AccountHelper.getInstance().isLogin()) {
                    navigation(Routes.Account.LOGIN).navigation();
                    return;
                }
                NewsCommentModel item = (NewsCommentModel) adapter.getItem(position);
                if(item == null) return;
                if(view.getId() == R.id.item_zan_image) {
                    mBaseNewsPresenter.addCommentZan(null, item);
                }else if(view.getId() == R.id.item_edit) {
                    String name = TextUtils.isEmpty(item.getUserNickName()) ? item.getUserName() : item.getUserNickName();
                    if(TextUtils.isEmpty(name)) {
                        name = "匿名用户";
                    }
                    showCommentDialog(item.getID(), "回复 " + name);
                }else if(view.getId() == R.id.item_sublist_hint) {
                    item.setExpandAllSubList(!item.isExpandAllSubList());
                    adapter.notifyItemChanged(position);
                }
            }
        });

        mRecyclerView.setAdapter(mNewsCommentAdapter);

        ViewListen.setClick(findViewById(R.id.action_bar_edittext), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                showCommentDialog(model.getResourceGUID(), "说点什么...");
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNewsCommentList(String.valueOf(Resource.API.PAGE), String.valueOf(mNewsCommentAdapter.getItemCount()));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNewsCommentList(String.valueOf(Resource.API.PAGE), "0");
            }
        });

        retry();
    }

    @Override
    public void getNewsCommentList(boolean result, boolean refresh, List<NewsCommentModel> list) {
        super.getNewsCommentList(result, refresh, list);

        if(refresh) {
            handlerRefreshResult(result, mNewsCommentAdapter, list, Resource.API.PAGE);
        }else {
            handlerLoadMoreResult(result, mNewsCommentAdapter, list, Resource.API.PAGE);
        }
    }

    @Override
    public void addCommentZan(boolean result, NewsCommentModel parent, NewsCommentModel comment) {
        super.addCommentZan(result, parent, comment);
        mNewsCommentAdapter.notifyData(parent, comment);
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

    @Override
    public void exitActivity(boolean onBackPressed) {
        RxBus.getDefault().post(model);
        super.exitActivity(onBackPressed);
    }
}
