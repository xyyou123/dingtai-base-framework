package com.lnr.android.base.framework.ui.control.web.more;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class MoreActionAdapter extends BaseAdapter<MoreAction> {

    @Override
    protected ItemConverter<MoreAction> createItemConverter(int viewType) {
        return new ItemConverter<MoreAction>() {

            @Override
            public int layoutId() {
                return R.layout.item_web_moreaction;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, MoreAction moreAction) {
                GlideHelper.load(holder.getView(R.id.more_action_image), moreAction.getRes());
                holder.setText(R.id.more_action_title, moreAction.getTitle());

                holder.addOnClickListener(R.id.more_action_image);
            }
        };
    }
}
