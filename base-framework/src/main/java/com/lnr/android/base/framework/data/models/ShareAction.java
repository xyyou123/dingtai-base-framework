package com.lnr.android.base.framework.data.models;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class ShareAction {

    private SHARE_MEDIA type;
    private String title;
    private int res;

    public ShareAction(SHARE_MEDIA type, String title, int res) {
        this.type = type;
        this.title = title;
        this.res = res;
    }

    public SHARE_MEDIA getId() {
        return type;
    }

    public void setId(SHARE_MEDIA type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
