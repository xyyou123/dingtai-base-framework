package com.dingtai.android.library.video.ui.vod.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Score;

import com.dingtai.android.library.video.VideoShareHelper;
import com.dingtai.android.library.video.event.AddVodReadNumEvent;
import com.dingtai.android.library.video.model.VodProgramModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.dingtai.android.library.video.ui.player.VideoPlayerFragment;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.uitl.AuthenticationUtil;

import java.util.List;

/**
 * author:lnr
 * date:2019/1/9
 * 点播详情
 */
@Route(path = "/video/vod/details")
public class VodDetailsActivity extends BaseActivity {

    protected VideoPlayerFragment fragment;

    @Autowired
    protected VodProgramModel model;

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void setContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vod);
    }

    @Override
    protected void initImmersionBar(boolean immersion) {

    }

    @Override
    protected void initView() {
        RxBus.getDefault().post(new AddVodReadNumEvent(model.getID()));
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        play();
        replaceFragment(R.id.comment, (Fragment) VideoNavigation.vodListDetailsComment(model));

        RxBus.getDefault().post(new ScoreModel(Score.SCORE_LOOK_VIDEO));
    }

    protected void play() {
        String programContentUrl = model.getProgramContentUrl();

        programContentUrl = AuthenticationUtil.getAuthenticationUrl(programContentUrl, model.getAuthenticationflag());
        PlayerModel.Builder builder = PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                .setTitle(model.getProgramContentName())
                .setThumb(model.getProgramContentLogo())
                .setSize(PlayerModel.SIZE_AUTO)
                .setShareInfo(formatShareUrl(), model.getProgramContentName(), GlobalConfig.SHARE_CONTENT_SPARE2)
                .addUrls(programContentUrl);
        PlayerModel playerModel = builder.build();
        fragment = VideoNavigation.player(playerModel);
        replaceFragment(R.id.frame, fragment);
    }

    protected String formatShareUrl() {
        return VideoShareHelper.getVodShareUrl(model.getID());
    }

    @Override
    public void onBackPressed() {
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
