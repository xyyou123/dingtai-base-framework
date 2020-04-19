package com.dingtai.android.library.video.ui.vod.details.comment;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.VodCommentModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.StringUtil;

/**
 * author:lnr
 * date:2019/1/9
 */
public class VodCommentAdapter extends BaseAdapter<VodCommentModel> {
    @Override
    protected ItemConverter<VodCommentModel> createItemConverter(int viewType) {
        return new ItemConverter<VodCommentModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_vod_comment;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, VodCommentModel model) {
                GlideHelper.loadCircle(holder.getView(R.id.item_icon), model.getUserLOGO());
                holder.setText(R.id.item_name, TextUtils.isEmpty(model.getUserNickName()) ?
                        StringUtil.formatPhone(model.getUserName()) : model.getUserNickName());
                holder.setText(R.id.item_time, model.getCommentTime());

                holder.setGone(R.id.item_top, false);

                holder.getView(R.id.item_zan_image).setSelected(model.isGoodPoint());

                holder.setText(R.id.item_zan_count, NumberUtil.parseInt(model.getGetGoodPoint()) + "");

                holder.setText(R.id.item_content, model.getCommentContent());

                holder.addOnClickListener(R.id.item_zan_image);
                holder.addOnClickListener(R.id.item_edit);
            }
        };
    }
}
