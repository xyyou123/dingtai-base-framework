package com.dingtai.android.library.news.ui.launch;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.news.DaggerNewsDagger;

import com.dingtai.android.library.news.model.LaunchAdDetailsModel;
import com.dingtai.android.library.news.model.LaunchAdModel;
import com.dingtai.android.library.news.ui.NewsNavigation;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;
import com.github.lnr.inject.annotation.Contract;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.FrameworkNavigation;
import com.lnr.android.base.framework.ui.base.BaseActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.FixImageView;
import com.lnr.android.base.framework.uitl.DimenUtil;
import com.lnr.android.base.framework.uitl.ListUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/9/26
 * 启动广告页
 */
@Route(path = "/news/launch")
@Contract(name = "NewsLaunch")
public class LaunchAdActivity extends BaseActivity implements NewsLaunchContract.View {

    public static float LAUNCH_BOTTOM_LOGO_MIN = 0.3f;

    @Inject
    protected NewsLaunchPresenter mNewsLaunchPresenter;

    protected FixImageView imageAd;
    protected Button btnPass;//跳过

    protected LaunchAdModel model;
    protected LaunchAdDetailsModel mCurrentAd;

    protected Disposable disposable;

    @Override
    protected List<IPresenter> getIPresenters() {
        return ListUtil.arrayList(mNewsLaunchPresenter);
    }

    @Override
    protected void setContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        getWindow().setBackgroundDrawableResource(R.color.white);
        setContentView(R.layout.activity_news_launch);

        ImageView logo = findViewById(R.id.image_logo);
        if(logo.getVisibility() == View.VISIBLE) {
            logo.setBackgroundResource(Resource.Drawable.LAUNCH_LOGO);
        }
    }

    @Override
    protected void initView() {
        imageAd = findViewById(R.id.image_ad);

        btnPass = findViewById(R.id.btn_pass);
        ViewListen.setClick(btnPass, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                pass();
            }
        });

        ViewListen.setClick(imageAd, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                if (model == null || model.getOpenPicDetail() == null) return;
                if(model.getOpenPicDetail().isEmpty()) {
                    ADModel ad = new ADModel();
                    ad.setADFor(model.getADFor());
                    ad.setLinkTo(model.getLinkTo());
                    ad.setLinkUrl(model.getLinkUrl());
                    ad.setADName(model.getOpenPicName());
                    clickAd(ad);
                }else {
                    clickAd(model.getOpenPicDetail().get(0));
                }
            }
        });
    }

    @Override
    protected void inject(ApplicationComponent component) {
        DaggerNewsDagger.builder().applicationComponent(component).asynCallModule(new AsynCallModule(this)).build().inject(this);
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        mNewsLaunchPresenter.GetOpenPicByStID(getDeviceResolution());
    }

    @Override
    protected void initImmersionBar(boolean immersion) {
        //super.initImmersionBar(immersion);
    }

    public String getDeviceResolution() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return "10";
        Point size = new Point();
        Display display = wm.getDefaultDisplay();
        display.getSize(size);

        switch (size.x + "*" + size.y) {
            case "320*480":
                return "5";
            case "480*640":
                return "6";
            case "480*800":
                return "7";
            case "540*960":
                return "8";
            case "720*1280":
                return "9";
            case "1080*1920":
                return "10";
            default:
                if (screenFlag11(size)) {
                    return "11";
                }
                return "10";
        }
    }

    protected boolean screenFlag11(Point size) {
        return ((float) size.y) / size.x > 1.8;
    }

    @Override
    public void GetOpenPicByStID(LaunchAdModel model) {
        if (model != null) {
            this.model = model;
            if(model.getOpenPicDetail() != null && model.getOpenPicDetail().size() > 0) {
                mCurrentAd = model.getOpenPicDetail().get(0);
                load(mCurrentAd);
            }else {
                ADModel ad = new ADModel();
                ad.setADFor(model.getADFor());
                ad.setLinkTo(model.getLinkTo());
                ad.setLinkUrl(model.getLinkUrl());
                ad.setADName(model.getOpenPicName());
                ad.setImgUrl(model.getImgUrl());
                load(ad);
            }
        }
        disposable = Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(GlobalConfig.LAUNCH_DURATION + 1)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        pass();
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        btnPass.setText("跳过(" + (GlobalConfig.LAUNCH_DURATION - aLong) + ")");
                    }
                });
        registe(disposable);
    }

    @Deprecated
    protected void load(LaunchAdDetailsModel model) {
        ADModel ad = new ADModel();
        ad.setADFor(model.getADFor());
        ad.setLinkTo(model.getLinkTo());
        ad.setLinkUrl(model.getLinkUrl());
        ad.setLiveChannel(model.getLiveChannel());
        ad.setADName(model.getOpenPicName());
        ad.setImgUrl(model.getImgUrl());
        load(ad);
    }


    /**
     * 加载图片
     * @param model
     */
    protected void load(ADModel model) {
        RequestOptions options = (new RequestOptions())
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(imageAd).load(model.getImgUrl()).apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if(imageAd != null) {
                            float r = resource.getIntrinsicHeight() * 1f / resource.getIntrinsicWidth();
                            int[] size = DimenUtil.getScreenSize(mActivity);

                            float max = (size[1] - size[0] * LAUNCH_BOTTOM_LOGO_MIN) / size[0];
                            if(r > max) {
                                r = max;
                            }
                            imageAd.setFixHeight(r);
                            imageAd.setImageDrawable(resource);
                        }
                    }
                });
    }

    /**
     * 跳出
     */
    protected void pass() {
        btnPass.setText("正在初始化...");
        btnPass.setEnabled(false);
        imageAd.setEnabled(false);
        if(disposable != null) disposable.dispose();
        FrameworkNavigation.home(this);
    }

    /**
     * 点击广告
     *
     * @param model
     */
    @Deprecated
    protected void clickAd(LaunchAdDetailsModel model) {
        if(mCurrentAd == null) return;

        ADModel ad = new ADModel();
        ad.setADFor(model.getADFor());
        ad.setLinkTo(model.getLinkTo());
        ad.setLinkUrl(model.getLinkUrl());
        ad.setLiveChannel(model.getLiveChannel());
        ad.setADName(model.getOpenPicName());
        clickAd(ad);
    }

    protected void clickAd(ADModel model) {
        if(model == null) return;
        if (NewsNavigation.adNavigation(true, model)) {
            btnPass.setText("正在初始化...");
            btnPass.setEnabled(false);
            imageAd.setEnabled(false);
            if(disposable != null) {
                disposable.dispose();
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void exitActivity(boolean onBackPressed) {
        finishActivity();
    }
}
