package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author:lnr
 * date:2018/9/4
 * 直播节目单实体类
 */
@Entity
public class LiveProgramModel implements Parcelable {

    @Id
    private Long _id;

    private String ID; //标识
    private String CreateTime; //创建时间
    private String ProgramName; //节目单名称
    private String Week; //周几直播
    private String PlayTime; //开始时间
    private String EndTime; //结束时间
    private String LChID; //频道ID
    private String Status; //状态
    private String ReMark; //备注
    private String Url; //播放地址
    private String NowWeek;
    private String authenticationflag;

    public String getAuthenticationflag() {
        return authenticationflag;
    }

    public void setAuthenticationflag(String authenticationflag) {
        this.authenticationflag = authenticationflag;
    }

    @Generated(hash = 1548387198)
    public LiveProgramModel(Long _id, String ID, String CreateTime, String ProgramName,
            String Week, String PlayTime, String EndTime, String LChID, String Status,
            String ReMark, String Url, String NowWeek, String authenticationflag) {
        this._id = _id;
        this.ID = ID;
        this.CreateTime = CreateTime;
        this.ProgramName = ProgramName;
        this.Week = Week;
        this.PlayTime = PlayTime;
        this.EndTime = EndTime;
        this.LChID = LChID;
        this.Status = Status;
        this.ReMark = ReMark;
        this.Url = Url;
        this.NowWeek = NowWeek;
        this.authenticationflag = authenticationflag;
    }

    @Generated(hash = 690117364)
    public LiveProgramModel() {
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

    public String getWeek() {
        return this.Week;
    }

    public void setWeek(String Week) {
        this.Week = Week;
    }

    public String getPlayTime() {
        return this.PlayTime;
    }

    public void setPlayTime(String PlayTime) {
        this.PlayTime = PlayTime;
    }

    public String getEndTime() {
        return this.EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getLChID() {
        return this.LChID;
    }

    public void setLChID(String LChID) {
        this.LChID = LChID;
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

    public String getUrl() {
        return this.Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getNowWeek() {
        return this.NowWeek;
    }

    public void setNowWeek(String NowWeek) {
        this.NowWeek = NowWeek;
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
        dest.writeString(this.ProgramName);
        dest.writeString(this.Week);
        dest.writeString(this.PlayTime);
        dest.writeString(this.EndTime);
        dest.writeString(this.LChID);
        dest.writeString(this.Status);
        dest.writeString(this.ReMark);
        dest.writeString(this.Url);
        dest.writeString(this.NowWeek);
        dest.writeString(this.authenticationflag);
    }

    protected LiveProgramModel(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.ID = in.readString();
        this.CreateTime = in.readString();
        this.ProgramName = in.readString();
        this.Week = in.readString();
        this.PlayTime = in.readString();
        this.EndTime = in.readString();
        this.LChID = in.readString();
        this.Status = in.readString();
        this.ReMark = in.readString();
        this.Url = in.readString();
        this.NowWeek = in.readString();
        this.authenticationflag = in.readString();
    }

    public static final Creator<LiveProgramModel> CREATOR = new Creator<LiveProgramModel>() {
        @Override
        public LiveProgramModel createFromParcel(Parcel source) {
            return new LiveProgramModel(source);
        }

        @Override
        public LiveProgramModel[] newArray(int size) {
            return new LiveProgramModel[size];
        }
    };
}
