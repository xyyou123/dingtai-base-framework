package com.dingtai.android.library.news.ui.list.adapter.item;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/17
 * 新闻item适配器 类型1
 */
public class NewsItemConverter_1 extends AbstractNewsItemConverter {


    @Override
    protected int normalLayoutId() {
        return R.layout.item_news_list_1;
    }

    @Override
    protected int noImageLayoutId() {
        return R.layout.item_news_list_1;
    }

    @Override
    protected void normalConvert(BaseViewHolder holder, int position, NewsListModel model) {
        ImageView imageView = holder.getView(R.id.item_image);

        if(TextUtils.isEmpty(model.getSmallPicUrl())) {
            imageView.setVisibility(View.GONE);
        }else {
            imageView.setVisibility(View.VISIBLE);
            loadImage(imageView, model.getSmallPicUrl());
        }

        if(TextUtils.isEmpty(model.getSummary())) {

        }else {

        }
    }

    @Override
    protected void noImageConvert(BaseViewHolder holder, int position, NewsListModel model) {
        ImageView imageView = holder.getView(R.id.item_image);
        imageView.setVisibility(View.GONE);
    }
}
