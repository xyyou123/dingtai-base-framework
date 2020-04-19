package com.dingtai.android.library.video.ui.player.controller.douyin;

import android.support.annotation.NonNull;
import android.view.View;

import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.video.ui.player.controller.DefaultAbstractController;
import com.dingtai.android.library.video.ui.player.controller.SimpleController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.date.DateUtil;

/**
 * author:lnr
 * date:2018/11/28
 * 电视直播
 */
public class DouYinController extends DefaultAbstractController {

    public DouYinController(@NonNull IjkVideoView view) {
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


    @Override
    public SimpleController init(PlayerModel model) {
        return super.init(model);
    }

    @Override
    public SimpleController init(PlayerModel model, final com.lnr.android.base.framework.ui.control.listener.OnClickListener backListener, com.lnr.android.base.framework.ui.control.listener.OnClickListener shareListener) {
        init(model);
        textTitle.setText(model.getTitle());

        if (backListener != null) {
            ViewListen.setClick(imageBack, new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
                @Override
                protected void onSafeClick(View view) {
                    backListener.onClick(view);
                }
            });
        }

        if (shareListener != null) {
            ViewListen.setClick(imageShare, shareListener);
            imageShare.setVisibility(VISIBLE);
        } else {
            imageShare.setVisibility(GONE);
        }


        textTimeSystem.setText(DateUtil.format(System.currentTimeMillis(), "HH:mm"));
        if (model.getThumb() != null) {
            GlideHelper.load(imageThum, model.getThumb());
            imageThum.setVisibility(VISIBLE);
        }
        return this;
    }
}
