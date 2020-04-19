package com.dingtai.android.library.video.ui.live.list.channel.adapter.converter;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.LiveChannelModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/11/2
 */
public class ItemConverter_1 implements ItemConverter<LiveChannelModel> {
    @Override
    public int layoutId() {
        return R.layout.item_live_list;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, LiveChannelModel model) {
        GlideHelper.load(holder.getView(R.id.item_image), model.getLiveImageUrl());
        holder.setText(R.id.item_title, model.getLiveChannelName());
    }
}
