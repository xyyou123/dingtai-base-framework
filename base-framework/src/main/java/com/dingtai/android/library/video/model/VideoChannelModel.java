package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/11/14
 */
@Entity
public class VideoChannelModel implements Parcelable {

    @Id
    private Long _id;

    private String ID;
    private String CreateTime;
    private String Name;
    private String ImageUrl;
    private String Tag;
    private String Detail;
    private String Number;
    private String FileCount;
    private String NewCount;
    private String AdID;
    private String ParentID;

    public VideoChannelModel() {
    }

    protected VideoChannelModel(Parcel in) {
        ID = in.readString();
        CreateTime = in.readString();
        Name = in.readString();
        ImageUrl = in.readString();
        Tag = in.readString();
        Detail = in.readString();
        Number = in.readString();
        FileCount = in.readString();
        NewCount = in.readString();
        AdID = in.readString();
        ParentID = in.readString();
    }

    @Generated(hash = 865953422)
    public VideoChannelModel(Long _id, String ID, String CreateTime, String Name, String ImageUrl,
            String Tag, String Detail, String Number, String FileCount, String NewCount, String AdID,
            String ParentID) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.Name = Name;
        this.ImageUrl = ImageUrl;
        this.Tag = Tag;
        this.Detail = Detail;
        this.Number = Number;
        this.FileCount = FileCount;
        this.NewCount = NewCount;
        this.AdID = AdID;
        this.ParentID = ParentID;
    }
    

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(CreateTime);
        dest.writeString(Name);
        dest.writeString(ImageUrl);
        dest.writeString(Tag);
        dest.writeString(Detail);
        dest.writeString(Number);
        dest.writeString(FileCount);
        dest.writeString(NewCount);
        dest.writeString(AdID);
        dest.writeString(ParentID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoChannelModel> CREATOR = new Creator<VideoChannelModel>() {
        @Override
        public VideoChannelModel createFromParcel(Parcel in) {
            return new VideoChannelModel(in);
        }

        @Override
        public VideoChannelModel[] newArray(int size) {
            return new VideoChannelModel[size];
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getFileCount() {
        return FileCount;
    }

    public void setFileCount(String fileCount) {
        FileCount = fileCount;
    }

    public String getNewCount() {
        return NewCount;
    }

    public void setNewCount(String newCount) {
        NewCount = newCount;
    }

    public String getAdID() {
        return AdID;
    }

    public void setAdID(String adID) {
        AdID = adID;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
