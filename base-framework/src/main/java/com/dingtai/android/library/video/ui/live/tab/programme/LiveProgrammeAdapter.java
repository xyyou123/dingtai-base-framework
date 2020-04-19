package com.dingtai.android.library.video.ui.live.tab.programme;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.LiveProgramModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.date.DateUtil;
import com.lnr.android.base.framework.uitl.date.Time;

/**
 * author:lnr
 * date:2018/9/4
 */
public class LiveProgrammeAdapter extends BaseAdapter<LiveProgramModel> {

   private int playingIndex = -1;
   private boolean isToday;

    public LiveProgrammeAdapter() {
    }

    public LiveProgrammeAdapter(boolean isToday) {
        this.isToday = isToday;
    }

    public void updatePlayingStatus(int index) {
        playingIndex = index;
        notifyDataSetChanged();
    }

    public boolean isCanPlaying(int position) {
        LiveProgramModel model = getItem(position);
        if(model == null) {
            return false;
        }
        if(!isToday) {
            return true;
        }
        long start = DateUtil.format(model.getPlayTime(), "HH:mm");
        long current = System.currentTimeMillis() - Time.today() - Time.Hour * 8;
        return current > start;

    }

    @Override
    protected ItemConverter<LiveProgramModel> createItemConverter(int viewType) {
        return new ItemConverter<LiveProgramModel>() {

            @Override
            public int layoutId() {
                return R.layout.item_live_programme;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, LiveProgramModel model) {
                holder.setText(R.id.item_time, model.getPlayTime());
                holder.setText(R.id.item_title, model.getProgramName());

                TextView replay = holder.getView(R.id.item_replay);
                replay.setSelected(playingIndex == position);

                if(isToday) {
                    long start = DateUtil.format(model.getPlayTime(), "HH:mm");
                    long end = DateUtil.format(model.getEndTime(), "HH:mm");
                    if(end < start) {
                        end += Time.Hour*24;
                    }
                    long current = System.currentTimeMillis() - Time.today() - Time.Hour * 8;
                    if(current > end) {
                        replay.setVisibility(View.VISIBLE);
                        replay.setText(playingIndex == position ? "正在回看" : "回看");
                    }else if(current > start && current < end){
                        replay.setVisibility(View.VISIBLE);
                        replay.setText("直播中");
                    }else {
                        replay.setVisibility(View.GONE);
                    }
                }else {
                    replay.setVisibility(View.VISIBLE);
                    replay.setText(playingIndex == position ? "正在回看" : "回看");
                }
            }
        };
    }
}
