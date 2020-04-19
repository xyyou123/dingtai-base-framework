package com.lnr.android.base.framework.common.image.load;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.lnr.android.base.framework.data.asyn.http.retrofit.progress.ProgressInterceptor;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * author:lnr
 * date:2018/12/26
 */
public class FrameworkGlideModule implements GlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // 写入咱们的okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 写入咱们的okhttp的拦截器,在拦截器中监听进度
        builder.addInterceptor(new ProgressInterceptor());
        OkHttpClient okHttpClient = builder.build();
        // glide吧urlConnection替换为okhttp
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
    }
}
