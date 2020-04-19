package com.lnr.android.base.framework.ui.control.web;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dingtai.android.library.resource.Resource;
import com.just.agentwebX5.AgentWebX5;
import com.just.agentwebX5.AgentWebX5Compat;
import com.just.agentwebX5.DefaultWebClient;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.web.more.ChangeTextSizeDialog;
import com.lnr.android.base.framework.ui.control.web.more.WebTextRatingBar;
import com.lnr.android.base.framework.uitl.AssetsUtil;
import com.lnr.android.base.framework.uitl.SP;
import com.lnr.android.base.framework.uitl.logger.Logger;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author:lnr
 * date:2018/12/13
 * 网页封装容器
 */
public class WebWrapper {

    private AgentWebX5.AgentBuilder mAgentBuilder;
    private AgentWebX5.AgentBuilderFragment mAgentBuilderFragment;
    private AgentWebX5.PreAgentWeb mPreAgentWeb;
    private AgentWebX5 mAgentWebX5;

    private WebCallback mWebCallback;
    public static boolean isReload = true;//是否reload

    /**
     * 自动设置高度（默认为false）
     */
    private boolean isAutoResizeHeight;
    /**
     * 是否使用glide加载图片
     */
    private boolean isUseGlide;

    private List<AbstractWebViewInterceptor> mWebViewClientInterceptors;

    private int[] textZoom = new int[]{
            60,
            80,
            100,
            120,
            140,
    };

    private WebWrapper() {
    }

    public static WebWrapper with(@NonNull Activity activity) {
        WebWrapper webWrapper = new WebWrapper();
        webWrapper.mAgentBuilder = AgentWebX5.with(activity);
        return webWrapper;
    }

    public static WebWrapper with(@NonNull Fragment fragment) {
        WebWrapper webWrapper = new WebWrapper();
        webWrapper.mAgentBuilderFragment = AgentWebX5.with(fragment);
        return webWrapper;
    }

    public void init(ViewGroup group, ViewGroup.LayoutParams params, WebCallback callback) {
        init(group, params, 0, callback);
    }

    public void init(ViewGroup group, ViewGroup.LayoutParams params, int position, WebCallback callback) {
        mWebCallback = callback;
        if (mAgentBuilder != null) {
            mPreAgentWeb = mAgentBuilder.setAgentWebParent(group, params, position)
                    .useDefaultIndicator()//
                    .setIndicatorColor(group.getResources().getColor(R.color.theme))
                    .setReceivedTitleCallback(callback)
                    .setWebViewClient(new WebClientWrapper())
                    .setWebChromeClient(new WebChromeClientWrapper())
                    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                    .interceptUnkownScheme()
                    .setSecutityType(AgentWebX5.SecurityType.strict)
                    .setNotifyIcon(Resource.Drawable.APP_ICON)
                    .createAgentWeb();
        } else {
            mPreAgentWeb = mAgentBuilderFragment.setAgentWebParent(group, params)
                    .setIndicatorColorWithHeight(group.getResources().getColor(R.color.theme), 2)
                    .setReceivedTitleCallback(callback)
                    .setWebViewClient(new WebClientWrapper())
                    .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                    .interceptUnkownScheme()
                    .setNotifyIcon(Resource.Drawable.APP_ICON)
                    .createAgentWeb();
        }
        mWebViewClientInterceptors = new ArrayList<>();
    }

    private void ready() {
        mPreAgentWeb.ready();
    }

    public void load(String url) {
        if (mAgentWebX5 == null) {
            ready();
            mAgentWebX5 = mPreAgentWeb.go(url);
            initSetting();
        } else {
            mAgentWebX5.getLoader().loadUrl(url);
        }
    }

    public void load(String data, String mimeType, String encoding) {
        if (mAgentWebX5 == null) {
            ready();
            mAgentWebX5 = mPreAgentWeb.go(data, mimeType, encoding);
            initSetting();
        } else {
            mAgentWebX5.getLoader().loadData(data, mimeType, encoding);
        }
    }

