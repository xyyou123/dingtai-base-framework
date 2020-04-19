package com.dingtai.android.library.video.event;

/**
 * author:lnr
 * date:2018/11/7
 */
public class AddVodReadNumEvent {

    private String vodId;

    public AddVodReadNumEvent(String vodId) {
        this.vodId = vodId;
    }

    public String getVodId() {
        return vodId;
    }
}
