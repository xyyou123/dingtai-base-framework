package com.dingtai.android.library.video;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface VideoComponentConstant {

    interface ADFor {
        /**
         * 不知道什么鬼, 无法正确命名
         */
        String TYPE_1 = "1";
        /**
         * 不知道什么鬼, 无法正确命名
         */
        String TYPE_2 = "2";
        /**
         * 不知道什么鬼, 无法正确命名
         */
        String TYPE_3 = "3";

    }

    interface ADLinkTo {
        /**
         * 详情
         */
        String DETAILS = "1";
        /**
         * 列表
         */
        String LIST = "2";


    }

    /**
     * 直播类型
     */
    interface LiveType {
        /**
         * 视频直播
         */
        int VEDIO = 1;
        /**
         * 音频直播
         */
        int AUDIO = 2;
        /**
         * 活动直播
         */
        int ACTIVITY = 3;
        /**
         * 图文直播
         */
        int IMAGE_AND_TEXT = 4;

    }

    class Constant {


        @IntDef({
                LiveHttpCallType.GetLiveByType,
                LiveHttpCallType.GetLiveByTypeNew,
                LiveHttpCallType.GetLiveNew
        })
        @Retention(RetentionPolicy.SOURCE)
        public @interface LiveHttpCallType {
            int GetLiveByType = 0;
            int GetLiveByTypeNew = 1; // 永州
            int GetLiveNew = 2; //今日郴州

        }

        @LiveHttpCallType
        public static int LiveHttpCall = LiveHttpCallType.GetLiveByType;
        
        
        public static boolean LIVE_CHANNEL_LIST_NEW = false;


    }


}
