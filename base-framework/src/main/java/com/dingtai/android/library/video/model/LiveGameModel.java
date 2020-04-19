package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/9/5
 * 直播互动游戏
 */
public class LiveGameModel implements Parcelable {

    private String ID;
    private String CreateTime;
    private String GameName;
    private String GameUrl;
    private String GameIntro;
    private String GameLogo;
    private String StID;
    private String Status;
    private String Remark;
    private String GameType;
    private String LiveID;
    private String GameYaoYaoID;
    private String BackLogo;

    /**
     * 投票类型：1、不显示投票2、只显示在列表3、只显示在详情4、列表详情都显示
     */
    private String VoteType;
    /**
     * 投票介绍
     */
    private String VoteRemark;
    /**
     * 选项1名称
     */
    private String VoteSubject1Name;// 选项1名称
    /**
     * 选项2名称
     */
    private String VoteSubject2Name;
    /**
     * 投票总数
     */
    private String VoteNum;
    /**
     *  选项1票数
     */
    private String VoteSubject1;
    /**
     * 选项2票数
     */
    private String VoteSubject2;
    /**
     * 选项1百分比
     */
    private String VoteSubject1Percent;
    /**
     * 选项2百分比
     */
    private String VoteSubject2Percent;
    private String Rule;
    private String ResourceGUID ;

    public LiveGameModel() {
    }

    protected LiveGameModel(Parcel in) {
        ID = in.readString();
        CreateTime = in.readString();
        GameName = in.readString();
        GameUrl = in.readString();
        GameIntro = in.readString();
        GameLogo = in.readString();
        StID = in.readString();
        Status = in.readString();
        Remark = in.readString();
        GameType = in.readString();
        LiveID = in.readString();
        GameYaoYaoID = in.readString();
        BackLogo = in.readString();
        VoteType = in.readString();
        VoteRemark = in.readString();
        VoteSubject1Name = in.readString();
        VoteSubject2Name = in.readString();
        VoteNum = in.readString();
        VoteSubject1 = in.readString();
        VoteSubject2 = in.readString();
        VoteSubject1Percent = in.readString();
        VoteSubject2Percent = in.readString();
        Rule = in.readString();
        ResourceGUID = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(CreateTime);
        dest.writeString(GameName);
        dest.writeString(GameUrl);
        dest.writeString(GameIntro);
        dest.writeString(GameLogo);
        dest.writeString(StID);
        dest.writeString(Status);
        dest.writeString(Remark);
        dest.writeString(GameType);
        dest.writeString(LiveID);
        dest.writeString(GameYaoYaoID);
        dest.writeString(BackLogo);
        dest.writeString(VoteType);
        dest.writeString(VoteRemark);
        dest.writeString(VoteSubject1Name);
        dest.writeString(VoteSubject2Name);
        dest.writeString(VoteNum);
        dest.writeString(VoteSubject1);
        dest.writeString(VoteSubject2);
        dest.writeString(VoteSubject1Percent);
        dest.writeString(VoteSubject2Percent);
        dest.writeString(Rule);
        dest.writeString(ResourceGUID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LiveGameModel> CREATOR = new Creator<LiveGameModel>() {
        @Override
        public LiveGameModel createFromParcel(Parcel in) {
            return new LiveGameModel(in);
        }

        @Override
        public LiveGameModel[] newArray(int size) {
            return new LiveGameModel[size];
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

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getGameUrl() {
        return GameUrl;
    }

    public void setGameUrl(String gameUrl) {
        GameUrl = gameUrl;
    }

    public String getGameIntro() {
        return GameIntro;
    }

    public void setGameIntro(String gameIntro) {
        GameIntro = gameIntro;
    }

    public String getGameLogo() {
        return GameLogo;
    }

    public void setGameLogo(String gameLogo) {
        GameLogo = gameLogo;
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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getGameType() {
        return GameType;
    }

    public void setGameType(String gameType) {
        GameType = gameType;
    }

    public String getLiveID() {
        return LiveID;
    }

    public void setLiveID(String liveID) {
        LiveID = liveID;
    }

    public String getGameYaoYaoID() {
        return GameYaoYaoID;
    }

    public void setGameYaoYaoID(String gameYaoYaoID) {
        GameYaoYaoID = gameYaoYaoID;
    }

    public String getBackLogo() {
        return BackLogo;
    }

    public void setBackLogo(String backLogo) {
        BackLogo = backLogo;
    }

    public String getVoteType() {
        return VoteType;
    }

    public void setVoteType(String voteType) {
        VoteType = voteType;
    }

    public String getVoteRemark() {
        return VoteRemark;
    }

    public void setVoteRemark(String voteRemark) {
        VoteRemark = voteRemark;
    }

    public String getVoteSubject1Name() {
        return VoteSubject1Name;
    }

    public void setVoteSubject1Name(String voteSubject1Name) {
        VoteSubject1Name = voteSubject1Name;
    }

    public String getVoteSubject2Name() {
        return VoteSubject2Name;
    }

    public void setVoteSubject2Name(String voteSubject2Name) {
        VoteSubject2Name = voteSubject2Name;
    }

    public String getVoteNum() {
        return VoteNum;
    }

    public void setVoteNum(String voteNum) {
        VoteNum = voteNum;
    }

    public String getVoteSubject1() {
        return VoteSubject1;
    }

    public void setVoteSubject1(String voteSubject1) {
        VoteSubject1 = voteSubject1;
    }

    public String getVoteSubject2() {
        return VoteSubject2;
    }

    public void setVoteSubject2(String voteSubject2) {
        VoteSubject2 = voteSubject2;
    }

    public String getVoteSubject1Percent() {
        return VoteSubject1Percent;
    }

    public void setVoteSubject1Percent(String voteSubject1Percent) {
        VoteSubject1Percent = voteSubject1Percent;
    }

    public String getVoteSubject2Percent() {
        return VoteSubject2Percent;
    }

    public void setVoteSubject2Percent(String voteSubject2Percent) {
        VoteSubject2Percent = voteSubject2Percent;
    }

    public String getRule() {
        return Rule;
    }

    public void setRule(String rule) {
        Rule = rule;
    }

    public String getResourceGUID() {
        return ResourceGUID;
    }

    public void setResourceGUID(String resourceGUID) {
        ResourceGUID = resourceGUID;
    }
}
