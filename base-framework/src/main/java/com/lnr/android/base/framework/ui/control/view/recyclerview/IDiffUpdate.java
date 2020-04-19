package com.lnr.android.base.framework.ui.control.view.recyclerview;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * author:lnr
 * date:2018/12/4
 */
public interface IDiffUpdate<T> {

    void update(BaseViewHolder holder, int position, T item, Object payload);
}
