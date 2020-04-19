package com.dingtai.android.library.video.ui.player.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.dingtai.android.library.model.models.PlayerModel;
import com.dueeeke.videoplayer.controller.GestureVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.WindowUtil;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.uitl.logger.Logger;

import java.util.Iterator;

/**
 * author:lnr
 * date:2018/9/12
 */
public abstract class SimpleController extends GestureVideoController implements SeekBar.OnSeekBarChangeListener {

    protected IjkVideoView mIjkVideoView;

    //进度条是否处于拖动状态
    private boolean isDragging;

    private OnScreenStateChangeListener mOnScreenStateChangeListener;

    protected PlayerModel model;

    /**
     * 错误次数
     */
    private int errorCount;

    public SimpleController(@NonNull IjkVideoView view) {
        super(view.getContext());
        this.mIjkVideoView = view;
        gestureEnabled = true;
    }


    public interface OnScreenStateChangeListener {

        void onScreenStateChange(boolean currentIsFull);
    }

    /**
     * 手势处理
     */
    private class PlayerGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean firstTouch;
        private boolean mChangePosition;
        private boolean mChangeBrightness;
        private boolean mChangeVolume;

        public boolean onDown(MotionEvent e) {
            if (SimpleController.this.gestureEnabled && !WindowUtil.isEdge(SimpleController.this.getContext(), e)) {
                Activity activity = WindowUtil.scanForActivity(getContext());
                if(activity == null) {
                    return super.onDown(e);
                }
                SimpleController.this.streamVolume = SimpleController.this.mAudioManager.getStreamVolume(3);
                SimpleController.this.mBrightness = activity.getWindow().getAttributes().screenBrightness;
                this.firstTouch = true;
                this.mChangePosition = false;
                this.mChangeBrightness = false;
                this.mChangeVolume = false;
                return true;
            } else {
                return super.onDown(e);
            }
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (SimpleController.this.mShowing) {
                SimpleController.this.hide();
            } else {
                SimpleController.this.show();
            }

            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (SimpleController.this.gestureEnabled && !WindowUtil.isEdge(SimpleController.this.getContext(), e1)) {
                float deltaX = e1.getX() - e2.getX();
                float deltaY = e1.getY() - e2.getY();
                if (this.firstTouch) {
                    this.mChangePosition = Math.abs(distanceX) >= Math.abs(distanceY);
                    if (!this.mChangePosition) {
                        int size = mediaPlayer != null && mediaPlayer.isFullScreen()
                                ? WindowUtil.getScreenHeight(getContext(), false) / 2
                                : WindowUtil.getScreenWidth(getContext()) / 2;

                        if (e2.getX() > size) {
                            this.mChangeVolume = true;
                            this.mChangeBrightness = false;
                        } else {
                            this.mChangeBrightness = true;
                            this.mChangeVolume = false;
                        }
                    }

                    this.firstTouch = false;
                }

                if (this.mChangePosition) {
                    slideToChangePosition(deltaX);
                } else if (this.mChangeBrightness) {
                    slideToChangeBrightness(deltaY);
                } else if (this.mChangeVolume) {
                    slideToChangeVolume(deltaY);
                }

                return true;
            } else {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (!isLocked) {
                onTouchDoubleTap();
            }

            return true;
        }
    }

    protected void onTouchDoubleTap() {
        doPauseResume();
    }

    public void setPlayListener(com.lnr.android.base.framework.ui.control.listener.OnClickListener listener) {

    }

    public void setOnScreenStateChangeListener(OnScreenStateChangeListener onScreenStateChangeListener) {
        this.mOnScreenStateChangeListener = onScreenStateChangeListener;
    }

    @Override
    protected void doStartStopFullScreen() {
        if(mOnScreenStateChangeListener != null) {
            mOnScreenStateChangeListener.onScreenStateChange(this.mediaPlayer.isFullScreen());
        }else {
            super.doStartStopFullScreen();
        }
    }

