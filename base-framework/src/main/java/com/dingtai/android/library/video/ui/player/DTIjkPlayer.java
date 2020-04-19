package com.dingtai.android.library.video.ui.player;

import android.content.Context;

import com.dueeeke.videoplayer.player.IjkPlayer;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * author:lnr
 * date:2019/1/31
 */
public class DTIjkPlayer extends IjkPlayer {

    public DTIjkPlayer(Context context) {
        super(context);
    }


    public IjkMediaPlayer getPlayer() {
        return mMediaPlayer;
    }
}
