package com.lnr.android.base.framework.common.umeng;

import com.dingtai.android.library.resource.GlobalConfig;
import com.lnr.android.base.framework.R;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/13
 */
public class UmengData {

    public static List<UmengAction> getLoginList() {
        List<UmengAction> tripartiteModules = new ArrayList<>();

        if(GlobalConfig.LOGIN_QQ) {
            tripartiteModules.add(new UmengAction(SHARE_MEDIA.QQ, "QQ", R.drawable.ic_share_qq));
        }

        if(GlobalConfig.LOGIN_WEIXIN) {
            tripartiteModules.add(new UmengAction(SHARE_MEDIA.WEIXIN, "微信", R.drawable.ic_share_weixin));
        }

        if(GlobalConfig.LOGIN_WEIBO) {
            tripartiteModules.add(new UmengAction(SHARE_MEDIA.SINA, "微博", R.drawable.ic_share_sina));
        }

        return tripartiteModules;
    }

    private static UmengAction createAction(SHARE_MEDIA media) {
        UmengAction action = null;
        switch (media) {
            case QQ:
                action  = new UmengAction(SHARE_MEDIA.QQ, "QQ", R.drawable.ic_share_qq);
                break;
            case WEIXIN:
                action  = new UmengAction(SHARE_MEDIA.WEIXIN, "微信", R.drawable.ic_share_weixin);
                break;
            case WEIXIN_CIRCLE:
                action  = new UmengAction(SHARE_MEDIA.WEIXIN_CIRCLE, "朋友圈", R.drawable.ic_share_friends);
                break;
            case QZONE:
                action  = new UmengAction(SHARE_MEDIA.QZONE, "QQ空间", R.drawable.ic_share_qqzone);
                break;
            case SINA:
                action  = new UmengAction(SHARE_MEDIA.SINA, "微博", R.drawable.ic_share_sina);
                break;
        }

        return action;
    }


    public static List<UmengAction> getShareList() {
        List<UmengAction> list = new ArrayList<>();


        if(GlobalConfig.SHARE_WEIXIN) {
            list.add(new UmengAction(SHARE_MEDIA.WEIXIN, "微信", R.drawable.ic_share_weixin));
        }

        if(GlobalConfig.SHARE_WEIXIN_PENGYOUQUAN) {
            list.add(new UmengAction(SHARE_MEDIA.WEIXIN_CIRCLE, "朋友圈", R.drawable.ic_share_friends));
        }

        if(GlobalConfig.SHARE_QQ) {
            list.add(new UmengAction(SHARE_MEDIA.QQ, "QQ", R.drawable.ic_share_qq));
        }

        if(GlobalConfig.SHARE_QZONE) {
            list.add(new UmengAction(SHARE_MEDIA.QZONE, "QQ空间", R.drawable.ic_share_qqzone));
        }

        if(GlobalConfig.SHARE_WEIBO) {
            list.add(new UmengAction(SHARE_MEDIA.SINA, "微博", R.drawable.ic_share_sina));
        }

        return list;
    }

    public static List<UmengAction> getShareList(List<SHARE_MEDIA> list) {
        if(list == null) {
            return getShareList();
        }else {
            List<UmengAction> actionArrayList = new ArrayList<>();
            for (SHARE_MEDIA media : list) {
                UmengAction action = createAction(media);
                if(action != null) {
                    actionArrayList.add(action);
                }
            }

            return actionArrayList;
        }
    }
}
