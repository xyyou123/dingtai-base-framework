package com.lnr.android.base.framework.ui.control.view.recyclerview;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author:lnr
 * date:2018/11/8
 */
public abstract class BasePagerAdapter<T> extends BaseAdapter<T> {

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public final int getItemViewType(int position) {
        if(mData.size() == 0) return 0;
        return super.getItemViewType(position % mData.size());
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(mData.size() > 0) {
            super.onBindViewHolder(holder, position % mData.size());
        }else {
            super.onBindViewHolder(holder, position);
        }
    }
}
