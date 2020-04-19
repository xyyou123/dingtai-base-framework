package com.dingtai.android.library.video.ui.player.listplayer.pip;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.dueeeke.videoplayer.controller.GestureVideoController;
import com.dueeeke.videoplayer.player.BaseIjkVideoView;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.R;

/**
 * 悬浮播放控制器
 * Created by Devlin_n on 2017/6/1.
 */

public class FloatController extends GestureVideoController implements View.OnClickListener {


    private ProgressBar proLoading;
    private ImageView playButton;


    public FloatController(@NonNull Context context) {
        super(context);
    }

    public FloatController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_float_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        this.setOnClickListener(this);
        controllerView.findViewById(R.id.btn_close).setOnClickListener(this);
        controllerView.findViewById(R.id.btn_skip).setOnClickListener(this);
        proLoading = controllerView.findViewById(R.id.loading);
        playButton = controllerView.findViewById(R.id.start_play);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_close) {
            PIPManager.getInstance().stopFloatWindow();
            PIPManager.getInstance().reset();
        } else if (id == R.id.start_play) {
            if(mediaPlayer == null) return;
            if(currentPlayState == BaseIjkVideoView.STATE_PLAYBACK_COMPLETED) {
                mediaPlayer.retry();
            }else {
                doPauseResume();
            }
        } else if (id == R.id.btn_skip) {
            if (PIPManager.getInstance().getActClass() != null) {
                Intent intent = new Intent(getContext(), PIPManager.getInstance().getActClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        }
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE:
                playButton.setSelected(false);
                playButton.setVisibility(VISIBLE);
                proLoading.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_PLAYING:
                playButton.setSelected(true);
                playButton.setVisibility(GONE);
                proLoading.setVisibility(GONE);
                hide();
                break;
            case IjkVideoView.STATE_PAUSED:
                playButton.setSelected(false);
                playButton.setVisibility(VISIBLE);
                proLoading.setVisibility(GONE);
                show(0);
                break;
            case IjkVideoView.STATE_PREPARING:
                playButton.setVisibility(GONE);
                proLoading.setVisibility(VISIBLE);
                break;
            case IjkVideoView.STATE_PREPARED:
                playButton.setVisibility(GONE);
                proLoading.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_ERROR:
                proLoading.setVisibility(GONE);
                playButton.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_BUFFERING:
                playButton.setVisibility(GONE);
                proLoading.setVisibility(VISIBLE);
                break;
            case IjkVideoView.STATE_BUFFERED:
                playButton.setVisibility(GONE);
                proLoading.setVisibility(GONE);
                break;
            case IjkVideoView.STATE_PLAYBACK_COMPLETED:
                show(0);
                removeCallbacks(mShowProgress);
                break;
        }
    }


    @Override
    public void show() {
        show(sDefaultTimeout);
    }

    private void show(int timeout) {
        if (currentPlayState == IjkVideoView.STATE_BUFFERING) return;
        if (!mShowing) {
            playButton.setVisibility(VISIBLE);
        }
        mShowing = true;

        removeCallbacks(mFadeOut);
        if (timeout != 0) {
            postDelayed(mFadeOut, timeout);
        }
    }


    @Override
    public void hide() {
        if (currentPlayState == IjkVideoView.STATE_BUFFERING) return;
        if (mShowing) {
            playButton.setVisibility(GONE);
            mShowing = false;
        }
    }

    private float downX;
    private float downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                // True if the child does not want the parent to intercept touch events.
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float absDeltaX = Math.abs(ev.getX() - downX);
                float absDeltaY = Math.abs(ev.getY() - downY);
                if (absDeltaX > ViewConfiguration.get(getContext()).getScaledTouchSlop() ||
                        absDeltaY > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
