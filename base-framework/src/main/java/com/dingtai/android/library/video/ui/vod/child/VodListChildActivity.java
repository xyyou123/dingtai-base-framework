package com.dingtai.android.library.video.ui.vod.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.DaggerVideoDagger;
import com.dingtai.android.library.video.model.VodProgramModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarRecyclerViewActivity;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/8/29
 * 点播子列表
 */
@Route(path = "/video/vod/list/child")
@Contract(name = "VodListChild")
public class VodListChildActivity extends ToolbarRecyclerViewActivity implements VodListChildContract.View, BaseQuickAdapter.OnItemClickListener {

    @Autowired
    protected String ID;
    @Autowired
    protected String VODType;
    @Autowired
    protected String ProgramName;

    @Inject
    protected VodListChildPresenter mVodListChildPresenter;
    protected BaseAdapter<VodProgramModel> mVodListChildAdapter;


    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mVodListChildPresenter);
    }
    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView() {
        super.initView();

        toolbar().setTitle(ProgramName);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, true));
        mVodListChildAdapter = getDataAdapter();
        mRecyclerView.setAdapter(mVodListChildAdapter);

        mVodListChildAdapter.setOnItemClickListener(this);

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mVodListChildPresenter.GetUpContent(ID, String.valueOf(Resource.API.PAGE), String.valueOf(mVodListChildAdapter.getItemCount()));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mVodListChildPresenter.GetDownConetent(ID, String.valueOf(Resource.API.PAGE));
            }
        });
    }

    @NonNull
    protected BaseAdapter<VodProgramModel> getDataAdapter() {
        return new VodListChildAdapter();
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mVodListChildPresenter.GetDownConetent(ID, String.valueOf(Resource.API.PAGE));
    }


    @Override
    protected void retry() {
        mVodListChildPresenter.GetDownConetent(ID, String.valueOf(Resource.API.PAGE));
    }


    @Override
    public void GetDownConetent(boolean result, String message, List<VodProgramModel> list) {
        handlerRefreshResult(result, mVodListChildAdapter, list, Resource.API.PAGE);
    }

    @Override
    public void GetUpContent(boolean result, String message, List<VodProgramModel> list) {
        handlerLoadMoreResult(result, mVodListChildAdapter, list, Resource.API.PAGE);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        VodProgramModel model = mVodListChildAdapter.getItem(position);
        if(model == null) return;
        VideoNavigation.vodListDetails(model);
    }
}
