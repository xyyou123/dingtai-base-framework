package com.dingtai.android.library.video.ui.shortvideo.pvlist;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.video.DaggerVideoDagger;
import com.dingtai.android.library.video.model.ShortVideoModel;
import com.dingtai.android.library.video.model.ShortVideoUserInfoModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.ToolbarRecyclerViewActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2019/3/14 0014.
 */
@Route(path = "/video/pvlist/list")
@Contract(name = "PersionVideoList")
public class PersionVideoListActivity extends ToolbarRecyclerViewActivity implements PersionVideoListContract.View {
    @Inject
    protected PersionVideoListPresenter mPersionVideoListPresenter;
    @Autowired(name = "userGUID")
    protected String userGUID;

    @Override
    protected void retry() {

    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int contentLayoutResId() {
        return super.contentLayoutResId();
    }
    

    @Override
    public void refreshUserData(ShortVideoUserInfoModel r) {

    }

    @Override
    public void refreshData(List<ShortVideoModel> list) {

    }
}
