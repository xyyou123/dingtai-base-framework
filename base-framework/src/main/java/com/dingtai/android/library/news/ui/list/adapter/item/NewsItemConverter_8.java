package com.dingtai.android.library.news.ui.list.adapter.item;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/17
 * 新闻item适配器 类型1
 */
public class NewsItemConverter_8 extends AbstractNewsItemConverter {


    @Override
    protected int normalLayoutId() {
        return R.layout.item_news_list_8;
    }

    @Override
    protected int noImageLayoutId() {
        return R.layout.item_news_list_8;
    }

    @Override
    protected void normalConvert(BaseViewHolder holder, int position, NewsListModel model) {
        String images = TextUtils.isEmpty(model.getPicPath()) ? model.getUploadPicNames() : model.getPicPath();
        if(images == null || images.isEmpty()) {
            holder.setGone(R.id.item_image1, false)
                    .setGone(R.id.item_image2, false);
        }else {
            String[] urls = images.split(",");
            if(urls.length == 1) {
                holder.setGone(R.id.item_image1, true)
                        .setGone(R.id.item_image2, false);
                loadImage(holder.getView(R.id.item_image1), urls[0]);
            }else if (urls.length == 2) {
                holder.setGone(R.id.item_image1, true)
                        .setGone(R.id.item_image2, true);
                loadImage(holder.getView(R.id.item_image1), urls[0]);
                loadImage(holder.getView(R.id.item_image2), urls[1]);
            }else {
                holder.setGone(R.id.item_image1, true)
                        .setGone(R.id.item_image2, true);
                loadImage(holder.getView(R.id.item_image1), urls[0]);
                loadImage(holder.getView(R.id.item_image2), urls[1]);
            }
        }
    }

    @Override
    protected void noImageConvert(BaseViewHolder holder, int position, NewsListModel model) {
        holder.setGone(R.id.item_image1, false)
                .setGone(R.id.item_image2, false);
    }
}