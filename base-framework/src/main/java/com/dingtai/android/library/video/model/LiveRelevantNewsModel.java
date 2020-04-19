package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:lnr
 * date:2018/9/5
 * 直播 相关新闻
 */
public class LiveRelevantNewsModel implements Parcelable {

    private String ChannelLogo;

    private String ResourceGUID;

    private String ChannelName;

    private String Title;

    private String Summary;

    private String SourceForm;

    private String CreateTime;

    private String UpdateTime;

    private String AuditTime;

    private String IsComment;

    private String IsCommentNoName;

    private String ResourceType;

    private String UploadPicNames;

    private String SmallPicUrl;

    private String ChID;

    private String parentID;

    private String ShowOrder;

    private String Longitude;

    private String Latitude;

    private String ReadNo;

    private String BandChID;

    private String CommentNum;

    private String RPID;

    private String RPNum;

    private String ResourceFlag;

    private String ResourceUrl;

    private String FakeReadNo;

    private String ThemeID;

    private String ResourceCSS;

    private String GetGoodPoint;

    private String PicPath;

    private String CommunityName;

    private String IsNewTopice;

    private boolean isRead;

    private String VoteType;

    private String VoteRemark;

    private String VoteSubject1Name;

    private String VoteSubject2Name;

    private String VoteNum;

    private String VoteSubject1;

    private String VoteSubject2;

    private String VoteSubject1Percent;

    private String VoteSubject2Percent;

    private String HornName;

    private String HornColor;

    private String ResourcePdForm;

    public LiveRelevantNewsModel() {
    }

    protected LiveRelevantNewsModel(Parcel in) {
        ChannelLogo = in.readString();
        ResourceGUID = in.readString();
        ChannelName = in.readString();
        Title = in.readString();
        Summary = in.readString();
        SourceForm = in.readString();
        CreateTime = in.readString();
        UpdateTime = in.readString();
        AuditTime = in.readString();
        IsComment = in.readString();
        IsCommentNoName = in.readString();
        ResourceType = in.readString();
        UploadPicNames = in.readString();
        SmallPicUrl = in.readString();
        ChID = in.readString();
        parentID = in.readString();
        ShowOrder = in.readString();
        Longitude = in.readString();
        Latitude = in.readString();
        ReadNo = in.readString();
        BandChID = in.readString();
        CommentNum = in.readString();
        RPID = in.readString();
        RPNum = in.readString();
        ResourceFlag = in.readString();
        ResourceUrl = in.readString();
        FakeReadNo = in.readString();
        ThemeID = in.readString();
        ResourceCSS = in.readString();
        GetGoodPoint = in.readString();
        PicPath = in.readString();
        CommunityName = in.readString();
        IsNewTopice = in.readString();
        isRead = in.readByte() != 0;
        VoteType = in.readString();
        VoteRemark = in.readString();
        VoteSubject1Name = in.readString();
        VoteSubject2Name = in.readString();
        VoteNum = in.readString();
        VoteSubject1 = in.readString();
        VoteSubject2 = in.readString();
        VoteSubject1Percent = in.readString();
        VoteSubject2Percent = in.readString();
        HornName = in.readString();
        HornColor = in.readString();
        ResourcePdForm = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ChannelLogo);
        dest.writeString(ResourceGUID);
        dest.writeString(ChannelName);
        dest.writeString(Title);
        dest.writeString(Summary);
        dest.writeString(SourceForm);
        dest.writeString(CreateTime);
        dest.writeString(UpdateTime);
        dest.writeString(AuditTime);
        dest.writeString(IsComment);
        dest.writeString(IsCommentNoName);
        dest.writeString(ResourceType);
        dest.writeString(UploadPicNames);
        dest.writeString(SmallPicUrl);
        dest.writeString(ChID);
        dest.writeString(parentID);
        dest.writeString(ShowOrder);
        dest.writeString(Longitude);
        dest.writeString(Latitude);
        dest.writeString(ReadNo);
        dest.writeString(BandChID);
        dest.writeString(CommentNum);
        dest.writeString(RPID);
        dest.writeString(RPNum);
        dest.writeString(ResourceFlag);
        dest.writeString(ResourceUrl);
        dest.writeString(FakeReadNo);
        dest.writeString(ThemeID);
        dest.writeString(ResourceCSS);
        dest.writeString(GetGoodPoint);
        dest.writeString(PicPath);
        dest.writeString(CommunityName);
        dest.writeString(IsNewTopice);
        dest.writeByte((byte) (isRead ? 1 : 0));
        dest.writeString(VoteType);
        dest.writeString(VoteRemark);
        dest.writeString(VoteSubject1Name);
        dest.writeString(VoteSubject2Name);
        dest.writeString(VoteNum);
        dest.writeString(VoteSubject1);
        dest.writeString(VoteSubject2);
        dest.writeString(VoteSubject1Percent);
        dest.writeString(VoteSubject2Percent);
        dest.writeString(HornName);
        dest.writeString(HornColor);
        dest.writeString(ResourcePdForm);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LiveRelevantNewsModel> CREATOR = new Creator<LiveRelevantNewsModel>() {
        @Override
        public LiveRelevantNewsModel createFromParcel(Parcel in) {
            return new LiveRelevantNewsModel(in);
        }

        @Override
        public LiveRelevantNewsModel[] newArray(int size) {
            return new LiveRelevantNewsModel[size];
        }
    };

