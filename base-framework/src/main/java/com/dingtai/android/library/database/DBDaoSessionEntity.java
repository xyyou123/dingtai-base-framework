package com.dingtai.android.library.database;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * 数据库entity及对应dao提供者 每个entity都必须添加
 */
public abstract class DBDaoSessionEntity {

    Class<?> entity;
    Class<? extends AbstractDao<?, Long>> dao;

    protected DBDaoSessionEntity(Class<?> entity, Class<? extends AbstractDao<?, Long>> dao) {
        this.entity = entity;
        this.dao = dao;
    }

    protected abstract AbstractDao<?, Long> createDao(DaoConfig config);

}
