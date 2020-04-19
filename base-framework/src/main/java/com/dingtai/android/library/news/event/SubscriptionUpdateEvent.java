package com.dingtai.android.library.news.event;

import com.dingtai.android.library.news.model.ChannelModel;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/27
 */
public class SubscriptionUpdateEvent {

    private List<ChannelModel> list;

    public SubscriptionUpdateEvent(List<ChannelModel> list) {
        this.list = list;
    }

    public List<ChannelModel> getList() {
        return list;
    }
}
