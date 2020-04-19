package com.dingtai.android.library.video.ui.shortvideo.detial;

import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/8/29
 */
public class ShortVideoDetialAdapter extends BaseAdapter<ShortVideoModel> {

    @Override
    protected int getDefItemViewType(int position) {
        return ShortVideoDetialAdapterProvider.getItemType(getItem(position));
    }

    @Override
    protected ItemConverter<ShortVideoModel> createItemConverter(int viewType) {
        return ShortVideoDetialAdapterProvider.getItemConvert(viewType);
    }
}
