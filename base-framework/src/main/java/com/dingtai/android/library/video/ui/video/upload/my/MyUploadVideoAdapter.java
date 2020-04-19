package com.dingtai.android.library.video.ui.video.upload.my;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.VideoModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.ContextUtil;

/**
 * author:lnr
 * date:2018/12/4
 */
public class MyUploadVideoAdapter extends BaseAdapter<VideoModel> {
    @Override
    protected ItemConverter<VideoModel> createItemConverter(int viewType) {
        return new ItemConverter<VideoModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_my_uploadvideo;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, VideoModel item) {
                GlideHelper.load(holder.getView(R.id.item_image), item.getImageUrl());

                holder.setText(R.id.item_title, item.getName());
                holder.setText(R.id.item_time, item.getUploadDate());

                TextView textViewAudit = holder.getView(R.id.item_audit);

                if ("True".equals(item.getIsApproved())) {
                    textViewAudit.setSelected(true);
                    textViewAudit.setText("已审核");
                    textViewAudit.setTextColor(ContextUtil.getColor(R.color.green2));

                } else {
                    textViewAudit.setSelected(false);
                    textViewAudit.setText("未审核");
                    textViewAudit.setTextColor(ContextUtil.getColor(R.color.red));
                }

                TextView textViewConverted = holder.getView(R.id.item_transcodeing);
                String state = item.getIsConverted();
                if ("0".equals(state)) {
                    textViewConverted.setSelected(false);
                    textViewConverted.setTextColor(ContextUtil.getColor(R.color.red));
                    textViewConverted.setText("未转换");
                } else if ("2".equals(state)) {
                    textViewConverted.setSelected(false);
                    textViewConverted.setTextColor(ContextUtil.getColor(R.color.red));
                    textViewConverted.setText("转换中");
                } else if ("3".equals(state)) {
                    textViewConverted.setSelected(true);
                    textViewConverted.setText("转成功");
                    textViewConverted.setTextColor(ContextUtil.getColor(R.color.green2));
                } else if ("4".equals(state)) {
                    textViewConverted.setSelected(false);
                    textViewConverted.setTextColor(ContextUtil.getColor(R.color.red));
                    textViewConverted.setText("转失败");
                }

                holder.addOnClickListener(R.id.item_layout_content);
                holder.addOnClickListener(R.id.item_delete);
            }
        };
    }
}
