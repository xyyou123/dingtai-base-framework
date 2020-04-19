package com.dingtai.android.library.video.ui.player.controller.live;

import android.support.annotation.NonNull;

import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dingtai.android.library.video.ui.player.controller.SimpleController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.common.image.load.GlideHelper;

/**
 * author:lnr
 * date:2018/11/28
 * 活动直播控制器
 */
public class ImageTextLiveController extends DefaultAbstractController {

    private int mState;
    private final static int STATE_TO_BEGIN = 1;
    private final static int STATE_LIVEING = 2;
    private final static int STATE_OVER= 3;


    public ImageTextLiveController(@NonNull IjkVideoView view) {
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
                layoutPlay.setVisibility(GONE);

                seekBarProgress.setVisibility(INVISIBLE);
                textTimeCurrent.setVisibility(GONE);
                textTimeTotal.setVisibility(GONE);

                textPlayStateHint.setVisibility(VISIBLE);
                textPlayStateHint.setText("直播未开始");

                imageBottomPlay.setVisibility(GONE);
                imageRefresh.setVisibility(GONE);
                imageSwitchScreen.setVisibility(GONE);

                loadingView.setVisibility(GONE);
                layoutPlay.setVisibility(GONE);
                imageThum.setVisibility(VISIBLE);
                GlideHelper.load(imageThum, model.getThumb());
                break;
            case STATE_LIVEING:
                layoutPlay.setVisibility(GONE);

                seekBarProgress.setVisibility(INVISIBLE);
                textTimeCurrent.setVisibility(GONE);
                textTimeTotal.setVisibility(GONE);

                imageBottomPlay.setVisibility(GONE);
                textPlayStateHint.setVisibility(GONE);
                textPlayStateHint.setText("正在直播");

                imageRefresh.setVisibility(GONE);
                imageSwitchScreen.setVisibility(GONE);

                imageThum.setVisibility(VISIBLE);
                GlideHelper.load(imageThum, model.getThumb());

                break;
            case STATE_OVER:
                layoutPlay.setVisibility(GONE);

                seekBarProgress.setVisibility(INVISIBLE);
                textTimeCurrent.setVisibility(GONE);
                textTimeTotal.setVisibility(GONE);

                imageBottomPlay.setVisibility(GONE);
                textPlayStateHint.setVisibility(VISIBLE);
                textPlayStateHint.setText("已结束");

                imageRefresh.setVisibility(GONE);
                imageSwitchScreen.setVisibility(GONE);

                imageThum.setVisibility(VISIBLE);
                GlideHelper.load(imageThum, model.getThumb());
                break;
        }
    }

    @Override
    protected void onTouchDoubleTap() {
        if(mState == STATE_TO_BEGIN) return;
        super.onTouchDoubleTap();
    }
}
