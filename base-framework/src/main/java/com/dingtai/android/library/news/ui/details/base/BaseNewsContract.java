package com.dingtai.android.library.news.ui.details.base;

import com.dingtai.android.library.news.model.NewsCommentModel;
import com.lnr.android.base.framework.mvp.presenter.IPresenter;
import com.lnr.android.base.framework.mvp.view.IView;

import java.util.List;

/**
 * author:lnr
 * date:2018-08-22
 */
public interface BaseNewsContract {

    interface View extends IView {
        /**
         * 点赞新闻
         */
        void addNewsZan(boolean result);

        /**
         * 收藏新闻
         */
        void addNewsCollect(boolean result);

        /**
         * 删除收藏
         */
        void deleteNewsCollect(boolean result);

        /**
         * 发布新闻评论
         */
        void addNewsComment(boolean result);

        /**
         * 新闻评论点赞
         */
        void addCommentZan(boolean result, NewsCommentModel parent, NewsCommentModel comment);

        /**
         * 获取新闻评论列表
         */
        void getNewsCommentList(boolean result, boolean refresh, List<NewsCommentModel> list);
    }

    interface  Presenter extends IPresenter<View> {

        /**
         * 新闻点赞 状态
         * @param newsId 新闻id
         * @return 是否已经点赞
         */
        boolean isNewsZaned(String newsId);

        /**
         * 新闻收藏状态
         * @param newsId 新闻id
         * @return 是否收藏
         */
        boolean isNewsCollected(String newsId);

        /**
         * 点赞新闻
         * @param newsId 新闻id
         */
        void addNewsZan(String newsId);

        /**
         * 收藏新闻
         * @param newsId 新闻id
         * @param type 新闻类型
         */
        void addNewsCollect(String newsId, String type);

        /**
         * 删除收藏
         * @param newsId 新闻id
         */
        void deleteNewsCollect(String newsId);

        /**
         * 发布新闻评论
         * @param newsId 新闻id
         * @param content 新闻内容
         */
        void addNewsComment(String newsId, String content);

        /**
         * 点赞新闻评论
         * @param comment 评论对象
         */
        void addCommentZan(NewsCommentModel parent, NewsCommentModel comment);

        /**
         * 发布新闻评论
         * @param newsId 新闻id
         * @param commentId 评论id
         * @param content 新闻内容
         */
        void addReplyComment(String newsId, String commentId, String content);

        /**
         * 获取新闻评论列表
         * @param newsId 新闻id
         * @param top 数量
         * @param dtop 偏移量
         */
        void getNewsCommentList(String newsId, String forapp, String top, String dtop);

        void addShareNum(String id);
    }
}
