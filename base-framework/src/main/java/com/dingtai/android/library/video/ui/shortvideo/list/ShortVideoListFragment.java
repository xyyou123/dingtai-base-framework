package com.dingtai.android.library.video.ui.shortvideo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/3/11 0011.
 */
@Route(path = "/video/shortvideo/list")
@Contract(name = "ShortVideoList")
public class ShortVideoListFragment extends DefaultRecyclerviewFragment implements ShortVideoListContract.View, BaseQuickAdapter.OnItemClickListener {
    public static final String ACTION = "";
    @Inject
    protected ShortVideoListPresenter mShortVideoListPresenter;


    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mShortVideoListPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mStatusLayoutManager.showContent();
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        ViewListen.setClick(findViewById(R.id.iv_upload), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
//                ARouter.getInstance().build(Routes.Video.VIDEO_UPLOAD_MY)
//                        .withBoolean(Resource.KEY.NEED_LOGIN, true).navigation();
                mShortVideoListPresenter.getData(20 + "", "0", Resource.API.STID);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ARouter.getInstance().build("/video/shortvideo/detial").withObject("list", adapter.getData())
                .withInt("position", position).navigation();
    }

    @Override
    protected void refresh(int page) {
        mShortVideoListPresenter.getData(page + "", "0", Resource.API.STID);
    }

    @Override
    protected void loadMore(int page, int current) {
        mShortVideoListPresenter.getData(page + "", current + "", Resource.API.STID);
    }

    @Override
    protected BaseQuickAdapter adapter() {
        return new ShortVideoListAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_short_video_list;
    }


}
