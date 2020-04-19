package com.dingtai.android.library.news.custom;

/**
 * author:lnr
 * date:2018/12/17
 */
public class ScrollTopHelper {

    public static final ScrollTopHelper HELPER = new ScrollTopHelper();

    private ScrollTopTabCallback mScrollTopTabCallback;
    private ScrollTopScrollerParentCallback mScrollTopScrollerParentCallback;
    private ScrollTopCallback mScrollTopCallback;

    private int scrollY;
    private boolean mIsCapture;
    /**
     * 滑动阈值
     */
    private int scrollThresholdY = 300;

    /**
     * 带tab的页面实现此接口
     */
    public interface ScrollTopTabCallback {
        /**
         * 滑动子tab时调用，捕获时就需要根据距离判断显示的图标 不捕获则显示正常的图标即可
         * @param isCapture 是否捕获
         */
        void capture(boolean isCapture);
    }

    /**
     * 滑动的父级页面实现此接口
     */
    public interface ScrollTopScrollerParentCallback {
        /**
         * 滑动时调用
         * @param scrollY 滑动的距离
         */
        void scroll(int scrollY);
        /**
         * 滑动到顶部时调用
         */
        void scrollToTop();
    }

    /**
     * 滑动页面实现此接口
     */
    public interface ScrollTopCallback {
        /**
         * 滑动到顶部时调用
         */
        void scrollToTop();
    }

    private ScrollTopHelper() {
    }

    public void register(ScrollTopTabCallback callback) {
        this.mScrollTopTabCallback = callback;
    }

    public void unRegister(ScrollTopTabCallback callback) {
        this.mScrollTopTabCallback = null;
    }

    public void register(ScrollTopScrollerParentCallback callback) {
        this.mScrollTopScrollerParentCallback = callback;
    }

    public void unRegister(ScrollTopScrollerParentCallback callback) {
        this.mScrollTopScrollerParentCallback = null;
    }

    public void register(ScrollTopCallback callback) {
        this.mScrollTopCallback = callback;
    }

    public void unRegister(ScrollTopCallback callback) {
        this.mScrollTopCallback = null;
    }

    /**
     * 设置滑动阈值 默认300
     * @param scrollThresholdY 阈值
     */
    public void setScrollThresholdY(int scrollThresholdY) {
        this.scrollThresholdY = scrollThresholdY;
    }

    public boolean isCanScrollTop() {
        return mIsCapture && scrollY > scrollThresholdY;
    }

    public void scroll(int scrollY) {
        this.scrollY = scrollY;
        if(mScrollTopScrollerParentCallback != null) {
            mScrollTopScrollerParentCallback.scroll(scrollY);
        }

        boolean isCapture = scrollY > scrollThresholdY;
        if(mIsCapture == isCapture) {
            return;
        }
        if(mScrollTopTabCallback != null) {
            mScrollTopTabCallback.capture(isCapture);
        }
        mIsCapture = isCapture;
    }

    public void scrollToTop() {
        if(mScrollTopScrollerParentCallback != null) {
            mScrollTopScrollerParentCallback.scrollToTop();
        }
        if(mScrollTopCallback != null) {
            mScrollTopCallback.scrollToTop();
        }
    }

    public void capture(boolean isCapture) {
        isCapture = (scrollY > scrollThresholdY) & isCapture;
        if(mIsCapture == isCapture) return;
        mIsCapture = isCapture;
        if(mScrollTopTabCallback != null) {
            mScrollTopTabCallback.capture(isCapture);
        }
    }
}
