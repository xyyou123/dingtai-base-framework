package com.dingtai.android.library.video.ui.player.listplayer;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.devlin_n.floatWindowPermission.FloatWindowManager;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.video.ui.player.controller.SimpleController;
import com.dingtai.android.library.video.ui.player.listplayer.pip.PIPManager;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/10/25
 */
public abstract class BasePlayerAdapter<T> extends BaseAdapter<T> {

    private PIPManager mPIPManager;
    private IjkVideoView mIjkVideoView;
    private FullPlayerDialog mFullPlayDialog;
    private boolean fullScreen;

    private View mCurrentPreView;

    private static final int TYPE_VIDEO = -102;

    public BasePlayerAdapter(PIPManager manager) {
        mPIPManager = manager;
        mIjkVideoView = mPIPManager.getIjkVideoView();
    }

    protected abstract boolean isVideo(int position);

    @Override
    protected int getDefItemViewType(int position) {
        return isVideo(position) ? TYPE_VIDEO : super.getDefItemViewType(position);
    }

    @Override
    protected final BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if(mFullPlayDialog == null) {
            mFullPlayDialog = new FullPlayerDialog(parent.getContext());
            mFullPlayDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    fullScreen = false;
                }
            });
        }
        BaseViewHolder holder = super.onCreateDefViewHolder(parent, viewType);
        if(viewType == TYPE_VIDEO) {
            return  new VideoViewHolder(this, holder);
        }

        return holder;
    }

    @Override
    protected ItemConverter<T> createItemConverter(int type) {
        if(type == TYPE_VIDEO) {
            return createVideoItemConverter();
        }
        return createOtherItemConverter(type);
    }

    protected abstract VideoItemConverter<T> createVideoItemConverter();


    protected abstract ItemConverter<T> createOtherItemConverter(int type);

    public abstract static class VideoItemConverter<T> implements ItemConverter<T> {
        @Override
        public void convert(BaseViewHolder baseViewHolder, int i, T model) {
            VideoViewHolder holder = (VideoViewHolder) baseViewHolder;
            holder.model = getPlayerModel(model);
            holder.playerLayout = getIjkVideoViewLayout(holder);
            holder.preLayout = getPreLayout(holder);
            holder.hash = getItemHash(model);

            holder.setPlayListener(getPlayButton(holder));
            convertItem(holder, i, model);
        }

        protected abstract PlayerModel getPlayerModel(T model);
        protected abstract ViewGroup getIjkVideoViewLayout(VideoViewHolder holder);
        protected abstract ViewGroup getPreLayout(VideoViewHolder holder);
        protected abstract View getPlayButton(VideoViewHolder holder);
        protected abstract int getItemHash(T model);

        protected abstract void convertItem(VideoViewHolder baseViewHolder, int i, T model);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        if(holder instanceof VideoViewHolder) {
            VideoViewHolder viewHolder = (VideoViewHolder) holder;
            ViewGroup player = viewHolder.playerLayout;
            if (viewHolder.hash == mPIPManager.getPlayingHash()) {
                mPIPManager.stopFloatWindow();
                viewHolder.preLayout.setVisibility(View.GONE);
                viewHolder.initIjkVideoView();
                viewHolder.updateController();
                resetIjkVideoViewLayout();
                player.addView(mIjkVideoView);
            }else {
                viewHolder.preLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        if(fullScreen) return;
        if(holder instanceof VideoViewHolder) {
            VideoViewHolder viewHolder = (VideoViewHolder) holder;
            ViewGroup player = viewHolder.playerLayout;
            if (player == null) return;
            if (viewHolder.hash == mPIPManager.getPlayingHash()) {
                resetIjkVideoViewLayout();
                viewHolder.preLayout.setVisibility(View.VISIBLE);

                if (FloatWindowManager.getInstance().checkPermission(viewHolder.itemView.getContext())) {
                    mPIPManager.startFloatWindow();
                } else {
                    mPIPManager.reset();
                    FloatWindowManager.getInstance().applyPermission(viewHolder.itemView.getContext());
                }
            }
        }
    }

    private void resetIjkVideoViewLayout() {
        ViewGroup parent = (ViewGroup) mIjkVideoView.getParent();
        if(parent != null) parent.removeView(mIjkVideoView);
    }

    public static class VideoViewHolder extends BaseViewHolder {
        private BasePlayerAdapter adapter;
        private SimpleController controller;

        private PlayerModel model;
        private ViewGroup playerLayout;
        private ViewGroup preLayout;
        private int hash;

        public VideoViewHolder(BasePlayerAdapter adapter, BaseViewHolder holder) {
            super(holder.itemView);
            this.adapter = adapter;

        }

        public void setPlayListener(View playButton) {
            ViewListen.setClick(playButton, new OnClickListener() {
                @Override
                protected void onSafeClick(View view) {
                    adapter.play(VideoViewHolder.this);
                }
            });
        }

        protected SimpleController createController(IjkVideoView videoView){
            return new DefaultListPlayerController(videoView);
        }

        private void initIjkVideoView() {
            if(controller == null) {
                adapter.mIjkVideoView.setPlayerConfig(new PlayerConfig.Builder().enableCache()
                        .addToPlayerManager()
                        .savingProgress().disableAudioFocus().build());
                this.controller = createController(adapter.mIjkVideoView);
                this.controller.setOnScreenStateChangeListener(new SimpleController.OnScreenStateChangeListener() {
                    @Override
                    public void onScreenStateChange(boolean currentIsFull) {
                        adapter.fullScreen();
                    }
                });
               updateController();
            }
        }

        private void updateController() {
            controller.setPlayState(adapter.mIjkVideoView.getCurrentPlayState());
            controller.setPlayerState(adapter.mIjkVideoView.getCurrentPlayerState());
            adapter.mIjkVideoView.setVideoController(controller);
        }
    }

    private void fullScreen() {
        //跳转全屏
        if(mFullPlayDialog.isShowing()) {
            mFullPlayDialog.dismiss();
            fullScreen = false;
        }else {
            mFullPlayDialog.show();
            fullScreen = true;
        }
    }

    public boolean isPlayerInThis() {
        return mPIPManager.getCurrentView() == mCurrentPreView;
    }

    private void play(VideoViewHolder holder) {
        if (mPIPManager.getPlayingHash() == holder.getAdapterPosition()) return;
        if (mPIPManager.isStartFloatWindow()) mPIPManager.stopFloatWindow();
        resetIjkVideoViewLayout();

        if(mCurrentPreView != null) {
            mCurrentPreView.setVisibility(View.VISIBLE);
        }

        ViewGroup player = holder.playerLayout;
        holder.preLayout.setVisibility(View.GONE);
        mIjkVideoView.release();
        holder.initIjkVideoView();
        holder.controller.init(holder.model);
        mIjkVideoView.start();
        player.addView(mIjkVideoView);
        mPIPManager.setPlayingHash(holder.hash, holder.preLayout);
        mCurrentPreView = holder.preLayout;
    }
}
