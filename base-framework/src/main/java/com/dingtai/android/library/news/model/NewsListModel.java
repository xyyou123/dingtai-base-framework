package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dingtai.android.library.news.ui.list.adapter.convertor.NewsItemConvertor;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

@Entity
public class NewsListModel implements Parcelable, MultiItemEntity {

    @Id
    private Long _id;

    @Transient
    private String ID;

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

    /**
     * 专题中的高新 稿件附带的专题信息 Topice = 1 为专题稿件
     */
    private String Topice;
    /**
     * 专题中的高新 稿件附带的专题信息 channelName1 专题名字
     */
    private String channelName1;
    /**
     * 专题中的高新 稿件附带的专题信息 channelID 专题id
     */
    private String channelID;

    private String MediaLogo;

    private String MediaUrl;

    private String MediaName;

    private String RedirectType;
    
    private String JsonKey;


    private String ResUnitName;//公号名称
    private String Address; // 发布新闻的地址

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }



    public String getResUnitName() {
        return ResUnitName;
    }

    public void setResUnitName(String resUnitName) {
        ResUnitName = resUnitName;
    }

    public String getRedirectType() {
        return RedirectType;
    }

    public void setRedirectType(String redirectType) {
        RedirectType = redirectType;
    }

    public String getJsonKey() {
        return JsonKey;
    }

    public void setJsonKey(String jsonKey) {
        JsonKey = jsonKey;
    }

    @Transient
    private String Status;

    @Transient
    private List<TopicChann> TopicChann;

    @Transient
    private List<NewsPhotoModel> photos;

    @Generated(hash = 310306933)
    public NewsListModel() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getChannelLogo() {
        return this.ChannelLogo;
    }

    public void setChannelLogo(String ChannelLogo) {
        this.ChannelLogo = ChannelLogo;
    }

    public String getResourceGUID() {
        return this.ResourceGUID;
    }

    public void setResourceGUID(String ResourceGUID) {
        this.ResourceGUID = ResourceGUID;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getSummary() {
        return this.Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getSourceForm() {
        return this.SourceForm;
    }

    public void setSourceForm(String SourceForm) {
        this.SourceForm = SourceForm;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getAuditTime() {
        return this.AuditTime;
    }

    public void setAuditTime(String AuditTime) {
        this.AuditTime = AuditTime;
    }

    public String getIsComment() {
        return this.IsComment;
    }

    public void setIsComment(String IsComment) {
        this.IsComment = IsComment;
    }

    public String getIsCommentNoName() {
        return this.IsCommentNoName;
    }

    public void setIsCommentNoName(String IsCommentNoName) {
        this.IsCommentNoName = IsCommentNoName;
    }

    public String getResourceType() {
        return this.ResourceType;
    }

    public void setResourceType(String ResourceType) {
        this.ResourceType = ResourceType;
    }

    public String getUploadPicNames() {
        return this.UploadPicNames;
    }

    public void setUploadPicNames(String UploadPicNames) {
        this.UploadPicNames = UploadPicNames;
    }

    public String getSmallPicUrl() {
        return this.SmallPicUrl;
    }

    public void setSmallPicUrl(String SmallPicUrl) {
        this.SmallPicUrl = SmallPicUrl;
    }

    public String getChID() {
        return this.ChID;
    }

    public void setChID(String ChID) {
        this.ChID = ChID;
    }

    public String getParentID() {
        return this.parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getShowOrder() {
        return this.ShowOrder;
    }

    public void setShowOrder(String ShowOrder) {
        this.ShowOrder = ShowOrder;
    }

    public String getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getReadNo() {
        return this.ReadNo;
    }

    public void setReadNo(String ReadNo) {
        this.ReadNo = ReadNo;
    }

    public String getBandChID() {
        return this.BandChID;
    }

    public void setBandChID(String BandChID) {
        this.BandChID = BandChID;
    }

    public String getCommentNum() {
        return this.CommentNum;
    }

    public void setCommentNum(String CommentNum) {
        this.CommentNum = CommentNum;
    }

    public String getRPID() {
        return this.RPID;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public String getRPNum() {
        return this.RPNum;
    }

    public void setRPNum(String RPNum) {
        this.RPNum = RPNum;
    }

    public String getResourceFlag() {
        return this.ResourceFlag;
    }

    public void setResourceFlag(String ResourceFlag) {
        this.ResourceFlag = ResourceFlag;
    }

    public String getResourceUrl() {
        return this.ResourceUrl;
    }

    public void setResourceUrl(String ResourceUrl) {
        this.ResourceUrl = ResourceUrl;
    }

    public String getFakeReadNo() {
        return this.FakeReadNo;
    }

    public void setFakeReadNo(String FakeReadNo) {
        this.FakeReadNo = FakeReadNo;
    }

    public String getThemeID() {
        return this.ThemeID;
    }

    public void setThemeID(String ThemeID) {
        this.ThemeID = ThemeID;
    }

    public String getResourceCSS() {
        return this.ResourceCSS;
    }

    public void setResourceCSS(String ResourceCSS) {
        this.ResourceCSS = ResourceCSS;
    }

    public String getGetGoodPoint() {
        return this.GetGoodPoint;
    }

    public void setGetGoodPoint(String GetGoodPoint) {
        this.GetGoodPoint = GetGoodPoint;
    }

    public String getPicPath() {
        return this.PicPath;
    }

    public void setPicPath(String PicPath) {
        this.PicPath = PicPath;
    }

    public String getCommunityName() {
        return this.CommunityName;
    }

    public void setCommunityName(String CommunityName) {
        this.CommunityName = CommunityName;
    }

    public String getIsNewTopice() {
        return this.IsNewTopice;
    }

    public void setIsNewTopice(String IsNewTopice) {
        this.IsNewTopice = IsNewTopice;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getVoteType() {
        return this.VoteType;
    }

    public void setVoteType(String VoteType) {
        this.VoteType = VoteType;
    }

    public String getVoteRemark() {
        return this.VoteRemark;
    }

    public void setVoteRemark(String VoteRemark) {
        this.VoteRemark = VoteRemark;
    }

    public String getVoteSubject1Name() {
        return this.VoteSubject1Name;
    }

    public void setVoteSubject1Name(String VoteSubject1Name) {
        this.VoteSubject1Name = VoteSubject1Name;
    }

    public String getVoteSubject2Name() {
        return this.VoteSubject2Name;
    }

    public void setVoteSubject2Name(String VoteSubject2Name) {
        this.VoteSubject2Name = VoteSubject2Name;
    }

    public String getVoteNum() {
        return this.VoteNum;
    }

    public void setVoteNum(String VoteNum) {
        this.VoteNum = VoteNum;
    }

    public String getVoteSubject1() {
        return this.VoteSubject1;
    }

    public void setVoteSubject1(String VoteSubject1) {
        this.VoteSubject1 = VoteSubject1;
    }

    public String getVoteSubject2() {
        return this.VoteSubject2;
    }

    public void setVoteSubject2(String VoteSubject2) {
        this.VoteSubject2 = VoteSubject2;
    }

    public String getVoteSubject1Percent() {
        return this.VoteSubject1Percent;
    }

    public void setVoteSubject1Percent(String VoteSubject1Percent) {
        this.VoteSubject1Percent = VoteSubject1Percent;
    }

    public String getVoteSubject2Percent() {
        return this.VoteSubject2Percent;
    }

    public void setVoteSubject2Percent(String VoteSubject2Percent) {
        this.VoteSubject2Percent = VoteSubject2Percent;
    }

    public String getHornName() {
        return this.HornName;
    }

    public void setHornName(String HornName) {
        this.HornName = HornName;
    }

    public String getHornColor() {
        return this.HornColor;
    }

    public void setHornColor(String HornColor) {
        this.HornColor = HornColor;
    }

    public String getResourcePdForm() {
        return this.ResourcePdForm;
    }

    public void setResourcePdForm(String ResourcePdForm) {
        this.ResourcePdForm = ResourcePdForm;
    }

    public List<NewsPhotoModel> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<NewsPhotoModel> photos) {
        this.photos = photos;
    }



    public List<TopicChann> getTopicChann() {
        return this.TopicChann;
    }



    public void setTopicChann(List<TopicChann> TopicChann) {
        this.TopicChann = TopicChann;
    }

    @Override
    public int getItemType() {
        return NewsItemConvertor.converterType(this);
    }



    public String getTopice() {
        return this.Topice;
    }



    public void setTopice(String Topice) {
        this.Topice = Topice;
    }



    public String getChannelName1() {
        return this.channelName1;
    }



    public void setChannelName1(String channelName1) {
        this.channelName1 = channelName1;
    }



    public String getChannelID() {
        return this.channelID;
    }



    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMediaLogo() {
        return MediaLogo;
    }

    public void setMediaLogo(String mediaLogo) {
        MediaLogo = mediaLogo;
    }

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
    }

    public String getMediaName() {
        return MediaName;
    }

    public void setMediaName(String mediaName) {
        MediaName = mediaName;
    }

    public static final class ChannelModelConverter implements PropertyConverter<NewsListModel, String> {
        @Override
        public NewsListModel convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseObject(databaseValue, NewsListModel.class);
        }

        @Override
        public String convertToDatabaseValue(NewsListModel entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }

    public static final class ChannelModelListConverter implements PropertyConverter<List<NewsListModel>, String> {
        @Override
        public List<NewsListModel> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, NewsListModel.class);
        }

        @Override
        public String convertToDatabaseValue(List<NewsListModel> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.ID);
        dest.writeString(this.ChannelLogo);
        dest.writeString(this.ResourceGUID);
        dest.writeString(this.ChannelName);
        dest.writeString(this.Title);
        dest.writeString(this.Summary);
        dest.writeString(this.SourceForm);
        dest.writeString(this.CreateTime);
        dest.writeString(this.UpdateTime);
        dest.writeString(this.AuditTime);
        dest.writeString(this.IsComment);
        dest.writeString(this.IsCommentNoName);
        dest.writeString(this.ResourceType);
        dest.writeString(this.UploadPicNames);
        dest.writeString(this.SmallPicUrl);
        dest.writeString(this.ChID);
        dest.writeString(this.parentID);
        dest.writeString(this.ShowOrder);
        dest.writeString(this.Longitude);
        dest.writeString(this.Latitude);
        dest.writeString(this.ReadNo);
        dest.writeString(this.BandChID);
        dest.writeString(this.CommentNum);
        dest.writeString(this.RPID);
        dest.writeString(this.RPNum);
        dest.writeString(this.ResourceFlag);
        dest.writeString(this.ResourceUrl);
        dest.writeString(this.FakeReadNo);
        dest.writeString(this.ThemeID);
        dest.writeString(this.ResourceCSS);
        dest.writeString(this.GetGoodPoint);
        dest.writeString(this.PicPath);
        dest.writeString(this.CommunityName);
        dest.writeString(this.IsNewTopice);
        dest.writeByte(this.isRead ? (byte) 1 : (byte) 0);
        dest.writeString(this.VoteType);
        dest.writeString(this.VoteRemark);
        dest.writeString(this.VoteSubject1Name);
        dest.writeString(this.VoteSubject2Name);
        dest.writeString(this.VoteNum);
        dest.writeString(this.VoteSubject1);
        dest.writeString(this.VoteSubject2);
        dest.writeString(this.VoteSubject1Percent);
        dest.writeString(this.VoteSubject2Percent);
        dest.writeString(this.HornName);
        dest.writeString(this.HornColor);
        dest.writeString(this.ResourcePdForm);
        dest.writeString(this.Topice);
        dest.writeString(this.channelName1);
        dest.writeString(this.channelID);
        dest.writeString(this.MediaLogo);
        dest.writeString(this.MediaUrl);
        dest.writeString(this.MediaName);
        dest.writeString(this.RedirectType);
        dest.writeString(this.JsonKey);
        dest.writeString(this.ResUnitName);
        dest.writeString(this.Status);
        dest.writeTypedList(this.TopicChann);
        dest.writeTypedList(this.photos);
    }

    protected NewsListModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.ChannelLogo = in.readString();
        this.ResourceGUID = in.readString();
        this.ChannelName = in.readString();
        this.Title = in.readString();
        this.Summary = in.readString();
        this.SourceForm = in.readString();
        this.CreateTime = in.readString();
        this.UpdateTime = in.readString();
        this.AuditTime = in.readString();
        this.IsComment = in.readString();
        this.IsCommentNoName = in.readString();
        this.ResourceType = in.readString();
        this.UploadPicNames = in.readString();
        this.SmallPicUrl = in.readString();
        this.ChID = in.readString();
        this.parentID = in.readString();
        this.ShowOrder = in.readString();
        this.Longitude = in.readString();
        this.Latitude = in.readString();
        this.ReadNo = in.readString();
        this.BandChID = in.readString();
        this.CommentNum = in.readString();
        this.RPID = in.readString();
        this.RPNum = in.readString();
        this.ResourceFlag = in.readString();
        this.ResourceUrl = in.readString();
        this.FakeReadNo = in.readString();
        this.ThemeID = in.readString();
        this.ResourceCSS = in.readString();
        this.GetGoodPoint = in.readString();
        this.PicPath = in.readString();
        this.CommunityName = in.readString();
        this.IsNewTopice = in.readString();
        this.isRead = in.readByte() != 0;
        this.VoteType = in.readString();
        this.VoteRemark = in.readString();
        this.VoteSubject1Name = in.readString();
        this.VoteSubject2Name = in.readString();
        this.VoteNum = in.readString();
        this.VoteSubject1 = in.readString();
        this.VoteSubject2 = in.readString();
        this.VoteSubject1Percent = in.readString();
        this.VoteSubject2Percent = in.readString();
        this.HornName = in.readString();
        this.HornColor = in.readString();
        this.ResourcePdForm = in.readString();
        this.Topice = in.readString();
        this.channelName1 = in.readString();
        this.channelID = in.readString();
        this.MediaLogo = in.readString();
        this.MediaUrl = in.readString();
        this.MediaName = in.readString();
        this.RedirectType = in.readString();
        this.JsonKey = in.readString();
        this.ResUnitName = in.readString();
        this.Status = in.readString();
        this.TopicChann = in.createTypedArrayList(com.dingtai.android.library.news.model.TopicChann.CREATOR);
        this.photos = in.createTypedArrayList(NewsPhotoModel.CREATOR);
    }

    @Generated(hash = 820430746)
    public NewsListModel(Long _id, String ChannelLogo, String ResourceGUID, String ChannelName, String Title,
            String Summary, String SourceForm, String CreateTime, String UpdateTime, String AuditTime, String IsComment,
            String IsCommentNoName, String ResourceType, String UploadPicNames, String SmallPicUrl, String ChID,
            String parentID, String ShowOrder, String Longitude, String Latitude, String ReadNo, String BandChID,
            String CommentNum, String RPID, String RPNum, String ResourceFlag, String ResourceUrl, String FakeReadNo,
            String ThemeID, String ResourceCSS, String GetGoodPoint, String PicPath, String CommunityName,
            String IsNewTopice, boolean isRead, String VoteType, String VoteRemark, String VoteSubject1Name,
            String VoteSubject2Name, String VoteNum, String VoteSubject1, String VoteSubject2, String VoteSubject1Percent,
            String VoteSubject2Percent, String HornName, String HornColor, String ResourcePdForm, String Topice,
            String channelName1, String channelID, String MediaLogo, String MediaUrl, String MediaName, String RedirectType,
            String JsonKey, String ResUnitName, String Address) {
        this._id = _id;
        this.ChannelLogo = ChannelLogo;
        this.ResourceGUID = ResourceGUID;
        this.ChannelName = ChannelName;
        this.Title = Title;
        this.Summary = Summary;
        this.SourceForm = SourceForm;
        this.CreateTime = CreateTime;
        this.UpdateTime = UpdateTime;
        this.AuditTime = AuditTime;
        this.IsComment = IsComment;
        this.IsCommentNoName = IsCommentNoName;
        this.ResourceType = ResourceType;
        this.UploadPicNames = UploadPicNames;
        this.SmallPicUrl = SmallPicUrl;
        this.ChID = ChID;
        this.parentID = parentID;
        this.ShowOrder = ShowOrder;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.ReadNo = ReadNo;
        this.BandChID = BandChID;
        this.CommentNum = CommentNum;
        this.RPID = RPID;
        this.RPNum = RPNum;
        this.ResourceFlag = ResourceFlag;
        this.ResourceUrl = ResourceUrl;
        this.FakeReadNo = FakeReadNo;
        this.ThemeID = ThemeID;
        this.ResourceCSS = ResourceCSS;
        this.GetGoodPoint = GetGoodPoint;
        this.PicPath = PicPath;
        this.CommunityName = CommunityName;
        this.IsNewTopice = IsNewTopice;
        this.isRead = isRead;
        this.VoteType = VoteType;
        this.VoteRemark = VoteRemark;
        this.VoteSubject1Name = VoteSubject1Name;
        this.VoteSubject2Name = VoteSubject2Name;
        this.VoteNum = VoteNum;
        this.VoteSubject1 = VoteSubject1;
        this.VoteSubject2 = VoteSubject2;
        this.VoteSubject1Percent = VoteSubject1Percent;
        this.VoteSubject2Percent = VoteSubject2Percent;
        this.HornName = HornName;
        this.HornColor = HornColor;
        this.ResourcePdForm = ResourcePdForm;
        this.Topice = Topice;
        this.channelName1 = channelName1;
        this.channelID = channelID;
        this.MediaLogo = MediaLogo;
        this.MediaUrl = MediaUrl;
        this.MediaName = MediaName;
        this.RedirectType = RedirectType;
        this.JsonKey = JsonKey;
        this.ResUnitName = ResUnitName;
        this.Address = Address;
    }

    public static final Creator<NewsListModel> CREATOR = new Creator<NewsListModel>() {
        @Override
        public NewsListModel createFromParcel(Parcel source) {
            return new NewsListModel(source);
        }

        @Override
        public NewsListModel[] newArray(int size) {
            return new NewsListModel[size];
        }
    };
}
