package com.dingtai.android.library.video.db.impl;

import com.dingtai.android.library.database.DBDaoSessionEntity;
import com.dingtai.android.library.database.DBDaoSessionGenerate;
import com.dingtai.android.library.video.db.LiveChannelModelDao;
import com.dingtai.android.library.video.db.LiveProgramModelDao;
import com.dingtai.android.library.video.db.VideoChannelModelDao;
import com.dingtai.android.library.video.db.VodListModelDao;
import com.dingtai.android.library.video.db.VodProgramModelDao;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.dingtai.android.library.video.model.VideoChannelModel;
import com.dingtai.android.library.video.model.VodListModel;
import com.dingtai.android.library.video.model.VodProgramModel;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.List;

public class ModelDBDaoSessionGenerateImpl implements DBDaoSessionGenerate {

    @Override
    public List<DBDaoSessionEntity> generate() {
        List<DBDaoSessionEntity> list = new ArrayList<>();
        list.add(new DBDaoSessionEntity(VodListModel.class, VodListModelDao.class) {
            @Override
            protected AbstractDao<VodListModel, Long> createDao(DaoConfig config) {
                return new VodListModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(VodProgramModel.class, VodProgramModelDao.class) {
            @Override
            protected AbstractDao<VodProgramModel, Long> createDao(DaoConfig config) {
                return new VodProgramModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(LiveChannelModel.class, LiveChannelModelDao.class) {
            @Override
            protected AbstractDao<LiveChannelModel, Long> createDao(DaoConfig config) {
                return new LiveChannelModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(LiveProgramModel.class, LiveProgramModelDao.class) {
            @Override
            protected AbstractDao<LiveProgramModel, Long> createDao(DaoConfig config) {
                return new LiveProgramModelDao(config);
            }
        });
        list.add(new DBDaoSessionEntity(VideoChannelModel.class, VideoChannelModelDao.class) {
            @Override
            protected AbstractDao<VideoChannelModel, Long> createDao(DaoConfig config) {
                return new VideoChannelModelDao(config);
            }
        });
        return list;
    }
}
