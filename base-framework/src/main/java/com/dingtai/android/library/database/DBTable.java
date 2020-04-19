package com.dingtai.android.library.database;

import org.greenrobot.greendao.database.Database;

/**
 * author:lnr
 * date:2018/5/11
 * 组件需要实现此类并添加到
 * @see DBTableMasterManager
 */

public interface DBTable {

    void createAllTables(Database db);

    void dropAllTables(Database db);
}
