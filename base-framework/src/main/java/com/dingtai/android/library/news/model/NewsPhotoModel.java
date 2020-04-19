package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

@Entity
public class NewsPhotoModel implements Parcelable {

    @Id
    private Long _id;
    private String RID;
    private String PhotoTitle;
    private String PhotoUrl;
    private String PhotoDescription;
    private String CreateTime;


    protected NewsPhotoModel(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        RID = in.readString();
        PhotoTitle = in.readString();
        PhotoUrl = in.readString();
        PhotoDescription = in.readString();
        CreateTime = in.readString();
    }

    @Generated(hash = 1517577149)
    public NewsPhotoModel(Long _id, String RID, String PhotoTitle, String PhotoUrl, String PhotoDescription, String CreateTime) {
        this._id = _id;
        this.RID = RID;
        this.PhotoTitle = PhotoTitle;
        this.PhotoUrl = PhotoUrl;
        this.PhotoDescription = PhotoDescription;
        this.CreateTime = CreateTime;
    }

    @Generated(hash = 838822576)
    public NewsPhotoModel() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(RID);
        dest.writeString(PhotoTitle);
        dest.writeString(PhotoUrl);
        dest.writeString(PhotoDescription);
        dest.writeString(CreateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getRID() {
        return this.RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getPhotoTitle() {
        return this.PhotoTitle;
    }

    public void setPhotoTitle(String PhotoTitle) {
        this.PhotoTitle = PhotoTitle;
    }

    public String getPhotoUrl() {
        return this.PhotoUrl;
    }

    public void setPhotoUrl(String PhotoUrl) {
        this.PhotoUrl = PhotoUrl;
    }

    public String getPhotoDescription() {
        return this.PhotoDescription;
    }

    public void setPhotoDescription(String PhotoDescription) {
        this.PhotoDescription = PhotoDescription;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public static final Creator<NewsPhotoModel> CREATOR = new Creator<NewsPhotoModel>() {
        @Override
        public NewsPhotoModel createFromParcel(Parcel in) {
            return new NewsPhotoModel(in);
        }

        @Override
        public NewsPhotoModel[] newArray(int size) {
            return new NewsPhotoModel[size];
        }
    };


    public static final class ChannelModelConverterAdapter implements PropertyConverter<NewsPhotoModel, String> {
        @Override
        public NewsPhotoModel convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseObject(databaseValue, NewsPhotoModel.class);
        }

        @Override
        public String convertToDatabaseValue(NewsPhotoModel entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }

    public static final class ChannelModelListConverterAdapter implements PropertyConverter<List<NewsPhotoModel>, String> {

        @Override
        public List<NewsPhotoModel> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, NewsPhotoModel.class);
        }

        @Override
        public String convertToDatabaseValue(List<NewsPhotoModel> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }
}
