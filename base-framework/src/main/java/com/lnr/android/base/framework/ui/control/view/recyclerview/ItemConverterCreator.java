package com.lnr.android.base.framework.ui.control.view.recyclerview;

/**
 * author:lnr
 * date:2018/11/2
 */
public interface ItemConverterCreator<T> {

    ItemConverter<T> create();
}
