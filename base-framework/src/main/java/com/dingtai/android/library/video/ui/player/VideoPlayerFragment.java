package com.dingtai.android.library.video.ui.player;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;

import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dingtai.android.library.video.ui.player.controller.live.ActivitiesLiveController;
import com.dingtai.android.library.video.ui.player.controller.live.AudioLiveController;
import com.dingtai.android.library.video.ui.player.controller.live.ImageTextLiveController;
import com.dingtai.android.library.video.ui.player.controller.live.TVLiveController;
import com.dingtai.android.library.video.ui.player.controller.vod.VodController;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UMengShare;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.uitl.DimenUtil;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author :  lnr
 * date : 2018/8/19.
 * 视频播放组件
 * 具体使用参见
 *
 * @see SimpleVidioPlayerActivity
 */
@Route(path = "/video/player/fragment")
public class VideoPlayerFragment extends BaseFragment {

    private DTVedioPlayer ijkVideoView;

    @Autowired
    protected PlayerModel model;

    protected ShareMenu shareMenu;

    @Override
    public String digest() {
        return null;
    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected View contentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_play, container, false);
    }

    @Override
    protected void initImmersionBar(boolean immersion) {

    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        ijkVideoView = (DTVedioPlayer) view;

    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        switch (model.getPlayerSize()) {
            case PlayerModel.SIZE_16_9:
                int size = DimenUtil.getScreenSize(view.getContext())[0];
                ijkVideoView.setLayoutParams(new FrameLayout.LayoutParams(size, (size * 9) >> 4));
                break;
            case PlayerModel.SIZE_FULL:
                ijkVideoView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                break;
            case PlayerModel.SIZE_AUTO:
                ijkVideoView.setPlaceholderScale(0.5625f);
                ijkVideoView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
            default:
                ijkVideoView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                break;
        }

        PlayerConfig.Builder builder = new PlayerConfig.Builder().disableAudioFocus();

        final DefaultAbstractController controller = createController(model.getType());
        if (controller == null) {
            return;
        }

        PlayerConfig playerConfig = builder.build();
        ijkVideoView.setPlayerConfig(playerConfig);

        controller.init(model, new OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                finish();
            }
        }, model.getShareUrl() == null ? null : new OnClickListener() {
            @Override
            protected void onSafeClick(View view) {
                shareMenu = createShareMenu(model);
                shareMenu.show();
            }
        });

        if (controller instanceof AudioLiveController) {
            requestPermission(Manifest.permission.RECORD_AUDIO)
                    .request(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                controller.play();
                            }
                        }
                    });
        } else {
            controller.play();
        }
    }

    protected ShareMenu createShareMenu(PlayerModel model) {
        UMWeb web = UMengShare.createWeb(model.getShareUrl(),
                model.getShareTitle(),
                model.getShareContent(),
                TextUtils.isEmpty(model.getThumb()) ? null : new UMImage(getActivity(), model.getThumb()));
        return new ShareMenu(getActivity(), web);
    }

    protected DefaultAbstractController createController(int type) {
        DefaultAbstractController controller = null;
        switch (type) {
            case PlayerModel.TYPE_LIVE_TV:
                controller = new TVLiveController(ijkVideoView);
                break;
            case PlayerModel.TYPE_LIVE_ACTIVITIES:
                controller = new ActivitiesLiveController(ijkVideoView);
                break;
            case PlayerModel.TYPE_LIVE_IMAGE_AND_TEXT:
                controller = new ImageTextLiveController(ijkVideoView);
                break;
            case PlayerModel.TYPE_LIVE_AUDIO:
                controller = new AudioLiveController(ijkVideoView);
                break;
            case PlayerModel.TYPE_VOD:
                controller = new VodController(ijkVideoView);
                break;
        }

        return controller;
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    public void onPause() {
        super.onPause();
        ijkVideoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ijkVideoView != null)
            ijkVideoView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ijkVideoView != null)
            ijkVideoView.release();
    }


    @Override
    public boolean onBackPressed() {
        return ijkVideoView != null && ijkVideoView.onBackPressed();
    }
}