    public String getChannelLogo() {
        return ChannelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        ChannelLogo = channelLogo;
    }

    public String getResourceGUID() {
        return ResourceGUID;
    }

    public void setResourceGUID(String resourceGUID) {
        ResourceGUID = resourceGUID;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getSourceForm() {
        return SourceForm;
    }

    public void setSourceForm(String sourceForm) {
        SourceForm = sourceForm;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getIsComment() {
        return IsComment;
    }

    public void setIsComment(String isComment) {
        IsComment = isComment;
    }

    public String getIsCommentNoName() {
        return IsCommentNoName;
    }

    public void setIsCommentNoName(String isCommentNoName) {
        IsCommentNoName = isCommentNoName;
    }

    public String getResourceType() {
        return ResourceType;
    }

    public void setResourceType(String resourceType) {
        ResourceType = resourceType;
    }

    public String getUploadPicNames() {
        return UploadPicNames;
    }

    public void setUploadPicNames(String uploadPicNames) {
        UploadPicNames = uploadPicNames;
    }

    public String getSmallPicUrl() {
        return SmallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        SmallPicUrl = smallPicUrl;
    }

    public String getChID() {
        return ChID;
    }

    public void setChID(String chID) {
        ChID = chID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getShowOrder() {
        return ShowOrder;
    }

    public void setShowOrder(String showOrder) {
        ShowOrder = showOrder;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getReadNo() {
        return ReadNo;
    }

    public void setReadNo(String readNo) {
        ReadNo = readNo;
    }

    public String getBandChID() {
        return BandChID;
    }

    public void setBandChID(String bandChID) {
        BandChID = bandChID;
    }

    public String getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(String commentNum) {
        CommentNum = commentNum;
    }

    public String getRPID() {
        return RPID;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public String getRPNum() {
        return RPNum;
    }

    public void setRPNum(String RPNum) {
        this.RPNum = RPNum;
    }

    public String getResourceFlag() {
        return ResourceFlag;
    }

    public void setResourceFlag(String resourceFlag) {
        ResourceFlag = resourceFlag;
    }

    public String getResourceUrl() {
        return ResourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        ResourceUrl = resourceUrl;
    }

    public String getFakeReadNo() {
        return FakeReadNo;
    }

    public void setFakeReadNo(String fakeReadNo) {
        FakeReadNo = fakeReadNo;
    }

    public String getThemeID() {
        return ThemeID;
    }

    public void setThemeID(String themeID) {
        ThemeID = themeID;
    }

    public String getResourceCSS() {
        return ResourceCSS;
    }

    public void setResourceCSS(String resourceCSS) {
        ResourceCSS = resourceCSS;
    }

    public String getGetGoodPoint() {
        return GetGoodPoint;
    }

    public void setGetGoodPoint(String getGoodPoint) {
        GetGoodPoint = getGoodPoint;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        PicPath = picPath;
    }

    public String getCommunityName() {
        return CommunityName;
    }

    public void setCommunityName(String communityName) {
        CommunityName = communityName;
    }

    public String getIsNewTopice() {
        return IsNewTopice;
    }

    public void setIsNewTopice(String isNewTopice) {
        IsNewTopice = isNewTopice;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
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

    public String getHornName() {
        return HornName;
    }

    public void setHornName(String hornName) {
        HornName = hornName;
    }

    public String getHornColor() {
        return HornColor;
    }

    public void setHornColor(String hornColor) {
        HornColor = hornColor;
    }

    public String getResourcePdForm() {
        return ResourcePdForm;
    }

    public void setResourcePdForm(String resourcePdForm) {
        ResourcePdForm = resourcePdForm;
    }
}
