package com.dingtai.android.library.video.ui.vod;

import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/8/29
 */
public class VodListAdapter extends BaseAdapter<VodListModel> {

    @Override
    protected int getDefItemViewType(int position) {
        return VodAdapterProvider.getItemType(getItem(position));
    }

    @Override
    protected ItemConverter<VodListModel> createItemConverter(int viewType) {
        return VodAdapterProvider.getItemConvert(viewType);
    }
}
