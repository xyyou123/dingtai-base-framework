package com.dingtai.android.library.model.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author:lnr
 * date:2018/9/29
 * 播放内容实体
 */
public final class PlayerModel implements Parcelable {

    public static final int SIZE_16_9 = 1;
    public static final int SIZE_FULL = 2;
    public static final int SIZE_AUTO = 3;


    public static final int TYPE_LIVE_TV = 1;
    public static final int TYPE_LIVE_ACTIVITIES = 2;
    public static final int TYPE_LIVE_AUDIO = 3;
    public static final int TYPE_LIVE_IMAGE_AND_TEXT = 4;
    public static final int TYPE_VOD = 11;


    /**
     * 播放器类型
     */
    private int type;
    /**
     * 播放器大小
     */
    private int playerSize;

    /**
     * 标题
     */
    private String title;
    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 直播开始时间
     */
    private long begin;
    /**
     * 直播结束时间
     */
    private long end;

    private ArrayList<String> urls;

    private String shareUrl;

    private String shareTitle;

    private String shareContent;

    private PlayerModel() {
    }

    public int getType() {
        return type;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public String getTitle() {
        return title;
    }

    public String getThumb() {
        return thumb;
    }

    public long getBegin() {
        return begin;
    }

    public long getEnd() {
        return end;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public static class Builder {

        /**
         * 播放器大小
         */
        private int playerSize;
        /**
         * 标题
         */
        private String title;

        /**
         * 播放器类型
         */
        private int type;

        /**
         * 直播开始时间
         */
        private long begin;
        /**
         * 直播结束时间
         */
        private long end;


        private String thumb;

        private ArrayList<String> playUrls;

        private String shareUrl;
        private String shareTitle;
        private String shareContent;

        private Builder(){}

        public static Builder newBuilder(int type) {
            Builder builder = new Builder();
            builder.type = type;
            return builder;
        }

        public Builder setSize(int size) {
            this.playerSize = size;
            return this;
        }

        public Builder setTimeZone(long begin, long end) {
            this.begin = begin;
            this.end = end;
            return this;
        }

        public Builder setThumb(String thumb) {
            this.thumb = thumb;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setShareInfo(String url, String title, String content) {
            this.shareUrl = url;
            this.shareTitle = title;
            this.shareContent = content;
            return this;
        }

        public Builder addUrls(String... urls) {
            if(playUrls == null) {
                playUrls = new ArrayList<>();
            }
            playUrls.addAll(Arrays.asList(urls));
            return this;
        }

        public PlayerModel build() {
            PlayerModel model = new PlayerModel();
            model.type = type;
            model.playerSize = playerSize;
            model.begin = begin;
            model.end = end;
            model.urls = playUrls;
            model.title = title;
            model.thumb = thumb;
            model.shareUrl = shareUrl;
            model.shareTitle = shareTitle;
            model.shareContent = shareContent;
            return model;
        }
    }

    protected PlayerModel(Parcel in) {
        type = in.readInt();
        playerSize = in.readInt();
        title = in.readString();
        thumb = in.readString();
        begin = in.readLong();
        end = in.readLong();
        urls = in.createStringArrayList();
        shareUrl = in.readString();
        shareTitle = in.readString();
        shareContent = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(playerSize);
        dest.writeString(title);
        dest.writeString(thumb);
        dest.writeLong(begin);
        dest.writeLong(end);
        dest.writeStringList(urls);
        dest.writeString(shareUrl);
        dest.writeString(shareTitle);
        dest.writeString(shareContent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlayerModel> CREATOR = new Creator<PlayerModel>() {
        @Override
        public PlayerModel createFromParcel(Parcel in) {
            return new PlayerModel(in);
        }

        @Override
        public PlayerModel[] newArray(int size) {
            return new PlayerModel[size];
        }
    };
}
