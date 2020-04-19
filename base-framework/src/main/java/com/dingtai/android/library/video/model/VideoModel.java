package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/11/6
 */
public class VideoModel implements Parcelable {

    private String ID;
    private String Detail;
    private String ID2;
    private String UploadDate;
    private String UserName;
    private String Name;
    private String ImageUrl;
    private String MediaUrl;
    private String DigCount;
    private String PaiCount;
    private String TotalView;
    private String Tag;
//  鉴权
    private String authenticationflag;

    public String getAuthenticationflag() {
        return authenticationflag;
    }

    public void setAuthenticationflag(String authenticationflag) {
        this.authenticationflag = authenticationflag;
    }

    //本地上传时使用
    private String uploadName;//上传名称
    /**
     * 评论数量
     */
    private String CommentNum;

    /**
     * 是否推荐
     */
    private String IsBest;
    /**
     * 是否置顶
     */
    private String UserTop;
    private String FileSize;
    private String ChannelID;
    private String ParentID;
    /**
     * 转换状态
     *  0：未转换
     2：转换中
     3：转换成功
     4：转换失败

     */
    private String IsConverted;
    /**
     * 是否审核 Flase：未审核 True：已审核
     */
    private String IsApproved;
    private String ClickNum;
    private String GoodPoint;
    private String IsExsitPoint;

    public VideoModel() {
    }

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

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
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

    public String getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(String commentNum) {
        CommentNum = commentNum;
    }

    public String getIsBest() {
        return IsBest;
    }

    public void setIsBest(String isBest) {
        IsBest = isBest;
    }

    public String getUserTop() {
        return UserTop;
    }

    public void setUserTop(String userTop) {
        UserTop = userTop;
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

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getIsConverted() {
        return IsConverted;
    }

    public void setIsConverted(String isConverted) {
        IsConverted = isConverted;
    }

    public String getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(String isApproved) {
        IsApproved = isApproved;
    }

    public String getClickNum() {
        return ClickNum;
    }

    public void setClickNum(String clickNum) {
        ClickNum = clickNum;
    }

    public String getGoodPoint() {
        return GoodPoint;
    }

    public void setGoodPoint(String goodPoint) {
        GoodPoint = goodPoint;
    }

    public String getIsExsitPoint() {
        return IsExsitPoint;
    }

    public void setIsExsitPoint(String isExsitPoint) {
        IsExsitPoint = isExsitPoint;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoModel model = (VideoModel) o;
        return ID != null && ID.equals(model.ID);
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.Detail);
        dest.writeString(this.ID2);
        dest.writeString(this.UploadDate);
        dest.writeString(this.UserName);
        dest.writeString(this.Name);
        dest.writeString(this.ImageUrl);
        dest.writeString(this.MediaUrl);
        dest.writeString(this.DigCount);
        dest.writeString(this.PaiCount);
        dest.writeString(this.TotalView);
        dest.writeString(this.Tag);
        dest.writeString(this.authenticationflag);
        dest.writeString(this.uploadName);
        dest.writeString(this.CommentNum);
        dest.writeString(this.IsBest);
        dest.writeString(this.UserTop);
        dest.writeString(this.FileSize);
        dest.writeString(this.ChannelID);
        dest.writeString(this.ParentID);
        dest.writeString(this.IsConverted);
        dest.writeString(this.IsApproved);
        dest.writeString(this.ClickNum);
        dest.writeString(this.GoodPoint);
        dest.writeString(this.IsExsitPoint);
    }

    protected VideoModel(Parcel in) {
        this.ID = in.readString();
        this.Detail = in.readString();
        this.ID2 = in.readString();
        this.UploadDate = in.readString();
        this.UserName = in.readString();
        this.Name = in.readString();
        this.ImageUrl = in.readString();
        this.MediaUrl = in.readString();
        this.DigCount = in.readString();
        this.PaiCount = in.readString();
        this.TotalView = in.readString();
        this.Tag = in.readString();
        this.authenticationflag = in.readString();
        this.uploadName = in.readString();
        this.CommentNum = in.readString();
        this.IsBest = in.readString();
        this.UserTop = in.readString();
        this.FileSize = in.readString();
        this.ChannelID = in.readString();
        this.ParentID = in.readString();
        this.IsConverted = in.readString();
        this.IsApproved = in.readString();
        this.ClickNum = in.readString();
        this.GoodPoint = in.readString();
        this.IsExsitPoint = in.readString();
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel source) {
            return new VideoModel(source);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };
}
