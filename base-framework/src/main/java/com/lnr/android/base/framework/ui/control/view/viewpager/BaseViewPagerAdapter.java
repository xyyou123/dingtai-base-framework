package com.lnr.android.base.framework.ui.control.view.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {

    protected List<T> data;

    protected List<View> cacheView = new ArrayList<>();


    public BaseViewPagerAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        cacheView.add((View) object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        T t = data.get(position);
        View view;
        if(!cacheView.isEmpty()) {
            view = cacheView.remove(0);
        }else {
            view = createView(container, position, t);
        }

        convert(view, position, t);

        container.addView(view);
        return view;
    }

    protected abstract View createView(ViewGroup container, int position, T t);

    protected abstract void convert(View view, int position, T t);
}
