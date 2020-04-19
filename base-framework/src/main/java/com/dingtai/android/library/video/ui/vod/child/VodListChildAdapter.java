package com.dingtai.android.library.video.ui.vod.child;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.VodProgramModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/8/29
 */
public class VodListChildAdapter extends BaseAdapter<VodProgramModel> {
    @Override
    protected ItemConverter<VodProgramModel> createItemConverter(int viewType) {
        return new ItemConverter<VodProgramModel>() {

            @Override
            public int layoutId() {
                return R.layout.item_vod_list_child;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, VodProgramModel model) {
                GlideHelper.load(holder.getView(R.id.item_image), model.getProgramContentLogo());
                holder.setText(R.id.item_title, model.getProgramContentName());
            }
        };
    }
}
