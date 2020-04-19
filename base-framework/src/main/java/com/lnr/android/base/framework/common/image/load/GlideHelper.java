package com.lnr.android.base.framework.common.image.load;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.data.asyn.http.retrofit.progress.ProgressInterceptor;
import com.lnr.android.base.framework.data.asyn.http.retrofit.progress.ProgressListener;
import com.lnr.android.base.framework.uitl.DimenUtil;

/**
 * author:lnr
 * date:2018/6/2
 */

public class GlideHelper {

    public static int placeholder = android.R.color.darker_gray;
    public static int error = android.R.color.darker_gray;
    public static int placeholder_user = R.drawable.icon_default_user;
    public static int error_user = R.drawable.icon_default_user;

    /**
     * 加载
     *
     * @param imageView 图片
     * @param url       地址
     */
    public static void load(View imageView, Object url) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder) //占位图
                .error(error)       //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);

    }

    /**
     * 加载
     *
     * @param imageView 图片
     * @param url       地址
     */
    public static void load(View imageView, Object url, int placeholder, int error) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder) //占位图
                .error(error)       //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);

    }

    /**
     * 加载
     *
     * @param imageView 图片
     * @param url       地址
     */
    public static void load(View imageView, Object url, Drawable p, int e) {
        RequestOptions options = new RequestOptions()
                .placeholder(p == null ? placeholder : placeholder) //占位图
                .error(e <= 0 ? error : e)       //错误图
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);
    }

    /**
     * 加载
     *
     * @param imageView 图片
     * @param url       地址
     */
    public static void load(View imageView, Object url, RequestOptions options) {
        Glide.with(imageView).load(url).apply(options.diskCacheStrategy(DiskCacheStrategy.ALL)).into((ImageView) imageView);
    }

    /**
     * 指定图片大小;使用override()方法指定了一个图片的尺寸。
     * Glide现在只会将图片加载成width*height像素的尺寸，而不会管你的ImageView的大小是多少了。
     * 如果你想加载一张图片的原始尺寸的话，可以使用Target.SIZE_ORIGINAL关键字----override(Target.SIZE_ORIGINAL)
     *
     * @param imageView 图片
     * @param url       地址
     * @param width     宽
     * @param height    高
     */
    public static void loadOverride(ImageView imageView, Object url, int width, int height) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder) //占位图
                .error(error)       //错误图
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into(imageView);
    }


    /**
     * 加载圆形图片
     * 默认图
     */
    public static void loadCircle(View imageView, Object url) {
        loadCircle(imageView, url, placeholder_user, error_user);
    }


    /**
     * 加载圆形图片
     */
    public static void loadCircle(View imageView, Object url, int placeholder, int error) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(placeholder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCenterCropCircle(View imageView, Object url, int r) {
        GlideRoundTransform roundedCorners = new GlideRoundTransform(r);
        RequestOptions options = new RequestOptions()
                .transform(roundedCorners)
                .placeholder(placeholder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);
    }

    /**
     * 预先加载图片
     * 在使用图片之前，预先把图片加载到缓存，调用了预加载之后，我们以后想再去加载这张图片就会非常快了，
     * 因为Glide会直接从缓存当中去读取图片并显示出来
     */
    public static void preloadImage(Context context, Object url) {
        Glide.with(context)
                .load(url)
                .preload();

    }

    /**
     * 加载圆角图片
     */
    public static void loadRound(View imageView, Object url, int r) {
        RoundedCorners roundedCorners = new RoundedCorners(DimenUtil.dp2px(imageView.getContext(), r));
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)//new GlideRoundTransform(r)
                .placeholder(placeholder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into((ImageView) imageView);

    }

//
//    /**
//     * 加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
//     *
//     * @param context
//     * @param url
//     * @param imageView
//     * @param type
//     */
//    public static void loadCustRoundCircleImage(Context context, String url, ImageView imageView, RoundedCornersTransformation.CornerType type) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(placeholderSoWhite)
//                .error(errorSoWhite)
//                //.priority(Priority.HIGH)
//                .bitmapTransform(new RoundedCornersTransformation(45, 0, type))
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//
//        Glide.with(context).initAgentWebX5(url).apply(options).into(imageView);
//    }


//    /**
//     * 加载模糊图片（自定义透明度）
//     *
//     * @param context
//     * @param url
//     * @param imageView
//     * @param blur      模糊度，一般1-100够了，越大越模糊
//     */
//    public static void loadBlurImage(Context context, String url, ImageView imageView, int blur) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(placeholderSoWhite)
//                .error(errorSoWhite)
//                //.priority(Priority.HIGH)
//                .bitmapTransform(new BlurTransformation(blur))
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(context).initAgentWebX5(url).apply(options).into(imageView);
//    }

//    /*
//     *加载灰度(黑白)图片（自定义透明度）
//     */
//    public static void loadBlackImage(Context context, String url, ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(placeholderSoWhite)
//                .error(errorSoWhite)
//                //.priority(Priority.HIGH)
//                .bitmapTransform(new GrayscaleTransformation())
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(context).initAgentWebX5(url).apply(options).into(imageView);
//    }

//    /**
//     * Glide.with(this).asGif()    //强制指定加载动态图片
//     * 如果加载的图片不是gif，则asGif()会报错， 当然，asGif()不写也是可以正常加载的。
//     * 加入了一个asBitmap()方法，这个方法的意思就是说这里只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了。
//     * 如果你传入的还是一张GIF图的话，Glide会展示这张GIF图的第一帧，而不会去播放它。
//     *
//     * @param context
//     * @param url       例如：https://image.niwoxuexi.com/blog/content/5c0d4b1972-loading.gif
//     * @param imageView
//     */
//    private void loadGif(Context context, String url, ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(placeholderSoWhite)
//                .error(errorSoWhite);
//        Glide.with(context)
//                .initAgentWebX5(url)
//                .apply(options)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(imageView);
//
//    }


    public static void loadUseProgress(ImageView imageView, final Object url, RequestOptions options, final ProgressListener listener) {
        ProgressInterceptor.addListener(url, listener);
        Glide.with(imageView).load(url).apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        listener.onProgress(100);
                        ProgressInterceptor.removeListener(url);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        listener.onProgress(100);
                        ProgressInterceptor.removeListener(url);
                        return false;
                    }
                })
                .into(imageView);
    }
}
