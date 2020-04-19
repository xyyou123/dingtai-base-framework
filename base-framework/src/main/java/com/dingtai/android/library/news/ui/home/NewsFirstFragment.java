package com.dingtai.android.library.news.ui.home;

import android.support.v4.widget.NestedScrollView;

import com.dingtai.android.library.news.custom.ScrollTopHelper;
import com.lnr.android.base.framework.ui.base.fragment.EmptyStatusFragment;

/**
 * author:lnr
 * date:2018/12/17
 */
public abstract class NewsFirstFragment extends EmptyStatusFragment implements ScrollTopHelper.ScrollTopCallback {

    private NestedScrollView mScrollTopNestedScrollView;

    @Override
    public void scrollToTop() {
        if(mScrollTopNestedScrollView != null) mScrollTopNestedScrollView.scrollTo(0, 0);
        autoRefresh();
    }

    public void registerScrollTop(NestedScrollView scrollView) {
        ScrollTopHelper.HELPER.register(this);
        mScrollTopNestedScrollView = scrollView;
        mScrollTopNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                ScrollTopHelper.HELPER.scroll(scrollY);
            }
        });
    }

    @Override
    public void onDestroy() {
        ScrollTopHelper.HELPER.unRegister(this);
        super.onDestroy();
    }
}
