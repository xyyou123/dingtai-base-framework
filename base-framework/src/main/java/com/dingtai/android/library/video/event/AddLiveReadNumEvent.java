package com.dingtai.android.library.video.event;

/**
 * author:lnr
 * date:2018/11/7
 */
public class AddLiveReadNumEvent {

    private String liveId;

    public AddLiveReadNumEvent(String liveId) {
        this.liveId = liveId;
    }

    public String getLiveId() {
        return liveId;
    }
}
