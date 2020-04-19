package com.lnr.android.base.framework.common.umeng;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

public class UmengActionAdapter extends BaseAdapter<UmengAction> {

    @Override
    protected ItemConverter<UmengAction> createItemConverter(int viewType) {
        return new ItemConverter<UmengAction>() {

            @Override
            public int layoutId() {
                return R.layout.item_umeng_action;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, UmengAction umengAction) {
                holder.setText(R.id.item_title, umengAction.getName());
                GlideHelper.load(holder.getView(R.id.item_icon), umengAction.getRes());
            }
        };
    }
}
