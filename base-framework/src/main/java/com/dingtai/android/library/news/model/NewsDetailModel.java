package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.dingtai.android.library.model.models.ADModel;
import com.lnr.android.base.framework.uitl.JsonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

@Entity
public class NewsDetailModel implements Parcelable {

    @Id
    private Long _id;

    /**
     * ID
     */
    private String ID;
    /**
     * 内容GUID
     */
    @Unique
    private String ResourceGUID;
    /**
     * 标题
     */
    private String Title;

    private String subTitle;// 副标题
    /**
     * 摘要
     */
    private String Summary;
    /**
     * 正文
     */
    private String Content;
    /**
     * 来源
     */
    private String SourceForm;
    /**
     * 修改时间
     */
    private String UpdateTime;
    /**
     * 审核时间
     */
    private String AuditTime;

    /**
     * （创建时间）
     */
    private String CreateTime;

    /**
     * （新闻类型）
     */
    private String ResourceType;
    /**
     * （缩略图地址
     */
    private String SmallPicUrl;

    /**
     * （图集缩略图，用逗号分开
     */
    private String UploadPicNames;
    /**
     * 是否评论
     */
    private String IsComment;
    /**
     * 栏目ID
     */
    private String ChID;
    /**
     * 排序
     */
    private String ShowOrder;
    /**
     * 经度
     */
    private String Longitude;
    /**
     * 纬度
     */
    private String Latitude;
    /**
     * 浏览
     */
    private String ReadNo;
    /**
     * 绑定栏目
     */
    private String BandChID;

    /**
     * 评论数量
     */
    private String CommentNum;

    /**
     * 记者信息
     */
    private String Journalist;
    /**
     * 相关新闻
     */
    @Transient
    private List<RelatedReaderModel> RelatedNews;
    /**
     * 栏目名称
     */
    private String ChannelName;
    /**
     * ThemeName
     */
    private String ThemeName;
    /**
     * 是否匿名评论
     */
    private String IsCommentNoName;
    /**
     * 新闻角标
     */
    private String ResourceFlag;
    /**
     * 新闻地址
     */
    private String ResourceUrl;
    /**
     * 新闻评论数
     */
    private String Comments;
    /**
     * 新闻阅读数
     */
    private String FakeReadNo;
    /**
     * 新闻点赞数
     */
    private String NewsGetGoodPoint;
    /**
     * 主题ID
     */
    private String ThemeID;
    /**
     * 音频dizhi
     */
    private String AudioUrl;
    /**
     * 音频长度
     */
    private String AudioLength;
    /**
     * 图集图片数量
     */
    private String RPNum;
    /**
     * 关键字
     */
    private String KeyWords;

    /**
     * 作者
     */
    private String Author;

    /**
     * 图片样式
     */
    private String NewsDetailStyle;

    @Transient
    private String Status;



    private String NoHtmlContent;

    /**
     * 样式
     */

    private String ResourceCSS;
    private String VoteType;
    private String VoteRemark;
    private String VoteSubject1Name;
    private String VoteSubject2Name;
    private String VoteNum;
    private String VoteSubject1;
    private String VoteSubject2;
    private String VoteSubject1Percent;
    private String VoteSubject2Percent;

    /**
     * 底部广告
     */
    private String ADLinkUrl;
    private String ADimgUrl;
    private String ADName;

    public String getADLinkUrl() {
        return ADLinkUrl;
    }

    public void setADLinkUrl(String ADLinkUrl) {
        this.ADLinkUrl = ADLinkUrl;
    }

    public String getADimgUrl() {
        return ADimgUrl;
    }

    public void setADimgUrl(String ADimgUrl) {
        this.ADimgUrl = ADimgUrl;
    }

    public String getADName() {
        return ADName;
    }

    public void setADName(String ADName) {
        this.ADName = ADName;
    }

    public String getNoHtmlContent() {
        return NoHtmlContent;
    }

    public void setNoHtmlContent(String noHtmlContent) {
        NoHtmlContent = noHtmlContent;
    }
    @Transient
    private List<ADModel> Ad;

    public List<ADModel> getAd() {
        return Ad;
    }

    public void setAd(List<ADModel> ad) {
        Ad = ad;
    }

    @Transient
    private List<NewsListModel> relatedNewLists;

