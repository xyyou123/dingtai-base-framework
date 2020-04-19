package com.dingtai.android.library.video.ui.player.listplayer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


import com.dingtai.android.library.video.ui.player.listplayer.pip.PIPManager;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.app.ActivityStack;
import com.lnr.android.base.framework.ui.control.dialog.AbstractDialog;

/**
 * author:lnr
 * date:2018/11/5
 */
public class FullPlayerDialog extends AbstractDialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {

    protected IjkVideoView ijkVideoView;

    protected FrameLayout mFrame;

    protected ViewGroup parent;
    protected ViewGroup.LayoutParams layoutParams;
    protected int index;

    protected OnDismissListener mOnDismissListener;

    public FullPlayerDialog(@NonNull Context context) {
        super(context);
        setOnShowListener(this);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_frame;
    }

    @Override
    protected void initView(View view) {
        mFrame = findViewById(R.id.frame);
    }

    @Override
    protected int[] getDialogSize(DisplayMetrics displayMetrics) {
        return new int[]{ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT};
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(this);
        this.mOnDismissListener = listener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Activity activity = ActivityStack.getInstance().currentActivity();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(ijkVideoView != null) {
            mFrame.removeView(ijkVideoView);
            parent.addView(ijkVideoView, index, layoutParams);
        }
        ijkVideoView = null;
        if(mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        ijkVideoView = PIPManager.getInstance().getIjkVideoView();
        if(ijkVideoView == null) {
            dismiss();
            return;
        }

        Activity activity = ActivityStack.getInstance().currentActivity();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        parent = (ViewGroup) ijkVideoView.getParent();
        layoutParams = ijkVideoView.getLayoutParams();
        index = parent.indexOfChild(ijkVideoView);

        parent.removeView(ijkVideoView);
        mFrame.addView(ijkVideoView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
