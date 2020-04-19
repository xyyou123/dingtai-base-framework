package com.lnr.android.base.framework.event;

/**
 * author:lnr
 * date:2018/12/11
 */
public class UpdateStatusEvent {

    private String key;
    private int status;

    public UpdateStatusEvent(String key, int status) {
        this.key = key;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public int getStatus() {
        return status;
    }
}
