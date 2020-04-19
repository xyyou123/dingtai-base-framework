package com.dingtai.android.library.news.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.model.models.ADModel;
import com.dingtai.android.library.model.models.ScoreModel;
import com.dingtai.android.library.news.NewsComponentConstant;
import com.dingtai.android.library.news.event.HomeSelectedTabEvent;
import com.dingtai.android.library.news.model.ChannelModel;
import com.dingtai.android.library.news.model.NewsListModel;
import com.dingtai.android.library.news.ui.list.more.NewsListMoreActivity;
import com.dingtai.android.library.news.ui.subject.old.SubjectOldListActivity;
import com.dingtai.android.library.news.ui.web.NewsNavigationCallback;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.resource.Score;
import com.lnr.android.base.framework.rx.RxBus;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/8/28
 */
public class NewsNavigation {

    public static ADNavigationCallback sADNavigationCallback;
    public static NewsNavigationCallback sNewsNavigationCallback;

    /**
     * 新闻列表 tab
     */
    public static Object tabActivity(String title, String action, String parentId) {
        return ARouter.getInstance().build(Routes.News.HOME_TAB_ACTIVITY)
                .withString("title", title)
                .withString("action", action)
                .withString("parentId", parentId).navigation();
    }

    /**
     * 新闻列表 tab
     * @see com.dingtai.android.library.news.ui.home.NewsHomeFragment
     */
    public static Object tab(String action, String parentId) {
        return ARouter.getInstance().build(Routes.News.HOME_TAB)
                .withString("action", action)
                .withString("parentId", parentId).navigation();
    }

    /**
     * 新闻列表第一页 推荐页
     */
    public static Object listFirst() {
        return ARouter.getInstance().build(Routes.News.HOME_FIRST).navigation();
    }

    /**
     * 新闻列表第一页 推荐页 更多新闻
     */
    public static void listFirstNewsMore(String title, String type) {
        ARouter.getInstance().build(Routes.News.HOME_FIRST_NEWS_MORE)
                .withString("title", title)
                .withString("type", type).navigation();
    }

    /**
     * 新闻 栏目订阅
     *
     * @see com.dingtai.android.library.news.ui.home.subscription.NewsSubscriptionActivity
     */
    public static void subscription(List<ChannelModel> list) {
        subscription("0", list);
    }

    /**
     * 新闻 栏目订阅
     *
     * @see com.dingtai.android.library.news.ui.home.subscription.NewsSubscriptionActivity
     */
    public static void subscription(String parentID, List<ChannelModel> list) {
        ARouter.getInstance().build(Routes.News.HOME_SUBSCRIPTION)
                .withString("parentID", parentID)
                .withParcelableArrayList("list", new ArrayList<Parcelable>(list)).navigation();
    }

    public static void listActivity(String title, String CHID) {
        ARouter.getInstance().build(Routes.News.NEWS_LIST_ACTIVITY)
                .withString("title", title)
                .withString("CHID", CHID)
                .withBoolean("hasAd", false)
                .navigation();
    }

    public static void listActivity(String title, String CHID, boolean hasAd) {
        ARouter.getInstance().build(Routes.News.NEWS_LIST_ACTIVITY)
                .withString("title", title)
                .withString("CHID", CHID)
                .withBoolean("hasAd", hasAd)
                .navigation();
    }

    /**
     * 新闻列表 无ad
     */
    public static Object listNoAd(String CHID) {
        return ARouter.getInstance().build(Routes.News.NEWS_LIST_NOAD).withString("CHID", CHID).navigation();
    }

    /**
     * 新闻列表 有ad
     */
    public static Object listHasAd(String CHID) {
        return ARouter.getInstance().build(Routes.News.NEWS_LIST_HASAD).withString("CHID", CHID).navigation();
    }

    /**
     * 新闻 搜索页
     */
    public static Object search() {
        return ARouter.getInstance().build(Routes.News.SEARCH).navigation();
    }

    /**
     * 新闻 搜索页结果页
     */
    public static Object searchResult(String key) {
        return ARouter.getInstance().build(Routes.News.SEARCH_RESULT).withString("key", key).navigation();
    }

    /**
     * 新闻 相关新闻
     *
     * @see com.dingtai.android.library.news.ui.relevant.NewsRelevantFragment
     */
    public static Object relevant(String id) {
        return ARouter.getInstance().build(Routes.News.RELEVANT).withString("id", id).navigation();
    }

    /**
     * 新闻图片页
     */
    public static Object image(NewsListModel model) {
        return ARouter.getInstance().build(Routes.News.IMAGE)
                .withParcelable("model", model).navigation();
    }


