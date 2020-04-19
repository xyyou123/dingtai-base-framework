package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/9/3
 * 直播评论
 */
public class LiveCommentModel implements Parcelable {

    private String ID;
    private String CreateTime;
    private String LID;
    private String CommentContent;
    private String CommentTime;
    private String UserGUID;
    private String UserLogo;
    private String UserLOGO;
    private String UserNickName;
    private String UserName;
    private String UserIP;
    private String GetGoodPoint;
    private String StID;
    private String Status;
    private String ReMark;
    private String CommentAuditStatus;
    private String SubTime;
    private String SubContent;
    private String SubUid;
    private String SubUserLOGO;
    private String SubUserNickName;
    private String SubUserName;
    private String SubUIP;
    private String SubPoint;
    private String SubCreateTime;
    private String Comments;

    public LiveCommentModel() {
    }

    protected LiveCommentModel(Parcel in) {
        ID = in.readString();
        CreateTime = in.readString();
        LID = in.readString();
        CommentContent = in.readString();
        CommentTime = in.readString();
        UserGUID = in.readString();
        UserLogo = in.readString();
        UserLOGO = in.readString();
        UserNickName = in.readString();
        UserName = in.readString();
        UserIP = in.readString();
        GetGoodPoint = in.readString();
        StID = in.readString();
        Status = in.readString();
        ReMark = in.readString();
        CommentAuditStatus = in.readString();
        SubTime = in.readString();
        SubContent = in.readString();
        SubUid = in.readString();
        SubUserLOGO = in.readString();
        SubUserNickName = in.readString();
        SubUserName = in.readString();
        SubUIP = in.readString();
        SubPoint = in.readString();
        SubCreateTime = in.readString();
        Comments = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(CreateTime);
        dest.writeString(LID);
        dest.writeString(CommentContent);
        dest.writeString(CommentTime);
        dest.writeString(UserGUID);
        dest.writeString(UserLogo);
        dest.writeString(UserLOGO);
        dest.writeString(UserNickName);
        dest.writeString(UserName);
        dest.writeString(UserIP);
        dest.writeString(GetGoodPoint);
        dest.writeString(StID);
        dest.writeString(Status);
        dest.writeString(ReMark);
        dest.writeString(CommentAuditStatus);
        dest.writeString(SubTime);
        dest.writeString(SubContent);
        dest.writeString(SubUid);
        dest.writeString(SubUserLOGO);
        dest.writeString(SubUserNickName);
        dest.writeString(SubUserName);
        dest.writeString(SubUIP);
        dest.writeString(SubPoint);
        dest.writeString(SubCreateTime);
        dest.writeString(Comments);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LiveCommentModel> CREATOR = new Creator<LiveCommentModel>() {
        @Override
        public LiveCommentModel createFromParcel(Parcel in) {
            return new LiveCommentModel(in);
        }

        @Override
        public LiveCommentModel[] newArray(int size) {
            return new LiveCommentModel[size];
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

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
        this.LID = LID;
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

    public String getUserLogo() {
        return UserLogo;
    }

    public void setUserLogo(String userLogo) {
        UserLogo = userLogo;
    }

    public String getUserLOGO() {
        return UserLOGO == null || UserLOGO.length() == 0 ? UserLogo : UserLOGO;
    }

    public void setUserLOGO(String userLOGO) {
        UserLOGO = userLOGO;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getUserName() {
        return UserName == null || UserName.length() == 0 ? UserNickName : UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getStID() {
        return StID;
    }

    public void setStID(String stID) {
        StID = stID;
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

    public String getCommentAuditStatus() {
        return CommentAuditStatus;
    }

    public void setCommentAuditStatus(String commentAuditStatus) {
        CommentAuditStatus = commentAuditStatus;
    }

    public String getSubTime() {
        return SubTime;
    }

    public void setSubTime(String subTime) {
        SubTime = subTime;
    }

    public String getSubContent() {
        return SubContent;
    }

    public void setSubContent(String subContent) {
        SubContent = subContent;
    }

    public String getSubUid() {
        return SubUid;
    }

    public void setSubUid(String subUid) {
        SubUid = subUid;
    }

    public String getSubUserLOGO() {
        return SubUserLOGO;
    }

    public void setSubUserLOGO(String subUserLOGO) {
        SubUserLOGO = subUserLOGO;
    }

    public String getSubUserNickName() {
        return SubUserNickName;
    }

    public void setSubUserNickName(String subUserNickName) {
        SubUserNickName = subUserNickName;
    }

    public String getSubUserName() {
        return SubUserName == null || SubUserName.length() == 0 ? SubUserNickName : SubUserName;
    }

    public void setSubUserName(String subUserName) {
        SubUserName = subUserName;
    }

    public String getSubUIP() {
        return SubUIP;
    }

    public void setSubUIP(String subUIP) {
        SubUIP = subUIP;
    }

    public String getSubPoint() {
        return SubPoint;
    }

    public void setSubPoint(String subPoint) {
        SubPoint = subPoint;
    }

    public String getSubCreateTime() {
        return SubCreateTime;
    }

    public void setSubCreateTime(String subCreateTime) {
        SubCreateTime = subCreateTime;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiveCommentModel that = (LiveCommentModel) o;
        return ID != null && ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }
}
