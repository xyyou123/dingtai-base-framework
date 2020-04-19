package com.dingtai.android.library.video.ui.live.tab.game;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.LiveGameModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/9/5
 */
public class LiveGameAdapter extends BaseAdapter<LiveGameModel> {

    @Override
    protected int getDefItemViewType(int position) {
        LiveGameModel liveGameModel = getItem(position);
        if(liveGameModel == null) {
            return super.getDefItemViewType(position);
        }

        String voteType = liveGameModel.getVoteType();
        int type = 0;
        if (voteType != null && (voteType.equals("2") || voteType.equals("4"))) {
            type = 1;
        }
        return type;
    }

    @Override
    protected ItemConverter<LiveGameModel> createItemConverter(int viewType) {
        ItemConverter<LiveGameModel> itemConverter = null;

        switch (viewType) {
            case 0:
                itemConverter = new ItemConverter<LiveGameModel>() {

                    @Override
                    public int layoutId() {
                        return R.layout.item_live_game_1;
                    }

                    @Override
                    public void convert(BaseViewHolder holder, int position, LiveGameModel liveGameModel) {
                        holder.setText(R.id.item_title, liveGameModel.getGameName());
                        holder.setText(R.id.item_summary, liveGameModel.getGameIntro());
                        GlideHelper.load(holder.getView(R.id.item_logo), liveGameModel.getGameLogo());
                    }
                };
                break;
            case 1:
                itemConverter = new ItemConverter<LiveGameModel>() {
                    @Override
                    public int layoutId() {
                        return R.layout.item_live_game_1;
                    }

                    @Override
                    public void convert(BaseViewHolder holder, int position, LiveGameModel liveGameModel) {
                        holder.setText(R.id.item_title, liveGameModel.getGameName());
                        holder.setText(R.id.item_summary, liveGameModel.getGameIntro());
                        GlideHelper.load(holder.getView(R.id.item_logo), liveGameModel.getGameLogo());
                    }
                };
                break;
        }
        return itemConverter;
    }
}
