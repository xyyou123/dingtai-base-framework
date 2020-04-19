package com.lnr.android.base.framework.ui.control.view.recyclerview;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author:lnr
 * date:2018/6/2
 * 布局填充器
 */

public interface ItemConverter<DATA> {

    int layoutId();

    void convert(BaseViewHolder holder, int position, DATA item);

}
