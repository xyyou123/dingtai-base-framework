package com.dingtai.android.library.video.event;

import com.dingtai.android.library.video.model.VideoModel;

/**
 * author:lnr
 * date:2018/12/4
 */
public class UploadVideoEvent {

    private VideoModel model;

    public UploadVideoEvent(VideoModel model) {
        this.model = model;
    }

    public VideoModel getModel() {
        return model;
    }
}
