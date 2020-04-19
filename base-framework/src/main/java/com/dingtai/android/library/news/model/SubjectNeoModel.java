package com.dingtai.android.library.news.model;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/17
 * 新专题
 */
public class SubjectNeoModel {

    private  String TopicsName;
    private  String TopicsLogo;
    private String ReMark;
    private String TopicsID;
    private List<NewsListModel> TopicsNews;

    public String getTopicsName() {
        return TopicsName;
    }

    public void setTopicsName(String topicsName) {
        TopicsName = topicsName;
    }

    public String getTopicsLogo() {
        return TopicsLogo;
    }

    public void setTopicsLogo(String topicsLogo) {
        TopicsLogo = topicsLogo;
    }

    public String getReMark() {
        return ReMark;
    }

    public void setReMark(String reMark) {
        ReMark = reMark;
    }

    public String getTopicsID() {
        return TopicsID;
    }

    public void setTopicsID(String topicsID) {
        TopicsID = topicsID;
    }

    public List<NewsListModel> getTopicsNews() {
        return TopicsNews;
    }

    public void setTopicsNews(List<NewsListModel> topicsNews) {
        TopicsNews = topicsNews;
    }
}
