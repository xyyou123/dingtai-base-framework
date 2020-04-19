package com.dingtai.android.library.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


/**
 * Author:  lnr
 * Date:    2018/2/7
 * 全局数据库
 */

public class DB {

    private DBDaoSession mCommonDaoSession;
    private DBDaoSession mUserDaoSession;

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder
    {
        private static final com.dingtai.android.library.database.DB INSTANCE = new com.dingtai.android.library.database.DB();
    }

    /**
     * 对外唯一实例的接口
     */
    public static com.dingtai.android.library.database.DB getInstance()
    {
        return SingleInstanceHolder.INSTANCE;
    }

    private DB() {
    }

    private static class DaoMaster extends AbstractDaoMaster {

        public DaoMaster(SQLiteDatabase db, int schemaVersion) {
            super(new StandardDatabase(db), schemaVersion);
        }

        public DBDaoSession newSession() {
            return new DBDaoSession(db, IdentityScopeType.Session);
        }

        @Override
        public DBDaoSession newSession(IdentityScopeType type) {
            return new DBDaoSession(db, type);
        }
    }

    /**
     * 初始化数据
     */
    public void initConmon(Context context)
    {
        if(mCommonDaoSession !=  null) return;
        com.dingtai.android.library.database.DBTableMasterManager manager = com.dingtai.android.library.database.DBTableMasterManager.getInstance();
        DBOpenHelper helper = new DBOpenHelper(context, "dingtai_" + context.getPackageName().replaceAll("\\.", "_"),
                DBVersion.VERSION, manager.getList());
        mCommonDaoSession = new DaoMaster(helper.getWritableDatabase(), DBVersion.VERSION).newSession();
    }

    public void initUser(Context context, String userId) {
        com.dingtai.android.library.database.DBTableMasterManager manager = DBTableMasterManager.getInstance();
        DBOpenHelper helper = new DBOpenHelper(context, userId + "_" + context.getPackageName().replaceAll("\\.", "_"),
                DBVersion.VERSION, manager.getList());
        mUserDaoSession = new DaoMaster(helper.getWritableDatabase(), DBVersion.VERSION).newSession();
    }

    public DBDaoSession getConmon()
    {
        return mCommonDaoSession;
    }

    public DBDaoSession getUser() {
        return mUserDaoSession;
    }
}