    /**
     * 新闻详情页
     */
    public static Object details(NewsListModel model) {
        return ARouter.getInstance().build(Routes.News.DETAILS)
                .withParcelable("model", model).navigation();
    }

    /**
     * 新闻 评论列表页
     */
    public static void comment(NewsListModel model) {
        ARouter.getInstance().build(Routes.News.DETAILS_COMMENT).withParcelable("model", model).navigation();
    }


    /**
     * 旧专题列表
     *
     * @see SubjectOldListActivity
     */
    public static Object subjectOldList(String ChID, String GUID, String title) {
        return ARouter.getInstance().build(Routes.News.SUBJECT_OLD_LIST)
                .withString("ChID", ChID).withString("GUID", GUID).withString("title", title).navigation();
    }

    /**
     * 新专题列表
     *
     * @see com.dingtai.android.library.news.ui.subject.neo.SubjectNeoListActivity
     */
    public static Object subjectNeoList(String ChID, String GUID, String title) {
        return ARouter.getInstance().build(Routes.News.SUBJECT_NEO_LIST)
                .withString("ChID", ChID).withString("GUID", GUID).withString("title", title).navigation();
    }

    /**
     * 新闻列表 更多
     *
     * @see NewsListMoreActivity
     */
    public static Object newsListMore(String action, String ChID, String DeptID, String title) {
        return ARouter.getInstance().build(Routes.News.LIST_MORE)
                .withString("ChID", ChID).withString("DeptID", DeptID).withString("title", title).withString("action", action).navigation();
    }

    public static void listItemNavigation(NewsListModel model) {
        if(sNewsNavigationCallback != null && sNewsNavigationCallback.intercept(model)) {
            return;
        }
        switch (model.getResourceType()) {
            case "1":
                details(model);
                break;
            case "2":
                ARouter.getInstance().build(Routes.Modules.WEB)
                        .withString("url", model.getResourceUrl()).withString("title", model.getTitle()).navigation();
                break;
            case "3":
                image(model);
                break;
            case "4":
                if ("True".equals(model.getIsNewTopice())) {
                    subjectNeoList(model.getChID(), model.getResourceGUID(), model.getTitle());
                } else {
                    subjectOldList(model.getChID(), model.getResourceGUID(), model.getTitle());
                }
                break;
        }
    }

    public static Intent listItemIntent(Context context, NewsListModel model) {
        Postcard postcatd = null;
        switch (model.getResourceType()) {
            case "1":
                postcatd = ARouter.getInstance().build(Routes.News.DETAILS)
                        .withParcelable("model", model);
                break;
            case "2":
                postcatd = ARouter.getInstance().build(Routes.Modules.WEB)
                        .withString("url", model.getResourceUrl()).withString("title", model.getTitle());
                break;
            case "3":
                postcatd = ARouter.getInstance().build(Routes.News.IMAGE)
                        .withParcelable("model", model);
                break;
            case "4":
                if ("True".equals(model.getIsNewTopice())) {
                    postcatd = ARouter.getInstance().build(Routes.News.SUBJECT_NEO_LIST)
                            .withString("ChID", model.getChID())
                            .withString("GUID", model.getResourceGUID())
                            .withString("title", model.getTitle());
                } else {
                    postcatd = ARouter.getInstance().build(Routes.News.SUBJECT_OLD_LIST)
                            .withString("ChID", model.getChID())
                            .withString("GUID", model.getResourceGUID())
                            .withString("title", model.getTitle());
                }
                break;
        }

        if(postcatd == null) return null;
        try {
            LogisticsCenter.completion(postcatd);
            Intent intent = new Intent(context, postcatd.getDestination());
            intent.putExtras(postcatd.getExtras());
            return intent;
        }catch (Exception e) {
            return null;
        }
    }

    public static void subjectItemNavigation(NewsListModel model) {
        switch (model.getResourceType()) {
            case "2":
                ARouter.getInstance().build(Routes.Modules.WEB)
                        .withString("url", model.getResourceUrl()).withString("title", model.getTitle()).navigation();
                break;
            case "3"://图集
                image(model);
                break;
            default:
                details(model);
                break;
        }
    }


