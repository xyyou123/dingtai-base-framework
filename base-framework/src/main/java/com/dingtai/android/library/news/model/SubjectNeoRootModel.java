package com.dingtai.android.library.news.model;

import java.util.List;

/**
 * author:lnr
 * date:2018/9/17
 * 新专题
 */
public class SubjectNeoRootModel {

    private String Title;
    private String Logo;
    private String ReMark;

    private List<SubjectNeoModel> Topics;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getReMark() {
        return ReMark;
    }

    public void setReMark(String reMark) {
        ReMark = reMark;
    }

    public List<SubjectNeoModel> getTopics() {
        return Topics;
    }

    public void setTopics(List<SubjectNeoModel> topics) {
        Topics = topics;
    }
}
