package com.dingtai.android.library.video.ui.live.tab.imagetext;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.LiveImageTextModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.uitl.AuthenticationUtil;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/29
 * 图文直播列表
 */
@Contract(name = "LiveImageText")
@Route(path = "/video/live/imagetext")
public class LiveImageTextFragment extends DefaultRecyclerviewFragment {

    @Inject
    protected LiveImageTextPresenter mLiveImageTextPresenter;

    @Autowired
    protected String liveId;

    @Override
    protected void refresh(int page) {
        mLiveImageTextPresenter.getLiveImageTextList(liveId, "" + Resource.API.PAGE, "0");
    }

    @Override
    protected void loadMore(int page, int current) {
        mLiveImageTextPresenter.getLiveImageTextList(liveId, "" + Resource.API.PAGE, "" + current);
    }

    @Override
    protected BaseAdapter adapter() {
        return new LiveImageTextAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mLiveImageTextPresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemChildClick(adapter, view, position);
        LiveImageTextModel model = (LiveImageTextModel) adapter.getItem(position);
        if (model == null) return;
        if (view.getId() == R.id.item_video) {
            String mediaURL = model.getMediaURL();
            mediaURL = AuthenticationUtil.getAuthenticationUrl(mediaURL, model.getMediaURL());
            VideoNavigation.simplePlayer(PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                    .setTitle(model.getNewsLiveTitle())
                    .addUrls(mediaURL)
                    .build());
        }
    }
}