    /**
     * 初始化设置
     */
    private void initSetting() {
        addJs("web_textsize", new AgentWebX5Compat() {
            @JavascriptInterface
            public void resize(final float height) {
                resizeWebView(height);
            }
        });
        mAgentWebX5.getWebSettings().getWebSettings().setTextZoom(textZoom[getTextSizeZoom()]);
        mAgentWebX5.getWebSettings().getWebSettings().setAllowUniversalAccessFromFileURLs(true);
        mAgentWebX5.getWebSettings().getWebSettings().setAllowFileAccess(true);
        mAgentWebX5.getWebSettings().getWebSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWebX5.getWebSettings().getWebSettings().setBlockNetworkImage(true);
//        mAgentWebX5.getWebSettings().getWebSettings().setBlockNetworkImage(isBlockNetworkImage);
//        if (Build.VERSION.SDK_INT >= 19) {
//            mAgentWebX5.getWebSettings().getWebSettings().setLoadsImagesAutomatically(true);
//        } else {
//            mAgentWebX5.getWebSettings().getWebSettings().setLoadsImagesAutomatically(false);
//        }
    }

    public void setAutoResizeHeight(boolean autoResizeHeight) {
        this.isAutoResizeHeight = autoResizeHeight;
    }

    public void setUseGlide(boolean useGlide) {
        this.isUseGlide = useGlide;
    }

    public void addInterceptor(AbstractWebViewInterceptor interceptor) {
        interceptor.wrapper = this;
        mWebViewClientInterceptors.add(interceptor);
    }

    public WebView getWebView() {
        if (mAgentWebX5 == null) return null;
        return mAgentWebX5.getWebCreator().get();
    }

    public AgentWebX5 getAgentWebX5() {
        return mAgentWebX5;
    }

    public void addJs(String name, final AgentWebX5Compat compat) {
        if (mAgentWebX5 == null) return;
        mAgentWebX5.getJsInterfaceHolder().addJavaObject(name, compat);
    }

    private class WebClientWrapper extends WebViewClient {

