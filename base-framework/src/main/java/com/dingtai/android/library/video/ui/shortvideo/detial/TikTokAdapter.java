package com.dingtai.android.library.video.ui.shortvideo.detial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;

import java.util.List;

public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.VideoHolder> {

    private List<ShortVideoModel> videos;
    private Context context;

    public TikTokAdapter(List<ShortVideoModel> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_short_video_douyin, parent, false);
        return new VideoHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {

        ShortVideoModel videoBean = videos.get(position);
        GlideHelper.loadCircle(holder.thumb, videoBean.getImgUrl());
    }

    @Override
    public int getItemCount() {
        return videos == null ? 0 : videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private ImageView thumb;

        VideoHolder(View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumb);
        }
    }
}