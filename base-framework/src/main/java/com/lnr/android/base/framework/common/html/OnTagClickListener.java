package com.lnr.android.base.framework.common.html;

import android.content.Context;

import java.util.List;

public interface OnTagClickListener {
    /**
     * 图片被点击
     */
    void onImageClick(Context context, List<String> imageUrlList, int position);

    /**
     * 链接被点击
     */
    void onLinkClick(Context context, String url);

    /**
     * 视频被点击
     */
    void onVideoClick(Context context, String url);
}