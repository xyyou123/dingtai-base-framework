package com.dingtai.android.library.video.ui.shortvideo.detial;

import android.net.Uri;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/11/5
 */
public class DetialItemConverter implements ItemConverter<ShortVideoModel> {
    @Override
    public int layoutId() {
        return R.layout.item_short_video_detial;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, ShortVideoModel vodListModel) {
        GlideHelper.loadCircle(holder.getView(R.id.fiv_user_logo), vodListModel.getImgUrl());
        holder.setText(R.id.tv_title, vodListModel.getVideoName());
        holder.setText(R.id.tv_name, vodListModel.getUserName());
        Uri uri = Uri.parse(vodListModel.getVideoUrl());

        
//        ((FullScreenVideoView) ((FullScreenVideoView) holder.getView(R.id.fsvv_video)).setMediaController();
//        holder.setText(R.id.item_title, vodListModel.getProgramName());
//        holder.setText(R.id.item_time, MessageFormat.format("最新更新时间:{0}", vodListModel.getNewDateTime()));
    }
}
