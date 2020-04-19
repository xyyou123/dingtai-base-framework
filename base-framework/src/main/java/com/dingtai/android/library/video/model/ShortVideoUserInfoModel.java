package com.dingtai.android.library.video.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2019/3/14 0014.
 */

public class ShortVideoUserInfoModel implements Parcelable {
    private String UserNickName;
    private String UserName;
    private String UserGUID;
    private String UserIcon;
    private String UserSex;
    private String FocusedNum;
    private String FansNum;
    private String UserIntro;

    protected ShortVideoUserInfoModel(Parcel in) {
        UserNickName = in.readString();
        UserName = in.readString();
        UserGUID = in.readString();
        UserIcon = in.readString();
        UserSex = in.readString();
        FocusedNum = in.readString();
        FansNum = in.readString();
        UserIntro = in.readString();
    }

    public static final Creator<ShortVideoUserInfoModel> CREATOR = new Creator<ShortVideoUserInfoModel>() {
        @Override
        public ShortVideoUserInfoModel createFromParcel(Parcel in) {
            return new ShortVideoUserInfoModel(in);
        }

        @Override
        public ShortVideoUserInfoModel[] newArray(int size) {
            return new ShortVideoUserInfoModel[size];
        }
    };

    public ShortVideoUserInfoModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UserNickName);
        parcel.writeString(UserName);
        parcel.writeString(UserGUID);
        parcel.writeString(UserIcon);
        parcel.writeString(UserSex);
        parcel.writeString(FocusedNum);
        parcel.writeString(FansNum);
        parcel.writeString(UserIntro);
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserGUID() {
        return UserGUID;
    }

    public void setUserGUID(String userGUID) {
        UserGUID = userGUID;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String userIcon) {
        UserIcon = userIcon;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getFocusedNum() {
        return FocusedNum;
    }

    public void setFocusedNum(String focusedNum) {
        FocusedNum = focusedNum;
    }

    public String getFansNum() {
        return FansNum;
    }

    public void setFansNum(String fansNum) {
        FansNum = fansNum;
    }

    public String getUserIntro() {
        return UserIntro;
    }

    public void setUserIntro(String userIntro) {
        UserIntro = userIntro;
    }
}
