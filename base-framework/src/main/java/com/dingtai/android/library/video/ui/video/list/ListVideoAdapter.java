package com.dingtai.android.library.video.ui.video.list;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dingtai.android.library.model.models.PlayerModel;

import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.ui.player.listplayer.BasePlayerAdapter;
import com.dingtai.android.library.video.ui.player.listplayer.pip.PIPManager;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/11/6
 */
public class ListVideoAdapter extends BasePlayerAdapter<VideoModel> {

    public ListVideoAdapter(PIPManager manager) {
        super(manager);
    }

    @Override
    protected boolean isVideo(int position) {
        return true;
    }

    @Override
    protected ItemConverter<VideoModel> createOtherItemConverter(int type) {
        return new ItemConverter<VideoModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_list_video;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, VideoModel model) {
                GlideHelper.load(holder.getView(R.id.thumb), model.getImageUrl());
            }
        };
    }

    @Override
    protected VideoItemConverter<VideoModel> createVideoItemConverter() {
        return new VideoItemConverter<VideoModel>() {
            @Override
            protected PlayerModel getPlayerModel(VideoModel videoModel) {
                return PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                        .setTitle(videoModel.getName())
                        .addUrls(videoModel.getMediaUrl()).build();
            }

            @Override
            protected ViewGroup getIjkVideoViewLayout(VideoViewHolder holder) {
                return holder.getView(R.id.IjkVideoView);
            }

            @Override
            protected ViewGroup getPreLayout(VideoViewHolder holder) {
                return holder.getView(R.id.layout_thumb);
            }

            @Override
            protected View getPlayButton(VideoViewHolder holder) {
                return holder.getView(R.id.item_play);
            }

            @Override
            protected int getItemHash(VideoModel model) {
                return model.getID().hashCode();
            }

            @Override
            protected void convertItem(VideoViewHolder baseViewHolder, int i, VideoModel model) {
                int widthPixels = baseViewHolder.itemView.getResources().getDisplayMetrics().widthPixels;
                baseViewHolder.getView(R.id.layout_container).setLayoutParams(new LinearLayout.LayoutParams(widthPixels, widthPixels * 9 / 16 + 1));

                GlideHelper.load(baseViewHolder.getView(R.id.thumb), model.getImageUrl());
                baseViewHolder.setText(R.id.item_comment_count, "评论 " + model.getCommentNum());
                baseViewHolder.setText(R.id.item_zan_count, model.getGoodPoint());
                boolean isZan = "1".equals(model.getIsExsitPoint());
                baseViewHolder.getView(R.id.item_zan_icon).setSelected(isZan);
                baseViewHolder.setTextColor(R.id.item_zan_count, isZan
                        ? Color.parseColor("#e84a47")
                        : Color.parseColor("#797979"));
                baseViewHolder.addOnClickListener(R.id.item_layout_share)
                        .addOnClickListener(R.id.item_layout_comment)
                        .addOnClickListener(R.id.item_layout_zan);
            }

            @Override
            public int layoutId() {
                return R.layout.item_list_video;
            }
        };
    }
}
