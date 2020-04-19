package com.dingtai.android.library.news.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ChannelModel implements Parcelable {

    @Id
    private Long _id;

    private String parentId;
    /**
     * 栏目ID
     */
    private String ID;
    /**
     * 栏目名称
     */
    private String ChannelName;
    /**
     * 栏目Logo
     */
    private String ImageUrl;
    /**
     * 是否有头图广告
     */
    private String IsAd;
    /**
     * 是否首页显示
     */
    private String IsShowHome;
    /**
     * 是否专题
     */
    private String IsTopic;
    //排序
    private String ShowOrder;
    //是否有上级
    private String HadChild;
    //是否订阅
    private String Issubscribe;
    /**
     * 是否跳转网页
     */
    private String IsHtml;
    /**
     * 链接网址
     */
    private String ChannelUrl;
    private String ReMark;
    private String isDingYue = "false";
    /**
     * 是否是固定栏目
     */
    private String IsDel = "false";

    private String StID;

    private String Type;

    /**
     * 英文名
     */
    private String EnChName;
    /**
     * 大字体栏目名
     */
    private String BigFont;
    /**
     *  部分项目用通栏图
     */
    private String ChannelLogo;

    public String getChannelLogo() {
        return ChannelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        ChannelLogo = channelLogo;
    }

    @Generated(hash = 456707983)
    public ChannelModel(Long _id, String parentId, String ID, String ChannelName, String ImageUrl, String IsAd,
            String IsShowHome, String IsTopic, String ShowOrder, String HadChild, String Issubscribe,
            String IsHtml, String ChannelUrl, String ReMark, String isDingYue, String IsDel, String StID,
            String Type, String EnChName, String BigFont, String ChannelLogo) {
        this._id = _id;
        this.parentId = parentId;
        this.ID = ID;
        this.ChannelName = ChannelName;
        this.ImageUrl = ImageUrl;
        this.IsAd = IsAd;
        this.IsShowHome = IsShowHome;
        this.IsTopic = IsTopic;
        this.ShowOrder = ShowOrder;
        this.HadChild = HadChild;
        this.Issubscribe = Issubscribe;
        this.IsHtml = IsHtml;
        this.ChannelUrl = ChannelUrl;
        this.ReMark = ReMark;
        this.isDingYue = isDingYue;
        this.IsDel = IsDel;
        this.StID = StID;
        this.Type = Type;
        this.EnChName = EnChName;
        this.BigFont = BigFont;
        this.ChannelLogo = ChannelLogo;
    }

    @Generated(hash = 706581214)
    public ChannelModel() {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChannelName() {
        return this.ChannelName;
    }

    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    public String getImageUrl() {
        return this.ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getIsAd() {
        return this.IsAd;
    }

    public void setIsAd(String IsAd) {
        this.IsAd = IsAd;
    }

    public String getIsShowHome() {
        return this.IsShowHome;
    }

    public void setIsShowHome(String IsShowHome) {
        this.IsShowHome = IsShowHome;
    }

    public String getIsTopic() {
        return this.IsTopic;
    }

    public void setIsTopic(String IsTopic) {
        this.IsTopic = IsTopic;
    }

    public String getShowOrder() {
        return this.ShowOrder;
    }

    public void setShowOrder(String ShowOrder) {
        this.ShowOrder = ShowOrder;
    }

    public String getHadChild() {
        return this.HadChild;
    }

    public void setHadChild(String HadChild) {
        this.HadChild = HadChild;
    }

    public String getIssubscribe() {
        return this.Issubscribe;
    }

    public void setIssubscribe(String Issubscribe) {
        this.Issubscribe = Issubscribe;
    }

    public String getIsHtml() {
        return this.IsHtml;
    }

    public void setIsHtml(String IsHtml) {
        this.IsHtml = IsHtml;
    }

    public String getChannelUrl() {
        return this.ChannelUrl;
    }

    public void setChannelUrl(String ChannelUrl) {
        this.ChannelUrl = ChannelUrl;
    }

    public String getReMark() {
        return this.ReMark;
    }

    public void setReMark(String ReMark) {
        this.ReMark = ReMark;
    }

    public String getIsDingYue() {
        return this.isDingYue;
    }

    public void setIsDingYue(String isDingYue) {
        this.isDingYue = isDingYue;
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

    public String getBigFont() {
        return this.BigFont;
    }

    public void setBigFont(String BigFont) {
        this.BigFont = BigFont;
    }

    public String getEnChName() {
        return this.EnChName;
    }

    public void setEnChName(String EnChName) {
        this.EnChName = EnChName;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ChannelModel && TextUtils.equals(ID, ((ChannelModel) obj).ID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.parentId);
        dest.writeString(this.ID);
        dest.writeString(this.ChannelName);
        dest.writeString(this.ImageUrl);
        dest.writeString(this.IsAd);
        dest.writeString(this.IsShowHome);
        dest.writeString(this.IsTopic);
        dest.writeString(this.ShowOrder);
        dest.writeString(this.HadChild);
        dest.writeString(this.Issubscribe);
        dest.writeString(this.IsHtml);
        dest.writeString(this.ChannelUrl);
        dest.writeString(this.ReMark);
        dest.writeString(this.isDingYue);
        dest.writeString(this.IsDel);
        dest.writeString(this.StID);
        dest.writeString(this.Type);
        dest.writeString(this.EnChName);
        dest.writeString(this.BigFont);
        dest.writeString(this.ChannelLogo);
    }

    protected ChannelModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.parentId = in.readString();
        this.ID = in.readString();
        this.ChannelName = in.readString();
        this.ImageUrl = in.readString();
        this.IsAd = in.readString();
        this.IsShowHome = in.readString();
        this.IsTopic = in.readString();
        this.ShowOrder = in.readString();
        this.HadChild = in.readString();
        this.Issubscribe = in.readString();
        this.IsHtml = in.readString();
        this.ChannelUrl = in.readString();
        this.ReMark = in.readString();
        this.isDingYue = in.readString();
        this.IsDel = in.readString();
        this.StID = in.readString();
        this.Type = in.readString();
        this.EnChName = in.readString();
        this.BigFont = in.readString();
        this.ChannelLogo = in.readString();
    }

    public static final Creator<ChannelModel> CREATOR = new Creator<ChannelModel>() {
        @Override
        public ChannelModel createFromParcel(Parcel source) {
            return new ChannelModel(source);
        }

        @Override
        public ChannelModel[] newArray(int size) {
            return new ChannelModel[size];
        }
    };
}
