package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/9/11
 * 用户账户
 */
@Entity
public class AccountModel implements Parcelable {

    @Id
    private Long _id;

    private String ID;
    private String UserGUID;
    private String CreateTime;
    private String UserNickName;
    private String UserName;
    private String UserRealName;
    private String UserIcon;
    private String UserEmail;
    private String UserPhone;
    private String UserAddress;
    private String UserZipPost;
    private String UserScore;
    private String UserSource;
    private String UserSex;
    private String LoginMode;
    private String InviteCode;
    private String steps = "0";
    private String DeptName;
    private String IDNum;
    private String IsLive;
    private String LiveUrl;
    private String IsEvent;
    private String BandName;
    private String QQAccount;
    private String WeixinAccount;
    private String FootprintCount;
    private String JoinCount;
    private String WeiboAccount;
    private String IsPoliticalPower;
    private String IsVM;
    private String ProducerID;//掌上怀化 商家id

    public String getIsVM() {
        return IsVM;
    }

    public void setIsVM(String isVM) {
        IsVM = isVM;
    }

    public String getFootprintCount() {
        return FootprintCount;
    }

    public void setFootprintCount(String footprintCount) {
        FootprintCount = footprintCount;
    }

    public String getJoinCount() {
        return JoinCount;
    }

    public void setJoinCount(String joinCount) {
        JoinCount = joinCount;
    }

    public String getIsPoliticalPower() {
        return IsPoliticalPower;
    }

    public void setIsPoliticalPower(String isPoliticalPower) {
        IsPoliticalPower = isPoliticalPower;
    }

    public String getQQAccount() {
        return QQAccount;
    }

    public void setQQAccount(String QQAccount) {
        this.QQAccount = QQAccount;
    }

    public String getWeixinAccount() {
        return WeixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        WeixinAccount = weixinAccount;
    }

    public String getWeiboAccount() {
        return WeiboAccount;
    }

    public void setWeiboAccount(String weiboAccount) {
        WeiboAccount = weiboAccount;
    }

    public String getProducerID() {
        return ProducerID;
    }

    public void setProducerID(String producerID) {
        ProducerID = producerID;
    }

    /**
     * 是否实名
     */
    private String IsAuthentication;


