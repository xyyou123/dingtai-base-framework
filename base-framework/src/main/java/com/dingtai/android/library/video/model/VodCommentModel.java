package com.dingtai.android.library.video.model;

import java.util.List;

/**
 * author:lnr
 * date:2019/1/9
 * 点播评论
 */
public class VodCommentModel {

    private String ID;
    private String CreateTime;
    private String VodID;
    private String CommentContent;
    private String CommentTime;
    private String UserGUID;
    private String UserIP;
    private String GetGoodPoint;
    private String StID;
    private String Status;
    private String ReMark;
    private String UserNickName;
    private String CommentAuditStatus;
    private String UserLOGO;
    private boolean isShow;
    private String UserName;
    private boolean isGoodPoint;
    private List<VodReplayModel> CommentSub;

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

    public String getVodID() {
        return VodID;
    }

    public void setVodID(String vodID) {
        VodID = vodID;
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

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getCommentAuditStatus() {
        return CommentAuditStatus;
    }

    public void setCommentAuditStatus(String commentAuditStatus) {
        CommentAuditStatus = commentAuditStatus;
    }

    public String getUserLOGO() {
        return UserLOGO;
    }

    public void setUserLOGO(String userLOGO) {
        UserLOGO = userLOGO;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public boolean isGoodPoint() {
        return isGoodPoint;
    }

    public void setGoodPoint(boolean goodPoint) {
        isGoodPoint = goodPoint;
    }

    public List<VodReplayModel> getCommentSub() {
        return CommentSub;
    }

    public void setCommentSub(List<VodReplayModel> commentSub) {
        CommentSub = commentSub;
    }
}
