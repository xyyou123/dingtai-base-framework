package com.dingtai.android.library.video.ui.player.listplayer.pip;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.Framework;

/**
 * author:lnr
 * date:2018/11/5
 */
public class PIPManager {

    private static PIPManager instance;
    private IjkVideoView ijkVideoView;
    private FloatView floatView;
    private FloatController mFloatController;
    private boolean isShowing;
    private int mPlayingHash = -1;
    private Class mActClass;
    private View mCurrentView;

    private PIPManager() {
        ijkVideoView = new IjkVideoView(Framework.getInstance().getApplication());
        mFloatController = new FloatController(Framework.getInstance().getApplication());
        floatView = new FloatView(Framework.getInstance().getApplication(), 0, 0);
    }

    public static PIPManager getInstance() {
        if (instance == null) {
            synchronized (PIPManager.class) {
                if (instance == null) {
                    instance = new PIPManager();
                }
            }
        }
        return instance;
    }

    public IjkVideoView getIjkVideoView() {
        return ijkVideoView;
    }

    public FloatView getFloatView() {
        return floatView;
    }

    public void startFloatWindow() {
        if (isShowing) return;
        removePlayerFormParent();
        mFloatController.setPlayState(ijkVideoView.getCurrentPlayState());
        mFloatController.setPlayerState(ijkVideoView.getCurrentPlayerState());
        ijkVideoView.setVideoController(mFloatController);
        floatView.addView(ijkVideoView);
        floatView.addToWindow();
        isShowing = true;
    }

    public void stopFloatWindow() {
        if (!isShowing) return;
        floatView.removeFromWindow();
        removePlayerFormParent();
        isShowing = false;
    }

    private void removePlayerFormParent() {
        if(ijkVideoView == null) return;
        ViewParent parent = ijkVideoView.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(ijkVideoView);
        }
    }

    public void setPlayingHash(int hash, View currentView) {
        this.mPlayingHash = hash;
        if(mCurrentView != null) {
            mCurrentView.setVisibility(View.VISIBLE);
        }
        this.mCurrentView = currentView;
    }

    public int getPlayingHash() {
        return mPlayingHash;
    }

    public void pause() {
        if (isShowing) return;
        ijkVideoView.pause();
    }

    public void resume() {
        if (isShowing) return;
        ijkVideoView.resume();
    }

    public void reset() {
        if (isShowing) return;
        removePlayerFormParent();
        ijkVideoView.setVideoController(null);
        ijkVideoView.release();
        mPlayingHash = -1;
        mCurrentView = null;
        mActClass = null;
    }

    public boolean onBackPress() {
        return !isShowing && ijkVideoView.onBackPressed();
    }

    public boolean isStartFloatWindow() {
        return isShowing;
    }

    /**
     * 显示悬浮窗
     */
    public void setFloatViewVisible() {
        if (isShowing) {
            ijkVideoView.resume();
            floatView.setVisibility(View.VISIBLE);
        }
    }

    public void setActClass(Class cls) {
        this.mActClass = cls;
    }

    public Class getActClass() {
        return mActClass;
    }

    public View getCurrentView() {
        return mCurrentView;
    }
}
