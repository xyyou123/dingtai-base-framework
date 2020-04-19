package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * author:lnr
 * date:2018/8/30
 * 直播列表
 */
@Entity
public class LiveChannelModel implements Parcelable {

    @Id
    private Long _id;

    private String ID;
    // 创建时间
    private String CreateTime;
    // 频道名称
    private String LiveChannelName;
    // 频道图片地址
    private String LiveImageUrl;
    private String LiveProgramName;
    private String LiveProgramDate;
    // 父节点ID
    private String ParentID;
    // 是否隐藏
    private String IsHide;
    // 是否头图广告
    private String IsAD;
    // 是否固定
    private String IsShowHome;
    // 是否删除
    private String IsDel;
    // 站点ID
    private String StID;
    // 排序号
    private String ShowOrder;
    // 图片地址
    private String PicPath;
    // 视频URL
    private String VideoUrl;
    // 随机数
    private String LiveRandomNum;
    // 修改随机数
    private String LiveNativeRandomNum;
    // 状态
    private String Status;
    // 备注
    private String ReMark;
    // 直播地址
    private String LiveRTMPUrl;
    // 评论
    private String CommentsNum;
    // zhonglie
    private String LiveType;
    // zhonglie
    private String Week;
    private String LiveBeginDate;
    private String LiveBeginLogo;
    private String LiveNewChID;
    private String LiveEventID;
    private String LiveEndDate;
    private String IsLive;
    private String ReadNo;
    private String LiveLink;//回看地址
    private String LiveBeginStatus;
    private String LiveChannleLogo;

    private String LiveBeginMedia;
    private String LiveBeginType;
    private String BannerImgUrl;
    private String LiveIntroduce;
    private String LiveVideoLogo;
    private String LiveVideoLogoPosition;
    private String LiveEndType;
    private String LiveEndMedia;
    private String LiveEndLogo;
    private String IsIntroduce;
    private String IsTopAD;
    private String GetGoodPoint;
    private String Iswebview;
    private String Webview;
    private String GoodUsers;
    private String isZaned;
    //分享地址(优先级最高)
    private String UserUrl;
    private String authenticationflag;

    public String getAuthenticationflag() {
        return authenticationflag;
    }

    public void setAuthenticationflag(String authenticationflag) {
        this.authenticationflag = authenticationflag;
    }

    @Convert(converter = LiveChildTab.LiveChildTabConConverter.class, columnType = String.class)
    private List<LiveChildTab> TabList;


