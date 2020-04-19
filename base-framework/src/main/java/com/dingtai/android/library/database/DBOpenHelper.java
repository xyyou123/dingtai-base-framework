package com.dingtai.android.library.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/5/11
 */

public class DBOpenHelper extends org.greenrobot.greendao.database.DatabaseOpenHelper {

    private List<com.dingtai.android.library.database.DBTable> daoMasterList;

    DBOpenHelper(Context context, String name, int version, List<com.dingtai.android.library.database.DBTable> daoMasterList) {
        super(context, name, version);
        this.daoMasterList = daoMasterList;
    }

    @Override
    public void onCreate(Database db) {
        for (DBTable daoMaster : daoMasterList) {
            daoMaster.createAllTables(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        List<DBDaoSessionGenerate> generateList = DBDaoSessionGenerateManager.getInstance().getList();
        if(generateList == null || generateList.isEmpty()) return;
        List<Class> arr = new ArrayList<>();
        for (int i = 0, count = generateList.size(); i < count; i++) {
            for (DBDaoSessionGenerate generate : generateList) {
                List<DBDaoSessionEntity> list = generate.generate();
                for (DBDaoSessionEntity entity : list) {
                    arr.add(entity.dao);
                }
            }
        }
        Class[] array = new Class[arr.size()];
        arr.toArray(array);
        MigrationHelper.migrate(db, array);
    }
}
