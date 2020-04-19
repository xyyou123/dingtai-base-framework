package com.dingtai.android.library.video.ui.live;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.resource.GlobalConfig;

import com.dingtai.android.library.video.model.LiveProgramModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;

/**
 * author:lnr
 * date:2018/9/29
 * 图文直播间
 */
@Route(path = "/video/live/main/imagetext")
public class LiveMainImageTextActivity extends LiveMainActivity {

    @Override
    protected void initView() {
        super.initView();
        SHARE_URL = GlobalConfig.SHARE_URL_LIVE_IMAGETEXT;
    }

    @Override
    protected void play(LiveProgramModel liveProgramModel) {

        FrameLayout frameLayout = findViewById(R.id.frame);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_live_main_image_text, frameLayout, false);
        GlideHelper.load(view.findViewById(R.id.image_pic), model.getLiveImageUrl());

        TextView title = view.findViewById(R.id.text_title);
        title.setText(model.getLiveChannelName());

        ViewListen.setClick(view.findViewById(R.id.image_back), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });

        frameLayout.addView(view);
    }
}
