package com.dingtai.android.library.resource;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/8/29
 * 资源配置文件
 */
public interface Resource {


    class Drawable {

        public static int TOOLBAR_BACK = R.drawable.icon_toolbar_back;
        public static int APP_ICON = R.drawable.icon_app;
        public static int LAUNCH_LOGO = R.drawable.image_launch_bottom_logo;

    }

    /**
     * 接口地址配置
     */
    class API {

        public static final String BASE = "base";
        /**
         * 基础地址
         */
//        public static String URL = "http://slb1.app.hn0746.com:80/";//今日永州
//        public static String URL = "http://slb.gd.hh.hn.d5mt.com.cn/";//我的怀化
        public static String URL = "http://slb.gd.zz.hn.d5mt.com.cn/";//新株洲
        //public static String URL = "http://slb.rb.hh.hn.d5mt.com.cn/";//掌上怀化
//        public static String URL = "http://slb.gd.cz.hn.d5mt.com.cn/";//广电郴州

        public static final String JIAOFEI = "JIAOFEI";
        public static String URL_JIAOFEI = "http://61.187.200.218:8101/";
        public static String URL_PAY = "http://gd.cz.hn.d5mt.com.cn/";

        public static String URL_PROTOCOL = "http://app.cznbtv.com:8081/APP/sm.html";
        /**
         * 签名
         */
        public static String SIGN = "1";
        /**
         * 站点id
         */
        public static String STID = "0";

        /**
         * 分页数量
         */
        public static int PAGE = 20;

        /**
         * 文件上传地址
         */
        public static String FIEL_IP = "47.96.169.55";
        public static String FIEL_UPLOAD_USERNAME = "admin";
        public static String FIEL_UPLOAD_PASSWORD = "21232f297a57a5a743894a0e4a801fc3";

        public static int FIEL_PORT = 8888;
    }


    interface KEY {
        /**
         * 是否需要登录
         */
        String NEED_LOGIN = "NEED_LOGIN";

        /**
         * 推送开关
         */
        String SETTING_PUSH = "SETTING_PUSH";
        /**
         * 3G/4G视频播放开关
         */
        String SETTING_VIDEO_PATTERN = "SETTING_VIDEO_PATTERN";
        /**
         * 阅读模式
         */
        String SETTING_READ_PATTERN = "SETTING_READ_PATTERN";

        /**
         * 字体模式
         */
        String SETTING_TEXTSIZE_PATTERN = "SETTING_TEXTSIZE_PATTERN";
    }

    interface VALUE {
        /**
         * 阅读模式 图文
         */
        int SETTING_READ_PATTERN_IMAGE_TEXT = 1;
        /**
         * 阅读模式 文本
         */
        int SETTING_READ_PATTERN_TEXT = 2;
        /**
         * 阅读模式 自动 仅wifi加载图片
         */
        int SETTING_READ_PATTERN_AUTO = 3;


        /**
         * 字体模式 小
         */
        int SETTING_TEXTSIZE_PATTERN_SMALL = 1;
        /**
         * 字体模式 中
         */
        int SETTING_TEXTSIZE_PATTERN_MID = 2;
        /**
         * 字体模式 大
         */
        int SETTING_TEXTSIZE_PATTERN_LARGE = 3;
    }

}
