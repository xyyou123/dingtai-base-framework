package com.lnr.android.base.framework.data.asyn.db.greendao;

import com.dingtai.android.library.database.DB;
import com.dingtai.android.library.database.DBDaoSession;
import com.lnr.android.base.framework.data.asyn.core.AsynCallAdapter;

public class GreendaoCallAdapter implements AsynCallAdapter {
    @Override
    public <T> T call(Class<T> tClass, Object... os) {
        DBDaoSession session = (os == null || os.length == 0) ? DB.getInstance().getConmon() : DB.getInstance().getUser();
        if(session == null) return null;
        return (T) session.getDao(tClass);
    }
}
