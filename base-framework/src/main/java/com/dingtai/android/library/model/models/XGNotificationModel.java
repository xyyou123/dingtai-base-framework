package com.dingtai.android.library.model.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/10/29
 */
@Entity
public class XGNotificationModel {

    @Id
    private Long _id;

    private String type;

    private String title;

    private String content;

    private String activity;

    private String customContent;

    private long time;


    @Generated(hash = 1535302314)
    public XGNotificationModel(Long _id, String type, String title, String content,
            String activity, String customContent, long time) {
        this._id = _id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.activity = activity;
        this.customContent = customContent;
        this.time = time;
    }

    @Generated(hash = 1676931394)
    public XGNotificationModel() {
    }


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCustomContent() {
        return customContent;
    }

    public void setCustomContent(String customContent) {
        this.customContent = customContent;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