    @Generated(hash = 540406365)
    public LiveChannelModel() {
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

    public String getLiveChannelName() {
        return this.LiveChannelName;
    }

    public void setLiveChannelName(String LiveChannelName) {
        this.LiveChannelName = LiveChannelName;
    }

    public String getLiveImageUrl() {
        return this.LiveImageUrl;
    }

    public void setLiveImageUrl(String LiveImageUrl) {
        this.LiveImageUrl = LiveImageUrl;
    }

    public String getLiveProgramName() {
        return this.LiveProgramName;
    }

    public void setLiveProgramName(String LiveProgramName) {
        this.LiveProgramName = LiveProgramName;
    }

    public String getLiveProgramDate() {
        return this.LiveProgramDate;
    }

    public void setLiveProgramDate(String LiveProgramDate) {
        this.LiveProgramDate = LiveProgramDate;
    }

    public String getParentID() {
        return this.ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public String getIsHide() {
        return this.IsHide;
    }

    public void setIsHide(String IsHide) {
        this.IsHide = IsHide;
    }

    public String getIsAD() {
        return this.IsAD;
    }

    public void setIsAD(String IsAD) {
        this.IsAD = IsAD;
    }

    public String getIsShowHome() {
        return this.IsShowHome;
    }

    public void setIsShowHome(String IsShowHome) {
        this.IsShowHome = IsShowHome;
    }

    public String getIsDel() {
        return this.IsDel;
    }

    public void setIsDel(String IsDel) {
        this.IsDel = IsDel;
    }

    public String getStID() {
        return this.StID;
    }

    public void setStID(String StID) {
        this.StID = StID;
    }

    public String getShowOrder() {
        return this.ShowOrder;
    }

    public void setShowOrder(String ShowOrder) {
        this.ShowOrder = ShowOrder;
    }

    public String getPicPath() {
        return this.PicPath;
    }

    public void setPicPath(String PicPath) {
        this.PicPath = PicPath;
    }

    public String getVideoUrl() {
        return this.VideoUrl;
    }

    public void setVideoUrl(String VideoUrl) {
        this.VideoUrl = VideoUrl;
    }

    public String getLiveRandomNum() {
        return this.LiveRandomNum;
    }

    public void setLiveRandomNum(String LiveRandomNum) {
        this.LiveRandomNum = LiveRandomNum;
    }

    public String getLiveNativeRandomNum() {
        return this.LiveNativeRandomNum;
    }

    public void setLiveNativeRandomNum(String LiveNativeRandomNum) {
        this.LiveNativeRandomNum = LiveNativeRandomNum;
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

    public String getLiveRTMPUrl() {
        return this.LiveRTMPUrl;
    }

    public void setLiveRTMPUrl(String LiveRTMPUrl) {
        this.LiveRTMPUrl = LiveRTMPUrl;
    }

    public String getCommentsNum() {
        return this.CommentsNum;
    }

    public void setCommentsNum(String CommentsNum) {
        this.CommentsNum = CommentsNum;
    }

    public String getLiveType() {
        return this.LiveType;
    }

    public void setLiveType(String LiveType) {
        this.LiveType = LiveType;
    }

    public String getWeek() {
        return this.Week;
    }

    public void setWeek(String Week) {
        this.Week = Week;
    }

    public String getLiveBeginDate() {
        return this.LiveBeginDate;
    }

    public void setLiveBeginDate(String LiveBeginDate) {
        this.LiveBeginDate = LiveBeginDate;
    }

    public String getLiveBeginLogo() {
        return this.LiveBeginLogo;
    }

    public void setLiveBeginLogo(String LiveBeginLogo) {
        this.LiveBeginLogo = LiveBeginLogo;
    }

    public String getLiveNewChID() {
        return this.LiveNewChID;
    }

    public void setLiveNewChID(String LiveNewChID) {
        this.LiveNewChID = LiveNewChID;
    }

    public String getLiveEventID() {
        return this.LiveEventID;
    }

    public void setLiveEventID(String LiveEventID) {
        this.LiveEventID = LiveEventID;
    }

    public String getLiveEndDate() {
        return this.LiveEndDate;
    }

    public void setLiveEndDate(String LiveEndDate) {
        this.LiveEndDate = LiveEndDate;
    }

    public String getIsLive() {
        return this.IsLive;
    }

    public void setIsLive(String IsLive) {
        this.IsLive = IsLive;
    }

    public String getReadNo() {
        return this.ReadNo;
    }

    public void setReadNo(String ReadNo) {
        this.ReadNo = ReadNo;
    }

    public String getLiveLink() {
        return this.LiveLink;
    }

    public void setLiveLink(String LiveLink) {
        this.LiveLink = LiveLink;
    }

    public String getLiveBeginStatus() {
        return this.LiveBeginStatus;
    }

    public void setLiveBeginStatus(String LiveBeginStatus) {
        this.LiveBeginStatus = LiveBeginStatus;
    }

    public String getLiveChannleLogo() {
        return this.LiveChannleLogo;
    }

    public void setLiveChannleLogo(String LiveChannleLogo) {
        this.LiveChannleLogo = LiveChannleLogo;
    }

    public String getLiveBeginMedia() {
        return this.LiveBeginMedia;
    }

    public void setLiveBeginMedia(String LiveBeginMedia) {
        this.LiveBeginMedia = LiveBeginMedia;
    }

    public String getLiveBeginType() {
        return this.LiveBeginType;
    }

    public void setLiveBeginType(String LiveBeginType) {
        this.LiveBeginType = LiveBeginType;
    }

    public String getBannerImgUrl() {
        return this.BannerImgUrl;
    }

    public void setBannerImgUrl(String BannerImgUrl) {
        this.BannerImgUrl = BannerImgUrl;
    }

    public String getLiveIntroduce() {
        return this.LiveIntroduce;
    }

    public void setLiveIntroduce(String LiveIntroduce) {
        this.LiveIntroduce = LiveIntroduce;
    }

    public String getLiveVideoLogo() {
        return this.LiveVideoLogo;
    }

    public void setLiveVideoLogo(String LiveVideoLogo) {
        this.LiveVideoLogo = LiveVideoLogo;
    }

    public String getLiveVideoLogoPosition() {
        return this.LiveVideoLogoPosition;
    }

    public void setLiveVideoLogoPosition(String LiveVideoLogoPosition) {
        this.LiveVideoLogoPosition = LiveVideoLogoPosition;
    }

    public String getLiveEndType() {
        return this.LiveEndType;
    }

    public void setLiveEndType(String LiveEndType) {
        this.LiveEndType = LiveEndType;
    }

    public String getLiveEndMedia() {
        return this.LiveEndMedia;
    }

    public void setLiveEndMedia(String LiveEndMedia) {
        this.LiveEndMedia = LiveEndMedia;
    }

    public String getLiveEndLogo() {
        return this.LiveEndLogo;
    }

    public void setLiveEndLogo(String LiveEndLogo) {
        this.LiveEndLogo = LiveEndLogo;
    }

    public String getIsIntroduce() {
        return this.IsIntroduce;
    }

    public void setIsIntroduce(String IsIntroduce) {
        this.IsIntroduce = IsIntroduce;
    }

    public String getIsTopAD() {
        return this.IsTopAD;
    }

    public void setIsTopAD(String IsTopAD) {
        this.IsTopAD = IsTopAD;
    }

    public String getGetGoodPoint() {
        return this.GetGoodPoint;
    }

    public void setGetGoodPoint(String GetGoodPoint) {
        this.GetGoodPoint = GetGoodPoint;
    }

    public String getIswebview() {
        return this.Iswebview;
    }

    public void setIswebview(String Iswebview) {
        this.Iswebview = Iswebview;
    }

    public String getWebview() {
        return this.Webview;
    }

    public void setWebview(String Webview) {
        this.Webview = Webview;
    }

    public List<LiveChildTab> getTabList() {
        return this.TabList;
    }

    public void setTabList(List<LiveChildTab> TabList) {
        this.TabList = TabList;
    }



    public String getGoodUsers() {
        return this.GoodUsers;
    }



    public void setGoodUsers(String GoodUsers) {
        this.GoodUsers = GoodUsers;
    }



    public String getIsZaned() {
        return this.isZaned;
    }



    public void setIsZaned(String isZaned) {
        this.isZaned = isZaned;
    }


    public String getUserUrl() {
        return this.UserUrl;
    }


    public void setUserUrl(String UserUrl) {
        this.UserUrl = UserUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.ID);
        dest.writeString(this.CreateTime);
        dest.writeString(this.LiveChannelName);
        dest.writeString(this.LiveImageUrl);
        dest.writeString(this.LiveProgramName);
        dest.writeString(this.LiveProgramDate);
        dest.writeString(this.ParentID);
        dest.writeString(this.IsHide);
        dest.writeString(this.IsAD);
        dest.writeString(this.IsShowHome);
        dest.writeString(this.IsDel);
        dest.writeString(this.StID);
        dest.writeString(this.ShowOrder);
        dest.writeString(this.PicPath);
        dest.writeString(this.VideoUrl);
        dest.writeString(this.LiveRandomNum);
        dest.writeString(this.LiveNativeRandomNum);
        dest.writeString(this.Status);
        dest.writeString(this.ReMark);
        dest.writeString(this.LiveRTMPUrl);
        dest.writeString(this.CommentsNum);
        dest.writeString(this.LiveType);
        dest.writeString(this.Week);
        dest.writeString(this.LiveBeginDate);
        dest.writeString(this.LiveBeginLogo);
        dest.writeString(this.LiveNewChID);
        dest.writeString(this.LiveEventID);
        dest.writeString(this.LiveEndDate);
        dest.writeString(this.IsLive);
        dest.writeString(this.ReadNo);
        dest.writeString(this.LiveLink);
        dest.writeString(this.LiveBeginStatus);
        dest.writeString(this.LiveChannleLogo);
        dest.writeString(this.LiveBeginMedia);
        dest.writeString(this.LiveBeginType);
        dest.writeString(this.BannerImgUrl);
        dest.writeString(this.LiveIntroduce);
        dest.writeString(this.LiveVideoLogo);
        dest.writeString(this.LiveVideoLogoPosition);
        dest.writeString(this.LiveEndType);
        dest.writeString(this.LiveEndMedia);
        dest.writeString(this.LiveEndLogo);
        dest.writeString(this.IsIntroduce);
        dest.writeString(this.IsTopAD);
        dest.writeString(this.GetGoodPoint);
        dest.writeString(this.Iswebview);
        dest.writeString(this.Webview);
        dest.writeString(this.GoodUsers);
        dest.writeString(this.isZaned);
        dest.writeString(this.UserUrl);
        dest.writeString(this.authenticationflag);
        dest.writeTypedList(this.TabList);
    }

    protected LiveChannelModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.CreateTime = in.readString();
        this.LiveChannelName = in.readString();
        this.LiveImageUrl = in.readString();
        this.LiveProgramName = in.readString();
        this.LiveProgramDate = in.readString();
        this.ParentID = in.readString();
        this.IsHide = in.readString();
        this.IsAD = in.readString();
        this.IsShowHome = in.readString();
        this.IsDel = in.readString();
        this.StID = in.readString();
        this.ShowOrder = in.readString();
        this.PicPath = in.readString();
        this.VideoUrl = in.readString();
        this.LiveRandomNum = in.readString();
        this.LiveNativeRandomNum = in.readString();
        this.Status = in.readString();
        this.ReMark = in.readString();
        this.LiveRTMPUrl = in.readString();
        this.CommentsNum = in.readString();
        this.LiveType = in.readString();
        this.Week = in.readString();
        this.LiveBeginDate = in.readString();
        this.LiveBeginLogo = in.readString();
        this.LiveNewChID = in.readString();
        this.LiveEventID = in.readString();
        this.LiveEndDate = in.readString();
        this.IsLive = in.readString();
        this.ReadNo = in.readString();
        this.LiveLink = in.readString();
        this.LiveBeginStatus = in.readString();
        this.LiveChannleLogo = in.readString();
        this.LiveBeginMedia = in.readString();
        this.LiveBeginType = in.readString();
        this.BannerImgUrl = in.readString();
        this.LiveIntroduce = in.readString();
        this.LiveVideoLogo = in.readString();
        this.LiveVideoLogoPosition = in.readString();
        this.LiveEndType = in.readString();
        this.LiveEndMedia = in.readString();
        this.LiveEndLogo = in.readString();
        this.IsIntroduce = in.readString();
        this.IsTopAD = in.readString();
        this.GetGoodPoint = in.readString();
        this.Iswebview = in.readString();
        this.Webview = in.readString();
        this.GoodUsers = in.readString();
        this.isZaned = in.readString();
        this.UserUrl = in.readString();
        this.authenticationflag = in.readString();
        this.TabList = in.createTypedArrayList(LiveChildTab.CREATOR);
    }

    @Generated(hash = 1284969056)
    public LiveChannelModel(Long _id, String ID, String CreateTime, String LiveChannelName,
            String LiveImageUrl, String LiveProgramName, String LiveProgramDate, String ParentID,
            String IsHide, String IsAD, String IsShowHome, String IsDel, String StID, String ShowOrder,
            String PicPath, String VideoUrl, String LiveRandomNum, String LiveNativeRandomNum,
            String Status, String ReMark, String LiveRTMPUrl, String CommentsNum, String LiveType,
            String Week, String LiveBeginDate, String LiveBeginLogo, String LiveNewChID,
            String LiveEventID, String LiveEndDate, String IsLive, String ReadNo, String LiveLink,
            String LiveBeginStatus, String LiveChannleLogo, String LiveBeginMedia, String LiveBeginType,
            String BannerImgUrl, String LiveIntroduce, String LiveVideoLogo,
            String LiveVideoLogoPosition, String LiveEndType, String LiveEndMedia, String LiveEndLogo,
            String IsIntroduce, String IsTopAD, String GetGoodPoint, String Iswebview, String Webview,
            String GoodUsers, String isZaned, String UserUrl, String authenticationflag,
            List<LiveChildTab> TabList) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.LiveChannelName = LiveChannelName;
        this.LiveImageUrl = LiveImageUrl;
        this.LiveProgramName = LiveProgramName;
        this.LiveProgramDate = LiveProgramDate;
        this.ParentID = ParentID;
        this.IsHide = IsHide;
        this.IsAD = IsAD;
        this.IsShowHome = IsShowHome;
        this.IsDel = IsDel;
        this.StID = StID;
        this.ShowOrder = ShowOrder;
        this.PicPath = PicPath;
        this.VideoUrl = VideoUrl;
        this.LiveRandomNum = LiveRandomNum;
        this.LiveNativeRandomNum = LiveNativeRandomNum;
        this.Status = Status;
        this.ReMark = ReMark;
        this.LiveRTMPUrl = LiveRTMPUrl;
        this.CommentsNum = CommentsNum;
        this.LiveType = LiveType;
        this.Week = Week;
        this.LiveBeginDate = LiveBeginDate;
        this.LiveBeginLogo = LiveBeginLogo;
        this.LiveNewChID = LiveNewChID;
        this.LiveEventID = LiveEventID;
        this.LiveEndDate = LiveEndDate;
        this.IsLive = IsLive;
        this.ReadNo = ReadNo;
        this.LiveLink = LiveLink;
        this.LiveBeginStatus = LiveBeginStatus;
        this.LiveChannleLogo = LiveChannleLogo;
        this.LiveBeginMedia = LiveBeginMedia;
        this.LiveBeginType = LiveBeginType;
        this.BannerImgUrl = BannerImgUrl;
        this.LiveIntroduce = LiveIntroduce;
        this.LiveVideoLogo = LiveVideoLogo;
        this.LiveVideoLogoPosition = LiveVideoLogoPosition;
        this.LiveEndType = LiveEndType;
        this.LiveEndMedia = LiveEndMedia;
        this.LiveEndLogo = LiveEndLogo;
        this.IsIntroduce = IsIntroduce;
        this.IsTopAD = IsTopAD;
        this.GetGoodPoint = GetGoodPoint;
        this.Iswebview = Iswebview;
        this.Webview = Webview;
        this.GoodUsers = GoodUsers;
        this.isZaned = isZaned;
        this.UserUrl = UserUrl;
        this.authenticationflag = authenticationflag;
        this.TabList = TabList;
    }

    public static final Creator<LiveChannelModel> CREATOR = new Creator<LiveChannelModel>() {
        @Override
        public LiveChannelModel createFromParcel(Parcel source) {
            return new LiveChannelModel(source);
        }

        @Override
        public LiveChannelModel[] newArray(int size) {
            return new LiveChannelModel[size];
        }
    };
}
