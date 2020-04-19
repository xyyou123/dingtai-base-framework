package com.dingtai.android.library.news.ui.home.more;

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
import com.lnr.android.base.framework.ui.base.common.DefaultToolbarRecyclerviewActivity;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/12/18
 */
@Contract(name = "NewsFirstMore")
@Route(path = "/news/first/newsmore")
public class NewsFirstMoreActivity extends DefaultToolbarRecyclerviewActivity {
    @Autowired
    protected String title;

    @Autowired
    protected String type;

    @Inject
    protected NewsFirstMorePresenter mFirstNewsMorePresenter;

    @Override
    protected void initView() {
        super.initView();
        toolbar().setTitle(title);
    }

    @Override
    protected void refresh(int i) {
        mFirstNewsMorePresenter.getNewsFirstMoreNews(type, i + "", "0");
    }

    @Override
    protected void loadMore(int i, int i1) {
        mFirstNewsMorePresenter.getNewsFirstMoreNews(type, i + "", "" + i1);
    }

    @Override
    protected BaseAdapter adapter() {
        return new NewsListAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mFirstNewsMorePresenter);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerNewsDagger.builder().applicationComponent(applicationComponent)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        NewsListModel model = (NewsListModel) adapter.getItem(position);
        if(model == null) return;
        NewsNavigation.listItemNavigation(model);
    }
}
