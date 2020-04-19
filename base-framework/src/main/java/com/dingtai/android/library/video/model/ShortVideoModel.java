package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2019/3/11 0011.
 */

public class ShortVideoModel implements Parcelable{
  
    private String ID;
    private String CreateTime;
    private String VideoName;
    private String UserGUID;
    private String ShowNum;
    private String ImgUrl;
    private String VideoUrl;
    private String GoodPoint;
    private String ShareNum;
    private String VideoIntro;
    private String UserName;
    private String UserIcon;
    private String FocusNum;
    private String CommentNum;

    protected ShortVideoModel(Parcel in) {
        ID = in.readString();
        CreateTime = in.readString();
        VideoName = in.readString();
        UserGUID = in.readString();
        ShowNum = in.readString();
        ImgUrl = in.readString();
        VideoUrl = in.readString();
        GoodPoint = in.readString();
        ShareNum = in.readString();
        VideoIntro = in.readString();
        UserName = in.readString();
        UserIcon = in.readString();
        FocusNum = in.readString();
        CommentNum = in.readString();
    }

    public ShortVideoModel() {
    }

    public static final Creator<ShortVideoModel> CREATOR = new Creator<ShortVideoModel>() {
        @Override
        public ShortVideoModel createFromParcel(Parcel in) {
            return new ShortVideoModel(in);
        }

        @Override
        public ShortVideoModel[] newArray(int size) {
            return new ShortVideoModel[size];
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

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String VideoName) {
        this.VideoName = VideoName;
    }

    public String getUserGUID() {
        return UserGUID;
    }

    public void setUserGUID(String UserGUID) {
        this.UserGUID = UserGUID;
    }

    public String getShowNum() {
        return ShowNum;
    }

    public void setShowNum(String ShowNum) {
        this.ShowNum = ShowNum;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String VideoUrl) {
        this.VideoUrl = VideoUrl;
    }

    public String getGoodPoint() {
        return GoodPoint;
    }

    public void setGoodPoint(String GoodPoint) {
        this.GoodPoint = GoodPoint;
    }

    public String getShareNum() {
        return ShareNum;
    }

    public void setShareNum(String ShareNum) {
        this.ShareNum = ShareNum;
    }

    public String getVideoIntro() {
        return VideoIntro;
    }

    public void setVideoIntro(String VideoIntro) {
        this.VideoIntro = VideoIntro;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public String getFocusNum() {
        return FocusNum;
    }

    public void setFocusNum(String FocusNum) {
        this.FocusNum = FocusNum;
    }

    public String getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(String CommentNum) {
        this.CommentNum = CommentNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(CreateTime);
        parcel.writeString(VideoName);
        parcel.writeString(UserGUID);
        parcel.writeString(ShowNum);
        parcel.writeString(ImgUrl);
        parcel.writeString(VideoUrl);
        parcel.writeString(GoodPoint);
        parcel.writeString(ShareNum);
        parcel.writeString(VideoIntro);
        parcel.writeString(UserName);
        parcel.writeString(UserIcon);
        parcel.writeString(FocusNum);
        parcel.writeString(CommentNum);
    }
}
