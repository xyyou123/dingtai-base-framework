package com.dingtai.android.library.news.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.news.ui.list.adapter.NewsListAdapter;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Routes;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/18
 */
@Route(path = "/news/list/noad")
@Contract(name = "NewsListNoAD")
public class NewsListFragment extends DefaultRecyclerviewFragment implements NewsListContract.View {

    @Inject
    protected NewsListPresenter mNewsListPresenter;

    @Autowired
    protected String CHID;

    @Override
    protected void refresh(int page) {
        mNewsListPresenter.refresh(AsynParams.create().put("top", String.valueOf(page)).put("sign", Resource.API.SIGN).put("chid", CHID));
    }

    @Override
    protected void loadMore(int page, int current) {
        mNewsListPresenter.load(AsynParams.create().put("top", String.valueOf(page)).put("dtop", String.valueOf(current)).put("sign", Resource.API.SIGN).put("chid", CHID));
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
        return ListUtil.arrayList(mNewsListPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void getListAd(boolean result, String message, List<ADModel> list) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        NewsListModel model = (NewsListModel) adapter.getItem(position);
        if(model == null) return;
        model.setIsRead(true);
        mNewsListPresenter.updateStatus(model.getChID() + "-" + model.getResourceGUID(), 1);
        NewsNavigation.listItemNavigation(model);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemChildClick(adapter, view, position);

        NewsListModel model = (NewsListModel) adapter.getItem(position);
        if(model == null) return;
        if(view.getId() == R.id.item_vote_left) {
            if(AccountHelper.getInstance().isLogin()) {
                mNewsListPresenter.addVoteAndResMTM(model, -1);
            }else {
                navigation(Routes.Account.LOGIN).navigation();
            }
        }else if(view.getId() == R.id.item_vote_right) {
            if(AccountHelper.getInstance().isLogin()) {
                mNewsListPresenter.addVoteAndResMTM(model, 1);
            }else {
                navigation(Routes.Account.LOGIN).navigation();
            }
        }
    }

    @Override
    public void addVoteAndResMTM(NewsListModel model, int result) {
        if(result == 1) {
            ToastHelper.toastSucceed("已投票");
        }else if(result == 0) {
            model.setVoteNum(NumberUtil.parseInt(model.getVoteNum()) + 1 + "");
            mAdapter.notifyItemChanged(mAdapter.getData().indexOf(model));
            ToastHelper.toastSucceed("已投票");
        }else {
            ToastHelper.toastSucceed("投票失败");
        }
    }
}
