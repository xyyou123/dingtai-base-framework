package com.dingtai.android.library.video.ui;

import android.app.Activity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Resource;
import com.dingtai.android.library.resource.Routes;
import com.dingtai.android.library.video.event.AddLiveReadNumEvent;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.model.VideoModel;
import com.dingtai.android.library.video.model.VodProgramModel;
import com.dingtai.android.library.video.ui.live.list.channel.LiveChannelListFragment;
import com.dingtai.android.library.video.ui.player.VideoPlayerFragment;
import com.dingtai.android.library.video.ui.video.list.details.VideoDetailsActivity;
import com.lnr.android.base.framework.rx.RxBus;
import com.lnr.android.base.framework.uitl.NumberUtil;

/**
 * author:lnr
 * date:2018/9/5
 */
public class VideoNavigation {


    public static void live(LiveChannelModel model) {
        if ("True".equals(model.getIswebview())) {
            //怎加阅读点击
            RxBus.getDefault().post(new AddLiveReadNumEvent(model.getID()));
            ARouter.getInstance().build(Routes.Modules.WEB)
                    .withString("url", model.getWebview()).withString("title", model.getLiveChannelName()).navigation();
            return;
        }
        switch (model.getLiveType()) {
            case "1":
            case "2":
            case "3":
                liveMain(model);
                break;
            case "4":
                liveMainImageText(model);
                break;
            case "5":
//                FrameworkNavigation.web(model.getLiveRTMPUrl(), model.getLiveChannelName());
                ARouter.getInstance().build(Routes.Modules.WEB)
                        .withString("url", model.getLiveRTMPUrl()).withString("title",model.getLiveChannelName()).navigation();
                break;
        }
    }

    /**
     * 视频直播间
     * LiveChannelModel model
     * @see com.dingtai.android.library.video.ui.live.LiveMainActivity
     */
    public static void liveMain(LiveChannelModel model) {
        ARouter.getInstance().build(Routes.Video.LIVE_MAIN).withParcelable("model", model).navigation();
    }

    /**
     * 图文直播间
     * LiveChannelModel model
     * @see com.dingtai.android.library.video.ui.live.LiveMainImageTextActivity
     */
    public static void liveMainImageText(LiveChannelModel model) {
        ARouter.getInstance().build(Routes.Video.LIVE_MAIN_IMAGETEXT).withParcelable("model", model).navigation();

    }

    /**
     * 直播类型
     * @see com.dingtai.android.library.video.ui.live.list.LiveChannelsActivity
     */
    public static void liveChannelTab() {
        ARouter.getInstance().build(Routes.Video.LIVE_LIST).navigation();

    }

    /**
     * 直播 聊天
     * @see com.dingtai.android.library.video.ui.live.tab.chat.LiveChatFragment
     */
    public static Object liveChatFragment(LiveChannelModel model) {
        return ARouter.getInstance().build(Routes.Video.LIVE_CHAT)
                .withParcelable("model", model)
                .withString("liveId", model.getID())
                .withString("name", model.getLiveChannelName())
                .withInt("type", NumberUtil.parseInt(model.getLiveType()))
                .withString("tabCode", "liaotian").navigation();
    }

    /**
     * 直播 聊天
     * @see com.dingtai.android.library.video.ui.live.tab.chat.LiveChatFragment
     */
    public static Object liveChatDescFragment(LiveChannelModel model) {
        return ARouter.getInstance().build(Routes.Video.LIVE_CHAT_DESC)
                .withParcelable("model", model)
                .withString("liveId", model.getID())
                .withString("name", model.getLiveChannelName())
                .withInt("type", NumberUtil.parseInt(model.getLiveType()))
                .withString("tabCode", "liaotian").navigation();
    }

    /**
     * 直播 聊天
     * @see com.dingtai.android.library.video.ui.live.tab.chat.LiveChatFragment
     */
    public static Object liveChatDescRedpacketFragment(LiveChannelModel model) {
        return ARouter.getInstance().build(Routes.Video.LIVE_CHAT_REDPACKET)
                .withParcelable("model", model)
                .withString("liveId", model.getID())
                .withString("name", model.getLiveChannelName())
                .withInt("type", NumberUtil.parseInt(model.getLiveType()))
                .withString("tabCode", "liaotian").navigation();
    }



    /**
     * 直播 节目单
     * @param liveId 直播id
     * @param tabCode  tabCode
     * @param type 类型
     * @see com.dingtai.android.library.video.ui.live.tab.programme.LiveProgrammeFragment
     */
    public static Object liveProgramme(String liveId, String tabCode, int type) {
        return ARouter.getInstance().build(Routes.Video.LIVE_PROGRAMME)
                .withString("liveId", liveId)
                .withInt("type", type)
                .withString("tabCode", tabCode).navigation();
    }


    /**
     * 直播 互动游戏
     * @param liveId 直播id
     * @param tabCode  tabCode
     * @param gameType 类型
     * @see com.dingtai.android.library.video.ui.live.tab.game.LiveGameFragment
     */
    public static Object liveGame(String liveId, String tabCode, String gameType) {
        return ARouter.getInstance().build(Routes.Video.LIVE_GAME)
                .withString("liveId", liveId)
                .withString("gameType", gameType)
                .withString("tabCode", tabCode).navigation();
    }

