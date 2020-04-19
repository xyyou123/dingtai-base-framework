package com.dingtai.android.library.news.ui.list.adapter.convertor;

import android.graphics.Color;

import com.lnr.android.base.framework.uitl.NumberUtil;

/**
 * author:lnr
 * date:2018/9/17
 * 新闻适配转换器
 */
public class NewsBadgeConvertor {

    /**
     * 角标
     */
    public static final class Badge {
        public String title;
        public int textColor;
        public int background;
    }

    public static Badge converterBadge(String flag) {
        Badge badge = new Badge();
        switch (NumberUtil.parseInt(flag, -1)) {
            case 1:
                badge.title = "图集";
                badge.background = Color.parseColor("#1c9029");
                badge.textColor = Color.WHITE;
                break;
            case 2:
                badge.title = "专题";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#f7781a");
                break;
            case 3:
                badge.title = "视频";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#007d0f");
                break;
            case 4:
                badge.title = "推广";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#01bce4");
                break;
            case 5:
                badge.title = "直播";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#e93030");
                break;
            case 6:
                badge.title = "本地";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#20aacc");
                break;
            case 7:
                badge.title = "热点";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#bd0413");
                break;
            case 8:
                badge.title = "独家";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#31c37c");
                break;
            case 9:
                badge.title = "问卷";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#b09a08");
                break;
            case 10:
                badge.title = "深读";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#b09a08");
                break;

            case 12:
                badge.title = "深读";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#f15a16");
                break;

            case 13:
                badge.title = "外媒";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#b09a08");
                break;

            case 14:
                badge.title = "广告";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#f15a16");
                break;

            case 15:
                badge.title = "置顶";
                badge.textColor = Color.WHITE;
                badge.background = Color.parseColor("#f15a16");
                break;
            default:
                badge = null;
                break;
        }
        if(badge != null)
        badge.textColor = badge.background;
        return badge;
    }
}
