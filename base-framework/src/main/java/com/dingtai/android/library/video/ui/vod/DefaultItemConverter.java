package com.dingtai.android.library.video.ui.vod;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

import java.text.MessageFormat;

/**
 * author:lnr
 * date:2018/11/5
 */
public class DefaultItemConverter implements ItemConverter<VodListModel> {
    @Override
    public int layoutId() {
        return R.layout.item_vod_list;
    }

    @Override
    public void convert(BaseViewHolder holder, int position, VodListModel vodListModel) {
        GlideHelper.load(holder.getView(R.id.item_image), vodListModel.getProgramLogo());
        holder.setText(R.id.item_title, vodListModel.getProgramName());
        holder.setText(R.id.item_time, MessageFormat.format("最新更新时间:{0}", vodListModel.getNewDateTime()));
    }
}
