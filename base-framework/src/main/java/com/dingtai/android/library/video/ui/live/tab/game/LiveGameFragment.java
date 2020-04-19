package com.dingtai.android.library.video.ui.live.tab.game;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;
import com.dingtai.android.library.video.model.LiveGameModel;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.data.asyn.core.AsynParams;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/9/5
 * 直播 互动游戏
 */
@Contract(name = "LiveGame")
@Route(path = "/video/live/game")
public class LiveGameFragment extends DefaultRecyclerviewFragment {

    /**
     * 直播id
     */
    @Autowired
    protected String liveId;

    @Autowired
    protected String tabCode;

    @Autowired
    protected String gameType;

    @Inject
    protected LiveGamePresenter mLiveGamePresenter;

    @Override
    protected void refresh(int page) {
        mLiveGamePresenter.refresh(AsynParams.create().put("LiveID", liveId).put("GameType", gameType).put("TabCode", tabCode));
    }

    @Override
    protected void loadMore(int page, int current) {

    }

    @Override
    protected int page() {
        return 100000;
    }

    @Override
    protected BaseAdapter adapter() {
        return new LiveGameAdapter();
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mLiveGamePresenter);
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);

        LiveGameModel model = (LiveGameModel) adapter.getItem(position);
        if (model == null) return;

        switch (NumberUtil.parseInt(model.getGameType())) {
            case 3:
                navigation(Routes.Account.YOYO).withString("id", model.getGameYaoYaoID()).navigation();
                break;
            case 5:
                navigation(Routes.News.DETAILS).withString("ID", model.getResourceGUID()).navigation();
                break;
            default:
                navigation(Routes.Modules.WEB)
                        .withString("url", model.getGameUrl())
                        .withString("title", model.getGameName()).navigation();
                break;
        }
    }
}
