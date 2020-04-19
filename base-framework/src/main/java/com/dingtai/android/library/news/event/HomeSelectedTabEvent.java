package com.dingtai.android.library.news.event;

/**
 * author:lnr
 * date:2018/10/8
 */
public class HomeSelectedTabEvent {
    private String chid;

    public HomeSelectedTabEvent(String chid) {
        this.chid = chid;
    }

    public String getChid() {
        return chid;
    }
}
