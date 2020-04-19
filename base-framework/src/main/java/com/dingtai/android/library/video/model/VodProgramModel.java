package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/8/29
 * 点播节目对象
 */
@Entity
public class VodProgramModel implements Parcelable {

    @Id
    private Long _id;

    private String ID;
    private String CreateTime;
    private String ProgramContentName;
    private String ProgramContentUrl;
    private String ProgramDate;
    private String ProgramContentLogo;
    private String ProgramCommentNum;
    private String VODName;
    private String VODType;
    private String GoodPoint;
    private String VODCPID;
    private String Status;
    private String ReMark;
    private String ReadNo;
    private String authenticationflag;

    public String getAuthenticationflag() {
        return authenticationflag;
    }

    public void setAuthenticationflag(String authenticationflag) {
        this.authenticationflag = authenticationflag;
    }

    @Generated(hash = 1787680320)
    public VodProgramModel(Long _id, String ID, String CreateTime, String ProgramContentName,
            String ProgramContentUrl, String ProgramDate, String ProgramContentLogo,
            String ProgramCommentNum, String VODName, String VODType, String GoodPoint,
            String VODCPID, String Status, String ReMark, String ReadNo,
            String authenticationflag) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.ProgramContentName = ProgramContentName;
        this.ProgramContentUrl = ProgramContentUrl;
        this.ProgramDate = ProgramDate;
        this.ProgramContentLogo = ProgramContentLogo;
        this.ProgramCommentNum = ProgramCommentNum;
        this.VODName = VODName;
        this.VODType = VODType;
        this.GoodPoint = GoodPoint;
        this.VODCPID = VODCPID;
        this.Status = Status;
        this.ReMark = ReMark;
        this.ReadNo = ReadNo;
        this.authenticationflag = authenticationflag;
    }

    @Generated(hash = 1412841882)
    public VodProgramModel() {
    }

    public String getProgramCommentNum() {
        return ProgramCommentNum;
    }

    public void setProgramCommentNum(String programCommentNum) {
        ProgramCommentNum = programCommentNum;
    }

    public String getVODName() {
        return VODName;
    }

    public void setVODName(String VODName) {
        this.VODName = VODName;
    }

    public String getVODType() {
        return VODType;
    }

    public void setVODType(String VODType) {
        this.VODType = VODType;
    }

    public String getGoodPoint() {
        return GoodPoint;
    }

    public void setGoodPoint(String goodPoint) {
        GoodPoint = goodPoint;
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

    public String getProgramContentName() {
        return this.ProgramContentName;
    }

    public void setProgramContentName(String ProgramContentName) {
        this.ProgramContentName = ProgramContentName;
    }

    public String getProgramContentUrl() {
        return this.ProgramContentUrl;
    }

    public void setProgramContentUrl(String ProgramContentUrl) {
        this.ProgramContentUrl = ProgramContentUrl;
    }

    public String getProgramDate() {
        return this.ProgramDate;
    }

    public void setProgramDate(String ProgramDate) {
        this.ProgramDate = ProgramDate;
    }

    public String getProgramContentLogo() {
        return this.ProgramContentLogo;
    }

    public void setProgramContentLogo(String ProgramContentLogo) {
        this.ProgramContentLogo = ProgramContentLogo;
    }

    public String getVODCPID() {
        return this.VODCPID;
    }

    public void setVODCPID(String VODCPID) {
        this.VODCPID = VODCPID;
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

    public String getReadNo() {
        return this.ReadNo;
    }

    public void setReadNo(String ReadNo) {
        this.ReadNo = ReadNo;
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
        dest.writeString(this.ProgramContentName);
        dest.writeString(this.ProgramContentUrl);
        dest.writeString(this.ProgramDate);
        dest.writeString(this.ProgramContentLogo);
        dest.writeString(this.ProgramCommentNum);
        dest.writeString(this.VODName);
        dest.writeString(this.VODType);
        dest.writeString(this.GoodPoint);
        dest.writeString(this.VODCPID);
        dest.writeString(this.Status);
        dest.writeString(this.ReMark);
        dest.writeString(this.ReadNo);
        dest.writeString(this.authenticationflag);
    }

    protected VodProgramModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.CreateTime = in.readString();
        this.ProgramContentName = in.readString();
        this.ProgramContentUrl = in.readString();
        this.ProgramDate = in.readString();
        this.ProgramContentLogo = in.readString();
        this.ProgramCommentNum = in.readString();
        this.VODName = in.readString();
        this.VODType = in.readString();
        this.GoodPoint = in.readString();
        this.VODCPID = in.readString();
        this.Status = in.readString();
        this.ReMark = in.readString();
        this.ReadNo = in.readString();
        this.authenticationflag = in.readString();
    }

    public static final Creator<VodProgramModel> CREATOR = new Creator<VodProgramModel>() {
        @Override
        public VodProgramModel createFromParcel(Parcel source) {
            return new VodProgramModel(source);
        }

        @Override
        public VodProgramModel[] newArray(int size) {
            return new VodProgramModel[size];
        }
    };
}
