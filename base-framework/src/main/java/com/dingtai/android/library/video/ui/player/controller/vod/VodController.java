package com.dingtai.android.library.video.ui.player.controller.vod;

import android.support.annotation.NonNull;

import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dueeeke.videoplayer.player.IjkVideoView;

/**
 * author:lnr
 * date:2018/11/28
 * 电视直播
 */
public class VodController extends DefaultAbstractController {

    public VodController(@NonNull IjkVideoView view) {
        super(view);
    }

    @Override
    protected void afterInitView() {
        super.afterInitView();

        seekBarProgress.setVisibility(VISIBLE);
        textTimeCurrent.setVisibility(VISIBLE);
        textTimeTotal.setVisibility(VISIBLE);

        textPlayStateHint.setVisibility(GONE);
        imageRefresh.setVisibility(GONE);
    }
}
