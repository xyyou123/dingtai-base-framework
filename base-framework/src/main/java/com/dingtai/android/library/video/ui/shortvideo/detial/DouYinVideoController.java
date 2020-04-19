package com.dingtai.android.library.video.ui.shortvideo.detial;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;


import com.dingtai.android.library.video.ui.player.MarqueeTextView;
import com.dueeeke.videoplayer.controller.GestureVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.L;
import com.lnr.android.base.framework.R;


/**
 * `
 * 直播/点播控制器
 * Created by Devlin_n on 2017/4/7.
 */

public class DouYinVideoController extends GestureVideoController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    protected TextView mTotalTime, mCurrTime;
    protected ImageView mFullScreenButton;
    protected LinearLayout mBottomContainer, mTopContainer;
    protected SeekBar mVideoProgress;
    protected ImageView mBackButton;
    protected ImageView mLockButton;
    protected MarqueeTextView mTitle;
    private boolean mIsLive;
    private boolean mIsDragging;

    private ProgressBar mBottomProgress;
    private ImageView mPlayButton;
    private ImageView mStartPlayButton;
    private ProgressBar mLoadingProgress;
    private ImageView mThumb;
    private LinearLayout mCompleteContainer;
    private TextView mSysTime;//系统当前时间
    //    private ImageView mBatteryLevel;//电量
    private Animation mShowAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_in);
    private Animation mHideAnim = AnimationUtils.loadAnimation(getContext(), R.anim.dkplayer_anim_alpha_out);
    //    private BatteryReceiver mBatteryReceiver;
    protected ImageView mRefreshButton;


    public DouYinVideoController(@NonNull Context context) {
        this(context, null);
    }

    public DouYinVideoController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DouYinVideoController(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dkplayer_layout_douyin_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        mFullScreenButton = controllerView.findViewById(R.id.fullscreen);
        mFullScreenButton.setOnClickListener(this);
        mBottomContainer = controllerView.findViewById(R.id.bottom_container);
        mTopContainer = controllerView.findViewById(R.id.top_container);
        mVideoProgress = controllerView.findViewById(R.id.seekBar);
        mVideoProgress.setOnSeekBarChangeListener(this);
        mTotalTime = controllerView.findViewById(R.id.total_time);
        mCurrTime = controllerView.findViewById(R.id.curr_time);
        mBackButton = controllerView.findViewById(R.id.back);
        mBackButton.setOnClickListener(this);
        mLockButton = controllerView.findViewById(R.id.lock);
        mLockButton.setOnClickListener(this);
        mThumb = controllerView.findViewById(R.id.thumb);
        mThumb.setOnClickListener(this);
        mPlayButton = controllerView.findViewById(R.id.iv_play);
        mPlayButton.setOnClickListener(this);
        mStartPlayButton = controllerView.findViewById(R.id.start_play);
        mLoadingProgress = controllerView.findViewById(R.id.loading);
        mBottomProgress = controllerView.findViewById(R.id.bottom_progress);
        ImageView rePlayButton = controllerView.findViewById(R.id.iv_replay);
        rePlayButton.setOnClickListener(this);
        mCompleteContainer = controllerView.findViewById(R.id.complete_container);
        mCompleteContainer.setOnClickListener(this);
        mTitle = controllerView.findViewById(R.id.title);
        mSysTime = controllerView.findViewById(R.id.sys_time);
//        mBatteryLevel = controllerView.findViewById(R.id.iv_battery);
//        mBatteryReceiver = new BatteryReceiver(mBatteryLevel);
        mRefreshButton = controllerView.findViewById(R.id.iv_refresh);
        mRefreshButton.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        getContext().unregisterReceiver(mBatteryReceiver);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        getContext().registerReceiver(mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fullscreen || i == R.id.back) {
            doStartStopFullScreen();
        } else if (i == R.id.lock) {
            doLockUnlock();
        } else if (i == R.id.iv_play || i == R.id.thumb) {
            doPauseResume();
        } else if (i == R.id.iv_replay) {
            mediaPlayer.retry();
        } else if (i == R.id.iv_refresh) {
            mediaPlayer.refresh();
        }
    }

    public void showTitle() {
        mTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPlayerState(int playerState) {
        switch (playerState) {
            case IjkVideoView.PLAYER_NORMAL:
                L.e("PLAYER_NORMAL");
//                if (mIsLocked) return;
                setLayoutParams(new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                mFullScreenButton.setSelected(false);
                mBackButton.setVisibility(View.GONE);
                mLockButton.setVisibility(View.GONE);
                mTitle.setVisibility(View.INVISIBLE);
                mSysTime.setVisibility(View.GONE);
//                mBatteryLevel.setVisibility(View.GONE);
                mTopContainer.setVisibility(View.GONE);
                break;
            case IjkVideoView.PLAYER_FULL_SCREEN:
                L.e("PLAYER_FULL_SCREEN");

                mFullScreenButton.setSelected(true);
                mBackButton.setVisibility(View.VISIBLE);
                mTitle.setVisibility(View.VISIBLE);
                mSysTime.setVisibility(View.VISIBLE);
//                mBatteryLevel.setVisibility(View.VISIBLE);
                if (mShowing) {
                    mLockButton.setVisibility(View.GONE);
                    mTopContainer.setVisibility(View.GONE);
                } else {
                    mLockButton.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE:
                L.e("STATE_IDLE");
                hide();

                mLockButton.setSelected(false);
                mediaPlayer.setLock(false);
                mBottomProgress.setProgress(0);
                mBottomProgress.setSecondaryProgress(0);
                mVideoProgress.setProgress(0);
                mVideoProgress.setSecondaryProgress(0);
                mCompleteContainer.setVisibility(View.GONE);
                mBottomProgress.setVisibility(View.GONE);
                mLoadingProgress.setVisibility(View.GONE);
                mStartPlayButton.setVisibility(View.VISIBLE);
                mThumb.setVisibility(View.VISIBLE);
                break;
            case IjkVideoView.STATE_PLAYING:
                L.e("STATE_PLAYING");
                post(mShowProgress);
                mPlayButton.setSelected(true);
                mLoadingProgress.setVisibility(View.GONE);
                mCompleteContainer.setVisibility(View.GONE);
                mThumb.setVisibility(View.GONE);
                mStartPlayButton.setVisibility(View.GONE);
                break;
            case IjkVideoView.STATE_PAUSED:
                L.e("STATE_PAUSED");
                mPlayButton.setSelected(false);
                mStartPlayButton.setVisibility(View.GONE);
                break;
            case IjkVideoView.STATE_PREPARING:
                L.e("STATE_PREPARING");
                mCompleteContainer.setVisibility(View.GONE);
                mStartPlayButton.setVisibility(View.GONE);
                mLoadingProgress.setVisibility(View.VISIBLE);
//                mThumb.setVisibility(View.VISIBLE);
                break;
            case IjkVideoView.STATE_PREPARED:
                L.e("STATE_PREPARED");
                if (!mIsLive) mBottomProgress.setVisibility(View.VISIBLE);
//                mLoadingProgress.setVisibility(GONE);
                mStartPlayButton.setVisibility(View.GONE);
                break;
            case IjkVideoView.STATE_ERROR:
                L.e("STATE_ERROR");
                mStartPlayButton.setVisibility(View.GONE);
                mLoadingProgress.setVisibility(View.GONE);
                mThumb.setVisibility(View.GONE);
                mBottomProgress.setVisibility(View.GONE);
                mTopContainer.setVisibility(View.GONE);
                break;
            case IjkVideoView.STATE_BUFFERING:
                L.e("STATE_BUFFERING");
                mStartPlayButton.setVisibility(View.GONE);
                mLoadingProgress.setVisibility(View.VISIBLE);
                mThumb.setVisibility(View.GONE);
                break;
            case IjkVideoView.STATE_BUFFERED:
                mLoadingProgress.setVisibility(View.GONE);
                mStartPlayButton.setVisibility(View.GONE);
                mThumb.setVisibility(View.GONE);
                L.e("STATE_BUFFERED");
                break;
            case IjkVideoView.STATE_PLAYBACK_COMPLETED:
                L.e("STATE_PLAYBACK_COMPLETED");
                hide();
                removeCallbacks(mShowProgress);
                mStartPlayButton.setVisibility(View.GONE);
                mThumb.setVisibility(View.VISIBLE);
                mCompleteContainer.setVisibility(View.VISIBLE);
                mBottomProgress.setProgress(0);
                mBottomProgress.setSecondaryProgress(0);

                mediaPlayer.setLock(false);
                break;
        }
    }

    protected void doLockUnlock() {
//        if (mIsLocked) {
//
//            mShowing = false;
//
//            show();
//            mLockButton.setSelected(false);
//            Toast.makeText(getContext(), R.string.dkplayer_unlocked, Toast.LENGTH_SHORT).show();
//        } else {
//            hide();
//    
//            mLockButton.setSelected(true);
//            Toast.makeText(getContext(), R.string.dkplayer_locked, Toast.LENGTH_SHORT).show();
//        }
////        mediaPlayer.setLock(mIsLocked);
    }

    /**
     * 设置是否为直播视频
     */
    public void setLive() {
        mIsLive = true;
        mBottomProgress.setVisibility(View.GONE);
        mVideoProgress.setVisibility(View.INVISIBLE);
        mTotalTime.setVisibility(View.INVISIBLE);
        mCurrTime.setVisibility(View.INVISIBLE);
        mRefreshButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mIsDragging = true;
        removeCallbacks(mShowProgress);
        removeCallbacks(mFadeOut);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = mediaPlayer.getDuration();
        long newPosition = (duration * seekBar.getProgress()) / mVideoProgress.getMax();
        mediaPlayer.seekTo((int) newPosition);
        mIsDragging = false;
        post(mShowProgress);
        show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }

        long duration = mediaPlayer.getDuration();
        long newPosition = (duration * progress) / mVideoProgress.getMax();
        if (mCurrTime != null)
            mCurrTime.setText(stringForTime((int) newPosition));
    }

    @Override
    public void hide() {
        if (mShowing) {
            if (mediaPlayer.isFullScreen()) {
                mLockButton.setVisibility(View.GONE);
//                if (!mIsLocked) {
//                    hideAllViews();
//                }
            } else {
                mBottomContainer.setVisibility(View.GONE);
                mBottomContainer.startAnimation(mHideAnim);
            }
            if (!mIsLive) {
                mBottomProgress.setVisibility(View.VISIBLE);
                mBottomProgress.startAnimation(mShowAnim);
            }
            mShowing = false;
        }
    }

    private void hideAllViews() {
        mTopContainer.setVisibility(View.GONE);
        mTopContainer.startAnimation(mHideAnim);
        mBottomContainer.setVisibility(View.GONE);
        mBottomContainer.startAnimation(mHideAnim);
    }

    private void show(int timeout) {
        if (mSysTime != null)
            mSysTime.setText(getCurrentSystemTime());
        if (!mShowing) {
            if (mediaPlayer.isFullScreen()) {
                mLockButton.setVisibility(View.VISIBLE);
//                if (!mIsLocked) {
//                    showAllViews();
//                }
            } else {
                mBottomContainer.setVisibility(View.VISIBLE);
                mBottomContainer.startAnimation(mShowAnim);
            }
            if (!mIsLive) {
                mBottomProgress.setVisibility(View.VISIBLE);
//                mBottomProgress.startAnimation(mHideAnim);
            }
            mShowing = true;
        }
        removeCallbacks(mFadeOut);
        if (timeout != 0) {
            postDelayed(mFadeOut, timeout);
        }
    }

    private void showAllViews() {
        mBottomContainer.setVisibility(View.VISIBLE);
        mBottomContainer.startAnimation(mShowAnim);
        mTopContainer.setVisibility(View.VISIBLE);
        mTopContainer.startAnimation(mShowAnim);
    }

    @Override
    public void show() {
        sDefaultTimeout = 0;
        show(sDefaultTimeout);
    }

    @Override
    protected int setProgress() {
        if (mediaPlayer == null || mIsDragging) {
            return 0;
        }

        if (mTitle != null && TextUtils.isEmpty(mTitle.getText())) {
            mTitle.setText(mediaPlayer.getTitle());
        }

        if (mIsLive) return 0;

        int position = (int) mediaPlayer.getCurrentPosition();
        int duration = (int) mediaPlayer.getDuration();
        if (mVideoProgress != null) {
            if (duration > 0) {
                mVideoProgress.setEnabled(true);
                int pos = (int) (position * 1.0 / duration * mVideoProgress.getMax());
                mVideoProgress.setProgress(pos);
                mBottomProgress.setProgress(pos);
            } else {
                mVideoProgress.setEnabled(false);
            }
            int percent = mediaPlayer.getBufferPercentage();
            if (percent >= 95) { //解决缓冲进度不能100%问题
                mVideoProgress.setSecondaryProgress(mVideoProgress.getMax());
                mBottomProgress.setSecondaryProgress(mBottomProgress.getMax());
            } else {
                mVideoProgress.setSecondaryProgress(percent * 10);
                mBottomProgress.setSecondaryProgress(percent * 10);
            }
        }

        if (mTotalTime != null)
            mTotalTime.setText(stringForTime(duration));
        if (mCurrTime != null)
            mCurrTime.setText(stringForTime(position));

        return position;
    }


    @Override
    protected void slideToChangePosition(float deltaX) {
        if (mIsLive) {
            mNeedSeek = false;
        } else {
            super.slideToChangePosition(deltaX);
        }
    }

    public ImageView getThumb() {
        return mThumb;
    }

    @Override
    public boolean onBackPressed() {
//        if (mIsLocked) {
//            show();
//            Toast.makeText(getContext(), R.string.dkplayer_lock_tip, Toast.LENGTH_SHORT).show();
//            return true;
//        }

        Activity activity = scanForActivity(getContext());
        if (activity == null) return super.onBackPressed();

        if (mediaPlayer.isFullScreen()) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mediaPlayer.stopFullScreen();
            return true;
        }
        return super.onBackPressed();
    }

    public Activity scanForActivity(Context context) {
        return context == null ? null : (context instanceof Activity ? (Activity) context : (context instanceof ContextWrapper ? scanForActivity(((ContextWrapper) context).getBaseContext()) : null));
    }
}
