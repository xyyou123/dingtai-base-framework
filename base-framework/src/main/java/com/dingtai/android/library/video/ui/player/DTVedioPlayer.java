package com.dingtai.android.library.video.ui.player;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.WindowUtil;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * author:lnr
 * date:2018/8/30
 */
public class DTVedioPlayer extends IjkVideoView {

    private float mPlaceholderScale;

    public DTVedioPlayer(@NonNull Context context) {
        super(context);
    }

    public DTVedioPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DTVedioPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onOrientationLandscape(Activity activity) {
        if (this.currentOrientation != 2) {
            if (this.currentOrientation == 1 && this.isFullScreen()) {
                this.currentOrientation = 2;
            } else {
                this.currentOrientation = 2;
                if (!this.isFullScreen()) {
                    this.startFullScreen();
                }
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }
    }

    public void startFullScreen() {
        if (this.mVideoController != null) {
            Activity activity = WindowUtil.scanForActivity(this.mVideoController.getContext());
            if (activity != null) {
                if (!this.isFullScreen) {
                    this.removeView(this.playerContainer);
                    ViewGroup contentView = activity.findViewById(android.R.id.content);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
                    contentView.addView(this.playerContainer, params);
                    this.orientationEventListener.enable();
                    this.isFullScreen = true;
                    this.setPlayerState(11);
                }
            }
        }
    }

    public void stopFullScreen() {
        if (this.mVideoController != null) {
            Activity activity = WindowUtil.scanForActivity(this.mVideoController.getContext());
            if (activity != null) {
                if (this.isFullScreen) {
                    if (!this.mPlayerConfig.mAutoRotate) {
                        this.orientationEventListener.disable();
                    }
                    ViewGroup contentView = activity.findViewById(android.R.id.content);
                    contentView.removeView(this.playerContainer);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
                    this.addView(this.playerContainer, params);
                    this.requestFocus();
                    this.isFullScreen = false;
                    this.setPlayerState(10);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(mPlaceholderScale == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec((int) (View.MeasureSpec.getSize(widthMeasureSpec) * mPlaceholderScale), View.MeasureSpec.EXACTLY));
    }

    public void setPlaceholderScale(@FloatRange(from = 0f, to = 1f) float scale) {
        this.mPlaceholderScale = scale;
    }

    @Override
    public void onVideoSizeChanged(int videoWidth, int videoHeight) {
        mPlaceholderScale = 0;
        requestLayout();
        super.onVideoSizeChanged(videoWidth, videoHeight);
    }

    @Override
    protected void initPlayer() {
        if (mMediaPlayer == null) {
            if (mPlayerConfig.mAbstractPlayer != null) {
                mMediaPlayer = mPlayerConfig.mAbstractPlayer;
            } else {
                mMediaPlayer = new DTIjkPlayer(getContext());
            }

            mMediaPlayer.bindVideoView(this);
            mMediaPlayer.initPlayer();
            mMediaPlayer.setEnableMediaCodec(mPlayerConfig.enableMediaCodec);
            mMediaPlayer.setLooping(mPlayerConfig.isLooping);
        }
        addDisplay();
    }

    public int getAudioSessionId() {
        if(mMediaPlayer instanceof DTIjkPlayer) {
            IjkMediaPlayer player = ((DTIjkPlayer) mMediaPlayer).getPlayer();
            if(player != null) {
                return player.getAudioSessionId();
            }
        }
        return 0;
    }
}
