package com.lnr.android.base.framework.ui.control.view.viewpager;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/11/15
 */
public abstract class PageGridAdapter<T> extends BaseViewPagerAdapter<PageGridAdapter.PageItem<T>> {


    protected int numColumns;
    protected int maxLines;

    public PageGridAdapter(int numColumns, int maxLines) {
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
}
