package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/29
 * 图文
 */
public class LiveImageTextModel implements Parcelable {

    private String ID;
    private String CreateTime;
    private String NewsLiveTitle;
    private String NewsLiveContent;
    private String PicUrl;
    private String MediaID;
    private String AudioID;
    private String LinkUrl;
    private String SendStatus;
    private String AdminID;
    private String Status;
    private String ReMark;
    private String NewsLiveEventID;
    private String NewsLiveType;
    private String LimitTime;
    private String IsTop;
    private String EventID;
    private String CommentContent;
    private String CommentTime;
    private String UserGUID;
    private String UserIP;
    private String GetGoodPoint;
    private String CommentAuditStatus;
    private String SBang;
    private String NewsSubTime;
    private String UserLOGO;
    private String UserName;
    private String UserNickName;
    private String HostName;
    private String HostLogo;
    private String MediaURL;
    private String MediaLogo;
    private String GoodUsers;
    private boolean GoodPoint;

    private String authenticationflag;

    public String getAuthenticationflag() {
        return authenticationflag;
    }

    public void setAuthenticationflag(String authenticationflag) {
        this.authenticationflag = authenticationflag;
    }

    public boolean isGoodPoint() {
        return GoodPoint;
    }

    public void setGoodPoint(boolean goodPoint) {
        GoodPoint = goodPoint;
    }

    private List<LiveCommentSubModel> CommentList;
    /**
     * UserLogo : 
     */

    private String UserLogo;

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

    public String getNewsLiveTitle() {
        return NewsLiveTitle;
    }

    public void setNewsLiveTitle(String newsLiveTitle) {
        NewsLiveTitle = newsLiveTitle;
    }

    public String getNewsLiveContent() {
        return NewsLiveContent;
    }

