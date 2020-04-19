package com.lnr.android.base.framework.data.asyn.core;

/**
 * 默认的适配器类型
 */
public enum AsynCallAdapterType {

    HTTP("http"),
    DATABASE("com/dingtai/android/library/database");

    String key;

    AsynCallAdapterType(String key) {
        this.key = key;
    }
}
