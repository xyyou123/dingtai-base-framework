package com.lnr.android.base.framework.common.image.selecte;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * author:lnr
 * date:2018/9/7
 */
public class LoaderEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder) //占位图
                .centerCrop()
                .override(resize, resize)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        GlideHelper.load(imageView, uri, options);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder) //占位图
                .centerCrop()
                .override(resize, resize)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        GlideHelper.load(imageView, uri, options);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .override(resizeX, resizeY)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        GlideHelper.load(imageView, uri, options);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(resizeX, resizeY)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        GlideHelper.load(imageView, uri, options);
    }

    @Override
    public boolean supportAnimatedGif() {
        return false;
    }
}
