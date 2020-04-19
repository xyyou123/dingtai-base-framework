package com.dingtai.android.library.news.ui.subject.neo;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.lnr.android.base.framework.ui.control.view.recyclerview.LinearLayoutManagerWrapper;

/**
 * author:lnr
 * date:2018/9/18
 */
public class AdvertiseLinearLayoutManager extends LinearLayoutManagerWrapper {

    public AdvertiseLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        AdvertiseLinearSmoothScroller linearSmoothScroller =
                new AdvertiseLinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}

class AdvertiseLinearSmoothScroller extends LinearSmoothScroller {

    public AdvertiseLinearSmoothScroller(Context context) {
        super(context);
    }

    /**
     *
     * @param viewStart RecyclerView的top位置
     * @param viewEnd RecyclerView的Bottom位置
     * @param boxStart item的top位置
     * @param boxEnd  item的bottom位置
     * @param snapPreference 滑动方向的识别
     * @return
     */
    @Override
    public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
        return boxStart-viewStart;//返回的就是我们item置顶需要的偏移量
    }

    /**
     * 此方法返回滚动每1px需要的时间,可以用来控制滚动速度
     * 即如果返回2ms，则每滚动1000px，需要2秒钟
     * @param displayMetrics
     * @return
     */
    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return super.calculateSpeedPerPixel(displayMetrics);
    }
}
