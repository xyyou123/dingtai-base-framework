package com.dingtai.android.library.video.ui.player.controller;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dingtai.android.library.model.models.PlayerModel;

import com.dueeeke.videoplayer.player.BaseIjkVideoView;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.date.DateUtil;

/**
 * author:lnr
 * date:2018/11/28
 */
public abstract class DefaultAbstractController extends SimpleController {

    /**
     * 顶部标题栏 底部操作栏 中间的播放按钮
     */
    protected View layoutTop, layoutBottom, layoutPlay;

    /**
     * 缩略图
     */
    protected ImageView imageThum, imageShare, imageCenterPlay, imageBack, imageBottomPlay, imageRefresh, imageLock, imageSwitchScreen;
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
    protected TextView textTitle, textReplayHint, textPlayStateHint, textTimeSystem, textTimeCurrent, textTimeTotal;

    public DefaultAbstractController(@NonNull IjkVideoView view) {
        super(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_player;
    }

    @Override
    protected void afterInitView() {
        layoutTop = findViewById(R.id.layout_playing_top_layout);
        layoutBottom = findViewById(R.id.layout_playing_bottom_layout);
        layoutPlay = findViewById(R.id.player_layout_play);

        imageThum = findViewById(R.id.player_image_thum);
        imageCenterPlay = findViewById(R.id.player_image_center_play);
        imageBack = findViewById(R.id.player_image_back);
        imageShare = findViewById(R.id.player_image_share);
        imageBottomPlay = findViewById(R.id.player_image_bottom_play);
        imageRefresh = findViewById(R.id.player_image_refresh);
        imageLock = findViewById(R.id.player_image_lock);
        imageSwitchScreen = findViewById(R.id.player_image_switch_screen);

        loadingView = findViewById(R.id.player_loading);

        seekBarProgress = findViewById(R.id.player_seekbar_progress);

        textTitle = findViewById(R.id.player_text_title);
        textReplayHint = findViewById(R.id.player_text_replay);
        textPlayStateHint = findViewById(R.id.player_text_state_hint);
        textTimeSystem = findViewById(R.id.player_time_system);
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

        ViewListen.setClick(imageRefresh, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mediaPlayer.refresh();
            }
        });

        ViewListen.setClick(imageLock, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                imageLock.setSelected(switchLock());
            }
        });

        ViewListen.setClick(imageSwitchScreen, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                doStartStopFullScreen();
            }
        });
    }

    public SimpleController init(PlayerModel model, final com.lnr.android.base.framework.ui.control.listener.OnClickListener backListener,
                                 com.lnr.android.base.framework.ui.control.listener.OnClickListener shareListener) {
        init(model);
        textTitle.setText(model.getTitle());

        if(backListener != null) {
            ViewListen.setClick(imageBack, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
                @Override
                protected void onSafeClick(View view) {
                    if(mediaPlayer != null && mediaPlayer.isFullScreen()) {
                        doStartStopFullScreen();
                    }else {
                        backListener.onClick(view);
                    }
                }
            });
        }

        if(shareListener != null) {
            ViewListen.setClick(imageShare, shareListener);
            imageShare.setVisibility(VISIBLE);
        }else {
            imageShare.setVisibility(GONE);
        }


        textTimeSystem.setText(DateUtil.format(System.currentTimeMillis(), "HH:mm"));
        if(model.getThumb() != null) {
            GlideHelper.load(imageThum, model.getThumb());
        }
        return this;
    }

    @Override
    protected void onViewInited() {

    }

    @Override
    protected int setProgress() {
        return super.setProgress();
    }

    @Override
    protected void slideToChangePosition(float deltaX) {
        super.slideToChangePosition(deltaX);
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
        imageThum.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        imageCenterPlay.setVisibility(GONE);
        textReplayHint.setVisibility(GONE);
    }

    @Override
    protected void onScreenNorlam2Full() {
        imageSwitchScreen.setSelected(true);
        imageLock.setVisibility(mShowing ? VISIBLE : GONE);
    }

    @Override
    protected void onScreenFull2Normal() {
        imageSwitchScreen.setSelected(false);
        imageLock.setVisibility(GONE);
    }

    @Override
    protected void showAllView(boolean fullscreen, boolean lock) {
        if(fullscreen) {
            imageLock.setVisibility(VISIBLE);
        }

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
        imageLock.setVisibility(GONE);

        if(layoutTop.getAlpha() >= 1f) {
            layoutTop.animate().alpha(0).setDuration(300).start();
        }

        if(layoutBottom.getAlpha() >= 1f) {
            layoutBottom.animate().alpha(0).setDuration(300).start();
        }
    }
}
