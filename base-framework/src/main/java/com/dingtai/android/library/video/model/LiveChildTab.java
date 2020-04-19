package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * author:lnr
 * date:2018/8/30
 * 直播频道的子tab
 */
public class LiveChildTab implements Parcelable {

    private String ID;
    private String CreateTime;
    private String TabName;
    private String TabType;
    private String TabCode;
    private String ShowOrder;
    private String Status;
    private String Remark;

    public LiveChildTab() {
    }

    protected LiveChildTab(Parcel in) {
        ID = in.readString();
        CreateTime = in.readString();
        TabName = in.readString();
        TabType = in.readString();
        TabCode = in.readString();
        ShowOrder = in.readString();
        Status = in.readString();
        Remark = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(CreateTime);
        dest.writeString(TabName);
        dest.writeString(TabType);
        dest.writeString(TabCode);
        dest.writeString(ShowOrder);
        dest.writeString(Status);
        dest.writeString(Remark);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LiveChildTab> CREATOR = new Creator<LiveChildTab>() {
        @Override
        public LiveChildTab createFromParcel(Parcel in) {
            return new LiveChildTab(in);
        }

        @Override
        public LiveChildTab[] newArray(int size) {
            return new LiveChildTab[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getTabName() {
        return TabName;
    }

    public void setTabName(String tabName) {
        TabName = tabName;
    }

    public String getTabType() {
        return TabType;
    }

    public void setTabType(String tabType) {
        TabType = tabType;
    }

    public String getTabCode() {
        return TabCode;
    }

    public void setTabCode(String tabCode) {
        TabCode = tabCode;
    }

    public String getShowOrder() {
        return ShowOrder;
    }

    public void setShowOrder(String showOrder) {
        ShowOrder = showOrder;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public static final class LiveChildTabConConverter implements PropertyConverter<List<LiveChildTab>, String> {
        @Override
        public List<LiveChildTab> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, LiveChildTab.class);
        }

        @Override
        public String convertToDatabaseValue(List<LiveChildTab> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }
}
