package com.dingtai.android.library.news.ui.relevant;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.news.DaggerNewsDagger;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.NewsListAdapter;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/28
 */
@Contract(name = "NewsRelevant")
@Route(path = "/news/relevant")
public class NewsRelevantFragment extends DefaultRecyclerviewFragment {

    @Inject
    protected NewsRelevantPresenter mNewsRelevantPresenter;

    @Autowired
    protected String id;

    @Override
    protected void refresh(int page) {
        mNewsRelevantPresenter.getLiveNews(id);
    }

    @Override
    protected void loadMore(int page, int current) {

    }

    @Override
    protected int page() {
        return 10000;
    }

    @Override
    protected BaseAdapter adapter() {
        return new NewsListAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return new DividerItemDecoration(getContext());
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mNewsRelevantPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        NewsNavigation.listItemNavigation((NewsListModel) adapter.getItem(position));
    }
}
