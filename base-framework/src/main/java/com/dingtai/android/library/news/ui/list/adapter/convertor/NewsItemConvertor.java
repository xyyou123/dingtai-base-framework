package com.dingtai.android.library.news.ui.list.adapter.convertor;

import android.text.TextUtils;

import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_1;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_2;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_3;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_4;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_5;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_7;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_8;
import com.dingtai.android.library.news.ui.list.adapter.item.NewsItemConverter_Vote;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverterCreator;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/9/17
 */
public class NewsItemConvertor {

    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final int TYPE_3 = 3;
    private static final int TYPE_4 = 4;
    private static final int TYPE_5 = 5;
    private static final int TYPE_6 = 6;
    private static final int TYPE_7 = 7;
    private static final int TYPE_8 = 8;

    public static final int TYPE_VOTE = 101;

    public static boolean OPEN_1 = true;
    public static boolean OPEN_2 = true;
    public static boolean OPEN_3 = true;
    public static boolean OPEN_4 = true;
    public static boolean OPEN_5 = true;
    public static boolean OPEN_6 = true;
    public static boolean OPEN_7 = true;
    public static boolean OPEN_8 = true;

    public static int ROUND_IMAGE = 0;

    public static boolean showChannelName = true;
    public static boolean showBadge = true;
    public static boolean showReadCount = true;
    public static boolean showReadCount_Number = false;//阅读数是否是纯数字
    public static boolean userFakeReadCount = false;
    public static boolean userResourcePdForm = false;
    public static boolean showTime = false;
    public static boolean showGhChangeTime = false; //我的怀化 时间textview显示公号
    public static boolean SHOWPICSIN_OPEN_1 = true;
    public static boolean showCenterCropConner= false;

    public static int converterType(NewsListModel model) {
        int vote = NumberUtil.parseInt(model.getVoteType());
        if (vote == 2 || vote == 4) {
            return TYPE_VOTE;
        }

        String resourceCSS = model.getResourceCSS();
        if (TextUtils.isEmpty(resourceCSS)) {
            resourceCSS = "1";
        }

        switch (resourceCSS) {
            case "1":
                if (SHOWPICSIN_OPEN_1) {
                    String images = TextUtils.isEmpty(model.getPicPath()) ? model.getUploadPicNames() : model.getPicPath();
                    if (images != null && images.split(",").length > 1 && OPEN_6) {
                        return TYPE_6;
                    }
                }

                break;
            case "2":
                if (OPEN_2) return TYPE_2;
                break;
            case "3":
                if (OPEN_3) return TYPE_3;
                break;
            case "4":
                if (OPEN_4) return TYPE_4;
                break;
            case "5":
                if (OPEN_5) return TYPE_5;
                break;
            case "7":
                if (OPEN_7) return TYPE_7;
                break;
            case "8":
                if (OPEN_8) return TYPE_8;
                break;
        }
        return NumberUtil.parseInt(resourceCSS, TYPE_1);
    }


    public static ItemConverter<NewsListModel> converterItem(int type) {
        ItemConverterCreator<NewsListModel> creator = CREATOR.get(type);
        if (creator != null) return creator.create();
        switch (type) {
            case TYPE_1:
                return new NewsItemConverter_1();
            case TYPE_2:
                return new NewsItemConverter_2();
            case TYPE_3:
                return new NewsItemConverter_3();
            case TYPE_4:
                return new NewsItemConverter_4();
            case TYPE_5:
                return new NewsItemConverter_5();
            case TYPE_6:
                return new NewsItemConverter_2();
            case TYPE_7:
                return new NewsItemConverter_7();
            case TYPE_8:
                return new NewsItemConverter_8();
            case TYPE_VOTE:
                return new NewsItemConverter_Vote();
        }

        return new NewsItemConverter_1();
    }

    private static final HashMap<Integer, ItemConverterCreator<NewsListModel>> CREATOR = new HashMap<>();

    public static void registe(int type, ItemConverterCreator<NewsListModel> creator) {
        CREATOR.put(type, creator);
    }
}
