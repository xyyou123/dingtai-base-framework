package com.dingtai.android.library.resource;

/**
 * author:lnr
 * date:2018/9/30
 */
public interface DataProviders {

    interface News {

        String NEWS_MODEL = "NEWS_MODEL";

        /**
         * 适配器 角标
         */
        String NEWS_LIST_ADAPTER = "NEWS_LIST_ADAPTER";

        /**
         * 适配器 角标
         */
        String NEWS_LIST_ADAPTER_BARGE = "NEWS_LIST_ADAPTER_BARGE";
        /**
         * 适配器 点击事件
         */
        String NEWS_LIST_ADAPTER_CLICK = "NEWS_LIST_ADAPTER_CLICK";

        String AD_LIST_ADAPTER_CLICK = "AD_LIST_ADAPTER_CLICK";
    }
}
