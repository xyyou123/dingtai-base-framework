package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/11/6
 */
public class VideoDetailsModel implements Parcelable {

    private String ID;
    private String PaiCount;
    private String DigCount;
    private String UploadType;
    private String Tag;
    private String FlvUrl;
    private String Detail;
    private String ID2;
    private String UploadDate;
    private String UserName;
    private String Name;
    private String ImageUrl;
    private String MediaUrl;
    private String ShareUrl;
    private String FileSize;
    private String fenxiang;

    private String ChannelName;

    public VideoDetailsModel() {
    }

    protected VideoDetailsModel(Parcel in) {
        ID = in.readString();
        PaiCount = in.readString();
        DigCount = in.readString();
        UploadType = in.readString();
        Tag = in.readString();
        FlvUrl = in.readString();
        Detail = in.readString();
        ID2 = in.readString();
        UploadDate = in.readString();
        UserName = in.readString();
        Name = in.readString();
        ImageUrl = in.readString();
        MediaUrl = in.readString();
        ShareUrl = in.readString();
        FileSize = in.readString();
        fenxiang = in.readString();
        ChannelName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(PaiCount);
        dest.writeString(DigCount);
        dest.writeString(UploadType);
        dest.writeString(Tag);
        dest.writeString(FlvUrl);
        dest.writeString(Detail);
        dest.writeString(ID2);
        dest.writeString(UploadDate);
        dest.writeString(UserName);
        dest.writeString(Name);
        dest.writeString(ImageUrl);
        dest.writeString(MediaUrl);
        dest.writeString(ShareUrl);
        dest.writeString(FileSize);
        dest.writeString(fenxiang);
        dest.writeString(ChannelName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoDetailsModel> CREATOR = new Creator<VideoDetailsModel>() {
        @Override
        public VideoDetailsModel createFromParcel(Parcel in) {
            return new VideoDetailsModel(in);
        }

        @Override
        public VideoDetailsModel[] newArray(int size) {
            return new VideoDetailsModel[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPaiCount() {
        return PaiCount;
    }

    public void setPaiCount(String paiCount) {
        PaiCount = paiCount;
    }

    public String getDigCount() {
        return DigCount;
    }

    public void setDigCount(String digCount) {
        DigCount = digCount;
    }

    public String getUploadType() {
        return UploadType;
    }

    public void setUploadType(String uploadType) {
        UploadType = uploadType;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getFlvUrl() {
        return FlvUrl;
    }

    public void setFlvUrl(String flvUrl) {
        FlvUrl = flvUrl;
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

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
    }

    public String getShareUrl() {
        return ShareUrl;
    }

    public void setShareUrl(String shareUrl) {
        ShareUrl = shareUrl;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public String getFenxiang() {
        return fenxiang;
    }

    public void setFenxiang(String fenxiang) {
        this.fenxiang = fenxiang;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }
}
