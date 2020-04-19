package com.lnr.android.base.framework.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.dingtai.android.library.model.models.AccountModel;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.model.models.ModelStatus;
import com.dingtai.android.library.model.models.NewsCollectModel;
import com.dingtai.android.library.model.models.XGNotificationModel;
import com.dingtai.android.library.news.model.ChannelModel;
import com.dingtai.android.library.news.model.LaunchAdModel;
import com.dingtai.android.library.news.model.NewsDetailModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.model.NewsPhotoModel;
import com.dingtai.android.library.news.model.RelatedReaderModel;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.model.LiveProgramModel;
import com.dingtai.android.library.video.model.VideoChannelModel;
import com.dingtai.android.library.video.model.VodListModel;
import com.dingtai.android.library.video.model.VodProgramModel;

import com.lnr.android.base.framework.db.AccountModelDao;
import com.lnr.android.base.framework.db.ADModelDao;
import com.lnr.android.base.framework.db.ModelStatusDao;
import com.lnr.android.base.framework.db.NewsCollectModelDao;
import com.lnr.android.base.framework.db.XGNotificationModelDao;
import com.lnr.android.base.framework.db.ChannelModelDao;
import com.lnr.android.base.framework.db.LaunchAdModelDao;
import com.lnr.android.base.framework.db.NewsDetailModelDao;
import com.lnr.android.base.framework.db.NewsListModelDao;
import com.lnr.android.base.framework.db.NewsPhotoModelDao;
import com.lnr.android.base.framework.db.RelatedReaderModelDao;
import com.lnr.android.base.framework.db.LiveChannelModelDao;
import com.lnr.android.base.framework.db.LiveProgramModelDao;
import com.lnr.android.base.framework.db.VideoChannelModelDao;
import com.lnr.android.base.framework.db.VodListModelDao;
import com.lnr.android.base.framework.db.VodProgramModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accountModelDaoConfig;
    private final DaoConfig aDModelDaoConfig;
    private final DaoConfig modelStatusDaoConfig;
    private final DaoConfig newsCollectModelDaoConfig;
    private final DaoConfig xGNotificationModelDaoConfig;
    private final DaoConfig channelModelDaoConfig;
    private final DaoConfig launchAdModelDaoConfig;
    private final DaoConfig newsDetailModelDaoConfig;
    private final DaoConfig newsListModelDaoConfig;
    private final DaoConfig newsPhotoModelDaoConfig;
    private final DaoConfig relatedReaderModelDaoConfig;
    private final DaoConfig liveChannelModelDaoConfig;
    private final DaoConfig liveProgramModelDaoConfig;
    private final DaoConfig videoChannelModelDaoConfig;
    private final DaoConfig vodListModelDaoConfig;
    private final DaoConfig vodProgramModelDaoConfig;

    private final AccountModelDao accountModelDao;
    private final ADModelDao aDModelDao;
    private final ModelStatusDao modelStatusDao;
    private final NewsCollectModelDao newsCollectModelDao;
    private final XGNotificationModelDao xGNotificationModelDao;
    private final ChannelModelDao channelModelDao;
    private final LaunchAdModelDao launchAdModelDao;
    private final NewsDetailModelDao newsDetailModelDao;
    private final NewsListModelDao newsListModelDao;
    private final NewsPhotoModelDao newsPhotoModelDao;
    private final RelatedReaderModelDao relatedReaderModelDao;
    private final LiveChannelModelDao liveChannelModelDao;
    private final LiveProgramModelDao liveProgramModelDao;
    private final VideoChannelModelDao videoChannelModelDao;
    private final VodListModelDao vodListModelDao;
    private final VodProgramModelDao vodProgramModelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accountModelDaoConfig = daoConfigMap.get(AccountModelDao.class).clone();
        accountModelDaoConfig.initIdentityScope(type);

        aDModelDaoConfig = daoConfigMap.get(ADModelDao.class).clone();
        aDModelDaoConfig.initIdentityScope(type);

        modelStatusDaoConfig = daoConfigMap.get(ModelStatusDao.class).clone();
        modelStatusDaoConfig.initIdentityScope(type);

        newsCollectModelDaoConfig = daoConfigMap.get(NewsCollectModelDao.class).clone();
        newsCollectModelDaoConfig.initIdentityScope(type);

        xGNotificationModelDaoConfig = daoConfigMap.get(XGNotificationModelDao.class).clone();
        xGNotificationModelDaoConfig.initIdentityScope(type);

        channelModelDaoConfig = daoConfigMap.get(ChannelModelDao.class).clone();
        channelModelDaoConfig.initIdentityScope(type);

        launchAdModelDaoConfig = daoConfigMap.get(LaunchAdModelDao.class).clone();
        launchAdModelDaoConfig.initIdentityScope(type);

        newsDetailModelDaoConfig = daoConfigMap.get(NewsDetailModelDao.class).clone();
        newsDetailModelDaoConfig.initIdentityScope(type);

        newsListModelDaoConfig = daoConfigMap.get(NewsListModelDao.class).clone();
        newsListModelDaoConfig.initIdentityScope(type);

        newsPhotoModelDaoConfig = daoConfigMap.get(NewsPhotoModelDao.class).clone();
        newsPhotoModelDaoConfig.initIdentityScope(type);

        relatedReaderModelDaoConfig = daoConfigMap.get(RelatedReaderModelDao.class).clone();
        relatedReaderModelDaoConfig.initIdentityScope(type);

        liveChannelModelDaoConfig = daoConfigMap.get(LiveChannelModelDao.class).clone();
        liveChannelModelDaoConfig.initIdentityScope(type);

        liveProgramModelDaoConfig = daoConfigMap.get(LiveProgramModelDao.class).clone();
        liveProgramModelDaoConfig.initIdentityScope(type);

        videoChannelModelDaoConfig = daoConfigMap.get(VideoChannelModelDao.class).clone();
        videoChannelModelDaoConfig.initIdentityScope(type);

        vodListModelDaoConfig = daoConfigMap.get(VodListModelDao.class).clone();
        vodListModelDaoConfig.initIdentityScope(type);

        vodProgramModelDaoConfig = daoConfigMap.get(VodProgramModelDao.class).clone();
        vodProgramModelDaoConfig.initIdentityScope(type);

        accountModelDao = new AccountModelDao(accountModelDaoConfig, this);
        aDModelDao = new ADModelDao(aDModelDaoConfig, this);
        modelStatusDao = new ModelStatusDao(modelStatusDaoConfig, this);
        newsCollectModelDao = new NewsCollectModelDao(newsCollectModelDaoConfig, this);
        xGNotificationModelDao = new XGNotificationModelDao(xGNotificationModelDaoConfig, this);
        channelModelDao = new ChannelModelDao(channelModelDaoConfig, this);
        launchAdModelDao = new LaunchAdModelDao(launchAdModelDaoConfig, this);
        newsDetailModelDao = new NewsDetailModelDao(newsDetailModelDaoConfig, this);
        newsListModelDao = new NewsListModelDao(newsListModelDaoConfig, this);
        newsPhotoModelDao = new NewsPhotoModelDao(newsPhotoModelDaoConfig, this);
        relatedReaderModelDao = new RelatedReaderModelDao(relatedReaderModelDaoConfig, this);
        liveChannelModelDao = new LiveChannelModelDao(liveChannelModelDaoConfig, this);
        liveProgramModelDao = new LiveProgramModelDao(liveProgramModelDaoConfig, this);
        videoChannelModelDao = new VideoChannelModelDao(videoChannelModelDaoConfig, this);
        vodListModelDao = new VodListModelDao(vodListModelDaoConfig, this);
        vodProgramModelDao = new VodProgramModelDao(vodProgramModelDaoConfig, this);

        registerDao(AccountModel.class, accountModelDao);
        registerDao(ADModel.class, aDModelDao);
        registerDao(ModelStatus.class, modelStatusDao);
        registerDao(NewsCollectModel.class, newsCollectModelDao);
        registerDao(XGNotificationModel.class, xGNotificationModelDao);
        registerDao(ChannelModel.class, channelModelDao);
        registerDao(LaunchAdModel.class, launchAdModelDao);
        registerDao(NewsDetailModel.class, newsDetailModelDao);
        registerDao(NewsListModel.class, newsListModelDao);
        registerDao(NewsPhotoModel.class, newsPhotoModelDao);
        registerDao(RelatedReaderModel.class, relatedReaderModelDao);
        registerDao(LiveChannelModel.class, liveChannelModelDao);
        registerDao(LiveProgramModel.class, liveProgramModelDao);
        registerDao(VideoChannelModel.class, videoChannelModelDao);
        registerDao(VodListModel.class, vodListModelDao);
        registerDao(VodProgramModel.class, vodProgramModelDao);
    }
    
    public void clear() {
        accountModelDaoConfig.clearIdentityScope();
        aDModelDaoConfig.clearIdentityScope();
        modelStatusDaoConfig.clearIdentityScope();
        newsCollectModelDaoConfig.clearIdentityScope();
        xGNotificationModelDaoConfig.clearIdentityScope();
        channelModelDaoConfig.clearIdentityScope();
        launchAdModelDaoConfig.clearIdentityScope();
        newsDetailModelDaoConfig.clearIdentityScope();
        newsListModelDaoConfig.clearIdentityScope();
        newsPhotoModelDaoConfig.clearIdentityScope();
        relatedReaderModelDaoConfig.clearIdentityScope();
        liveChannelModelDaoConfig.clearIdentityScope();
        liveProgramModelDaoConfig.clearIdentityScope();
        videoChannelModelDaoConfig.clearIdentityScope();
        vodListModelDaoConfig.clearIdentityScope();
        vodProgramModelDaoConfig.clearIdentityScope();
    }

    public AccountModelDao getAccountModelDao() {
        return accountModelDao;
    }

    public ADModelDao getADModelDao() {
        return aDModelDao;
    }

    public ModelStatusDao getModelStatusDao() {
        return modelStatusDao;
    }

    public NewsCollectModelDao getNewsCollectModelDao() {
        return newsCollectModelDao;
    }

    public XGNotificationModelDao getXGNotificationModelDao() {
        return xGNotificationModelDao;
    }

    public ChannelModelDao getChannelModelDao() {
        return channelModelDao;
    }

    public LaunchAdModelDao getLaunchAdModelDao() {
        return launchAdModelDao;
    }

    public NewsDetailModelDao getNewsDetailModelDao() {
        return newsDetailModelDao;
    }

    public NewsListModelDao getNewsListModelDao() {
        return newsListModelDao;
    }

    public NewsPhotoModelDao getNewsPhotoModelDao() {
        return newsPhotoModelDao;
    }

    public RelatedReaderModelDao getRelatedReaderModelDao() {
        return relatedReaderModelDao;
    }

    public LiveChannelModelDao getLiveChannelModelDao() {
        return liveChannelModelDao;
    }

    public LiveProgramModelDao getLiveProgramModelDao() {
        return liveProgramModelDao;
    }

    public VideoChannelModelDao getVideoChannelModelDao() {
        return videoChannelModelDao;
    }

    public VodListModelDao getVodListModelDao() {
        return vodListModelDao;
    }

    public VodProgramModelDao getVodProgramModelDao() {
        return vodProgramModelDao;
    }

}