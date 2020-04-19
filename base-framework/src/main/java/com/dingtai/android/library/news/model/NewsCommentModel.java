package com.dingtai.android.library.news.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/26
 */
public class NewsCommentModel {

    private String ID; 	//评论ID号
    private String UserName; //用户名
    private String UserGUID; //用户名
    private String UserIcon; //头像
    private String UserNickName;//用户昵称
    private String CommentTime;		//评论时间
    private String CommentContent;    //评论内容
    private String GetGoodPoint;    //点赞数量
    private String SubCount;    //字评论数量

    private boolean isGoodPoint;

    private List<NewsCommentModel> subList;
    private JSONObject SubCommentList;
    /**
     * 展开全部子评论
     */
    private boolean expandAllSubList;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserGUID() {
        return UserGUID;
    }

    public void setUserGUID(String userGUID) {
        UserGUID = userGUID;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public String getGetGoodPoint() {
        return GetGoodPoint;
    }

    public void setGetGoodPoint(String getGoodPoint) {
        GetGoodPoint = getGoodPoint;
    }

    public String getSubCount() {
        return SubCount;
    }

    public void setSubCount(String subCount) {
        SubCount = subCount;
    }

    public boolean isGoodPoint() {
        return isGoodPoint;
    }

    public void setGoodPoint(boolean goodPoint) {
        isGoodPoint = goodPoint;
    }

    public List<NewsCommentModel> getSubList() {
        return subList;
    }

    public void setSubList(List<NewsCommentModel> subList) {
        this.subList = subList;
    }

    public JSONObject getSubCommentList() {
        return SubCommentList;
    }

    public void setSubCommentList(JSONObject subCommentList) {
        SubCommentList = subCommentList;
    }

    public boolean isExpandAllSubList() {
        return expandAllSubList;
    }

    public void setExpandAllSubList(boolean expandAllSubList) {
        this.expandAllSubList = expandAllSubList;
    }
}
