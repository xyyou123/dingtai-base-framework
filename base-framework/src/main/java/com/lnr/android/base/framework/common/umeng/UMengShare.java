package com.lnr.android.base.framework.common.umeng;

import android.app.Activity;
import android.text.TextUtils;

import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Score;
import com.lnr.android.base.framework.FrameworkConfig;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.ui.control.toast.ToastHelper;
import com.lnr.android.base.framework.uitl.ContextUtil;
import com.lnr.android.base.framework.uitl.ReflectUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.BaseMediaObject;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.UmengText;

/**
 * author:lnr
 * date:2018/9/25
 */
public class UMengShare {

    public static int SHARE_ICON;

    private static final class UMengListener implements UMShareListener {

        private Class activity;

        public UMengListener(Class activity) {
            this.activity = activity;
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            //AppHandler.getInstance().showLoading(activity);
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            //AppHandler.getInstance().hideLoading(activity);
            ToastHelper.toastDefault("分享成功");

            RxBus.getDefault().post(new ScoreModel(Score.SCORE_SHAR));
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            //AppHandler.getInstance().hideLoading(activity);
            ToastHelper.toastDefault("分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            //AppHandler.getInstance().hideLoading(activity);
            ToastHelper.toastDefault("取消分享");
        }
    }

    public static UMWeb createWeb(String url, String title, String content) {
        UMWeb  web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setDescription(content);//描述
        return web;
    }

    public static UMWeb createWeb(String url, String title, String content, UMImage image) {
        UMWeb  web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        return web;
    }

    public static void shareWeb(Activity activity, SHARE_MEDIA media, UMWeb web) {
        ShareAction action = new ShareAction(activity);
        if(FrameworkConfig.SHARE_USE_APP_ICON || web.getThumbImage() == null) {
            if(SHARE_ICON == 0) {
                web.setThumb(new UMImage(activity, GlobalConfig.SHARE_ICON));;  //缩略图
            }else {
                web.setThumb(new UMImage(activity, SHARE_ICON));;  //缩略图
            }
        }

        if(TextUtils.isEmpty(web.getDescription())) {
            if(TextUtils.isEmpty(GlobalConfig.SHARE_CONTENT)) {
                web.setDescription( GlobalConfig.SHARE_CONTENT_SPARE);//描述
            }else {
                web.setDescription(GlobalConfig.SHARE_CONTENT);
            }
        }

        if(media == SHARE_MEDIA.SINA) {
            UMImage thumbImage = web.getThumbImage();
            action.setPlatform(media)
                    .withMedia(thumbImage)
                    .withText(web.getTitle() + ReflectUtil.getFieldValue(BaseMediaObject.class, "a", web))
                    .setCallback(new UMengListener(activity.getClass()))
                    .share();


        }else {
            action.setPlatform(media)
                    .withMedia(web)
                    .setCallback(new UMengListener(activity.getClass()))
                    .share();
        }

        RxBus.getDefault().post(new ShareEvent(web));
    }

    public static void shareWeb(Activity activity, SHARE_MEDIA media, String title, String content, String url) {
        shareWeb(activity, media, title, content, url, null);
    }

    public static void shareWeb(Activity activity, SHARE_MEDIA media, String title, String content, String url, UMImage thumb) {
        if(!url.equals(GlobalConfig.DOWNLOAD_URL)) {
            url += "&stid=" + Resource.API.STID;
        }
        UMWeb  web = new UMWeb(url);
        web.setTitle(title);//标题

        if(FrameworkConfig.SHARE_USE_APP_ICON || thumb == null) {
            if(SHARE_ICON == 0) {
                thumb = new UMImage(activity, GlobalConfig.SHARE_ICON);  //缩略图
            }else {
                thumb = new UMImage(activity, SHARE_ICON);  //缩略图
            }
        }
        web.setThumb(thumb);  //缩略图
        if(TextUtils.isEmpty(content)) {
            if(TextUtils.isEmpty(GlobalConfig.SHARE_CONTENT)) {
                web.setDescription( GlobalConfig.SHARE_CONTENT_SPARE);//描述
            }else {
                web.setDescription(GlobalConfig.SHARE_CONTENT);
            }
        }else {
            web.setDescription(content);
        }

        shareWeb(activity, media, web);
    }
}
