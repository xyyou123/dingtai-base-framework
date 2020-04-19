package com.lnr.android.base.framework.ui.control.view.viewpager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lnr.android.base.framework.ui.control.view.adapterview.FixGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/11/15
 */
public abstract class SimplePageGridAdapter<T> extends BaseViewPagerAdapter<SimplePageGridAdapter.PageItem<T>> {


    protected int numColumns;
    protected int maxLines;

    public SimplePageGridAdapter(int numColumns, int maxLines) {
        super(null);
        this.numColumns = numColumns;
        this.maxLines = maxLines;
    }

    public void setNewData(List<T> newData) {
        data = new ArrayList<>();

        PageItem<T> item = new PageItem<>();
        item.index = 0;
        item.data = new ArrayList<>();
        int max = numColumns * maxLines;
        if(max <= 0) {
            throw new IllegalArgumentException("numColumns * maxLines must not be <= 0");
        }

        for (T t : newData) {
            if(item.data.size() >= max) {
                int index = item.index + 1;
                data.add(item);

                item = new PageItem<>();
                item.index = index;
                item.data = new ArrayList<>();
            }
            item.data.add(t);
        }

        if(item.data.size() > 0) {
            data.add(item);
        }
    }

    public static class PageItem<T> {

        private int index;
        private List<T> data;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }
    }

    @Override
    protected View createView(ViewGroup container, int position, PageItem<T> tPageItem) {
        FixGridView gridView = new FixGridView(container.getContext());
        gridView.setNumColumns(numColumns);
        return gridView;
    }

    @Override
    protected void convert(View view, int position, PageItem<T> tPageItem) {
        convert((GridView)view, position, tPageItem);
    }

    protected abstract void convert(GridView gridView, int position, PageItem<T> item);
}
