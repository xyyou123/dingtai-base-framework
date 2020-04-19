package com.lnr.android.base.framework.ui.control.view.statuslayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * author:lnr
 * date:2018/5/10
 */

public class StatusLayout extends FrameLayout {
    /**
     * loading 加载id
     */
    public static final int LAYOUT_LOADING_ID = 1;

    /**
     * 内容id
     */
    public static final int LAYOUT_CONTENT_ID = 2;

    /**
     * 异常id
     */
    public static final int LAYOUT_ERROR_ID = 3;

    /**
     * 网络异常id
     */
    public static final int LAYOUT_NETWORK_ERROR_ID = 4;

    /**
     * 空数据id
     */
    public static final int LAYOUT_EMPTYDATA_ID = 5;


    /**
     * 存放布局集合
     */
    private SparseArray<View> layoutSparseArray = new SparseArray();

    /**
     * 状态布局管理类
     */
    private StatusLayoutManager mStatusLayoutManager;

    private int currentLayoutId = LAYOUT_LOADING_ID;

    public StatusLayout(@NonNull Context context) {
        super(context);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置管理器
    public void setStatusLayoutManager(StatusLayoutManager statusLayoutManager) {
        this.mStatusLayoutManager = statusLayoutManager;
        addLayout();
    }

    //初始化加载中布局以及内容布局 这两个布局不需要点击监听
    private void addLayout() {
        if (mStatusLayoutManager.mContentResId != 0)
            addLayoutResId(mStatusLayoutManager.mContentResId, LAYOUT_CONTENT_ID);

        if (mStatusLayoutManager.mLoadResId != 0)
            addLayoutResId(mStatusLayoutManager.mLoadResId, LAYOUT_LOADING_ID);

        if (mStatusLayoutManager.mEmptyVs != null) addView(mStatusLayoutManager.mEmptyVs);
        if (mStatusLayoutManager.mErrorVs != null) addView(mStatusLayoutManager.mErrorVs);
        if (mStatusLayoutManager.mNetWorkErrorVs != null)
            addView(mStatusLayoutManager.mNetWorkErrorVs);


        showLoading();
    }

    /**
     * 将资源id 加载成view 缓存到数据集中
     *
     * @param resId
     * @param viewId
     */
    private void addLayoutResId(int resId, int viewId) {
        View view = LayoutInflater.from(getContext()).inflate(resId, null);
        if (view != null) {
            addView(view);
            layoutSparseArray.put(viewId, view);
        }
    }

    public void showNetWorkError() {
        if (inflateLayout(LAYOUT_NETWORK_ERROR_ID)) {
            hideViewById(LAYOUT_NETWORK_ERROR_ID);

        }
    }

    public void showNetWorkError(String msg) {
        if (inflateLayout(LAYOUT_NETWORK_ERROR_ID)) {
            hideViewById(LAYOUT_NETWORK_ERROR_ID);
            ViewGroup view = (ViewGroup) layoutSparseArray.get(LAYOUT_NETWORK_ERROR_ID);
            for (int i = 0; i < view.getChildCount(); i++) {
                if (view.getChildAt(i) != null && view.getChildAt(i) instanceof TextView) {
                    ((TextView) view.getChildAt(i)).setText(msg);
                }
            }
        }
    }

    public void showLoading() {
        if (layoutSparseArray.get(LAYOUT_LOADING_ID) != null) {
            hideViewById(LAYOUT_LOADING_ID);
        }
    }

    public void showEmpty() {
        if (inflateLayout(LAYOUT_EMPTYDATA_ID)) {
            hideViewById(LAYOUT_EMPTYDATA_ID);
        }
    }

    public void showEmpty(String msg) {
        if (inflateLayout(LAYOUT_EMPTYDATA_ID)) {
            hideViewById(LAYOUT_EMPTYDATA_ID);
        }
        ViewGroup view = (ViewGroup) layoutSparseArray.get(LAYOUT_EMPTYDATA_ID);
        for (int i = 0; i < view.getChildCount(); i++) {
            if (view.getChildAt(i) != null && view.getChildAt(i) instanceof TextView) {
                ((TextView) view.getChildAt(i)).setText(msg);
            }
        }
    }

    public void showError() {
        if (inflateLayout(LAYOUT_ERROR_ID)) {
            hideViewById(LAYOUT_ERROR_ID);
        }
    }

    public void showError(String msg) {
        if (inflateLayout(LAYOUT_ERROR_ID)) {
            hideViewById(LAYOUT_ERROR_ID);
        }
        ViewGroup view = (ViewGroup) layoutSparseArray.get(LAYOUT_ERROR_ID);
        for (int i = 0; i < view.getChildCount(); i++) {
            if (view.getChildAt(i) != null && view.getChildAt(i) instanceof TextView) {
                ((TextView) view.getChildAt(i)).setText(msg);
            }
        }

    }

    public void showContent() {
        if (layoutSparseArray.get(LAYOUT_CONTENT_ID) != null) {
            hideViewById(LAYOUT_CONTENT_ID);
        }
    }

    /**
     * 根据layoutid 显示或者隐藏view
     *
     * @param layoutId
     */
    public void hideViewById(int layoutId) {
        for (int i = 0; i < layoutSparseArray.size(); i++) {
            int key = layoutSparseArray.keyAt(i);
            View view = layoutSparseArray.valueAt(i);
            if (layoutId == key) {
                if (view.getVisibility() != View.VISIBLE) {
                    view.setVisibility(View.VISIBLE);

                    setCurrentStatus(layoutId);

                }
            } else {
                if (view.getVisibility() != View.GONE) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setCurrentStatus(int layoutId) {
        this.currentLayoutId = layoutId;
    }

    public int getCurrentLayoutId() {
        return currentLayoutId;
    }

    /**
     * 填充view
     *
     * @param viewId
     * @return
     */
    public boolean inflateLayout(int viewId) {
        boolean isShow = true;
        if (layoutSparseArray.get(viewId) != null) return isShow;
        switch (viewId) {
            case LAYOUT_ERROR_ID:
                if (mStatusLayoutManager.mErrorVs != null) {
                    View view = mStatusLayoutManager.mErrorVs.inflate();
                    layoutSparseArray.put(viewId, view);
                    retryLoad(view, mStatusLayoutManager.mErrorRertyResId);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_NETWORK_ERROR_ID:
                if (mStatusLayoutManager.mNetWorkErrorVs != null) {
                    View view = mStatusLayoutManager.mNetWorkErrorVs.inflate();
                    layoutSparseArray.put(viewId, view);
                    retryLoad(view, mStatusLayoutManager.mNetErrorRertyResId);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
            case LAYOUT_EMPTYDATA_ID:
                if (mStatusLayoutManager.mEmptyVs != null) {
                    View view = mStatusLayoutManager.mEmptyVs.inflate();
                    layoutSparseArray.put(viewId, view);
                    retryLoad(view, mStatusLayoutManager.mEmptyRertyResId);
                    isShow = true;
                } else {
                    isShow = false;
                }
                break;
        }
        return isShow;
    }

    /**
     * 重试加载
     *
     * @param v
     * @param vId
     */
    public void retryLoad(View v, int vId) {
        int id = mStatusLayoutManager.mRetryResId != 0 ? mStatusLayoutManager.mRetryResId : vId;
        final View view = v.findViewById(id);
        if (view == null || mStatusLayoutManager.getRetryListener() == null) return;
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mStatusLayoutManager.getRetryListener().retry(view);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            int currentLayoutId = bundle.getInt("CurrentLayoutId");
            hideViewById(currentLayoutId);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("CurrentLayoutId", getCurrentLayoutId());
        return bundle;
    }
}