    public void setNewsLiveContent(String newsLiveContent) {
        NewsLiveContent = newsLiveContent;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaID() {
        return MediaID;
    }

    public void setMediaID(String mediaID) {
        MediaID = mediaID;
    }

    public String getAudioID() {
        return AudioID;
    }

    public void setAudioID(String audioID) {
        AudioID = audioID;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }

    public String getSendStatus() {
        return SendStatus;
    }

    public void setSendStatus(String sendStatus) {
        SendStatus = sendStatus;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getReMark() {
        return ReMark;
    }

    public void setReMark(String reMark) {
        ReMark = reMark;
    }

    public String getNewsLiveEventID() {
        return NewsLiveEventID;
    }

    public void setNewsLiveEventID(String newsLiveEventID) {
        NewsLiveEventID = newsLiveEventID;
    }

    public String getNewsLiveType() {
        return NewsLiveType;
    }

    public void setNewsLiveType(String newsLiveType) {
        NewsLiveType = newsLiveType;
    }

    public String getLimitTime() {
        return LimitTime;
    }

    public void setLimitTime(String limitTime) {
        LimitTime = limitTime;
    }

    public String getIsTop() {
        return IsTop;
    }

    public void setIsTop(String isTop) {
        IsTop = isTop;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getUserGUID() {
        return UserGUID;
    }

    public void setUserGUID(String userGUID) {
        UserGUID = userGUID;
    }

    public String getUserIP() {
        return UserIP;
    }

    public void setUserIP(String userIP) {
        UserIP = userIP;
    }

    public String getGetGoodPoint() {
        return GetGoodPoint;
    }

    public void setGetGoodPoint(String getGoodPoint) {
        GetGoodPoint = getGoodPoint;
    }

    public String getCommentAuditStatus() {
        return CommentAuditStatus;
    }

    public void setCommentAuditStatus(String commentAuditStatus) {
        CommentAuditStatus = commentAuditStatus;
    }

    public String getSBang() {
        return SBang;
    }

    public void setSBang(String SBang) {
        this.SBang = SBang;
    }

    public String getNewsSubTime() {
        return NewsSubTime;
    }

    public void setNewsSubTime(String newsSubTime) {
        NewsSubTime = newsSubTime;
    }

    public String getUserLOGO() {
        return UserLOGO;
    }

    public void setUserLOGO(String userLOGO) {
        UserLOGO = userLOGO;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public String getHostLogo() {
        return HostLogo;
    }

    public void setHostLogo(String hostLogo) {
        HostLogo = hostLogo;
    }

    public String getMediaURL() {
        return MediaURL;
    }

    public void setMediaURL(String mediaURL) {
        MediaURL = mediaURL;
    }

    public String getMediaLogo() {
        return MediaLogo;
    }

    public void setMediaLogo(String mediaLogo) {
        MediaLogo = mediaLogo;
    }

    public String getGoodUsers() {
        return GoodUsers;
    }

    public void setGoodUsers(String goodUsers) {
        GoodUsers = goodUsers;
    }

    public List<LiveCommentSubModel> getCommentList() {
        return CommentList;
    }

    public void setCommentList(List<LiveCommentSubModel> commentList) {
        CommentList = commentList;
    }

    public String getUserLogo() {
        return UserLogo;
    }

    public void setUserLogo(String UserLogo) {
        this.UserLogo = UserLogo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.CreateTime);
        dest.writeString(this.NewsLiveTitle);
        dest.writeString(this.NewsLiveContent);
        dest.writeString(this.PicUrl);
        dest.writeString(this.MediaID);
        dest.writeString(this.AudioID);
        dest.writeString(this.LinkUrl);
        dest.writeString(this.SendStatus);
        dest.writeString(this.AdminID);
        dest.writeString(this.Status);
        dest.writeString(this.ReMark);
        dest.writeString(this.NewsLiveEventID);
        dest.writeString(this.NewsLiveType);
        dest.writeString(this.LimitTime);
        dest.writeString(this.IsTop);
        dest.writeString(this.EventID);
        dest.writeString(this.CommentContent);
        dest.writeString(this.CommentTime);
        dest.writeString(this.UserGUID);
        dest.writeString(this.UserIP);
        dest.writeString(this.GetGoodPoint);
        dest.writeString(this.CommentAuditStatus);
        dest.writeString(this.SBang);
        dest.writeString(this.NewsSubTime);
        dest.writeString(this.UserLOGO);
        dest.writeString(this.UserName);
        dest.writeString(this.UserNickName);
        dest.writeString(this.HostName);
        dest.writeString(this.HostLogo);
        dest.writeString(this.MediaURL);
        dest.writeString(this.MediaLogo);
        dest.writeString(this.GoodUsers);
        dest.writeByte(this.GoodPoint ? (byte) 1 : (byte) 0);
        dest.writeString(this.authenticationflag);
        dest.writeList(this.CommentList);
        dest.writeString(this.UserLogo);
    }

    public LiveImageTextModel() {
    }

    protected LiveImageTextModel(Parcel in) {
        this.ID = in.readString();
        this.CreateTime = in.readString();
        this.NewsLiveTitle = in.readString();
        this.NewsLiveContent = in.readString();
        this.PicUrl = in.readString();
        this.MediaID = in.readString();
        this.AudioID = in.readString();
        this.LinkUrl = in.readString();
        this.SendStatus = in.readString();
        this.AdminID = in.readString();
        this.Status = in.readString();
        this.ReMark = in.readString();
        this.NewsLiveEventID = in.readString();
        this.NewsLiveType = in.readString();
        this.LimitTime = in.readString();
        this.IsTop = in.readString();
        this.EventID = in.readString();
        this.CommentContent = in.readString();
        this.CommentTime = in.readString();
        this.UserGUID = in.readString();
        this.UserIP = in.readString();
        this.GetGoodPoint = in.readString();
        this.CommentAuditStatus = in.readString();
        this.SBang = in.readString();
        this.NewsSubTime = in.readString();
        this.UserLOGO = in.readString();
        this.UserName = in.readString();
        this.UserNickName = in.readString();
        this.HostName = in.readString();
        this.HostLogo = in.readString();
        this.MediaURL = in.readString();
        this.MediaLogo = in.readString();
        this.GoodUsers = in.readString();
        this.GoodPoint = in.readByte() != 0;
        this.authenticationflag = in.readString();
        this.CommentList = new ArrayList<LiveCommentSubModel>();
        in.readList(this.CommentList, LiveCommentSubModel.class.getClassLoader());
        this.UserLogo = in.readString();
    }

    public static final Creator<LiveImageTextModel> CREATOR = new Creator<LiveImageTextModel>() {
        @Override
        public LiveImageTextModel createFromParcel(Parcel source) {
            return new LiveImageTextModel(source);
        }

        @Override
        public LiveImageTextModel[] newArray(int size) {
            return new LiveImageTextModel[size];
        }
    };
}
