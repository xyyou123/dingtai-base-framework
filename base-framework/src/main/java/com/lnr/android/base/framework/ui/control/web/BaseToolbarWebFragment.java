package com.lnr.android.base.framework.ui.control.web;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtai.android.library.resource.GlobalConfig;
import com.gyf.barlibrary.BarHide;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.fragment.ToolbarFragment;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.FixImageView;
import com.lnr.android.base.framework.ui.control.web.file.UploadFileWebIntercepotor;
import com.tencent.smtt.sdk.WebView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/9/14
 */
@Route(path = "/framework/web/base/fragment")
public class BaseToolbarWebFragment extends ToolbarFragment implements WebCallback {

    @Autowired
    protected String title;
    @Autowired
    protected String url;

    @Autowired
    protected boolean showHome = false;

    @Autowired
    protected int headerHeight;

    protected FixImageView iv_home;

    protected WebWrapper mWebWrapper;


    private String originalUrl;


    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected View contentView() {
        return View.inflate(getContext(), R.layout.layout_fragment_web, null);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        toolbar().setTitle(title);

        iv_home = findViewById(R.id.iv_home);


        ViewListen.setClick(iv_home, new com.lnr.android.base.framework.ui.control.listener
                .OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mWebWrapper.load(originalUrl);
                iv_home.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void afterInitView(View view, @Nullable Bundle savedInstanceState) {
        initAgentWebX5();

        if (headerHeight > 0) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + headerHeight, view
                    .getPaddingRight(), view.getPaddingBottom());
        }
        headerHeight = 0;
    }

    protected void initAgentWebX5() {
        initAgentWebX5((ViewGroup) findViewById(R.id.frame), new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void initAgentWebX5(ViewGroup group, ViewGroup.LayoutParams params) {
        originalUrl = url;

        mWebWrapper = WebWrapper.with(this);
        mWebWrapper.init(group, params, this);

        mWebWrapper.addInterceptor(new UploadFileWebIntercepotor(getActivity(), new
                UploadFileWebIntercepotor.Callback() {
            @Override
            public void requestPermission(Consumer<Boolean> consumer, String... permissions) {
                BaseToolbarWebFragment.this.requestPermission(permissions).request(consumer);
            }

            @Override
            public LifecycleTransformer bindLife() {
                return bindToLifecycle();
            }
        }));

        mWebWrapper.addInterceptor(new AbstractWebViewInterceptor(getActivity()) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!originalUrl.equals(url) && showHome) {
                    if (iv_home != null) {
                        iv_home.setVisibility(View.VISIBLE);
                    }

                } else {

                    if (iv_home != null) {
                        iv_home.setVisibility(View.GONE);
                    }

                }

                return false;
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

            }
        });

        mWebWrapper.load(url);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (this.title == null) {
            toolbar().setTitle(title);
        }
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {

    }

    @Override
    public void onResizeHeight(float height) {

    }

    @Override
    public boolean onBackPressed() {
        return mWebWrapper != null && mWebWrapper.onBackPressed();
    }

    @Override
    public void onPause() {
        if (mWebWrapper != null) mWebWrapper.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebWrapper != null) mWebWrapper.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (mImmersionBar != null) {
                mImmersionBar.reset().hideBar(BarHide.FLAG_HIDE_BAR).init();
            }
        } else if (getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_PORTRAIT) {
            if (mImmersionBar != null) {
                mImmersionBar.reset().fitsSystemWindows(true).statusBarColor(R.color.statusbar)
                        .statusBarDarkFont(GlobalConfig.STATUSBAR_TEXT_DART)
                        .flymeOSStatusBarFontColor(GlobalConfig.STATUSBAR_TEXT_COLOR)
                        .init();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mWebWrapper != null) mWebWrapper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        if (mWebWrapper != null) mWebWrapper.onDestroy();
        super.onDestroy();
    }
}
