package com.dingtai.android.library.database;

/**
 * 数据库版本 entity变更后必须提高版本号
 */
public class DBVersion {

    static int VERSION = 1;

    /**
     * 初始化数据库版本 涉及数据库升级 必须在application中初始化
     * @param version 版本号
     */
    public static void initVersion(int version) {
        VERSION = version;
    }
}
