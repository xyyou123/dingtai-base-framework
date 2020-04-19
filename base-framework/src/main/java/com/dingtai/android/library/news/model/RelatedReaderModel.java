package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/9/14
 * 相关阅读
 */
@Entity
public class RelatedReaderModel implements Parcelable {

    @Id
    private Long _id;

    private String RelatedID;

    private String RelatedTitle;

    private String RelatedResourceGUID;

    private String RelatedCreateTime;
    //新闻GUID  

    private String ResourceGUID;
    //图集ID

    private String RelatedRPID;
    //新闻栏目

    private String RelatedChID ;
    //新闻类型

    private String RelatedResourceType;
    //新闻URL

    private String RelatedResourceUrl;
    //图片URL

    private String 	RelatedSmallPicUrl;

    private String RelatedIsNewTopice;

    public RelatedReaderModel() {
    }

    protected RelatedReaderModel(Parcel in) {
        RelatedID = in.readString();
        RelatedTitle = in.readString();
        RelatedResourceGUID = in.readString();
        RelatedCreateTime = in.readString();
        ResourceGUID = in.readString();
        RelatedRPID = in.readString();
        RelatedChID = in.readString();
        RelatedResourceType = in.readString();
        RelatedResourceUrl = in.readString();
        RelatedSmallPicUrl = in.readString();
        RelatedIsNewTopice = in.readString();
    }

    @Generated(hash = 1806243007)
    public RelatedReaderModel(Long _id, String RelatedID, String RelatedTitle,
            String RelatedResourceGUID, String RelatedCreateTime, String ResourceGUID,
            String RelatedRPID, String RelatedChID, String RelatedResourceType,
            String RelatedResourceUrl, String RelatedSmallPicUrl, String RelatedIsNewTopice) {
        this._id = _id;
        this.RelatedID = RelatedID;
        this.RelatedTitle = RelatedTitle;
        this.RelatedResourceGUID = RelatedResourceGUID;
        this.RelatedCreateTime = RelatedCreateTime;
        this.ResourceGUID = ResourceGUID;
        this.RelatedRPID = RelatedRPID;
        this.RelatedChID = RelatedChID;
        this.RelatedResourceType = RelatedResourceType;
        this.RelatedResourceUrl = RelatedResourceUrl;
        this.RelatedSmallPicUrl = RelatedSmallPicUrl;
        this.RelatedIsNewTopice = RelatedIsNewTopice;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(RelatedID);
        dest.writeString(RelatedTitle);
        dest.writeString(RelatedResourceGUID);
        dest.writeString(RelatedCreateTime);
        dest.writeString(ResourceGUID);
        dest.writeString(RelatedRPID);
        dest.writeString(RelatedChID);
        dest.writeString(RelatedResourceType);
        dest.writeString(RelatedResourceUrl);
        dest.writeString(RelatedSmallPicUrl);
        dest.writeString(RelatedIsNewTopice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RelatedReaderModel> CREATOR = new Creator<RelatedReaderModel>() {
        @Override
        public RelatedReaderModel createFromParcel(Parcel in) {
            return new RelatedReaderModel(in);
        }

        @Override
        public RelatedReaderModel[] newArray(int size) {
            return new RelatedReaderModel[size];
        }
    };

    public String getRelatedID() {
        return RelatedID;
    }

    public void setRelatedID(String relatedID) {
        RelatedID = relatedID;
    }

    public String getRelatedTitle() {
        return RelatedTitle;
    }

    public void setRelatedTitle(String relatedTitle) {
        RelatedTitle = relatedTitle;
    }

    public String getRelatedResourceGUID() {
        return RelatedResourceGUID;
    }

    public void setRelatedResourceGUID(String relatedResourceGUID) {
        RelatedResourceGUID = relatedResourceGUID;
    }

    public String getRelatedCreateTime() {
        return RelatedCreateTime;
    }

    public void setRelatedCreateTime(String relatedCreateTime) {
        RelatedCreateTime = relatedCreateTime;
    }

    public String getResourceGUID() {
        return ResourceGUID;
    }

    public void setResourceGUID(String resourceGUID) {
        ResourceGUID = resourceGUID;
    }

    public String getRelatedRPID() {
        return RelatedRPID;
    }

    public void setRelatedRPID(String relatedRPID) {
        RelatedRPID = relatedRPID;
    }

    public String getRelatedChID() {
        return RelatedChID;
    }

    public void setRelatedChID(String relatedChID) {
        RelatedChID = relatedChID;
    }

    public String getRelatedResourceType() {
        return RelatedResourceType;
    }

    public void setRelatedResourceType(String relatedResourceType) {
        RelatedResourceType = relatedResourceType;
    }

    public String getRelatedResourceUrl() {
        return RelatedResourceUrl;
    }

    public void setRelatedResourceUrl(String relatedResourceUrl) {
        RelatedResourceUrl = relatedResourceUrl;
    }

    public String getRelatedSmallPicUrl() {
        return RelatedSmallPicUrl;
    }

    public void setRelatedSmallPicUrl(String relatedSmallPicUrl) {
        RelatedSmallPicUrl = relatedSmallPicUrl;
    }

    public String getRelatedIsNewTopice() {
        return RelatedIsNewTopice;
    }

    public void setRelatedIsNewTopice(String relatedIsNewTopice) {
        RelatedIsNewTopice = relatedIsNewTopice;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
