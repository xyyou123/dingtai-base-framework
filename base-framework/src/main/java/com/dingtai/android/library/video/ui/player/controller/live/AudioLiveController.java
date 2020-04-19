package com.dingtai.android.library.video.ui.player.controller.live;

import android.support.annotation.NonNull;
import android.view.SurfaceView;


import com.dingtai.android.library.video.ui.player.DTVedioPlayer;
import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.R;

import me.bogerchan.niervisualizer.NierVisualizerManager;
import me.bogerchan.niervisualizer.renderer.IRenderer;
import me.bogerchan.niervisualizer.renderer.columnar.ColumnarType4Renderer;

/**
 * author:lnr
 * date:2018/11/28
 * 电视直播
 */
public class AudioLiveController extends DefaultAbstractController {

    private SurfaceView mVisualizerSurfaceView;

    private NierVisualizerManager mNierVisualizerManager;

    public AudioLiveController(@NonNull IjkVideoView view) {
        super(view);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_audio_player;
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();

        seekBarProgress.setVisibility(INVISIBLE);
        textTimeCurrent.setVisibility(GONE);
        textTimeTotal.setVisibility(GONE);

        textPlayStateHint.setVisibility(VISIBLE);
        textPlayStateHint.setText("正在直播");

        imageRefresh.setVisibility(VISIBLE);

        mVisualizerSurfaceView = findViewById(R.id.Visualizer);
    }

    @Override
    protected void onPlaying() {
        super.onPlaying();
        startVisualizer();
    }

    @Override
    protected void onPlayPause() {
        super.onPlayPause();
        stopVisualizer(true);
    }

    @Override
    protected void onPlayComplete() {
        super.onPlayComplete();
        stopVisualizer(true);
    }

    @Override
    protected void playError() {
        super.playError();
        stopVisualizer(false);
    }

    private void startVisualizer() {
        mVisualizerSurfaceView.setVisibility(VISIBLE);

        if(mNierVisualizerManager == null) {
            if(mIjkVideoView instanceof DTVedioPlayer) {
                mNierVisualizerManager = new NierVisualizerManager();
                mNierVisualizerManager.init(((DTVedioPlayer) mIjkVideoView).getAudioSessionId());
                mNierVisualizerManager.start(mVisualizerSurfaceView, new IRenderer[]{new ColumnarType4Renderer()});
            }else {
                //会报错
            }
        }else {
            mNierVisualizerManager.resume();
        }
    }

    private void stopVisualizer(boolean pause) {
        if(!pause) {
            mVisualizerSurfaceView.setVisibility(GONE);
        }
        if(mNierVisualizerManager != null) {
            if(pause) {
                mNierVisualizerManager.pause();
            }else {
                mNierVisualizerManager.stop();
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        if(super.onBackPressed()) {
            stopVisualizer(false);
            if(mNierVisualizerManager != null) {
                mNierVisualizerManager.release();
            }
            return true;
        }
        return false;
    }
}