        private final List<String> ALL_IMAGES = Arrays.asList("gif,bmp,jpg,png,tif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,wmf,webp".split(","));

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            for (WebViewInterceptor interceptor : mWebViewClientInterceptors) {
                if (interceptor.shouldOverrideUrlLoading(view, url)) {
                    return true;
                }
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            if (webView.getSettings().getBlockNetworkImage()) {
                webView.getSettings().setBlockNetworkImage(false);
                webView.reload();
            }

            if (!webView.getSettings().getLoadsImagesAutomatically()) {
                webView.getSettings().setLoadsImagesAutomatically(true);

            }


            super.onPageFinished(webView, s);


            for (WebViewInterceptor interceptor : mWebViewClientInterceptors) {
                interceptor.onPageFinished(webView, s);
            }
            if (mAgentWebX5 != null) {
                mAgentWebX5.getJsEntraceAccess().callJs(AssetsUtil.readAll(webView.getContext(), "html_shield.js"));
                mAgentWebX5.getJsEntraceAccess().quickCallJs("resize");
            }
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (!isUseGlide) return super.shouldInterceptRequest(webView, webResourceRequest);
            String uri = webResourceRequest.getUrl().toString();
            try {
                String imageType = getImageType(uri);
                if (imageType == null) {
                    return super.shouldInterceptRequest(webView, webResourceRequest);
                } else if ("gif".equals(imageType)) {
                    File file = Glide.with(webView)
                            .applyDefaultRequestOptions(RequestOptions.timeoutOf(5))
                            .download(uri)
                            .submit().get();
                    return new WebResourceResponse("image/gif", "UTF-8", new FileInputStream(file));
                } else {
                    File file = Glide.with(webView)
                            .applyDefaultRequestOptions(RequestOptions.timeoutOf(5))
                            .download(uri)
                            .thumbnail(0.5f)
                            .submit().get();
                    return new WebResourceResponse("image/" + imageType, "UTF-8", new FileInputStream(file));
                }
            } catch (Exception e) {
                try {
                    return shouldInterceptDefaultImage(webView.getResources());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        private WebResourceResponse shouldInterceptDefaultImage(Resources resources) throws IOException {
            if (GlideHelper.error > 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(resources, GlideHelper.error);
                if (bitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    return new WebResourceResponse("image/jpg", "UTF-8", new ByteArrayInputStream(baos.toByteArray()));
                }
            }
            return new WebResourceResponse("image/png", "UTF-8", resources.getAssets().open("html_image_error.png"));
        }

        private String getImageType(String path) {
            int index = path.lastIndexOf(".");
            if (index > 0 && path.length() > index + 1) {
                String type = path.substring(index + 1).toLowerCase();
                if (ALL_IMAGES.indexOf(type) >= 0) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            Logger.log("onReceivedError", "ErrorCode:" + i+" s:"+s+"s1:"+s1);
            super.onReceivedError(webView, i, s, s1);
        }
    }

    private class WebChromeClientWrapper extends WebChromeClient {
        @Override
        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            super.onReceivedIcon(webView, bitmap);
            if (mWebCallback != null) {
                mWebCallback.onReceivedIcon(webView, bitmap);
            }
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i == 100) {
                if (webView.getSettings().getBlockNetworkImage()) {
                    webView.getSettings().setBlockNetworkImage(false);
                    if (isReload){
                        webView.reload();
                    }
                }
            }

        }
    }

    /**
     * 重新设置webview 高度
     * @param height 高度
     */
    private void resizeWebView(final float height) {
        if (!isAutoResizeHeight || mAgentWebX5 == null) return;
        mAgentWebX5.getWebCreator().get().post(new Runnable() {
            @Override
            public void run() {
                if (mAgentWebX5 != null) {
                    WebView webView = mAgentWebX5.getWebCreator().get();
                    if (webView != null) {
                        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                        layoutParams.height = (int) (height * webView.getResources().getDisplayMetrics().density);
                        webView.setLayoutParams(layoutParams);
                        if (mWebCallback != null) {
                            mWebCallback.onResizeHeight(height);
                        }
                    }
                }
            }
        });
    }

    /**
     * 设置字体大小
     * @param index 字体大小级别
     */
    public void setTextSizeZoom(int index) {
        if (mAgentWebX5 == null) return;
        mAgentWebX5.getWebSettings().getWebSettings().setTextZoom(textZoom[index]);
        SP.getDefault().edit().putInt(Resource.KEY.SETTING_TEXTSIZE_PATTERN, index).apply();
        mAgentWebX5.getJsEntraceAccess().quickCallJs("resize");
    }

    /**
     * 显示设置字体大小的对话框
     * @param activity 宿主
     */
    public void showResizeTextSizeZoomDialog(Activity activity) {
        ChangeTextSizeDialog dialog = new ChangeTextSizeDialog(activity, getTextSizeZoom(), new WebTextRatingBar.OnRatingListener() {
            @Override
            public void onRating(int index) {
                setTextSizeZoom(index);
            }
        });
        dialog.show();
    }

    public void quickCallJs(String name, String data) {
        if (mAgentWebX5 == null) return;
        if (data != null) {
            mAgentWebX5.getJsEntraceAccess().quickCallJs(name, data);
        } else {
            mAgentWebX5.getJsEntraceAccess().quickCallJs(name);
        }
    }

    public void callJs(String data, ValueCallback<String> callback) {
        if (mAgentWebX5 == null) return;
        if (callback == null) {
            mAgentWebX5.getJsEntraceAccess().callJs(data);
        } else {
            mAgentWebX5.getJsEntraceAccess().callJs(data, callback);
        }
    }

    /**
     * 获取字体缩放级别
     * @return 级别
     */
    public int getTextSizeZoom() {
        if (mAgentWebX5 == null) return 2;
        return SP.getDefault().getInt(Resource.KEY.SETTING_TEXTSIZE_PATTERN, 2);
    }

    public boolean onBackPressed() {
        return mAgentWebX5 != null && mAgentWebX5.handleKeyEvent(KeyEvent.KEYCODE_BACK, null);
    }

    public void onPause() {
        if (mAgentWebX5 != null) mAgentWebX5.getWebLifeCycle().onPause();
    }

    public void onResume() {
        if (mAgentWebX5 != null) mAgentWebX5.getWebLifeCycle().onResume();
    }

    public void onDestroy() {
        if (mAgentWebX5 != null) mAgentWebX5.getWebLifeCycle().onDestroy();
        for (WebViewInterceptor interceptor : mWebViewClientInterceptors) {
            interceptor.onDestroy();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (WebViewInterceptor interceptor : mWebViewClientInterceptors) {
            interceptor.onActivityResult(requestCode, resultCode, data);
        }

        if (mAgentWebX5 != null) mAgentWebX5.uploadFileResult(requestCode, resultCode, data);
    }
}
