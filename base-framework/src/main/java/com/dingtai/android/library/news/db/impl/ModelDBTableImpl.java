package com.dingtai.android.library.news.db.impl;

import com.dingtai.android.library.database.DBTable;
import com.dingtai.android.library.news.db.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class ModelDBTableImpl implements DBTable {

    @Override
    public void createAllTables(Database db) {
        DaoMaster.createAllTables(db, false);
    }

    @Override
    public void dropAllTables(Database db) {
        DaoMaster.dropAllTables(db, true);
    }
}
