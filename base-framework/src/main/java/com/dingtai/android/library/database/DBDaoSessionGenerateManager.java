package com.dingtai.android.library.database;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/5/11
 */

public final class DBDaoSessionGenerateManager {

    private final List<com.dingtai.android.library.database.DBDaoSessionGenerate> generateList;

    private DBDaoSessionGenerateManager() {
        generateList = new ArrayList<>();
    }

    private static final class SingleHolder {
        private static final com.dingtai.android.library.database.DBDaoSessionGenerateManager INSTANCE = new com.dingtai.android.library.database.DBDaoSessionGenerateManager();
    }

    public static com.dingtai.android.library.database.DBDaoSessionGenerateManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public com.dingtai.android.library.database.DBDaoSessionGenerateManager add(com.dingtai.android.library.database.DBDaoSessionGenerate generate) {
        generateList.add(generate);
        return this;
    }

    public List<DBDaoSessionGenerate> getList() {
        return generateList;
    }

}