    @Override
    public final void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }
        if(currentPlayState < IjkVideoView.STATE_PREPARING) {
            //视频加载完成，不允许操作进度条
            return;
        }

        if(mediaPlayer == null) return;

        float r = mediaPlayer.getDuration() / ((float)seekBar.getMax());

        updateProgress((int) (progress * r), (int) mediaPlayer.getDuration());
    }

    protected abstract SeekBar getSeekbar();

    @Override
    protected int setProgress() {
        if (mediaPlayer == null || isDragging) {
            return 0;
        }

        SeekBar seekBar = getSeekbar();
        if(seekBar == null) return 0;
        int position = (int) mediaPlayer.getCurrentPosition();
        int duration = (int) mediaPlayer.getDuration();
        if (duration > 0) {
            seekBar.setEnabled(true);
            int pos = (int) (position * 1.0 / duration * seekBar.getMax());
            seekBar.setProgress(pos);
        } else {
            seekBar.setEnabled(false);
        }
        int percent = mediaPlayer.getBufferPercentage();
        if (percent >= 95) { //解决缓冲进度不能100%问题
            seekBar.setSecondaryProgress(seekBar.getMax());
        } else {
            seekBar.setSecondaryProgress(percent * 10);
        }

        updateProgress(position, duration);
        return position;
    }

    /**
     * 当进度跳更新时
     * @param current 当前进度
     * @param duration 视频总长度
     */
    protected abstract void updateProgress(int current, int duration);

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isDragging = true;
        gestureEnabled = false;
        removeCallbacks(mFadeOut);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = mediaPlayer.getDuration();
        long newPosition = (duration * seekBar.getProgress()) / seekBar.getMax();
        mediaPlayer.seekTo((int) newPosition);
        isDragging = false;
        gestureEnabled = true;
        removeCallbacks(mFadeOut);
        postDelayed(mFadeOut, sDefaultTimeout);
    }

    @Override
    protected void initView() {
        super.initView();
        mGestureDetector = new GestureDetector(getContext(), new PlayerGestureListener());
        mShowing = true;
        afterInitView();
        onViewInited();
    }

    protected abstract void afterInitView();

    /**
     * 初始化
     */
    public SimpleController init(PlayerModel model) {
        this.model = model;
        filterPlayUrl();
        mIjkVideoView.setVideoController(this);
        if(model.getUrls() != null && model.getUrls().size() > 0) {
            mIjkVideoView.setUrl(model.getUrls().get(0));
        }
        return this;
    }


    public final SimpleController onlyInit(PlayerModel model) {
        this.model = model;
        filterPlayUrl();
        mIjkVideoView.setVideoController(this);
        if(model.getUrls() != null && model.getUrls().size() > 0) {
            mIjkVideoView.setUrl(model.getUrls().get(0));
        }
        return this;
    }

    /**
     * 过滤播放地址
     */
    private void filterPlayUrl() {
        if(model.getUrls() == null) return;
        Iterator<String> iterator = model.getUrls().iterator();
        while (iterator.hasNext()) {
            if(TextUtils.isEmpty(iterator.next())) iterator.remove();
        }
    }

    public synchronized void play() {
        doPauseResume();
    }

    protected void playError() {
        if(model.getUrls().size() <= errorCount + 1) {
            errorCount = 0;
            onPlayError();
            return;
        }
        mIjkVideoView.setUrl(model.getUrls().get(errorCount++));
        
        mIjkVideoView.retry();
    }

    /**
     * view初始化完成后
     */
    protected abstract void onViewInited();

    /**
     * 播放初始化阶段
     */
    protected abstract void onPlayInit();

    /**
     * 播放加载阶段
     */
    protected abstract void onPlayPrepare();

    /**
     * 播放阶段
     */
    protected abstract void onPlaying();

    /**
     * 播放 暂停
     */
    protected abstract void onPlayPause();

    /**
     * 缓冲
     */
    protected abstract void onPlayBuffering();

    /**
     * 播放完成阶段
     */
    protected abstract void onPlayComplete();

    /**
     * 播放错误
     */
    protected abstract void onPlayError();

    /**
     * 非全屏 -> 全屏
     */
    protected abstract void onScreenNorlam2Full();

    /**
     * 全屏 -> 非全屏
     */
    protected abstract void onScreenFull2Normal();

    @Override
    public void show() {
        showAllView(mediaPlayer != null && mediaPlayer.isFullScreen(), isLocked);
        removeCallbacks(mFadeOut);
        postDelayed(mFadeOut, sDefaultTimeout);
        mShowing = true;
    }

    @Override
    public void hide() {
        removeCallbacks(mFadeOut);
        hideAllView(mediaPlayer != null && mediaPlayer.isFullScreen(), isLocked);
        mShowing = false;
    }

    /**
     * 显示所有view
     * @param fullscreen 是否全屏
     * @param lock 是否锁定
     */
    protected abstract void showAllView(boolean fullscreen, boolean lock);

    /**
     * 当锁定状态切换时
     * @param fullscreen 是否全屏
     * @param lock 是否锁定
     */
    protected abstract void onLockChanged(boolean fullscreen, boolean lock);

    /**
     * 隐藏所有view 即只显示视频
     * @param fullscreen 是否全屏
     * @param lock 是否锁定
     */
    protected abstract void hideAllView(boolean fullscreen, boolean lock);

    public boolean switchLock() {
        isLocked = !isLocked;
        onLockChanged(mediaPlayer != null && mediaPlayer.isFullScreen(), isLocked);
        if(mediaPlayer != null) mediaPlayer.setLock(isLocked);
        return isLocked;
    }

    public boolean isLock() {
        return isLocked;
    }

    @Override
    public void setPlayerState(int playerState) {
        super.setPlayerState(playerState);
        switch (playerState) {
            case IjkVideoView.PLAYER_NORMAL:
                if (isLocked) return;
                setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                onScreenFull2Normal();
                break;
            case IjkVideoView.PLAYER_FULL_SCREEN:
                if (isLocked) return;
                onScreenNorlam2Full();
                break;
        }
    }

    @Override
    public void setPlayState(int playState) {
        if(playState == currentPlayState) return;
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE://初始状态
                onPlayInit();
                Logger.log("player", "STATE_IDLE");
                break;
            case IjkVideoView.STATE_PREPARING://加载中
                onPlayPrepare();
                Logger.log("player", "STATE_PREPARING");
                break;
            case IjkVideoView.STATE_PREPARED://加载完成
                onPlaying();
                Logger.log("player", "STATE_PREPARED");
                break;
            case IjkVideoView.STATE_PLAYING://播放中
                removeCallbacks(mShowProgress);
                post(mShowProgress);
                onPlaying();
                hide();
                Logger.log("player", "STATE_PLAYING");
                break;
            case IjkVideoView.STATE_PAUSED://暂停
                onPlayPause();
                show();
                Logger.log("player", "STATE_PAUSED");
                break;
            case IjkVideoView.STATE_ERROR://播放错误
                playError();
                Logger.log("player", "STATE_ERROR");
                break;
            case IjkVideoView.STATE_BUFFERING://数据缓冲
                onPlayBuffering();
                Logger.log("player", "STATE_BUFFERING");
                break;
            case IjkVideoView.STATE_BUFFERED://缓冲完成
                onPlaying();
                Logger.log("player", "STATE_BUFFERED");
                break;
            case IjkVideoView.STATE_PLAYBACK_COMPLETED:
                onPlayComplete();//播放完成
                show();
                Logger.log("player", "STATE_PLAYBACK_COMPLETED");
                break;
        }
    }

    @Override
    public boolean onBackPressed() {
        if(mediaPlayer != null && mediaPlayer.isFullScreen()) {
            if(isLocked) {
                ToastHelper.toastDefault("请先解除锁定");
            }else {
                doStartStopFullScreen();
            }
            return true;
        }
        return false;
    }
}
