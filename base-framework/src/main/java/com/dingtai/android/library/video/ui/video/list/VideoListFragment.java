package com.dingtai.android.library.video.ui.video.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.model.helper.AccountHelper;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.dingtai.android.library.video.ui.player.listplayer.pip.PIPManager;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UMengShare;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.common.DefaultRecyclerviewFragment;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.uitl.ListUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;

/**
 * author:lnr
 * date:2018/11/5
 */
@Contract(name = "VideoList")
@Route(path = "/video/video/list")
public class VideoListFragment extends DefaultRecyclerviewFragment implements VideoListContract.View {

    private ListVideoAdapter mListVideoAdapter;
    private PIPManager mPIPManager;

    private ShareMenu shareMenu;

    @Inject
    protected VideoListPresenter mHotVideoListPresenter;

    @Autowired
    protected String CHID;

    @Override
    protected void refresh(int i) {
        if(mListVideoAdapter.isPlayerInThis()) {
            mPIPManager.reset();
        }

        mHotVideoListPresenter.getHotVideoList(CHID, "" + i, "0");
    }

    @Override
    protected void loadMore(int i, int i1) {
        mHotVideoListPresenter.getHotVideoList(CHID, "" + i, "" + i1);
    }

    @Override
    protected BaseQuickAdapter adapter() {
        mPIPManager = PIPManager.getInstance();
        mPIPManager.setActClass(getActivity().getClass());
        mListVideoAdapter = new ListVideoAdapter(mPIPManager);
        return mListVideoAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecoration() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mHotVideoListPresenter);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
    }

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerVideoDagger.builder().applicationComponent(applicationComponent)
                .asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemChildClick(adapter, view, position);
        VideoModel item = (VideoModel) adapter.getItem(position);
        if(item == null) return;
        itemChildClick(view, item);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        VideoModel item = (VideoModel) adapter.getItem(position);
        if(item == null) return;
        itemClick(item);
    }

    protected void itemClick(VideoModel item) {
        VideoNavigation.videoListDetails(item);
    }

    protected void itemChildClick(View view, VideoModel item) {
        int i = view.getId();
        if (i == R.id.item_layout_share) {
            share(item);

        } else if (i == R.id.item_layout_comment) {
            VideoNavigation.videoListDetails(item);
        } else if (i == R.id.item_layout_zan) {
            if (!AccountHelper.getInstance().isLogin()) {
                navigation(Routes.Account.LOGIN).navigation();
                return;
            }
            if ("1".equals(item.getIsExsitPoint())) {
                mHotVideoListPresenter.deleteZan(item);
            } else {
                mHotVideoListPresenter.addZan(item);
            }
        }
    }

    private void share(VideoModel item) {
       if(shareMenu == null) {
           String url = GlobalConfig.SHARE_URL_VIDEO + item.getID2();
           String content = MessageFormat.format("看电视、听广播、读资讯，尽在{0}！", getResources().getString(R.string.app_name));
           UMWeb web = UMengShare.createWeb(url, item.getName(), content, new UMImage(getContext(), item.getImageUrl()));
           shareMenu = new ShareMenu(getActivity(), web);
       }
       if(!shareMenu.isShowing()) shareMenu.show();
    }

    @Override
    public void addZan(boolean result, VideoModel model) {
        if(result) {
            model.setIsExsitPoint("1");
            model.setGoodPoint((NumberUtil.parseInt(model.getGoodPoint()) + 1) + "");
            mListVideoAdapter.notifyItemChanged(mListVideoAdapter.getData().indexOf(model));
            ToastHelper.toastSucceed("已点赞");
        }
    }

    @Override
    public void deleteZan(boolean result, VideoModel model) {
        if(result) {
            model.setIsExsitPoint("0");
            model.setGoodPoint((NumberUtil.parseInt(model.getGoodPoint()) - 1) + "");
            mListVideoAdapter.notifyItemChanged(mListVideoAdapter.getData().indexOf(model));
        }
    }


    @Override
    public void onPause() {
        if(mPIPManager != null)mPIPManager.pause();
        super.onPause();
    }

    @Override
    public boolean onBackPressed() {
        return mPIPManager != null && mPIPManager.onBackPress();
    }

    @Override
    public void onDestroy() {
        if(mPIPManager != null)mPIPManager.reset();
        UMShareAPI.get(getContext()).release();
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden && mPIPManager != null) {
            mPIPManager.pause();
        }
    }

    public void onParentHiddenChanged(boolean hidden) {
        if(hidden && mPIPManager != null) {
            mPIPManager.pause();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser && mPIPManager != null) {
            mPIPManager.pause();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);
    }

}
