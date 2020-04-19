package com.dingtai.android.library.news.model;

import com.alibaba.fastjson.JSON;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/26
 */
public class LaunchAdDetailsModel {

    private String ID;

    private String CreateTime;
    private String OpenPicName;
    private String ImgUrl;
    private String LinkUrl;
    private String ADFor;
    private String ADContent;
    private String LinkTo;
    private String Weight;
    private String ReMark;
    private String OpenTime;
    private String LiveChannel;

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


    public String getLinkTo() {
        return this.LinkTo;
    }


    public void setLinkTo(String LinkTo) {
        this.LinkTo = LinkTo;
    }


    public String getWeight() {
        return this.Weight;
    }


    public void setWeight(String Weight) {
        this.Weight = Weight;
    }


    public String getReMark() {
        return this.ReMark;
    }


    public void setReMark(String ReMark) {
        this.ReMark = ReMark;
    }


    public String getOpenTime() {
        return this.OpenTime;
    }


    public void setOpenTime(String OpenTime) {
        this.OpenTime = OpenTime;
    }

    public String getLiveChannel() {
        return LiveChannel;
    }

    public void setLiveChannel(String liveChannel) {
        LiveChannel = liveChannel;
    }

    public static final class LaunchAdDetailsModelListConverter implements PropertyConverter<List<LaunchAdDetailsModel>, String> {
        @Override
        public List<LaunchAdDetailsModel> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, LaunchAdDetailsModel.class);
        }

        @Override
        public String convertToDatabaseValue(List<LaunchAdDetailsModel> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }
}
