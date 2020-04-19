package com.dingtai.android.library.video.ui.player.controller.live;

import android.support.annotation.NonNull;

import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dingtai.android.library.video.ui.player.controller.SimpleController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.common.image.load.GlideHelper;

import java.util.ArrayList;

/**
 * author:lnr
 * date:2018/11/28
 * 活动直播控制器
 */
public class ActivitiesLiveController extends DefaultAbstractController {

    private int mState;
    private final static int STATE_TO_BEGIN = 1;
    private final static int STATE_LIVEING = 2;
    private final static int STATE_OVER= 3;


    public ActivitiesLiveController(@NonNull IjkVideoView view) {
        super(view);
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
    }

    @Override
    public SimpleController init(PlayerModel model) {
        super.init(model);

        long current = System.currentTimeMillis();
        if(current < model.getBegin()) {
            //未开始
            mState = STATE_TO_BEGIN;
        }else if(current < model.getEnd()) {
            mState = STATE_LIVEING;
        }else {
            mState = STATE_OVER;
        }
        return this;
    }


    @Override
    public synchronized void play() {
        updateView();
    }

    private void updateView() {
        switch (mState) {
            case STATE_TO_BEGIN :
                ArrayList<String> urls = model.getUrls();
                if(urls != null && urls.size() > 0) {
                    seekBarProgress.setVisibility(INVISIBLE);
                    textTimeCurrent.setVisibility(GONE);
                    textTimeTotal.setVisibility(GONE);

                    imageBottomPlay.setVisibility(VISIBLE);
                    textPlayStateHint.setVisibility(VISIBLE);
                    textPlayStateHint.setText("即将开始");

                    imageRefresh.setVisibility(VISIBLE);
                    imageSwitchScreen.setVisibility(VISIBLE);

                    doPauseResume();
                }else {
                    seekBarProgress.setVisibility(INVISIBLE);
                    textTimeCurrent.setVisibility(GONE);
                    textTimeTotal.setVisibility(GONE);

                    textPlayStateHint.setVisibility(VISIBLE);
                    textPlayStateHint.setText("即将开始");

                    imageBottomPlay.setVisibility(GONE);
                    imageRefresh.setVisibility(GONE);
                    imageSwitchScreen.setVisibility(GONE);

                    loadingView.setVisibility(GONE);
                    layoutPlay.setVisibility(GONE);
                    imageThum.setVisibility(VISIBLE);
                    GlideHelper.load(imageThum, model.getThumb());
                }
                break;
            case STATE_LIVEING:
                seekBarProgress.setVisibility(INVISIBLE);
                textTimeCurrent.setVisibility(GONE);
                textTimeTotal.setVisibility(GONE);

                imageBottomPlay.setVisibility(VISIBLE);
                textPlayStateHint.setVisibility(VISIBLE);
                textPlayStateHint.setText("正在直播");

                imageRefresh.setVisibility(VISIBLE);
                imageSwitchScreen.setVisibility(VISIBLE);

                doPauseResume();

                break;
            case STATE_OVER:
                seekBarProgress.setVisibility(VISIBLE);
                textTimeCurrent.setVisibility(VISIBLE);
                textTimeTotal.setVisibility(VISIBLE);

                imageBottomPlay.setVisibility(VISIBLE);
                textPlayStateHint.setVisibility(VISIBLE);
                textPlayStateHint.setText("正在回放");

                imageRefresh.setVisibility(GONE);
                imageSwitchScreen.setVisibility(VISIBLE);

                doPauseResume();
                break;
        }
    }
}
