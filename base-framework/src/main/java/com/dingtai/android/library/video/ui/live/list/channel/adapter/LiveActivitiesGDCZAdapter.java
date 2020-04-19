package com.dingtai.android.library.video.ui.live.list.channel.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.LiveChannelModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.date.DateUtil;

/**
 * author:lnr
 * date:2018/8/30
 */
public class LiveActivitiesGDCZAdapter extends BaseAdapter<LiveChannelModel> {

    @Override
    protected ItemConverter<LiveChannelModel> createItemConverter(int viewType) {
        return new ItemConverter<LiveChannelModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_live_activities_list_3;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, LiveChannelModel model) {
                GlideHelper.load(holder.getView(R.id.item_image), model.getLiveImageUrl());
                holder.setText(R.id.item_title, model.getLiveChannelName());

                holder.setText(R.id.item_count, model.getReadNo() + "人参与");

                long begin = DateUtil.format(model.getLiveBeginDate());
                long end = DateUtil.format(model.getLiveEndDate());
                long current = System.currentTimeMillis();

                if(begin > current) {
                    //未开始
                    holder.setImageResource(R.id.item_state, R.drawable.icon_activities_state_begin);
                }else if(end <= current) {
                    //已结束
                    holder.setImageResource(R.id.item_state, R.drawable.icon_activities_state_over);
                }else {
                    holder.setImageResource(R.id.item_state, R.drawable.icon_activities_state_ing);
                }

            }
        };
    }
}
