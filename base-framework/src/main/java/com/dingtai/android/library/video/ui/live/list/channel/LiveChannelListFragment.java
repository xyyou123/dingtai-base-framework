package com.dingtai.android.library.video.ui.live.list.channel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.video.DaggerVideoDagger;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.dingtai.android.library.video.ui.live.list.channel.adapter.AdapterProvider;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DividerItemDecoration;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/8/30
 */
@Contract(name = "LiveChannel")
@Route(path = "/video/live/list/channel")
public class LiveChannelListFragment extends DefaultRecyclerviewFragment {

    @Inject
    protected LiveChannelPresenter mLiveChannelPresenter;

    @Autowired
    protected String type;

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void refresh(int page) {
        mLiveChannelPresenter.refresh(AsynParams.create().put("type", type).put("top", String.valueOf(page)).put("dtop", "0"));
    }

    @Override
    protected void loadMore(int page, int count) {
        mLiveChannelPresenter.loadMore(AsynParams.create().put("type", type).put("top", String.valueOf(page())).put("dtop", String.valueOf(count)));
    }

    @Override
    protected BaseAdapter adapter() {
        return AdapterProvider.getAdapter(type);
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return new DividerItemDecoration(getContext());
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mLiveChannelPresenter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        LiveChannelModel model = (LiveChannelModel) adapter.getItem(position);
        if(model == null) return;
        VideoNavigation.live(model);
    }
}
