package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/8/29
 */
@Entity
public class VodListModel implements Parcelable {

    @Id
    private Long _id;

    private String ID;
    private String CreateTime;
    private String ProgramName;
    private String ProgramLogo;
    private String IsRec;
    private String VODChID;
    private String Status;
    private String ReMark;
    private String VODType;
    private String NewDateTime;
    private String ProgramHornLogo;

    protected VodListModel(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        ID = in.readString();
        CreateTime = in.readString();
        ProgramName = in.readString();
        ProgramLogo = in.readString();
        IsRec = in.readString();
        VODChID = in.readString();
        Status = in.readString();
        ReMark = in.readString();
        VODType = in.readString();
        NewDateTime = in.readString();
        ProgramHornLogo = in.readString();
    }

    @Generated(hash = 455486046)
    public VodListModel(Long _id, String ID, String CreateTime, String ProgramName,
            String ProgramLogo, String IsRec, String VODChID, String Status, String ReMark,
            String VODType, String NewDateTime, String ProgramHornLogo) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.ProgramName = ProgramName;
        this.ProgramLogo = ProgramLogo;
        this.IsRec = IsRec;
        this.VODChID = VODChID;
        this.Status = Status;
        this.ReMark = ReMark;
        this.VODType = VODType;
        this.NewDateTime = NewDateTime;
        this.ProgramHornLogo = ProgramHornLogo;
    }

    @Generated(hash = 302845633)
    public VodListModel() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(ID);
        dest.writeString(CreateTime);
        dest.writeString(ProgramName);
        dest.writeString(ProgramLogo);
        dest.writeString(IsRec);
        dest.writeString(VODChID);
        dest.writeString(Status);
        dest.writeString(ReMark);
        dest.writeString(VODType);
        dest.writeString(NewDateTime);
        dest.writeString(ProgramHornLogo);
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

    public String getProgramName() {
        return this.ProgramName;
    }

    public void setProgramName(String ProgramName) {
        this.ProgramName = ProgramName;
    }

    public String getProgramLogo() {
        return this.ProgramLogo;
    }

    public void setProgramLogo(String ProgramLogo) {
        this.ProgramLogo = ProgramLogo;
    }

    public String getIsRec() {
        return this.IsRec;
    }

    public void setIsRec(String IsRec) {
        this.IsRec = IsRec;
    }

    public String getVODChID() {
        return this.VODChID;
    }

    public void setVODChID(String VODChID) {
        this.VODChID = VODChID;
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

    public String getVODType() {
        return this.VODType;
    }

    public void setVODType(String VODType) {
        this.VODType = VODType;
    }

    public String getNewDateTime() {
        return this.NewDateTime;
    }

    public void setNewDateTime(String NewDateTime) {
        this.NewDateTime = NewDateTime;
    }

    public String getProgramHornLogo() {
        return this.ProgramHornLogo;
    }

    public void setProgramHornLogo(String ProgramHornLogo) {
        this.ProgramHornLogo = ProgramHornLogo;
    }

    public static final Creator<VodListModel> CREATOR = new Creator<VodListModel>() {
        @Override
        public VodListModel createFromParcel(Parcel in) {
            return new VodListModel(in);
        }

        @Override
        public VodListModel[] newArray(int size) {
            return new VodListModel[size];
        }
    };
}
