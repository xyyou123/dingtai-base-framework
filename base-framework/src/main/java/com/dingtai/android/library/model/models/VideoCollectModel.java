package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/9/12
 * 收藏
 */
public class VideoCollectModel implements Collect, Parcelable {

    private String ID;
    private String Detail;
    private String ID2;
    private String UploadDate;
    private String UserName;
    private String Name;
    private String ImageUrl;
    private String DigCount;
    private String PaiCount;
    private String TotalView;
    private String Tag;
    private String FileSize;
    private String ChannelID;
    private String MediaID;
    private String MediaChannelName;

    public VideoCollectModel() {
    }

    protected VideoCollectModel(Parcel in) {
        ID = in.readString();
        Detail = in.readString();
        ID2 = in.readString();
        UploadDate = in.readString();
        UserName = in.readString();
        Name = in.readString();
        ImageUrl = in.readString();
        DigCount = in.readString();
        PaiCount = in.readString();
        TotalView = in.readString();
        Tag = in.readString();
        FileSize = in.readString();
        ChannelID = in.readString();
        MediaID = in.readString();
        MediaChannelName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Detail);
        dest.writeString(ID2);
        dest.writeString(UploadDate);
        dest.writeString(UserName);
        dest.writeString(Name);
        dest.writeString(ImageUrl);
        dest.writeString(DigCount);
        dest.writeString(PaiCount);
        dest.writeString(TotalView);
        dest.writeString(Tag);
        dest.writeString(FileSize);
        dest.writeString(ChannelID);
        dest.writeString(MediaID);
        dest.writeString(MediaChannelName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoCollectModel> CREATOR = new Creator<VideoCollectModel>() {
        @Override
        public VideoCollectModel createFromParcel(Parcel in) {
            return new VideoCollectModel(in);
        }

        @Override
        public VideoCollectModel[] newArray(int size) {
            return new VideoCollectModel[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getID2() {
        return ID2;
    }

    public void setID2(String ID2) {
        this.ID2 = ID2;
    }

    public String getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(String uploadDate) {
        UploadDate = uploadDate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getDigCount() {
        return DigCount;
    }

    public void setDigCount(String digCount) {
        DigCount = digCount;
    }

    public String getPaiCount() {
        return PaiCount;
    }

    public void setPaiCount(String paiCount) {
        PaiCount = paiCount;
    }

    public String getTotalView() {
        return TotalView;
    }

    public void setTotalView(String totalView) {
        TotalView = totalView;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getMediaID() {
        return MediaID;
    }

    public void setMediaID(String mediaID) {
        MediaID = mediaID;
    }

    public String getMediaChannelName() {
        return MediaChannelName;
    }

    public void setMediaChannelName(String mediaChannelName) {
        MediaChannelName = mediaChannelName;
    }
}
