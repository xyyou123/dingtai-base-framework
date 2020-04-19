package com.dingtai.android.library.news.ui.subject.old;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.news.DaggerNewsDagger;
import com.dingtai.android.library.news.NewsComponentConstant;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.NewsListAdapter;
import com.dingtai.android.library.resource.Resource;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
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
 * date:2018/9/14
 * 专题列表  -- 旧专题
 */
@Contract(name = "SubjectOldList")
@Route(path = "/news/subject/old/list")
public class SubjectOldListActivity extends ToolbarRecyclerViewActivity implements SubjectOldListContract.View {

    @Inject
    protected SubjectOldListPresenter mSubjectOldListPresenter;

    private NewsListAdapter mNewsListAdapter;

    @Autowired
    protected String ChID;

    @Autowired
    protected String GUID;

    @Autowired
    protected String title;

    protected ShareMenu shareMenu;

    @Override
    protected void retry() {
        mSubjectOldListPresenter.refresh(ChID, Resource.API.PAGE);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mSubjectOldListPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        if(title == null || title.trim().length() == 0) {
            title = "专题列表";
        }
        toolbar().setTitle(title);

//        toolbar().setRightImage(R.drawable.icon_share);
//        toolbar().setRightListener(new OnClickListener() {
//            @Override
//            protected void onSafeClick(View v) {
//                if(shareMenu == null) {
//                    UMWeb web = UMengShare.createNewsWeb(title, null, GlobalConfig.SHARE_URL_SUJECT_OLD + ChID,
//                            new UMImage(mActivity, GlobalConfig.SHARE_ICON));
//                    shareMenu = new ShareMenu(mActivity, web);
//                }
//                if(!shareMenu.isShowing()) shareMenu.show();
//            }
//        });


        mNewsListAdapter = new NewsListAdapter();
        mRecyclerView.setAdapter(mNewsListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mSubjectOldListPresenter.loadMore(ChID, Resource.API.PAGE, mNewsListAdapter.getItemCount());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSubjectOldListPresenter.refresh(ChID, Resource.API.PAGE);
            }
        });

        mNewsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsNavigation.subjectItemNavigation(mNewsListAdapter.getItem(position));
            }
        });

        mSubjectOldListPresenter.useType2 = userType2();

        mSubjectOldListPresenter.refresh(ChID, Resource.API.PAGE);
    }

    protected boolean userType2() {
        return NewsComponentConstant.Subject.OLD_USER_TYPE_2;
    }

    @Override
    public void refresh(boolean result, String message, List<NewsListModel> list) {
        handlerRefreshResult(result, mNewsListAdapter, list, Resource.API.PAGE);
    }

    @Override
    public void loadMore(boolean result, String message, List<NewsListModel> list) {
        handlerLoadMoreResult(result, mNewsListAdapter, list, Resource.API.PAGE);
    }
}
