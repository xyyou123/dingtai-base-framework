package com.lnr.android.base.framework.rx.event;

/**
 * author:lnr
 * date:2018/10/8
 */
public class AddReadingHistoryEvent {

    private String guid;

    public AddReadingHistoryEvent(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }
}