    /**
     * 直播 图文内容
     * @param liveId 直播id
     * @see com.dingtai.android.library.video.ui.live.tab.imagetext.LiveImageTextFragment
     */
    public static Object liveImageText(String liveId) {
        return ARouter.getInstance().build(Routes.Video.LIVE_IMAGE_TEXT)
                .withString("liveId", liveId).navigation();
    }

    /**
     * 直播列表
     * @param type 类型
     * @see LiveChannelListFragment
     */
    public static Object liveList(String type) {
        return ARouter.getInstance().build(Routes.Video.LIVE_LIST_CHANNEL)
                .withString("type", type).navigation();
    }

    /**
     * 点播列表
     * @see com.dingtai.android.library.video.ui.vod.VodListFragment
     */
    public static Object vodList(String type) {
        return ARouter.getInstance().build(Routes.Video.VOD_LIST).withString("RecType", type).navigation();
    }

    /**
     * 点播tab
     * @see com.dingtai.android.library.video.ui.vod.VodChannelsActivity
     */
    public static void vodChannels() {
        ARouter.getInstance().build(Routes.Video.VOD_CHANNELS).navigation();
    }

    /**
     * 点播子列表
     * @see com.dingtai.android.library.video.ui.vod.child.VodListChildActivity
     */
    public static void vodListChild(String ID, String name, String type) {
        ARouter.getInstance().build(Routes.Video.VOD_LIST_CHILD)
                .withString("ID", ID).withString("ProgramName", name).withString("VODType", type).navigation();
    }

    /**
     * 点播详情
     * @see com.dingtai.android.library.video.ui.vod.details.VodDetailsActivity
     */
    public static void vodListDetails(VodProgramModel model) {
        ARouter.getInstance().build(Routes.Video.VOD_DETAILS)
                .withParcelable("model", model).navigation();
    }

    /**
     * 点播评论
     * @see com.dingtai.android.library.video.ui.vod.details.comment.VodCommentFragment
     */
    public static Object vodListDetailsComment(VodProgramModel model) {
        return ARouter.getInstance().build(Routes.Video.VOD_DETAILS_COMMENT)
                .withParcelable("model", model).navigation();
    }

    /**
     * 播放器
     * @see com.dingtai.android.library.video.ui.player.SimpleVidioPlayerActivity
     */
    public static VideoPlayerFragment player(PlayerModel model) {
        return (VideoPlayerFragment) ARouter.getInstance().build(Routes.Video.PLAYER)
                .withParcelable("model", model)
                .navigation();
    }

    /**
     * 播放器
     * @see com.dingtai.android.library.video.ui.player.SimpleVidioPlayerActivity
     */
    public static void simplePlayer(PlayerModel model) {
        ARouter.getInstance().build(Routes.Video.PLAYER_SIMPLE)
                .withParcelable("model", model)
                .navigation();
    }

    /**
     * 发布图文直播
     * @see com.dingtai.android.library.video.ui.publish.PublishImageTextLiveActivty
     */
    public static void pulishImageText() {
        ARouter.getInstance().build(Routes.Video.PUBLISH_IMAGE_TEXT)
                .navigation();
    }

    /**
     * 视频列表 tab
     * @see com.dingtai.android.library.video.ui.video.tab.VideoTabActivity
     */
    public static void videoTab() {
        ARouter.getInstance().build(Routes.Video.VIDEO_TAB).navigation();
    }

    /**
     * 视频列表 tab
     * @see com.dingtai.android.library.video.ui.video.tab.VideoTabActivity
     */
    public static Object videoTabFragment() {
        return ARouter.getInstance().build(Routes.Video.VIDEO_TAB + "/fragment").navigation();
    }

    /**
     * 视频列表
     * @see com.dingtai.android.library.video.ui.video.list.VideoListFragment
     */
    public static Object videoList(String CHID) {
        return ARouter.getInstance().build(Routes.Video.VIDEO_LIST).withString("CHID", CHID).navigation();
    }

    /**
     * 视频列表
     * @see VideoDetailsActivity
     */
    public static void videoListDetails(VideoModel model) {
        ARouter.getInstance().build(Routes.Video.VIDEO_LIST_DETAILS).withParcelable("model", model).navigation();
    }

    /**
     * 视频 我的上传
     * @see com.dingtai.android.library.video.ui.video.upload.my.MyUploadVideoListActivity
     */
    public static void videoMyUpload() {
        ARouter.getInstance().build(Routes.Video.VIDEO_UPLOAD_MY)
                .withBoolean(Resource.KEY.NEED_LOGIN, true).navigation();
    }

    /**
     * 视频 发布视频
     * @see com.dingtai.android.library.video.ui.video.upload.publish.UploadVideoActivity
     */
    public static void videoUploadPublish() {
        ARouter.getInstance().build(Routes.Video.VIDEO_UPLOAD_PUBLISH)
                .withBoolean(Resource.KEY.NEED_LOGIN, true).navigation();
    }

    /**
     * 视频 正在上传
     * @see com.dingtai.android.library.video.ui.video.upload.uploading.VideoUploadingListActivity
     */
    public static void videoUploading(final Activity activity) {
        if(activity != null) {
            ARouter.getInstance().build(Routes.Video.VIDEO_UPLOAD_UPLOADING)
                    .withBoolean(Resource.KEY.NEED_LOGIN, true).navigation(activity, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    activity.finish();
                }
            });
        }else {
            ARouter.getInstance().build(Routes.Video.VIDEO_UPLOAD_UPLOADING)
                    .withBoolean(Resource.KEY.NEED_LOGIN, true).navigation();
        }
    }
}