    @Generated(hash = 202444181)
    public AccountModel() {
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

    public String getUserGUID() {
        return this.UserGUID;
    }

    public void setUserGUID(String UserGUID) {
        this.UserGUID = UserGUID;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getUserNickName() {
        return this.UserNickName;
    }

    public void setUserNickName(String UserNickName) {
        this.UserNickName = UserNickName;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserRealName() {
        return this.UserRealName;
    }

    public void setUserRealName(String UserRealName) {
        this.UserRealName = UserRealName;
    }

    public String getUserIcon() {
        return this.UserIcon;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public String getUserEmail() {
        return this.UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public String getUserPhone() {
        return this.UserPhone;
    }

    public void setUserPhone(String UserPhone) {
        this.UserPhone = UserPhone;
    }

    public String getUserAddress() {
        return this.UserAddress;
    }

    public void setUserAddress(String UserAddress) {
        this.UserAddress = UserAddress;
    }

    public String getUserZipPost() {
        return this.UserZipPost;
    }

    public void setUserZipPost(String UserZipPost) {
        this.UserZipPost = UserZipPost;
    }

    public String getUserScore() {
        return this.UserScore;
    }

    public void setUserScore(String UserScore) {
        this.UserScore = UserScore;
    }

    public String getUserSource() {
        return this.UserSource;
    }

    public void setUserSource(String UserSource) {
        this.UserSource = UserSource;
    }

    public String getUserSex() {
        return this.UserSex;
    }

    public void setUserSex(String UserSex) {
        this.UserSex = UserSex;
    }

    public String getLoginMode() {
        return this.LoginMode;
    }

    public void setLoginMode(String LoginMode) {
        this.LoginMode = LoginMode;
    }

    public String getInviteCode() {
        return this.InviteCode;
    }

    public void setInviteCode(String InviteCode) {
        this.InviteCode = InviteCode;
    }

    public String getSteps() {
        return this.steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getDeptName() {
        return this.DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getIDNum() {
        return this.IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getIsLive() {
        return this.IsLive;
    }

    public void setIsLive(String IsLive) {
        this.IsLive = IsLive;
    }

    public String getLiveUrl() {
        return this.LiveUrl;
    }

    public void setLiveUrl(String LiveUrl) {
        this.LiveUrl = LiveUrl;
    }

    public String getIsEvent() {
        return this.IsEvent;
    }

    public void setIsEvent(String IsEvent) {
        this.IsEvent = IsEvent;
    }



    public String getBandName() {
        return this.BandName;
    }



    public void setBandName(String BandName) {
        this.BandName = BandName;
    }





    public String getIsAuthentication() {
        return this.IsAuthentication;
    }





    public void setIsAuthentication(String IsAuthentication) {
        this.IsAuthentication = IsAuthentication;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.ID);
        dest.writeString(this.UserGUID);
        dest.writeString(this.CreateTime);
        dest.writeString(this.UserNickName);
        dest.writeString(this.UserName);
        dest.writeString(this.UserRealName);
        dest.writeString(this.UserIcon);
        dest.writeString(this.UserEmail);
        dest.writeString(this.UserPhone);
        dest.writeString(this.UserAddress);
        dest.writeString(this.UserZipPost);
        dest.writeString(this.UserScore);
        dest.writeString(this.UserSource);
        dest.writeString(this.UserSex);
        dest.writeString(this.LoginMode);
        dest.writeString(this.InviteCode);
        dest.writeString(this.steps);
        dest.writeString(this.DeptName);
        dest.writeString(this.IDNum);
        dest.writeString(this.IsLive);
        dest.writeString(this.LiveUrl);
        dest.writeString(this.IsEvent);
        dest.writeString(this.BandName);
        dest.writeString(this.QQAccount);
        dest.writeString(this.WeixinAccount);
        dest.writeString(this.FootprintCount);
        dest.writeString(this.JoinCount);
        dest.writeString(this.WeiboAccount);
        dest.writeString(this.IsPoliticalPower);
        dest.writeString(this.IsVM);
        dest.writeString(this.ProducerID);
        dest.writeString(this.IsAuthentication);
    }

    protected AccountModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.UserGUID = in.readString();
        this.CreateTime = in.readString();
        this.UserNickName = in.readString();
        this.UserName = in.readString();
        this.UserRealName = in.readString();
        this.UserIcon = in.readString();
        this.UserEmail = in.readString();
        this.UserPhone = in.readString();
        this.UserAddress = in.readString();
        this.UserZipPost = in.readString();
        this.UserScore = in.readString();
        this.UserSource = in.readString();
        this.UserSex = in.readString();
        this.LoginMode = in.readString();
        this.InviteCode = in.readString();
        this.steps = in.readString();
        this.DeptName = in.readString();
        this.IDNum = in.readString();
        this.IsLive = in.readString();
        this.LiveUrl = in.readString();
        this.IsEvent = in.readString();
        this.BandName = in.readString();
        this.QQAccount = in.readString();
        this.WeixinAccount = in.readString();
        this.FootprintCount = in.readString();
        this.JoinCount = in.readString();
        this.WeiboAccount = in.readString();
        this.IsPoliticalPower = in.readString();
        this.IsVM = in.readString();
        this.ProducerID = in.readString();
        this.IsAuthentication = in.readString();
    }

    @Generated(hash = 512091999)
    public AccountModel(Long _id, String ID, String UserGUID, String CreateTime,
            String UserNickName, String UserName, String UserRealName, String UserIcon,
            String UserEmail, String UserPhone, String UserAddress, String UserZipPost,
            String UserScore, String UserSource, String UserSex, String LoginMode,
            String InviteCode, String steps, String DeptName, String IDNum, String IsLive,
            String LiveUrl, String IsEvent, String BandName, String QQAccount,
            String WeixinAccount, String FootprintCount, String JoinCount,
            String WeiboAccount, String IsPoliticalPower, String IsVM, String ProducerID,
            String IsAuthentication) {
        this._id = _id;
        this.ID = ID;
        this.UserGUID = UserGUID;
        this.CreateTime = CreateTime;
        this.UserNickName = UserNickName;
        this.UserName = UserName;
        this.UserRealName = UserRealName;
        this.UserIcon = UserIcon;
        this.UserEmail = UserEmail;
        this.UserPhone = UserPhone;
        this.UserAddress = UserAddress;
        this.UserZipPost = UserZipPost;
        this.UserScore = UserScore;
        this.UserSource = UserSource;
        this.UserSex = UserSex;
        this.LoginMode = LoginMode;
        this.InviteCode = InviteCode;
        this.steps = steps;
        this.DeptName = DeptName;
        this.IDNum = IDNum;
        this.IsLive = IsLive;
        this.LiveUrl = LiveUrl;
        this.IsEvent = IsEvent;
        this.BandName = BandName;
        this.QQAccount = QQAccount;
        this.WeixinAccount = WeixinAccount;
        this.FootprintCount = FootprintCount;
        this.JoinCount = JoinCount;
        this.WeiboAccount = WeiboAccount;
        this.IsPoliticalPower = IsPoliticalPower;
        this.IsVM = IsVM;
        this.ProducerID = ProducerID;
        this.IsAuthentication = IsAuthentication;
    }

    public static final Creator<AccountModel> CREATOR = new Creator<AccountModel>() {
        @Override
        public AccountModel createFromParcel(Parcel source) {
            return new AccountModel(source);
        }

        @Override
        public AccountModel[] newArray(int size) {
            return new AccountModel[size];
        }
    };
}
