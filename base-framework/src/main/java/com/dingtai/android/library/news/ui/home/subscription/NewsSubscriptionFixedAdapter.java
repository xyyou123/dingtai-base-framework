package com.dingtai.android.library.news.ui.home.subscription;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.news.model.ChannelModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseDraggableAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/9/27
 */
public class NewsSubscriptionFixedAdapter extends BaseDraggableAdapter<ChannelModel> {

    public static final String[] color = new String[]{"#79a2a0", "#d3dcfb", "#cba2bf", "#a299c0", "#edc7ff", "#cbf9f9", "#cd6d57", "#a08dae"};

    @Override
    protected int getDefItemViewType(int position) {
        ChannelModel item = getItem(position);
        if(item == null) return super.getDefItemViewType(position);
        return "True".equals(item.getIsDel()) ? 1 : 0;
    }

    @Override
    protected ItemConverter<ChannelModel> createItemConverter(final int viewType) {
        return new ItemConverter<ChannelModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_news_subscription;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, ChannelModel model) {
                if(viewType == 1) {
                    holder.itemView.setBackgroundResource(R.color.backgroundDark_4);
                }else {
                    holder.itemView.setBackgroundColor(Color.parseColor(color[position % color.length]));
                }

                holder.setText(R.id.item_name, model.getChannelName());
                holder.setText(R.id.item_des, model.getEnChName());
            }
        };
    }
}
