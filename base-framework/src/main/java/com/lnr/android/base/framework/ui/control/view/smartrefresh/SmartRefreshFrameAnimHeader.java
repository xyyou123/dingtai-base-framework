package com.lnr.android.base.framework.ui.control.view.smartrefresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnr.android.base.framework.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * author:lnr
 * date:2018/12/6
 */
public class SmartRefreshFrameAnimHeader implements RefreshHeader {

    protected Context mContext;

    protected AnimationDrawable mLoadingAnimation;

    protected View mLayout;
    protected ImageView mLoadingView;
    protected TextView mTextHint;
    protected TextView mTextResult;

    public SmartRefreshFrameAnimHeader(Context context, AnimationDrawable loadingAnim) {
        this.mContext = context;
        this.mLoadingAnimation = loadingAnim;
    }

    public SmartRefreshFrameAnimHeader(Context context, @DrawableRes int loadingAnim) {
        this.mContext = context;
        this.mLoadingAnimation = (AnimationDrawable) context.getResources().getDrawable(loadingAnim);
    }

    protected View createView() {
        mLayout = View.inflate(mContext, R.layout.layout_frame_refresh, null);
        mLoadingView = mLayout.findViewById(R.id.image_loading);
        mTextHint = mLayout.findViewById(R.id.text_hint);
        mTextResult = mLayout.findViewById(R.id.text_result);
        mLoadingView.setImageDrawable(mLoadingAnimation);
        return mLayout;
    }

    @NonNull
    @Override
    public View getView() {
        return mLayout == null ? createView() : mLayout;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mLoadingAnimation.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mLoadingAnimation.stop();
        mLoadingView.setVisibility(View.GONE);
        mTextHint.setVisibility(View.GONE);
        mTextResult.setVisibility(View.VISIBLE);
        if(success) {
            mTextResult.setText("刷新完成");
        }else {
            mTextResult.setText("刷新失败");
        }
        return 300;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mLoadingView.setVisibility(View.VISIBLE);
                mTextHint.setVisibility(View.VISIBLE);
                mTextResult.setVisibility(View.GONE);
                mTextHint.setText("下拉开始刷新");
                break;
            case Refreshing:
                mTextHint.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                mTextHint.setText("松开立即刷新");
                break;
        }
    }
}
