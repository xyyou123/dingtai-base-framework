package com.dingtai.android.library.video.ui.live.tab.chat.redpacket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dingtai.android.library.resource.GlobalConfig;

import com.dingtai.android.library.video.VideoShareHelper;
import com.dingtai.android.library.video.control.FlutteringLayout;
import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.ui.live.tab.chat.LiveChatDescFragment;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.umeng.ShareMenu;
import com.lnr.android.base.framework.common.umeng.UMengShare;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/12/12
 * 红包类型的聊天页面
 */
@Route(path = "/video/live/chat/redpacket")
public class LiveRedacketChatFragment extends LiveChatDescFragment {

    protected FlutteringLayout mFlutteringLayout;

    protected View btnRedpacketShare;

    protected ShareMenu shareMenu;

    protected RedPacketDialog mRedPacketDialog;

    @Override
    protected int rootLayoutResId() {
        return R.layout.fragment_live_chat_redpacket;
    }

    @Override
    protected BaseQuickAdapter adapter() {
        return new LiveChatRedPacketCommentAdapter();
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        mFlutteringLayout = findViewById(R.id.FlutteringLayout);

        findViewById(R.id.btn_zan).setOnClickListener(new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                mFlutteringLayout.addHeart();
            }
        });

        btnRedpacketShare = findViewById(R.id.btn_redpacket);

        ViewListen.setClick(btnRedpacketShare, new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                showTipDialog(true);
            }
        });

        mRecyclerView.setBackgroundResource(R.color.backgroundDark);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //super.onItemClick(adapter, view, position);
        //去掉聊天item的点击事件
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemChildClick(adapter, view, position);

        if (view.getId() == R.id.item_redpacket_open) {
            showTipDialog(false);
        }
    }

    protected void showTipDialog(boolean send) {
        if (mRedPacketDialog == null) {
            mRedPacketDialog = new RedPacketDialog(getActivity(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareRedpacket();
                }
            });
        }

        mRedPacketDialog.show(send);
    }

    /**
     * 分享
     */
    protected void shareRedpacket() {
        if (shareMenu == null) {
            shareMenu = createShareMenu(model);
        }

        shareMenu.show();
    }

    protected ShareMenu createShareMenu(LiveChannelModel model) {
        String shareUrl = formatShareUrl(type);
        String content = "直播：" + name + GlobalConfig.SHARE_CONTENT_SPARE2;
        UMWeb web = UMengShare.createWeb(shareUrl, name, content, new UMImage(getContext(), model.getLiveImageUrl()));
        List<SHARE_MEDIA> media = new ArrayList<>();
        media.add(SHARE_MEDIA.WEIXIN);
        media.add(SHARE_MEDIA.WEIXIN_CIRCLE);
        return new ShareMenu(getActivity(), web, media);
    }

    @Override
    public void onStop() {
        if (mFlutteringLayout != null) {
            mFlutteringLayout.stop();
        }
        super.onStop();
    }

    protected String formatShareUrl(int liveType) {
        return VideoShareHelper.getLiveShareUrl(liveType, model.getID());
    }
}
