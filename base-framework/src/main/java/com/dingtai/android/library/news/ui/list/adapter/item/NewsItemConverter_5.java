package com.dingtai.android.library.news.ui.list.adapter.item;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.NewsListModel;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/17
 */
public class NewsItemConverter_5 extends AbstractNewsItemConverter {

    @Override
    protected int normalLayoutId() {
        return R.layout.item_news_list_5;
    }

    @Override
    protected int noImageLayoutId() {
        return R.layout.item_news_list_5;
    }

    @Override
    protected void normalConvert(BaseViewHolder holder, int position, NewsListModel model) {
        String images = TextUtils.isEmpty(model.getPicPath()) ? model.getUploadPicNames() : model.getPicPath();
        if(images == null || images.isEmpty()) {
            holder.setGone(R.id.item_image1, false)
                    .setGone(R.id.item_image2, false)
                    .setGone(R.id.item_image3, false);
        }else {
            holder.setGone(R.id.item_image1, true)
                    .setGone(R.id.item_image2, true)
                    .setGone(R.id.item_image3, true);

            String[] urls = images.split(",");
            loadImage(holder.getView(R.id.item_image1), urls[0]);
            if(urls.length == 2) {
                loadImage(holder.getView(R.id.item_image2), urls[1]);
            }else if(urls.length > 2) {
                loadImage(holder.getView(R.id.item_image2), urls[1]);
                loadImage(holder.getView(R.id.item_image3), urls[2]);
            }
        }
    }

    @Override
    protected void noImageConvert(BaseViewHolder holder, int position, NewsListModel model) {
        holder.setGone(R.id.item_image1, false)
                .setGone(R.id.item_image2, false)
                .setGone(R.id.item_image3, false);
    }
}
