package com.dingtai.android.library.news.ui.home.subscription;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.ChannelModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/9/27
 */
public class NewsSubscriptionMoreAdapter extends BaseAdapter<ChannelModel> {

    @Override
    protected ItemConverter<ChannelModel> createItemConverter(int viewType) {
        return new ItemConverter<ChannelModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_news_subscription;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, ChannelModel model) {
                holder.setText(R.id.item_name, model.getChannelName());
                holder.setText(R.id.item_des, model.getEnChName());
            }
        };
    }
}
