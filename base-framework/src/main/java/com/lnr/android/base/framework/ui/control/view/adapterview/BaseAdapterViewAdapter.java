package com.lnr.android.base.framework.ui.control.view.adapterview;


import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/7
 */
public abstract class BaseAdapterViewAdapter<T> extends BaseAdapter {

    private List<T> datas;

    public BaseAdapterViewAdapter() {
        if(datas == null) {
            datas = new ArrayList<>();
        }
    }

    public BaseAdapterViewAdapter(List<T> datas) {
        this.datas = datas;
        if(datas == null) {
            this.datas = new ArrayList<>();
        }
    }

    public List<T> getDatas() {
        return datas;
    }

    public void add(T t) {
        datas.add(t);
    }

    public void addAll(List<T> list) {
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(position < 0 || position >= datas.size()) {
            return;
        }

        datas.remove(position);
    }

    public void clear() {
        datas.clear();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public int getViewType(int position) {
        return 0;
    }

    protected abstract View createView(ViewGroup parent, Context context, int type);

    protected abstract void convert(ViewHolder holder, int position, T t);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder(createView(parent, parent.getContext(), getViewType(position)));
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        convert(holder, position, getItem(position));

        return holder.contentView;
    }

    protected static class ViewHolder {

        private SparseArray<View> viewLists;
        private View contentView;

        public ViewHolder(View view) {
            viewLists = new SparseArray<>();
            contentView = view;
            contentView.setTag(this);
        }

        public <T extends View> T getView(int id) {
            T t = (T) viewLists.get(id);
            if(t == null) {
                t = contentView.findViewById(id);
                viewLists.put(id, t);
            }
            return t;
        }

        public View getContentView() {
            return contentView;
        }

        public ImageView getImageView(int id) {
            return getView(id);
        }

        public void setText(int id, String content) {
            TextView view = getView(id);
            view.setText(content);
        }
    }
}
