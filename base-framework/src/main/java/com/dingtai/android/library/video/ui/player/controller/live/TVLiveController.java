package com.dingtai.android.library.video.ui.player.controller.live;

import android.support.annotation.NonNull;

import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dueeeke.videoplayer.player.IjkVideoView;

/**
 * author:lnr
 * date:2018/11/28
 * 电视直播
 */
public class TVLiveController extends DefaultAbstractController {

    public static String PLAY_STATE_HINT = "正在直播";

    public TVLiveController(@NonNull IjkVideoView view) {
        super(view);
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();

        seekBarProgress.setVisibility(INVISIBLE);
        textTimeCurrent.setVisibility(GONE);
        textTimeTotal.setVisibility(GONE);

        textPlayStateHint.setVisibility(VISIBLE);
        textPlayStateHint.setText(PLAY_STATE_HINT);

        imageRefresh.setVisibility(VISIBLE);
    }
}
