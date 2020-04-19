package com.dingtai.android.library.news.db.impl;

import com.dingtai.android.library.database.DBDaoSessionEntity;
import com.dingtai.android.library.database.DBDaoSessionGenerate;
import com.dingtai.android.library.news.db.ChannelModelDao;
import com.dingtai.android.library.news.db.LaunchAdModelDao;
import com.dingtai.android.library.news.db.NewsDetailModelDao;
import com.dingtai.android.library.news.db.NewsListModelDao;
import com.dingtai.android.library.news.db.NewsPhotoModelDao;
import com.dingtai.android.library.news.model.ChannelModel;
import com.dingtai.android.library.news.model.LaunchAdModel;
import com.dingtai.android.library.news.model.NewsDetailModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.model.NewsPhotoModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.List;

public class ModelDBDaoSessionGenerateImpl implements DBDaoSessionGenerate {

    @Override
    public List<DBDaoSessionEntity> generate() {
        List<DBDaoSessionEntity> list = new ArrayList<>();
        list.add(new DBDaoSessionEntity(NewsListModel.class, NewsListModelDao.class) {
            @Override
            protected AbstractDao<NewsListModel, Long> createDao(DaoConfig config) {
                return new NewsListModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(NewsPhotoModel.class, NewsPhotoModelDao.class) {
            @Override
            protected AbstractDao<NewsPhotoModel, Long> createDao(DaoConfig config) {
                return new NewsPhotoModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(NewsDetailModel.class, NewsDetailModelDao.class) {
            @Override
            protected AbstractDao<NewsDetailModel, Long> createDao(DaoConfig config) {
                return new NewsDetailModelDao(config);
            }
        });

        list.add(new DBDaoSessionEntity(ChannelModel.class, ChannelModelDao.class) {
            @Override
            protected AbstractDao<ChannelModel, Long> createDao(DaoConfig config) {
                return new ChannelModelDao(config);
            }
        });

        list.add(new DBDaoSessionEntity(LaunchAdModel.class, LaunchAdModelDao.class) {
            @Override
            protected AbstractDao<LaunchAdModel, Long> createDao(DaoConfig config) {
                return new LaunchAdModelDao(config);
            }
        });
        return list;
    }
}
