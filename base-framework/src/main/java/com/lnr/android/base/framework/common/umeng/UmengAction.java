package com.lnr.android.base.framework.common.umeng;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * author:lnr
 * date:2018/9/13
 */
public class UmengAction {

    private SHARE_MEDIA type;
    private String name;
    private int res;

    public UmengAction(SHARE_MEDIA type, String name, int res) {
        this.type = type;
        this.name = name;
        this.res = res;
    }

    public SHARE_MEDIA getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getRes() {
        return res;
    }
}
