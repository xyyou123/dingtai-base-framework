package com.dingtai.android.library.news.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/26
 */
@Entity
public class LaunchAdModel {

    @Id
    protected Long _id;

    @Unique
    private String ID;

    private String CreateTime;

    private String OpenPicName;

    private String ImgUrl;

    private String LinkUrl;

    private String StID;

    private String ChID;

    private String ADFor;

    private String ADContent;

    private String ForApp;

    private String LinkTo;

    private String RandomNum;

    private String Status;

    private String ReMark;

    private String Rural;

    private String ImgUrls;
    private String OpenTime;

    @Convert(converter = LaunchAdDetailsModel.LaunchAdDetailsModelListConverter.class, columnType = String.class)
    private List<LaunchAdDetailsModel> OpenPicDetail;


    @Generated(hash = 1991300678)
    public LaunchAdModel(Long _id, String ID, String CreateTime, String OpenPicName, String ImgUrl,
            String LinkUrl, String StID, String ChID, String ADFor, String ADContent, String ForApp,
            String LinkTo, String RandomNum, String Status, String ReMark, String Rural, String ImgUrls,
            String OpenTime, List<LaunchAdDetailsModel> OpenPicDetail) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.OpenPicName = OpenPicName;
        this.ImgUrl = ImgUrl;
        this.LinkUrl = LinkUrl;
        this.StID = StID;
        this.ChID = ChID;
        this.ADFor = ADFor;
        this.ADContent = ADContent;
        this.ForApp = ForApp;
        this.LinkTo = LinkTo;
        this.RandomNum = RandomNum;
        this.Status = Status;
        this.ReMark = ReMark;
        this.Rural = Rural;
        this.ImgUrls = ImgUrls;
        this.OpenTime = OpenTime;
        this.OpenPicDetail = OpenPicDetail;
    }

    @Generated(hash = 673343143)
    public LaunchAdModel() {
    }


    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getOpenPicName() {
        return this.OpenPicName;
    }

    public void setOpenPicName(String OpenPicName) {
        this.OpenPicName = OpenPicName;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getLinkUrl() {
        return this.LinkUrl;
    }

    public void setLinkUrl(String LinkUrl) {
        this.LinkUrl = LinkUrl;
    }

    public String getStID() {
        return this.StID;
    }

    public void setStID(String StID) {
        this.StID = StID;
    }

    public String getChID() {
        return this.ChID;
    }

    public void setChID(String ChID) {
        this.ChID = ChID;
    }

    public String getADFor() {
        return this.ADFor;
    }

    public void setADFor(String ADFor) {
        this.ADFor = ADFor;
    }

    public String getADContent() {
        return this.ADContent;
    }

    public void setADContent(String ADContent) {
        this.ADContent = ADContent;
    }

    public String getForApp() {
        return this.ForApp;
    }

    public void setForApp(String ForApp) {
        this.ForApp = ForApp;
    }

    public String getLinkTo() {
        return this.LinkTo;
    }

    public void setLinkTo(String LinkTo) {
        this.LinkTo = LinkTo;
    }

    public String getRandomNum() {
        return this.RandomNum;
    }

    public void setRandomNum(String RandomNum) {
        this.RandomNum = RandomNum;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getReMark() {
        return this.ReMark;
    }

    public void setReMark(String ReMark) {
        this.ReMark = ReMark;
    }

    public String getImgUrls() {
        return this.ImgUrls;
    }

    public void setImgUrls(String ImgUrls) {
        this.ImgUrls = ImgUrls;
    }

    public List<LaunchAdDetailsModel> getOpenPicDetail() {
        return this.OpenPicDetail;
    }

    public void setOpenPicDetail(List<LaunchAdDetailsModel> OpenPicDetail) {
        this.OpenPicDetail = OpenPicDetail;
    }

    public String getRural() {
        return Rural;
    }

    public void setRural(String rural) {
        Rural = rural;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(String openTime) {
        OpenTime = openTime;
    }
}
