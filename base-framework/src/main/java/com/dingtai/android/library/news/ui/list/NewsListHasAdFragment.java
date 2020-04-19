package com.dingtai.android.library.news.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lnr.android.base.framework.ui.control.view.AdvertisementView;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 包含广告的新闻列表
 */
@Contract(name = "NewsListHasAD")
@Route(path = "/news/list/hasad")
public class NewsListHasAdFragment extends DefaultRecyclerviewFragment implements OnBannerListener, NewsListContract.View {

    @Inject
    protected NewsListPresenter mNewsListPresenter;
    /**
     * 广告
     */
    protected Banner mAdvertisementView;

    @Autowired
    protected String CHID;
    @Autowired
    protected String ADType;
    @Autowired
    protected String ADFor;
    @Autowired
    protected String IsIndexAD;
    @Autowired
    protected String OPType;

    @Autowired
    protected float adAspectRatio;

    protected List<ADModel> mADModels;

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
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        mAdvertisementView = AdvertisementView.createInRecyclerView(getContext(), adAspectRatio == 0 ?
                0.5625f : adAspectRatio);
        mAdvertisementView.setOnBannerListener(this);
        mAdvertisementView.setVisibility(View.GONE);
        super.afterInitView(view, savedInstanceState);
    }

    @Override
    public void OnBannerClick(int position) {
        if(mADModels == null || position > mADModels.size() - 1) {
            return;
        }
        ADModel model = mADModels.get(position);
        // 点击统计
        mNewsListPresenter.InsertADStatistics(model.getID(), OPType, Resource.API.SIGN);
        onAdClick(model);
    }

    /**
     * 广告点击
     * @param model 点击的对象
     */
    protected void onAdClick(ADModel model) {
        NewsNavigation.adNavigation(model);
    }

    @Override
    public void getListAd(boolean result, String message, List<ADModel> list) {
        mADModels = list;
        if(list == null || list.isEmpty()) {
            if(mAdvertisementView != null) {
                mAdvertisementView.stopAutoPlay();
                mAdvertisementView.setVisibility(View.GONE);
            }
        }else {
            mStatusLayoutManager.showContent();
            List<String> titls = new ArrayList<>();
            List<String> urls = new ArrayList<>();

            for (ADModel model : list) {
                titls.add(model.getADName());
                urls.add(model.getImgUrl());
            }
            mAdapter.setHeaderView(mAdvertisementView);
            mAdvertisementView.setVisibility(View.VISIBLE);
            mAdvertisementView.setBannerTitles(titls);
            mAdvertisementView.setImages(urls);
            mAdvertisementView.start();

            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void refresh(int page) {
        mNewsListPresenter.getListAd(CHID, Resource.API.SIGN, ADType, ADFor, IsIndexAD);
        mNewsListPresenter.refresh(AsynParams.create().put("top", String.valueOf(page)).put("sign", Resource.API.SIGN).put("chid", CHID));
    }

    @Override
    protected void loadMore(int page, int current) {
        mNewsListPresenter.load(AsynParams.create().put("top", String.valueOf(page)).put("dtop", String.valueOf(current)).put("sign", Resource.API.SIGN).put("chid", CHID));
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
    protected BaseAdapter adapter() {
        return new NewsListAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return new DividerItemDecoration(getContext());
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

    @Override
    public void onDestroy() {
        if(mAdvertisementView != null) {
            mAdvertisementView.releaseBanner();
        }
        super.onDestroy();
    }
}
