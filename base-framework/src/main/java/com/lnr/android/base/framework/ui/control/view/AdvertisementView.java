package com.lnr.android.base.framework.ui.control.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.uitl.DimenUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementView {

    /**
     * 创建广告控件
     * @param context 上下文
     * @param imagerR 加载的图圆角半径
     * @return 广告控件
     */
    public static Banner create(Context context, final int imagerR) {
        Banner banner = (Banner) View.inflate(context, R.layout.layout_advertisement, null);
        banner.setDelayTime(4000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                if(imagerR <= 0) {
                    GlideHelper.load(imageView, path);
                }else {
                    GlideHelper.loadRound(imageView, path, imagerR);
                }
            }
        });
        return banner;
    }


    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @param imagerR 加载的图圆角半径
     * @return 广告控件
     */
    public static Banner createInRecyclerView(Context context, float viewR, final int imagerR) {
        return createInRecyclerView(context, null, viewR, imagerR);
    }

    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @param imagerR 加载的图圆角半径
     * @return 广告控件
     */
    public static Banner createInRecyclerView(Context context, int[] margins, float viewR, final int imagerR) {
        Banner banner = create(context, imagerR);
        int size = DimenUtil.getScreenSize(context)[0];
        if(margins != null) {
            size = size - margins[0] - margins[2];
        }
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(size, (int) (size * viewR));
        if(margins != null) {
            params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        }
        banner.setLayoutParams(params);
        return banner;
    }


    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @return 广告控件
     */
    public static Banner createInRecyclerView(Context context, float viewR) {
        return createInRecyclerView(context, viewR, 0);
    }

    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @param imagerR 加载的图圆角半径
     * @return 广告控件
     */
    public static Banner createInLinearLayout(Context context, float viewR, final int imagerR) {
        Banner banner = create(context, imagerR);
        int size = DimenUtil.getScreenSize(context)[0];
        banner.setLayoutParams(new LinearLayout.LayoutParams(size, (int) (size * viewR)));
        return banner;
    }

    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @param imagerR 加载的图圆角半径
     * @return 广告控件
     */
    public static Banner createInLinearLayout(Context context, int[] margins, float viewR, final int imagerR) {
        Banner banner = create(context, imagerR);
        int size = DimenUtil.getScreenSize(context)[0];
        if(margins != null) {
            size = size - margins[0] - margins[2];
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, (int) (size * viewR));
        if(margins != null) {
            params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        }
        banner.setLayoutParams(params);
        return banner;
    }


    /**
     * 创建广告控件
     * @param context 上下文
     * @param viewR 宽高比
     * @return 广告控件
     */
    public static Banner createInLinearLayout(Context context, float viewR) {
        return createInLinearLayout(context, viewR, 0);
    }

    public static void attach(Banner banner, List<ADModel> list) {
        List<String> titls = new ArrayList<>();
        List<String> urls = new ArrayList<>();

        for (ADModel model : list) {
            titls.add(model.getADName());
            urls.add(model.getImgUrl());
        }

        banner.setBannerTitles(titls);
        banner.setImages(urls);
    }
}
