package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * model状态管理 任何model都可以管理
 */
@Entity
public class ModelStatus implements Parcelable {

    @Id
    private Long _id;

    @Unique
    private String key;

    private int status;

    protected ModelStatus(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        key = in.readString();
        status = in.readInt();
    }

    @Generated(hash = 66799577)
    public ModelStatus(Long _id, String key, int status) {
        this._id = _id;
        this.key = key;
        this.status = status;
    }

    @Generated(hash = 2035450232)
    public ModelStatus() {
    }

    public ModelStatus(String key, int status) {
        this.key = key;
        this.status = status;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(key);
        dest.writeInt(status);
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final Creator<ModelStatus> CREATOR = new Creator<ModelStatus>() {
        @Override
        public ModelStatus createFromParcel(Parcel in) {
            return new ModelStatus(in);
        }

        @Override
        public ModelStatus[] newArray(int size) {
            return new ModelStatus[size];
        }
    };
}
