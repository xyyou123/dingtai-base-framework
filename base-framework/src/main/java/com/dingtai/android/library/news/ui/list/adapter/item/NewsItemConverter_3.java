package com.dingtai.android.library.news.ui.list.adapter.item;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/17
 * 新闻item适配器 类型3
 */
public class NewsItemConverter_3 extends AbstractNewsItemConverter {

    @Override
    protected int normalLayoutId() {
        return R.layout.item_news_list_3;
    }

    @Override
    protected int noImageLayoutId() {
        return R.layout.item_news_list_3;
    }

    @Override
    protected void normalConvert(BaseViewHolder holder, int position, NewsListModel model) {
        ImageView imageView = holder.getView(R.id.item_image);
        loadImage(imageView, model.getSmallPicUrl());
    }

    @Override
    protected void noImageConvert(BaseViewHolder holder, int position, NewsListModel model) {
        holder.setGone(R.id.item_image, false);
    }
}
