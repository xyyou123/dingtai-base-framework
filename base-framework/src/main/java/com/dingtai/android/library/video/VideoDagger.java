package com.dingtai.android.library.video;

import com.dingtai.android.library.video.ui.live.LiveMainActivity;
import com.dingtai.android.library.video.ui.live.list.channel.LiveChannelListFragment;
import com.dingtai.android.library.video.ui.live.tab.chat.LiveChatFragment;
import com.dingtai.android.library.video.ui.live.tab.game.LiveGameFragment;
import com.dingtai.android.library.video.ui.live.tab.imagetext.LiveImageTextFragment;
import com.dingtai.android.library.video.ui.live.tab.programme.LiveProgrammeFragment;
import com.dingtai.android.library.video.ui.publish.PublishImageTextLiveActivty;
import com.dingtai.android.library.video.ui.shortvideo.detial.ShortVideoDetialActivity;
import com.dingtai.android.library.video.ui.shortvideo.list.ShortVideoListFragment;
import com.dingtai.android.library.video.ui.shortvideo.pvlist.PersionVideoListActivity;
import com.dingtai.android.library.video.ui.video.list.VideoListFragment;
import com.dingtai.android.library.video.ui.video.list.details.comment.HotVideoCommentFragment;
import com.dingtai.android.library.video.ui.video.list.details.info.VideoInfoFragment;
import com.dingtai.android.library.video.ui.video.tab.VideoTabActivity;
import com.dingtai.android.library.video.ui.video.tab.VideoTabFragment;
import com.dingtai.android.library.video.ui.video.upload.my.MyUploadVideoListActivity;
import com.dingtai.android.library.video.ui.video.upload.publish.UploadVideoActivity;
import com.dingtai.android.library.video.ui.vod.VodListFragment;
import com.dingtai.android.library.video.ui.vod.child.VodListChildActivity;
import com.dingtai.android.library.video.ui.vod.details.comment.VodCommentFragment;
import com.lnr.android.base.framework.dagger.ActivityScope;
import com.lnr.android.base.framework.dagger.ApplicationComponent;
import com.lnr.android.base.framework.dagger.AsynCallModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = AsynCallModule.class)
public interface VideoDagger {

    void inject(VodListFragment fragment);
    void inject(VodListChildActivity fragment);
    void inject(LiveChannelListFragment fragment);
    void inject(LiveMainActivity activity);
    void inject(LiveChatFragment fragment);
    void inject(LiveProgrammeFragment fragment);
    void inject(LiveGameFragment fragment);
    void inject(LiveImageTextFragment fragment);
    void inject(PublishImageTextLiveActivty fragment);
    void inject(VideoListFragment fragment);
    void inject(HotVideoCommentFragment fragment);
    void inject(VideoInfoFragment fragment);
    void inject(VideoTabActivity fragment);
    void inject(MyUploadVideoListActivity fragment);
    void inject(UploadVideoActivity fragment);
    void inject(VodCommentFragment fragment);
    void inject(VideoTabFragment fragment);
    void inject(ShortVideoListFragment fragment);
    void inject(ShortVideoDetialActivity fragment);
    void inject(PersionVideoListActivity fragment);

}
