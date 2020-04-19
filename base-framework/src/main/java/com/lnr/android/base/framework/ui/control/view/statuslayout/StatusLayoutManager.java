package com.lnr.android.base.framework.ui.control.view.statuslayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 * author:lnr
 * date:2018/5/10
 */

public class StatusLayoutManager {
    //内容layout id
    final int mContentResId;
    //空数据viewStub
    final ViewStub mEmptyVs;

    //网络异常 viewStub
    final ViewStub mNetWorkErrorVs;

    //加载中 layout id
    final int mLoadResId;
    //异常viewStub
    final ViewStub mErrorVs;

    //错误点击view id
    final int mErrorRertyResId;
    //网络异常点击view id
    final int mNetErrorRertyResId;
    //空数据点击view id
    final int mEmptyRertyResId;

    //重试view id
    final int mRetryResId;


    //重试监听
    private RetryListener mRetryListener;
    //状态布局
    private final StatusLayout mStatusLayout;

    StatusLayoutManager(Builder builder) {
        this.mContentResId = builder.mContentResId;
        this.mLoadResId = builder.mLoadResId;
        this.mNetWorkErrorVs = builder.mNetWorkErrorVs;
        this.mEmptyVs = builder.mEmptyVs;
        this.mErrorVs = builder.mErrorVs;


        this.mRetryResId = builder.mRetryResId;
        this.mErrorRertyResId = builder.mErrorRertyResId;
        this.mEmptyRertyResId = builder.mEmptyRertyResId;
        this.mNetErrorRertyResId = builder.mNetErrorRertyResId;

        mStatusLayout = new StatusLayout(builder.mContext);
        mStatusLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mStatusLayout.setStatusLayoutManager(this);
    }


    /**
     * 构建参数类
     */
    public static class Builder {
        private Context mContext;
        private int mContentResId;
        private ViewStub mEmptyVs;
        int mRetryResId;
        private ViewStub mNetWorkErrorVs;
        private ViewStub mErrorVs;
        private int mLoadResId;
        private int mErrorRertyResId;
        private int mNetErrorRertyResId;
        private int mEmptyRertyResId;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder contentResId(int contentResId) {
            mContentResId = contentResId;
            return this;
        }

        //重试统一id
        public Builder retryResId(int retryResId) {
            mRetryResId = retryResId;
            return this;
        }

        //空数据view
        public Builder emptyView(int emptyResId) {
            mEmptyVs = new ViewStub(mContext);
            mEmptyVs.setLayoutResource(emptyResId);
            return this;
        }

        //网络异常view
        public Builder netWorkErrorView(int netWorkErrorResId) {
            mNetWorkErrorVs = new ViewStub(mContext);
            mNetWorkErrorVs.setLayoutResource(netWorkErrorResId);
            return this;

        }

        //错误view
        public Builder errorView(int errorResId) {
            mErrorVs = new ViewStub(mContext);
            mErrorVs.setLayoutResource(errorResId);
            return this;
        }

        public Builder loadResId(int loadResId) {
            mLoadResId = loadResId;
            return this;
        }

        public Builder errorRetryResId(int errorRertyResId) {
            mErrorRertyResId = errorRertyResId;
            return this;
        }

        public Builder netErrorRetryResId(int netErrorRertyResId) {
            mNetErrorRertyResId = netErrorRertyResId;
            return this;
        }

        public Builder emptyRetryResId(int emptyRertyResId) {
            mEmptyRertyResId = emptyRertyResId;

            return this;
        }

        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }

        public static Builder createBuilder(Context context) {
            return new Builder(context);
        }
    }

    //显示加载中view
    public void showLoading() {
        mStatusLayout.showLoading();
    }

    //显示内容view
    public void showContent() {
        mStatusLayout.showContent();
    }

    //显示网络异常view
    public void showNetWorkError() {
        mStatusLayout.showNetWorkError();
    }

    //显示网络异常view
    public void showNetWorkError(String msg) {
        mStatusLayout.showNetWorkError(msg);
    }

    //显示空数据view
    public void showEmpty() {
        mStatusLayout.showEmpty();
    }

    //显示空数据view
    public void showEmpty(String msg) {
        mStatusLayout.showEmpty(msg);
    }

    public void showError(String msg) {
        mStatusLayout.showError(msg);
    }


    public int getCurrentLayoutId() {
        return mStatusLayout.getCurrentLayoutId();
    }

    //显示错误view
    public void showError() {
        mStatusLayout.showError();
    }

    //注入状态layout
    public StatusLayoutManager inject(ViewGroup viewGroup) {
        viewGroup.addView(mStatusLayout);
        return this;
    }

    public interface RetryListener {
        void retry(View v);
    }

    RetryListener getRetryListener() {
        return mRetryListener;
    }

    public StatusLayoutManager setRetryListener(RetryListener retryListener) {
        mRetryListener = retryListener;
        return this;
    }
}
