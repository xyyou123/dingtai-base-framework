package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class TopicChann implements Parcelable {

private String ID;
private String ChannelName;
private String ImageUrl;
private String IsAd;
private String IsShowHome;
private String IsDel;
private String IsHtml;
private String ChannelUrl;
private String ShowOrder;
private String HadChild;
private String ReMark;
private String Issubscribe;
private String EnChName;

public TopicChann() {
}

protected TopicChann(Parcel in) {
    ID = in.readString();
    ChannelName = in.readString();
    ImageUrl = in.readString();
    IsAd = in.readString();
    IsShowHome = in.readString();
    IsDel = in.readString();
    IsHtml = in.readString();
    ChannelUrl = in.readString();
    ShowOrder = in.readString();
    HadChild = in.readString();
    ReMark = in.readString();
    Issubscribe = in.readString();
    EnChName = in.readString();
}

@Override
public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(ID);
    dest.writeString(ChannelName);
    dest.writeString(ImageUrl);
    dest.writeString(IsAd);
    dest.writeString(IsShowHome);
    dest.writeString(IsDel);
    dest.writeString(IsHtml);
    dest.writeString(ChannelUrl);
    dest.writeString(ShowOrder);
    dest.writeString(HadChild);
    dest.writeString(ReMark);
    dest.writeString(Issubscribe);
    dest.writeString(EnChName);
}

@Override
public int describeContents() {
    return 0;
}

public static final Creator<TopicChann> CREATOR = new Creator<TopicChann>() {
    @Override
    public TopicChann createFromParcel(Parcel in) {
        return new TopicChann(in);
    }

    @Override
    public TopicChann[] newArray(int size) {
        return new TopicChann[size];
    }
};

public static final class TopicChannConverter implements PropertyConverter<TopicChann, String> {
    @Override
    public TopicChann convertToEntityProperty(String databaseValue) {
        return JsonUtil.parseObject(databaseValue, TopicChann.class);
    }

    @Override
    public String convertToDatabaseValue(TopicChann entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}

public static final class TopicChannListConverter implements PropertyConverter<List<TopicChann>, String> {
    @Override
    public List<TopicChann> convertToEntityProperty(String databaseValue) {
        return JsonUtil.parseArray(databaseValue, TopicChann.class);
    }

    @Override
    public String convertToDatabaseValue(List<TopicChann> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
}
