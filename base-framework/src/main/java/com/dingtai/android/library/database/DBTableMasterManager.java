package com.dingtai.android.library.database;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/5/11
 */

public class DBTableMasterManager {

    private final List<DBTable> daoMasterList;

    private DBTableMasterManager() {
        daoMasterList = new ArrayList<>();
    }

    private static final class SingleHolder {
        private static final com.dingtai.android.library.database.DBTableMasterManager INSTANCE = new com.dingtai.android.library.database.DBTableMasterManager();
    }

    public static com.dingtai.android.library.database.DBTableMasterManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void add(DBTable daoMaster) {
        if(daoMasterList.contains(daoMaster)) {
            return;
        }
        daoMasterList.add(daoMaster);
    }

    public List<DBTable> getList() {
        return daoMasterList;
    }

}
