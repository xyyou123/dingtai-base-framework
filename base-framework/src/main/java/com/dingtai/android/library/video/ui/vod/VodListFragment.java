package com.dingtai.android.library.video.ui.vod;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.video.DaggerVideoDagger;
import com.dingtai.android.library.video.model.VodListModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.fragment.BaseRecyclerViewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/8/29
 */
@Route(path = "/video/vod/list")
@Contract(name = "VodList")
public class VodListFragment extends BaseRecyclerViewFragment implements VodListContract.View, BaseQuickAdapter.OnItemClickListener {

    @Autowired
    protected String RecType;


    @Inject
    protected VodListPresenter mVodListPresenter;
    protected VodListAdapter mVodListAdapter;

    @Override
    protected void retry() {
        mVodListPresenter.GetResByTrueProgram(RecType);
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mVodListPresenter);
    }


    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManagerWrapper(view.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext()));
        mVodListAdapter = new VodListAdapter();
        mRecyclerView.setAdapter(mVodListAdapter);

        mVodListAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        mVodListPresenter.GetResByTrueProgram(RecType);
    }

    @Override
    public void GetResByTrueProgram(boolean result, String message, List<VodListModel> list) {
        handlerRefreshResult(result, mVodListAdapter, list, 10000);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        VodListModel vodListModel = mVodListAdapter.getItem(position);
        if ("1".equals(vodListModel.getVODType())) {
            VideoNavigation.vodListChild(vodListModel.getID(), vodListModel.getProgramName(), vodListModel.getVODType());
        } else {
            VideoNavigation.vodListChild(vodListModel.getID(), vodListModel.getProgramName(), vodListModel.getVODType());
        }
    }
}
