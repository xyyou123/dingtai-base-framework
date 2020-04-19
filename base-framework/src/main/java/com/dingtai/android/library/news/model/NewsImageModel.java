package com.dingtai.android.library.news.model;

/**
 * author:lnr
 * date:2018/10/30
 */
public class NewsImageModel {

    private String ID;
    private String CreateTime;
    private String PhotoDescription;
    private String PhotoTitle;
    private String PicturePath;
    private String RID;

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

    public String getPhotoDescription() {
        return PhotoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        PhotoDescription = photoDescription;
    }

    public String getPhotoTitle() {
        return PhotoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        PhotoTitle = photoTitle;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }
}
