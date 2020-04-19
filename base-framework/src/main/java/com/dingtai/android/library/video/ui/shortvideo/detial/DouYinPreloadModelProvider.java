package com.dingtai.android.library.video.ui.shortvideo.detial;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.dingtai.android.library.video.model.ShortVideoModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2019/3/13 0013.
 */

public class DouYinPreloadModelProvider implements ListPreloader.PreloadModelProvider<String> {
    private  List<ShortVideoModel> models;

    public DouYinPreloadModelProvider() {
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {
        return Collections.singletonList("");
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
        return null;
    }
}
