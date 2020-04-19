package com.dingtai.android.library.video.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.DaggerVideoDagger;

import com.dingtai.android.library.video.VideoComponentConstant;
import com.dingtai.android.library.video.VideoShareHelper;
import com.dingtai.android.library.video.event.AddLiveReadNumEvent;
import com.dingtai.android.library.video.event.Event;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.dingtai.android.library.video.ui.VideoNavigation;
import com.dingtai.android.library.video.ui.player.VideoPlayerFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.base.BaseFragment;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.uitl.AuthenticationUtil;
import com.lnr.android.base.framework.uitl.JsonUtil;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/8/30
 * 直播activity
 */
@Route(path = "/video/live/main")
public class LiveMainActivity extends BaseActivity {

    protected VideoPlayerFragment fragment;

    protected SlidingTabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected List<BaseFragment> fragmentList;
    protected List<String> titles;

    @Autowired
    protected LiveChannelModel model;

    @Autowired
    protected String data;

    protected ImageView btnUp;

    protected View mPlayerFrame;

    /**
     * 分享地址
     */
    protected String SHARE_URL = GlobalConfig.SHARE_URL_LIVE;

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void setContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_live);
    }

    @Override
    protected void initImmersionBar(boolean immersion) {

    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.TabLayout);
        mViewPager = findViewById(R.id.ViewPager);

        mPlayerFrame = findViewById(R.id.frame);
        btnUp = findViewById(R.id.btn_up);

        ViewListen.setClick(btnUp, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if (!btnUp.isSelected()) {
                    mPlayerFrame.setVisibility(View.GONE);
                    btnUp.setImageResource(R.drawable.icon_down);
                } else {
                    mPlayerFrame.setVisibility(View.VISIBLE);
                    btnUp.setImageResource(R.drawable.icon_up);
                }
                btnUp.setSelected(!btnUp.isSelected());
            }
        });

        if (model == null) {
            if (data == null) {
                finish();
                return;
            }
            if (data.startsWith("[")) {
                model = JsonUtil.parseArray(data, LiveChannelModel.class).get(0);
            } else {
                model = JsonUtil.parseObject(data, LiveChannelModel.class);
            }
        }

        RxBus.getDefault().post(new AddLiveReadNumEvent(model.getID()));
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerVideoDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {

        play(null);

        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();

        int liveType = NumberUtil.parseInt(model.getLiveType());
        initPager(liveType);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mTabLayout.setViewPager(mViewPager);

        registe(Event.ChangeCurrentLiveProgramme.class, new Consumer<Event.ChangeCurrentLiveProgramme>() {
            @Override
            public void accept(Event.ChangeCurrentLiveProgramme event) throws Exception {
                play(event.model);
            }
        });

        RxBus.getDefault().post(new ScoreModel("10011"));
    }

    protected void initPager(int liveType) {
        switch (liveType) {
            case VideoComponentConstant.LiveType.VEDIO:
            case VideoComponentConstant.LiveType.AUDIO:
                fragmentList.add((BaseFragment) VideoNavigation.liveChatFragment(model));
                titles.add("聊天");
                fragmentList.add((BaseFragment) VideoNavigation.liveProgramme(model.getID(), "jiemudan", liveType));
                titles.add("节目单");
                break;
            default:
                //case VideoComponentConstant.LiveType.IMAGE_AND_TEXT://图文
                fragmentList.add((BaseFragment) VideoNavigation.liveImageText(model.getID()));
                titles.add("直播");
                fragmentList.add((BaseFragment) VideoNavigation.liveChatFragment(model));
                titles.add("聊天");
                break;
        }

        fragmentList.add((BaseFragment) VideoNavigation.liveGame(model.getID(), "hudongyouxi", "1"));
        titles.add("互动游戏");

        fragmentList.add((BaseFragment) navigation(Routes.News.RELEVANT).withString("id", model.getID()).navigation());
        titles.add("相关");
    }

    protected void play(LiveProgramModel liveProgramModel) {
        if (liveProgramModel == null) {
            int liveType = PlayerModel.TYPE_LIVE_TV;
            switch (NumberUtil.parseInt(model.getLiveType())) {
                case VideoComponentConstant.LiveType.VEDIO:
                    liveType = PlayerModel.TYPE_LIVE_TV;
                    SHARE_URL = GlobalConfig.SHARE_URL + "/Share2/dszb.aspx?id=" + model.getID();
                    break;
                case VideoComponentConstant.LiveType.AUDIO:
                    liveType = PlayerModel.TYPE_LIVE_AUDIO;
                    SHARE_URL = GlobalConfig.SHARE_URL + "/Share2/dtzb.aspx?id=" + model.getID();
                    break;
                case VideoComponentConstant.LiveType.ACTIVITY:
                    liveType = PlayerModel.TYPE_LIVE_ACTIVITIES;
                    SHARE_URL = GlobalConfig.SHARE_URL + "/Share2/hdzb.aspx?id=" + model.getID();
                    break;
                case VideoComponentConstant.LiveType.IMAGE_AND_TEXT:
                    liveType = PlayerModel.TYPE_LIVE_IMAGE_AND_TEXT;
                    SHARE_URL = GlobalConfig.SHARE_URL + "/Share2/twzb.aspx?id=" + model.getID();
                    break;
            }

            formatShareUrl(NumberUtil.parseInt(model.getLiveType()));

            PlayerModel.Builder builder = createPlayBuilder(liveType);

            long begin = DateUtil.format(model.getLiveBeginDate());
            long end = DateUtil.format(model.getLiveEndDate());
            long current = System.currentTimeMillis();
            if (begin > current) {
                //未开始
                if (!TextUtils.isEmpty(model.getLiveBeginMedia())) {
                    String strUrl = model.getLiveBeginMedia();
                    strUrl = AuthenticationUtil.getAuthenticationUrl(strUrl, model.getAuthenticationflag());

                    builder.addUrls(strUrl);
                } else if (!TextUtils.isEmpty(model.getLiveBeginLogo())) {
                    builder.setThumb(model.getLiveBeginLogo());
                }
            } else if (end <= current) {
                //已结束
                if (!TextUtils.isEmpty(model.getLiveEndLogo())) {
                    builder.setThumb(model.getLiveEndLogo());
                }
                setPlayUrls(model, liveType, builder);
            } else {
                //直播中
                setPlayUrls(model, liveType, builder);
            }

            PlayerModel playerModel = builder.build();
            fragment = VideoNavigation.player(playerModel);
        } else {

            String url = liveProgramModel.getUrl();
            url = AuthenticationUtil.getAuthenticationUrl(url, liveProgramModel.getAuthenticationflag());
            PlayerModel playerModel = PlayerModel.Builder.newBuilder(PlayerModel.TYPE_LIVE_TV)
                    .setTitle(liveProgramModel.getProgramName())
                    .setThumb(model.getLiveImageUrl())
                    .setSize(PlayerModel.SIZE_AUTO)
                    .addUrls(url)
                    .build();

            fragment = VideoNavigation.player(playerModel);
        }

        replaceFragment(R.id.frame, fragment);
    }

    protected PlayerModel.Builder createPlayBuilder(int liveType) {
        return PlayerModel.Builder.newBuilder(liveType)
                .setTitle(model.getLiveChannelName())
                .setThumb(model.getLiveImageUrl())
                .setTimeZone(DateUtil.format(model.getLiveBeginDate()), DateUtil.format(model.getLiveEndDate()))
                .setSize(PlayerModel.SIZE_AUTO)
                .setShareInfo(SHARE_URL, "直播：" + model.getLiveChannelName(), GlobalConfig.SHARE_CONTENT_SPARE2);
    }

    protected void setPlayUrls(LiveChannelModel live, int liveType, PlayerModel.Builder builder) {
        String liveLinkUrl = model.getLiveLink();
        liveLinkUrl = AuthenticationUtil.getAuthenticationUrl(liveLinkUrl, model.getAuthenticationflag());
        String videoUrl = model.getVideoUrl();
        videoUrl = AuthenticationUtil.getAuthenticationUrl(videoUrl, model.getAuthenticationflag());
        if (liveType == VideoComponentConstant.LiveType.ACTIVITY) {
            String liveBeginMediaUrl= model.getLiveBeginMedia();
            liveBeginMediaUrl = AuthenticationUtil.getAuthenticationUrl(liveBeginMediaUrl, model.getAuthenticationflag());

            String liveRTMPUrl = model.getLiveRTMPUrl();
            liveRTMPUrl = AuthenticationUtil.getAuthenticationUrl(liveRTMPUrl, model.getAuthenticationflag());
            builder.addUrls(liveBeginMediaUrl, liveLinkUrl, videoUrl, liveRTMPUrl);
        } else {
            builder.addUrls(liveLinkUrl, videoUrl);
        }
    }

    protected void formatShareUrl(int liveType) {
        SHARE_URL = TextUtils.isEmpty(model.getUserUrl())
                ? VideoShareHelper.getLiveShareUrl(liveType, model.getID())
                : model.getUserUrl();
    }

    @Override
    public void onBackPressed() {
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
