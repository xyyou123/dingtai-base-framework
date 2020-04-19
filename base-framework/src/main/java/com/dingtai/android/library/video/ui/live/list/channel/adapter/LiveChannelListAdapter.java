package com.dingtai.android.library.video.ui.live.list.channel.adapter;

import com.dingtai.android.library.video.model.LiveChannelModel;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.NumberUtil;

/**
 * author:lnr
 * date:2018/8/30
 */
public class LiveChannelListAdapter extends BaseAdapter<LiveChannelModel> {

    private String type;

    public LiveChannelListAdapter(String type) {
        this.type = type;
    }

    @Override
    protected int getDefItemViewType(int position) {
        LiveChannelModel item = getItem(position);
        if(item == null) return 0;
        return NumberUtil.parseInt(item.getLiveType());
    }

    @Override
    protected ItemConverter<LiveChannelModel> createItemConverter(int viewType) {
        return AdapterProvider.getItemConvert(type);
    }
}
