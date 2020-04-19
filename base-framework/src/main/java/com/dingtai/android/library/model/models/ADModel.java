package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ADModel implements Parcelable {

    @Id
    private Long _id;

    private int GenID;

    private String ADTypeID;

    /**
     * 广告ID
     */
    private String ID;

    /**
     * 广告名称
     */
    private String ADName;

    /**
     * 广告图片
     */
    private String ImgUrl;

    /**
     * 广告链接
     */
    private String LinkUrl;

    /**
     * 广告站点ID
     */
    private String StID;

    /**
     * 广告创建时间
     */
    private String CreateTime;

    /**
     * 广告栏目ID
     */
    private String ChID;

    /**
     * 广告类型
     */
    private String ADType;

    /**
     * 广告来自
     */
    private String ADFor;

    /**
     * 广告跳转
     */
    private String LinkTo;

    /**
     *
     */
    private String ResType;

    /**
     *
     */
    private String ResUrl;
    /**
     * 直播信息
     */
    private String LiveChannel;

    protected ADModel(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        GenID = in.readInt();
        ADTypeID = in.readString();
        ID = in.readString();
        ADName = in.readString();
        ImgUrl = in.readString();
        LinkUrl = in.readString();
        StID = in.readString();
        CreateTime = in.readString();
        ChID = in.readString();
        ADType = in.readString();
        ADFor = in.readString();
        LinkTo = in.readString();
        ResType = in.readString();
        ResUrl = in.readString();
        LiveChannel = in.readString();
    }



    @Generated(hash = 672656442)
    public ADModel() {
    }



    @Generated(hash = 1190393866)
    public ADModel(Long _id, int GenID, String ADTypeID, String ID, String ADName,
            String ImgUrl, String LinkUrl, String StID, String CreateTime,
            String ChID, String ADType, String ADFor, String LinkTo, String ResType,
            String ResUrl, String LiveChannel) {
        this._id = _id;
        this.GenID = GenID;
        this.ADTypeID = ADTypeID;
        this.ID = ID;
        this.ADName = ADName;
        this.ImgUrl = ImgUrl;
        this.LinkUrl = LinkUrl;
        this.StID = StID;
        this.CreateTime = CreateTime;
        this.ChID = ChID;
        this.ADType = ADType;
        this.ADFor = ADFor;
        this.LinkTo = LinkTo;
        this.ResType = ResType;
        this.ResUrl = ResUrl;
        this.LiveChannel = LiveChannel;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeInt(GenID);
        dest.writeString(ADTypeID);
        dest.writeString(ID);
        dest.writeString(ADName);
        dest.writeString(ImgUrl);
        dest.writeString(LinkUrl);
        dest.writeString(StID);
        dest.writeString(CreateTime);
        dest.writeString(ChID);
        dest.writeString(ADType);
        dest.writeString(ADFor);
        dest.writeString(LinkTo);
        dest.writeString(ResType);
        dest.writeString(ResUrl);
        dest.writeString(LiveChannel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public int getGenID() {
        return this.GenID;
    }

    public void setGenID(int GenID) {
        this.GenID = GenID;
    }

    public String getADTypeID() {
        return this.ADTypeID;
    }

    public void setADTypeID(String ADTypeID) {
        this.ADTypeID = ADTypeID;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getADName() {
        return this.ADName;
    }

    public void setADName(String ADName) {
        this.ADName = ADName;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getLinkUrl() {
        return this.LinkUrl;
    }

    public void setLinkUrl(String LinkUrl) {
        this.LinkUrl = LinkUrl;
    }

    public String getStID() {
        return this.StID;
    }

    public void setStID(String StID) {
        this.StID = StID;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getChID() {
        return this.ChID;
    }

    public void setChID(String ChID) {
        this.ChID = ChID;
    }

    public String getADType() {
        return this.ADType;
    }

    public void setADType(String ADType) {
        this.ADType = ADType;
    }

    public String getADFor() {
        return this.ADFor;
    }

    public void setADFor(String ADFor) {
        this.ADFor = ADFor;
    }

    public String getLinkTo() {
        return this.LinkTo;
    }

    public void setLinkTo(String LinkTo) {
        this.LinkTo = LinkTo;
    }

    public String getResType() {
        return this.ResType;
    }

    public void setResType(String ResType) {
        this.ResType = ResType;
    }

    public String getResUrl() {
        return this.ResUrl;
    }

    public void setResUrl(String ResUrl) {
        this.ResUrl = ResUrl;
    }



    public String getLiveChannel() {
        return this.LiveChannel;
    }



    public void setLiveChannel(String LiveChannel) {
        this.LiveChannel = LiveChannel;
    }

    public static final Creator<ADModel> CREATOR = new Creator<ADModel>() {
        @Override
        public ADModel createFromParcel(Parcel in) {
            return new ADModel(in);
        }

        @Override
        public ADModel[] newArray(int size) {
            return new ADModel[size];
        }
    };
}
