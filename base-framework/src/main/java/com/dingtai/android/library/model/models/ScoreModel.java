package com.dingtai.android.library.model.models;

/**
 * author:lnr
 * date:2018/9/26
 * 积分
 */
public class ScoreModel {

    private String taskCode;
    private String taskContentID;

    public ScoreModel(String taskCode, String taskContentID) {
        this.taskCode = taskCode;
        this.taskContentID = taskContentID;
    }

    public ScoreModel(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskContentID() {
        return taskContentID;
    }

    public void setTaskContentID(String taskContentID) {
        this.taskContentID = taskContentID;
    }
}