    @Generated(hash = 1700453335)
    public NewsDetailModel() {
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

    public String getResourceGUID() {
        return this.ResourceGUID;
    }

    public void setResourceGUID(String ResourceGUID) {
        this.ResourceGUID = ResourceGUID;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSummary() {
        return this.Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getContent() {
        return this.Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getSourceForm() {
        return this.SourceForm;
    }

    public void setSourceForm(String SourceForm) {
        this.SourceForm = SourceForm;
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

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getResourceType() {
        return this.ResourceType;
    }

    public void setResourceType(String ResourceType) {
        this.ResourceType = ResourceType;
    }

    public String getSmallPicUrl() {
        return this.SmallPicUrl;
    }

    public void setSmallPicUrl(String SmallPicUrl) {
        this.SmallPicUrl = SmallPicUrl;
    }

    public String getUploadPicNames() {
        return this.UploadPicNames;
    }

    public void setUploadPicNames(String UploadPicNames) {
        this.UploadPicNames = UploadPicNames;
    }

    public String getIsComment() {
        return this.IsComment;
    }

    public void setIsComment(String IsComment) {
        this.IsComment = IsComment;
    }

    public String getChID() {
        return this.ChID;
    }

    public void setChID(String ChID) {
        this.ChID = ChID;
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

    public String getJournalist() {
        return this.Journalist;
    }

    public void setJournalist(String Journalist) {
        this.Journalist = Journalist;
    }


    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    public String getThemeName() {
        return this.ThemeName;
    }

    public void setThemeName(String ThemeName) {
        this.ThemeName = ThemeName;
    }

    public String getIsCommentNoName() {
        return this.IsCommentNoName;
    }

    public void setIsCommentNoName(String IsCommentNoName) {
        this.IsCommentNoName = IsCommentNoName;
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

    public String getComments() {
        return this.Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public String getFakeReadNo() {
        return this.FakeReadNo;
    }

    public void setFakeReadNo(String FakeReadNo) {
        this.FakeReadNo = FakeReadNo;
    }

    public String getNewsGetGoodPoint() {
        return this.NewsGetGoodPoint;
    }

    public void setNewsGetGoodPoint(String NewsGetGoodPoint) {
        this.NewsGetGoodPoint = NewsGetGoodPoint;
    }

    public String getThemeID() {
        return this.ThemeID;
    }

    public void setThemeID(String ThemeID) {
        this.ThemeID = ThemeID;
    }

    public String getAudioUrl() {
        return this.AudioUrl;
    }

    public void setAudioUrl(String AudioUrl) {
        this.AudioUrl = AudioUrl;
    }

    public String getAudioLength() {
        return this.AudioLength;
    }

    public void setAudioLength(String AudioLength) {
        this.AudioLength = AudioLength;
    }

    public String getRPNum() {
        return this.RPNum;
    }

    public void setRPNum(String RPNum) {
        this.RPNum = RPNum;
    }

    public String getKeyWords() {
        return this.KeyWords;
    }

    public void setKeyWords(String KeyWords) {
        this.KeyWords = KeyWords;
    }

    public String getResourceCSS() {
        return this.ResourceCSS;
    }

    public void setResourceCSS(String ResourceCSS) {
        this.ResourceCSS = ResourceCSS;
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

    public String getNewsDetailStyle() {
        return NewsDetailStyle;
    }

    public void setNewsDetailStyle(String newsDetailStyle) {
        NewsDetailStyle = newsDetailStyle;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<NewsListModel> getRelatedNewLists() {
        return this.relatedNewLists;
    }



    public void setRelatedNewLists(List<NewsListModel> relatedNewLists) {
        this.relatedNewLists = relatedNewLists;
    }


    public List<RelatedReaderModel> getRelatedNews() {
        return RelatedNews;
    }

    public void setRelatedNews(List<RelatedReaderModel> relatedNews) {
        RelatedNews = relatedNews;
    }

    public static final class ChannelModelConverter implements PropertyConverter<NewsDetailModel, String> {
        @Override
        public NewsDetailModel convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseObject(databaseValue, NewsDetailModel.class);
        }

        @Override
        public String convertToDatabaseValue(NewsDetailModel entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }

    public static final class ChannelModelListConverter implements PropertyConverter<List<NewsDetailModel>, String> {
        @Override
        public List<NewsDetailModel> convertToEntityProperty(String databaseValue) {
            return JsonUtil.parseArray(databaseValue, NewsDetailModel.class);
        }

        @Override
        public String convertToDatabaseValue(List<NewsDetailModel> entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.ID);
        dest.writeString(this.ResourceGUID);
        dest.writeString(this.Title);
        dest.writeString(this.subTitle);
        dest.writeString(this.Summary);
        dest.writeString(this.Content);
        dest.writeString(this.SourceForm);
        dest.writeString(this.UpdateTime);
        dest.writeString(this.AuditTime);
        dest.writeString(this.CreateTime);
        dest.writeString(this.ResourceType);
        dest.writeString(this.SmallPicUrl);
        dest.writeString(this.UploadPicNames);
        dest.writeString(this.IsComment);
        dest.writeString(this.ChID);
        dest.writeString(this.ShowOrder);
        dest.writeString(this.Longitude);
        dest.writeString(this.Latitude);
        dest.writeString(this.ReadNo);
        dest.writeString(this.BandChID);
        dest.writeString(this.CommentNum);
        dest.writeString(this.Journalist);
        dest.writeTypedList(this.RelatedNews);
        dest.writeString(this.ChannelName);
        dest.writeString(this.ThemeName);
        dest.writeString(this.IsCommentNoName);
        dest.writeString(this.ResourceFlag);
        dest.writeString(this.ResourceUrl);
        dest.writeString(this.Comments);
        dest.writeString(this.FakeReadNo);
        dest.writeString(this.NewsGetGoodPoint);
        dest.writeString(this.ThemeID);
        dest.writeString(this.AudioUrl);
        dest.writeString(this.AudioLength);
        dest.writeString(this.RPNum);
        dest.writeString(this.KeyWords);
        dest.writeString(this.Author);
        dest.writeString(this.NewsDetailStyle);
        dest.writeString(this.Status);
        dest.writeString(this.NoHtmlContent);
        dest.writeString(this.ResourceCSS);
        dest.writeString(this.VoteType);
        dest.writeString(this.VoteRemark);
        dest.writeString(this.VoteSubject1Name);
        dest.writeString(this.VoteSubject2Name);
        dest.writeString(this.VoteNum);
        dest.writeString(this.VoteSubject1);
        dest.writeString(this.VoteSubject2);
        dest.writeString(this.VoteSubject1Percent);
        dest.writeString(this.VoteSubject2Percent);
        dest.writeString(this.ADLinkUrl);
        dest.writeString(this.ADimgUrl);
        dest.writeString(this.ADName);
        dest.writeTypedList(this.Ad);
        dest.writeTypedList(this.relatedNewLists);
    }

    protected NewsDetailModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.ResourceGUID = in.readString();
        this.Title = in.readString();
        this.subTitle = in.readString();
        this.Summary = in.readString();
        this.Content = in.readString();
        this.SourceForm = in.readString();
        this.UpdateTime = in.readString();
        this.AuditTime = in.readString();
        this.CreateTime = in.readString();
        this.ResourceType = in.readString();
        this.SmallPicUrl = in.readString();
        this.UploadPicNames = in.readString();
        this.IsComment = in.readString();
        this.ChID = in.readString();
        this.ShowOrder = in.readString();
        this.Longitude = in.readString();
        this.Latitude = in.readString();
        this.ReadNo = in.readString();
        this.BandChID = in.readString();
        this.CommentNum = in.readString();
        this.Journalist = in.readString();
        this.RelatedNews = in.createTypedArrayList(RelatedReaderModel.CREATOR);
        this.ChannelName = in.readString();
        this.ThemeName = in.readString();
        this.IsCommentNoName = in.readString();
        this.ResourceFlag = in.readString();
        this.ResourceUrl = in.readString();
        this.Comments = in.readString();
        this.FakeReadNo = in.readString();
        this.NewsGetGoodPoint = in.readString();
        this.ThemeID = in.readString();
        this.AudioUrl = in.readString();
        this.AudioLength = in.readString();
        this.RPNum = in.readString();
        this.KeyWords = in.readString();
        this.Author = in.readString();
        this.NewsDetailStyle = in.readString();
        this.Status = in.readString();
        this.NoHtmlContent = in.readString();
        this.ResourceCSS = in.readString();
        this.VoteType = in.readString();
        this.VoteRemark = in.readString();
        this.VoteSubject1Name = in.readString();
        this.VoteSubject2Name = in.readString();
        this.VoteNum = in.readString();
        this.VoteSubject1 = in.readString();
        this.VoteSubject2 = in.readString();
        this.VoteSubject1Percent = in.readString();
        this.VoteSubject2Percent = in.readString();
        this.ADLinkUrl = in.readString();
        this.ADimgUrl = in.readString();
        this.ADName = in.readString();
        this.Ad = in.createTypedArrayList(ADModel.CREATOR);
        this.relatedNewLists = in.createTypedArrayList(NewsListModel.CREATOR);
    }

    @Generated(hash = 1892310835)
    public NewsDetailModel(Long _id, String ID, String ResourceGUID, String Title, String subTitle, String Summary,
            String Content, String SourceForm, String UpdateTime, String AuditTime, String CreateTime, String ResourceType,
            String SmallPicUrl, String UploadPicNames, String IsComment, String ChID, String ShowOrder, String Longitude,
            String Latitude, String ReadNo, String BandChID, String CommentNum, String Journalist, String ChannelName,
            String ThemeName, String IsCommentNoName, String ResourceFlag, String ResourceUrl, String Comments,
            String FakeReadNo, String NewsGetGoodPoint, String ThemeID, String AudioUrl, String AudioLength, String RPNum,
            String KeyWords, String Author, String NewsDetailStyle, String NoHtmlContent, String ResourceCSS,
            String VoteType, String VoteRemark, String VoteSubject1Name, String VoteSubject2Name, String VoteNum,
            String VoteSubject1, String VoteSubject2, String VoteSubject1Percent, String VoteSubject2Percent,
            String ADLinkUrl, String ADimgUrl, String ADName) {
        this._id = _id;
        this.ID = ID;
        this.ResourceGUID = ResourceGUID;
        this.Title = Title;
        this.subTitle = subTitle;
        this.Summary = Summary;
        this.Content = Content;
        this.SourceForm = SourceForm;
        this.UpdateTime = UpdateTime;
        this.AuditTime = AuditTime;
        this.CreateTime = CreateTime;
        this.ResourceType = ResourceType;
        this.SmallPicUrl = SmallPicUrl;
        this.UploadPicNames = UploadPicNames;
        this.IsComment = IsComment;
        this.ChID = ChID;
        this.ShowOrder = ShowOrder;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.ReadNo = ReadNo;
        this.BandChID = BandChID;
        this.CommentNum = CommentNum;
        this.Journalist = Journalist;
        this.ChannelName = ChannelName;
        this.ThemeName = ThemeName;
        this.IsCommentNoName = IsCommentNoName;
        this.ResourceFlag = ResourceFlag;
        this.ResourceUrl = ResourceUrl;
        this.Comments = Comments;
        this.FakeReadNo = FakeReadNo;
        this.NewsGetGoodPoint = NewsGetGoodPoint;
        this.ThemeID = ThemeID;
        this.AudioUrl = AudioUrl;
        this.AudioLength = AudioLength;
        this.RPNum = RPNum;
        this.KeyWords = KeyWords;
        this.Author = Author;
        this.NewsDetailStyle = NewsDetailStyle;
        this.NoHtmlContent = NoHtmlContent;
        this.ResourceCSS = ResourceCSS;
        this.VoteType = VoteType;
        this.VoteRemark = VoteRemark;
        this.VoteSubject1Name = VoteSubject1Name;
        this.VoteSubject2Name = VoteSubject2Name;
        this.VoteNum = VoteNum;
        this.VoteSubject1 = VoteSubject1;
        this.VoteSubject2 = VoteSubject2;
        this.VoteSubject1Percent = VoteSubject1Percent;
        this.VoteSubject2Percent = VoteSubject2Percent;
        this.ADLinkUrl = ADLinkUrl;
        this.ADimgUrl = ADimgUrl;
        this.ADName = ADName;
    }

    public static final Creator<NewsDetailModel> CREATOR = new Creator<NewsDetailModel>() {
        @Override
        public NewsDetailModel createFromParcel(Parcel source) {
            return new NewsDetailModel(source);
        }

        @Override
        public NewsDetailModel[] newArray(int size) {
            return new NewsDetailModel[size];
        }
    };
}
