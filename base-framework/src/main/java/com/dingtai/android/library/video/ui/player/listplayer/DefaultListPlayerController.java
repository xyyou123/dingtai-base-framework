package com.dingtai.android.library.video.ui.player.listplayer;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dingtai.android.library.model.models.PlayerModel;

import com.dingtai.android.library.video.ui.player.controller.SimpleController;
import com.dueeeke.videoplayer.player.BaseIjkVideoView;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;

/**
 * author:lnr
 * date:2018/10/25
 */
public class DefaultListPlayerController extends SimpleController {


    /**
     * 顶部标题栏 底部操作栏 中间的播放按钮
     */
    protected View layoutTop, layoutBottom, layoutPlay;

    /**
     * 缩略图
     */
    protected ImageView imageThum, imageCenterPlay, imageBottomPlay, imageSwitchScreen;
    /**
     * 加载view
     */
    protected ProgressBar loadingView;
    /**
     * 进度条
     */
    protected SeekBar seekBarProgress;
    /**
     * 标题、重新播放提示、播放状态提示、系统时间、当前播放时间、视频总时间
     */
    protected TextView textTitle, textReplayHint, textTimeCurrent, textTimeTotal;


    public DefaultListPlayerController(@NonNull IjkVideoView view) {
        super(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_player_list;
    }

    @Override
    protected void afterInitView() {
        layoutTop = findViewById(R.id.layout_playing_top_layout);
        layoutBottom = findViewById(R.id.layout_playing_bottom_layout);
        layoutPlay = findViewById(R.id.player_layout_play);

        imageThum = findViewById(R.id.player_image_thum);
        imageCenterPlay = findViewById(R.id.player_image_center_play);
        imageBottomPlay = findViewById(R.id.player_image_bottom_play);
        imageSwitchScreen = findViewById(R.id.player_image_switch_screen);

        loadingView = findViewById(R.id.player_loading);

        seekBarProgress = findViewById(R.id.player_seekbar_progress);

        textTitle = findViewById(R.id.player_text_title);
        textReplayHint = findViewById(R.id.player_text_replay);
        textTimeCurrent = findViewById(R.id.player_time_current);
        textTimeTotal = findViewById(R.id.player_time_total);

        seekBarProgress.setProgress(0);
        seekBarProgress.setSecondaryProgress(0);
        seekBarProgress.setOnSeekBarChangeListener(this);

        ViewListen.setClick(imageCenterPlay, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if(mediaPlayer == null) return;
                if(currentPlayState == BaseIjkVideoView.STATE_PLAYBACK_COMPLETED) {
                    mediaPlayer.retry();
                }else {
                    doPauseResume();
                }
            }
        });

        ViewListen.setClick(imageBottomPlay, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                doPauseResume();
            }
        });

        ViewListen.setClick(imageSwitchScreen, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                doStartStopFullScreen();
            }
        });
    }

    @Override
    protected void onViewInited() {

    }

    @Override
    public void setPlayListener(com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {
        ViewListen.setClick(imageCenterPlay, listener);
        ViewListen.setClick(imageBottomPlay, listener);
    }

    @Override
    public SimpleController init(PlayerModel model) {
        super.init(model);
        textTitle.setText(model.getTitle());
        if(model.getThumb() != null) {
            GlideHelper.load(imageThum, model.getThumb());
        }
        return this;
    }

    @Override
    protected SeekBar getSeekbar() {
        return seekBarProgress;
    }

    @Override
    protected void updateProgress(int current, int duration) {
        textTimeCurrent.setText(stringForTime(current));
        textTimeTotal.setText(stringForTime(duration));
    }

    @Override
    protected void onPlayInit() {
        layoutPlay.setVisibility(VISIBLE);
        imageCenterPlay.setVisibility(VISIBLE);
        textReplayHint.setVisibility(GONE);
    }

    @Override
    protected void onPlayPrepare() {
        layoutPlay.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
    }

    @Override
    protected void onPlaying() {
        imageThum.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        imageBottomPlay.setSelected(true);
    }

    @Override
    protected void onPlayPause() {
        imageBottomPlay.setSelected(false);
    }

    @Override
    protected void onPlayBuffering() {
        loadingView.setVisibility(VISIBLE);
    }

    @Override
    protected void onPlayComplete() {
        layoutPlay.setVisibility(VISIBLE);
        imageCenterPlay.setVisibility(VISIBLE);
        textReplayHint.setVisibility(VISIBLE);
    }

    @Override
    protected void onPlayError() {
        loadingView.setVisibility(GONE);
        imageCenterPlay.setVisibility(GONE);
        textReplayHint.setVisibility(GONE);
    }

    @Override
    protected void onScreenNorlam2Full() {
        imageSwitchScreen.setSelected(true);
    }

    @Override
    protected void onScreenFull2Normal() {
        imageSwitchScreen.setSelected(false);
    }

    @Override
    protected void showAllView(boolean fullscreen, boolean lock) {
        if(isLocked) return;

        if(layoutTop.getAlpha() <= 0f) {
            layoutTop.animate().alpha(1).setDuration(300).start();
        }

        if(layoutBottom.getAlpha() <= 0f) {
            layoutBottom.animate().alpha(1).setDuration(300).start();
        }
    }

    @Override
    protected void onLockChanged(boolean fullscreen, boolean lock) {
        if(lock) {
            hideAllView(mediaPlayer != null && mediaPlayer.isFullScreen(), true);
        }else {
            showAllView(mediaPlayer != null && mediaPlayer.isFullScreen(), false);
        }
    }

    @Override
    protected void hideAllView(boolean fullscreen, boolean lock) {
        if(layoutTop.getAlpha() >= 1f) {
            layoutTop.animate().alpha(0).setDuration(300).start();
        }

        if(layoutBottom.getAlpha() >= 1f) {
            layoutBottom.animate().alpha(0).setDuration(300).start();
        }
    }

}