    //逻辑很乱，客户端最好定义sADNavigationCallback自己实现，很多客户端的判断条件不一样
    public static boolean adNavigation(boolean launch, ADModel model) {
        boolean result = false;
        if(sADNavigationCallback != null && sADNavigationCallback.intercept(model)) {
            result = true;
        }else {
            String liveChannel = model.getLiveChannel();
            if(!TextUtils.isEmpty(liveChannel) && !TextUtils.isEmpty(liveChannel.replace("[]", ""))) {
                switch (model.getLinkTo()) {
                    case "5":
                        ARouter.getInstance().build(Routes.Video.LIVE_MAIN)
                                .withString("data", model.getLiveChannel()).navigation();
                        result = true;
                        break;
                    case "6":
                        ARouter.getInstance().build(Routes.Video.LIVE_MAIN)
                                .withString("data", model.getLiveChannel()).navigation();
                        result = true;
                        break;
                    case "7":
                    case "8":
                        ARouter.getInstance().build(Routes.Video.LIVE_MAIN_IMAGETEXT)
                                .withString("data", model.getLiveChannel()).navigation();
                        result = true;
                        break;
                }
            }else {
                String url;
                String[] urls;
                switch (model.getADFor()) {
                    case "1":
                    case "4":
                        // *************************************************
                        switch (model.getLinkTo()) {
                            case "1":
                                url = model.getLinkUrl();
                                if (url == null) {
                                    result = false;
                                    break;// 不知道为什么会为nul
                                }
                                urls = url.split(",");
                                if (urls.length != 2) {
                                    result = false;
                                    break;// 不知道为什么会!=2
                                }

                                NewsListModel newsListModel = new NewsListModel();
                                newsListModel.setResourceGUID(urls[1]);
                                newsListModel.setResourceType(urls[0]);
                                details(newsListModel);
                                result = true;
                                break;
                            case "2":
                                url = model.getLinkUrl();
                                if (url == null) {
                                    subjectOldList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;// 不知道为什么会为null
                                }
                                urls = url.split(",");
                                if (urls.length != 2) {
                                    subjectOldList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;
                                }
                                subjectOldList(urls[0], urls[1], model.getADName());
                                result = true;
                                break;
                            case "3"://新专题
                                url = model.getLinkUrl();
                                if (url == null) {
                                    subjectNeoList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;
                                }
                                urls = url.split(",");
                                if (urls.length != 2) {
                                    subjectNeoList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;
                                }
                                subjectNeoList(urls[0], urls[1], model.getADName());
                                result = true;
                                break;
                            case "4"://旧专题
                                url = model.getLinkUrl();
                                if (url == null) {
                                    subjectOldList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;
                                }
                                urls = url.split(",");
                                if (urls.length != 2) {
                                    subjectOldList(model.getChID(), model.getID(), model.getADName());
                                    result = true;
                                    break;
                                }
                                subjectOldList(urls[0], urls[1], model.getADName());
                                result = true;
                                break;
                        }
                        // *************************************************
                        break;
                    case NewsComponentConstant.ADFor.TYPE_2:
                        // 未知分支,不知道是什么鬼
                        break;
                    case NewsComponentConstant.ADFor.TYPE_3:
                        switch (model.getLinkTo()) {
                            case NewsComponentConstant.ADLinkTo.DETAILS:
                                ARouter.getInstance().build(Routes.Modules.WEB)
                                        .withString("url", model.getLinkUrl()).withString("title", model.getADName()).navigation();
                                result = true;
                                break;
                            case NewsComponentConstant.ADLinkTo.LIST:
                                break;
                            case NewsComponentConstant.ADLinkTo.LIST_2:
                                subjectNeoList(model.getChID(), "", model.getADName());
                                result = true;
                                break;
                            case "7":
                                ARouter.getInstance().build(Routes.Modules.WEB)
                                        .withString("url", model.getLinkUrl()).withString("title", model.getADName()).navigation();
                                result = true;
                                break;
                        }
                        // 未知分支,不知道是什么鬼
                        break;
                    case NewsComponentConstant.ADFor.TYPE_5:
                        RxBus.getDefault().post(new HomeSelectedTabEvent(model.getChID()));
                        break;
                    case NewsComponentConstant.ADFor.TYPE_7:
                        //视频
                        switch (model.getLinkTo()) {
                            case "5":
                                ARouter.getInstance().build(Routes.Video.LIVE_MAIN)
                                        .withString("data", model.getLiveChannel()).navigation();
                                result = true;
                                break;
                            case "6":
                                ARouter.getInstance().build(Routes.Video.LIVE_MAIN)
                                        .withString("data", model.getLiveChannel()).navigation();
                                result = true;
                                break;
                            case "7":
                                ARouter.getInstance().build(Routes.Video.LIVE_MAIN_IMAGETEXT)
                                        .withString("data", model.getLiveChannel()).navigation();
                                result = true;
                                break;
                        }
                        // 未知分支,不知道是什么鬼
                        break;
                }
            }
        }

        if(result) {
            if(launch) {
                RxBus.getDefault().post(new ScoreModel(Score.SCORE_CLICK_STARTAD));
            }else {
                RxBus.getDefault().post(new ScoreModel(Score.SCORE_CLICK_OTHERAD));
            }
        }

        return result;
    }

    public static boolean adNavigation(ADModel model) {
        return adNavigation(false, model);
    }
}
