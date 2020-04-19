package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * author:lnr
 * date:2018/9/12
 */
@Entity
public class NewsCollectModel implements Collect, Parcelable {

    @Id
    private Long _id;

    /**
     * 收藏ID
     */
    @Unique
    private String ID;



    /**
     * 收藏ID
     */
    private String CollectID;

    /**
     * 图集图片
     */
    private String UploadPicNames;
    /**
     * 栏目ID
     */
    private String ChID;
    /**
     * 更新时间
     */
    private String UpdateTime;
    /**
     * 是否开启评论
     */
    private String IsComment;
    /**
     * 新闻GUID
     */
    private String ResourceGUID;
    /**
     * 标题
     */
    private String Title;
    /**
     * 排序
     */
    private String ShowOrder;
    /**
     * 摘要
     */
    private String Summary;
    /**
     * 浏览数
     */
    private String ReadNo;
    /**
     * 缩略图
     */
    private String SmallPicUrl;
    /**
     * 审核时间
     */
    private String AuditTime;
    /**
     * 新闻类型
     */
    private String ResourceType;
    /**
     * 图集对应ID
     */
    private String RPID;
    /**
     * 创建时间
     */
    private String CreateTime;

    /**
     * 纬度
     */
    private String Latitude;
    /**
     * 经度
     */
    private String Longitude;
    /**
     * 新闻角标
     * 2015年3月21号 李亚军 添加ResourceFlag
     */
    private String ResourceFlag;


    @Generated(hash = 496591524)
    public NewsCollectModel() {
    }

    public String getCollectID() {
        return CollectID;
    }

    public void setCollectID(String collectID) {
        CollectID = collectID;
    }
    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getUploadPicNames() {
        return this.UploadPicNames;
    }

    public void setUploadPicNames(String UploadPicNames) {
        this.UploadPicNames = UploadPicNames;
    }

    public String getChID() {
        return this.ChID;
    }

    public void setChID(String ChID) {
        this.ChID = ChID;
    }

    public String getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getIsComment() {
        return this.IsComment;
    }

    public void setIsComment(String IsComment) {
        this.IsComment = IsComment;
    }

    public String getResourceGUID() {
        return this.ResourceGUID;
    }

    public void setResourceGUID(String ResourceGUID) {
        this.ResourceGUID = ResourceGUID;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getShowOrder() {
        return this.ShowOrder;
    }

    public void setShowOrder(String ShowOrder) {
        this.ShowOrder = ShowOrder;
    }

    public String getSummary() {
        return this.Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getReadNo() {
        return this.ReadNo;
    }

    public void setReadNo(String ReadNo) {
        this.ReadNo = ReadNo;
    }

    public String getSmallPicUrl() {
        return this.SmallPicUrl;
    }

    public void setSmallPicUrl(String SmallPicUrl) {
        this.SmallPicUrl = SmallPicUrl;
    }

    public String getAuditTime() {
        return this.AuditTime;
    }

    public void setAuditTime(String AuditTime) {
        this.AuditTime = AuditTime;
    }

    public String getResourceType() {
        return this.ResourceType;
    }

    public void setResourceType(String ResourceType) {
        this.ResourceType = ResourceType;
    }

    public String getRPID() {
        return this.RPID;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getResourceFlag() {
        return this.ResourceFlag;
    }

    public void setResourceFlag(String ResourceFlag) {
        this.ResourceFlag = ResourceFlag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.ID);
        dest.writeString(this.CollectID);
        dest.writeString(this.UploadPicNames);
        dest.writeString(this.ChID);
        dest.writeString(this.UpdateTime);
        dest.writeString(this.IsComment);
        dest.writeString(this.ResourceGUID);
        dest.writeString(this.Title);
        dest.writeString(this.ShowOrder);
        dest.writeString(this.Summary);
        dest.writeString(this.ReadNo);
        dest.writeString(this.SmallPicUrl);
        dest.writeString(this.AuditTime);
        dest.writeString(this.ResourceType);
        dest.writeString(this.RPID);
        dest.writeString(this.CreateTime);
        dest.writeString(this.Latitude);
        dest.writeString(this.Longitude);
        dest.writeString(this.ResourceFlag);
    }

    protected NewsCollectModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.CollectID = in.readString();
        this.UploadPicNames = in.readString();
        this.ChID = in.readString();
        this.UpdateTime = in.readString();
        this.IsComment = in.readString();
        this.ResourceGUID = in.readString();
        this.Title = in.readString();
        this.ShowOrder = in.readString();
        this.Summary = in.readString();
        this.ReadNo = in.readString();
        this.SmallPicUrl = in.readString();
        this.AuditTime = in.readString();
        this.ResourceType = in.readString();
        this.RPID = in.readString();
        this.CreateTime = in.readString();
        this.Latitude = in.readString();
        this.Longitude = in.readString();
        this.ResourceFlag = in.readString();
    }

    @Generated(hash = 914392647)
    public NewsCollectModel(Long _id, String ID, String CollectID, String UploadPicNames,
            String ChID, String UpdateTime, String IsComment, String ResourceGUID,
            String Title, String ShowOrder, String Summary, String ReadNo, String SmallPicUrl,
            String AuditTime, String ResourceType, String RPID, String CreateTime,
            String Latitude, String Longitude, String ResourceFlag) {
        this._id = _id;
        this.ID = ID;
        this.CollectID = CollectID;
        this.UploadPicNames = UploadPicNames;
        this.ChID = ChID;
        this.UpdateTime = UpdateTime;
        this.IsComment = IsComment;
        this.ResourceGUID = ResourceGUID;
        this.Title = Title;
        this.ShowOrder = ShowOrder;
        this.Summary = Summary;
        this.ReadNo = ReadNo;
        this.SmallPicUrl = SmallPicUrl;
        this.AuditTime = AuditTime;
        this.ResourceType = ResourceType;
        this.RPID = RPID;
        this.CreateTime = CreateTime;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.ResourceFlag = ResourceFlag;
    }

    public static final Creator<NewsCollectModel> CREATOR = new Creator<NewsCollectModel>() {
        @Override
        public NewsCollectModel createFromParcel(Parcel source) {
            return new NewsCollectModel(source);
        }

        @Override
        public NewsCollectModel[] newArray(int size) {
            return new NewsCollectModel[size];
        }
    };
}
