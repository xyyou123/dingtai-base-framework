package com.lnr.android.base.framework.ui.control.web;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Routes;
import com.gyf.barlibrary.BarHide;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.ui.base.avtivity.StatusToolbarActivity;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.web.file.UploadFileWebIntercepotor;
import com.tencent.smtt.sdk.WebView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * author:lnr
 * date:2018/9/14
 */
@Route(path = "/framework/web/base")
public class BaseToolbarWebActivity extends StatusToolbarActivity implements WebCallback {

    @Autowired
    protected String url;
    @Autowired
    protected String title;

    protected WebWrapper mWebWrapper;

    @Override
    protected int contentLayoutResId() {
        return R.layout.layout_frame;
    }

    @Override
    protected void retry() {

    }

    @Override
    protected List<IPresenter> getIPresenters() {
        return null;
    }

    @Override
    protected void inject(ApplicationComponent component) {

    }

    @Override
    protected void initView() {
        super.initView();

        toolbar().requestFocus();

        toolbar().setLeftImage(R.drawable.icon_web_close);
        toolbar().setLeftListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void afterInitView(@Nullable Bundle savedInstanceState) {
        toolbar().setTitle(title);
        mStatusLayoutManager.showContent();
        initAgentWebX5();
    }

    protected void initAgentWebX5() {
        initAgentWebX5((ViewGroup) findViewById(R.id.frame), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void initAgentWebX5(ViewGroup group, ViewGroup.LayoutParams params) {
        initAgentWebX5(group, params, 0);
    }

    protected void initAgentWebX5(ViewGroup group, ViewGroup.LayoutParams params, int position) {
        mWebWrapper = WebWrapper.with(this);
        mWebWrapper.init(group, params, position, this);

        mWebWrapper.addInterceptor(new UploadFileWebIntercepotor(this, new UploadFileWebIntercepotor.Callback() {
            @Override
            public void requestPermission(Consumer<Boolean> consumer, String... permissions) {
                BaseToolbarWebActivity.this.requestPermission(permissions)
                .request(consumer);


            }

            @Override
            public LifecycleTransformer bindLife() {
                return bindToLifecycle();
            }
        }));


        this.mWebWrapper.addInterceptor(new AbstractWebViewInterceptor(null) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String s) {

                if (s.endsWith(".doc") ||s.endsWith(".docx")||s.endsWith(".docx")||s.endsWith(".rar")||s.endsWith(".zip")||s.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                    return true;
                }
                return false;
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

            }
        });

       load();
    }

    protected void load() {
        mWebWrapper.load(url);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        toolbar().setTitle(title);
    }

    @Override
    public void onResizeHeight(float height) {

    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {

    }

    @Override
    public void onBackPressed() {
        if(mWebWrapper != null && mWebWrapper.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        if(mWebWrapper != null) mWebWrapper.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mWebWrapper != null) mWebWrapper.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mImmersionBar.reset().hideBar(BarHide.FLAG_HIDE_BAR).init();
        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mImmersionBar.reset().fitsSystemWindows(true).statusBarColor(R.color.statusbar)
                    .statusBarDarkFont(GlobalConfig.STATUSBAR_TEXT_DART)
                    .flymeOSStatusBarFontColor(GlobalConfig.STATUSBAR_TEXT_COLOR)
                    .init();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mWebWrapper != null) mWebWrapper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if(mWebWrapper != null) mWebWrapper.onDestroy();
        super.onDestroy();
    }
}
