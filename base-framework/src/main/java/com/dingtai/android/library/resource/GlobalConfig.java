package com.dingtai.android.library.resource;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/13
 * 全局配置
 */
public class GlobalConfig {

    public static String UMENG_KEY = "5ba9e411f1f556e295000026";
    public static String QQ_ID = "1105411101";
    public static String QQ_KEY = "pZpOMynz0I2J5KhW";
    public static String WENXIN_ID = "wxbb702012d37ce620";
    public static String WENXIN_SECRET = "84d0798aca8d7d17d11bb94048fd77db";
    public static String WEIBO_KEY = "2949111693";
    public static String WEIBO_SECRET = "65021c84509ac97273e3fdcd0ccae45d";
    public static String WEIBO_CALLBACKURI = "http://www.dingtoo.com/";



    /**
     * 三方登录配置
     */
    public static boolean LOGIN_QQ = true;
    public static boolean LOGIN_WEIXIN = true;
    public static boolean LOGIN_WEIBO = true;

    /**
     * 分享配置
     */
    public static boolean SHARE_QQ = true;
    public static boolean SHARE_QZONE = true;
    public static boolean SHARE_WEIXIN = true;
    public static boolean SHARE_WEIXIN_PENGYOUQUAN = true;
    public static boolean SHARE_WEIBO = true;

    public static String SHARE_URL = "http://gd.cz.hn.d5mt.com.cn/";

    /**
     * 分享icon
     */
    public static String SHARE_ICON = SHARE_URL + "/Images/ic_launcher.png";
    /**
     * 客户端下载页
     */
    public static String DOWNLOAD_URL = SHARE_URL + "/APP/hmyDown.html";

    public static String SHARE_URL_GUID = SHARE_URL + "/Share/Shares.aspx?guid=";
    /**
     * 爆料分享链接
     */
    public static String SHARE_URL_BAOLIAO = SHARE_URL + "/Share/RevelationShare1.aspx?ID=";
    /**
     * 直播分享地址
     */
    public static String SHARE_URL_LIVE = SHARE_URL + "/Share2/hdzb.aspx?id=";

    public static String SHARE_URL_VIDEO = SHARE_URL + "/Share/MediaShares.aspx?GUID=";
    /**
     * 图文直播分享地址
     */
    public static String SHARE_URL_LIVE_IMAGETEXT = SHARE_URL + "/Share2/twzb.aspx?id=";

    public static String SHARE_URL_SUJECT_OLD = SHARE_URL + "/Share/ProjectShares.aspx?ID=";

    /**
     * 政务分享地址
     */
    public static String SHARE_URL_ZHENGWU = SHARE_URL + "/Share/PoliticsShare.aspx?id=";


    /**
     * 隐私政策地址
     */
    public static String PRIVACY = "http://app.cznbtv.com:8081/APP/zc.html";

    /**
     * 分享 备用
     */
    public static String SHARE_TITLE_SPARE = "新闻";
    public static String SHARE_CONTENT_SPARE = "，主流媒体，权威发声";
    public static String SHARE_CONTENT_SPARE2 =  ",无论身在何处，同样感受精彩";
    public static String SHARE_CONTENT = null;

    public static String CITY = "郴州市";

    public static String CITY_ID = "101010100";
    /**
     * 状态栏字体是否显示为暗色
     */
    public static boolean STATUSBAR_TEXT_DART = true;
    public static int STATUSBAR_TEXT_COLOR = R.color.black;


    /**
     * 启动页广告持续时间
     */
    public static int LAUNCH_DURATION = 3;
    /**
     * 首页栏目过滤
     */
    public static String HOME_CHANNEL_FILTER;

    public static int[] GUIDE = new int[]{R.drawable.image_guide_0, R.drawable.image_guide_1, R.drawable.image_guide_2};

}
