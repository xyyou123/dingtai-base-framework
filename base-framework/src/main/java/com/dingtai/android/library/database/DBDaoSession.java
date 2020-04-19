package com.dingtai.android.library.database;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:lnr
 * date:2018/5/11
 */

public final class DBDaoSession extends AbstractDaoSession {

    private final HashMap<Class, DaoConfig> mDaoConfigMap;
    private final HashMap<Class, AbstractDao> mDaoMap;

    public DBDaoSession(Database db, IdentityScopeType type) {
        super(db);

        mDaoConfigMap = new HashMap<>();
        mDaoMap = new HashMap<>();

        List<DBDaoSessionGenerate> generateList = DBDaoSessionGenerateManager.getInstance().getList();
        for (DBDaoSessionGenerate generate : generateList) {
            List<DBDaoSessionEntity> list = generate.generate();
            for (DBDaoSessionEntity entity : list) {
                DaoConfig config = new DaoConfig(db, entity.dao);
                config.initIdentityScope(type);
                AbstractDao dao = entity.createDao(config);
                registerDao(entity.entity, dao);
                mDaoConfigMap.put(entity.entity, config);
                mDaoMap.put(entity.dao, dao);
            }
        }
    }

    public void clear(Class clazz) {
        DaoConfig config = mDaoConfigMap.get(clazz);
        if(config != null) {
            config.clearIdentityScope();
        }
    }

    public void clearAll() {
        for (Map.Entry<Class, DaoConfig> entry : mDaoConfigMap.entrySet()) {
            DaoConfig config = entry.getValue();
            if (config != null) {
                config.clearIdentityScope();
            }
        }
    }

    @Override
    public AbstractDao<?, ?> getDao(Class<?> entityClass) {
        return mDaoMap.get(entityClass);
    }
}
