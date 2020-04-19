package com.dingtai.android.library.model.db.impl;

import com.dingtai.android.library.database.DBDaoSessionEntity;
import com.dingtai.android.library.database.DBDaoSessionGenerate;
import com.dingtai.android.library.model.db.ADModelDao;
import com.dingtai.android.library.model.db.AccountModelDao;
import com.dingtai.android.library.model.db.ModelStatusDao;
import com.dingtai.android.library.model.db.NewsCollectModelDao;
import com.dingtai.android.library.model.db.XGNotificationModelDao;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.model.models.AccountModel;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.model.models.NewsCollectModel;
import com.dingtai.android.library.model.models.XGNotificationModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.List;

public class ModelDBDaoSessionGenerateImpl implements DBDaoSessionGenerate {

    @Override
    public List<DBDaoSessionEntity> generate() {
        List<DBDaoSessionEntity> list = new ArrayList<>();
        list.add(new DBDaoSessionEntity(AccountModel.class, AccountModelDao.class) {
            @Override
            protected AbstractDao<AccountModel, Long> createDao(DaoConfig config) {
                return new AccountModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(ModelStatus.class, ModelStatusDao.class) {
            @Override
            protected AbstractDao<ModelStatus, Long> createDao(DaoConfig config) {
                return new ModelStatusDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(ADModel.class, ADModelDao.class) {
            @Override
            protected AbstractDao<ADModel, Long> createDao(DaoConfig config) {
                return new ADModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(NewsCollectModel.class, NewsCollectModelDao.class) {
            @Override
            protected AbstractDao<NewsCollectModel, Long> createDao(DaoConfig config) {
                return new NewsCollectModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(XGNotificationModel.class, XGNotificationModelDao.class) {
            @Override
            protected AbstractDao<XGNotificationModel, Long> createDao(DaoConfig config) {
                return new XGNotificationModelDao(config);
            }
        });
        return list;
    }
}
