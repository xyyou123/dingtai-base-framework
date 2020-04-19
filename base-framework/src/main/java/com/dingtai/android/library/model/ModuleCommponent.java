package com.dingtai.android.library.model;

import android.app.Application;

import com.dingtai.android.library.database.DBDaoSessionGenerateManager;
import com.dingtai.android.library.database.DBTableMasterManager;
import com.dingtai.android.library.model.db.impl.ModelDBDaoSessionGenerateImpl;
import com.dingtai.android.library.model.db.impl.ModelDBTableImpl;

public class ModuleCommponent {

    public static void init(Application application) {
        DBTableMasterManager.getInstance().add(new ModelDBTableImpl());
        DBDaoSessionGenerateManager.getInstance().add(new ModelDBDaoSessionGenerateImpl());
    }
}
