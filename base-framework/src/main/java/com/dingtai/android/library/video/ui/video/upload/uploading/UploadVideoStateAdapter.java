package com.dingtai.android.library.video.ui.video.upload.uploading;

import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.ui.video.upload.UploadVideoManager;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.DiffItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;

/**
 * author:lnr
 * date:2018/12/4
 */
public class UploadVideoStateAdapter extends BaseAdapter<UploadVideoManager.UploadState> {

    @Override
    protected ItemConverter<UploadVideoManager.UploadState> createItemConverter(int viewType) {
        return new DiffItemConverter<UploadVideoManager.UploadState>() {
            @Override
            public int layoutId() {
                return R.layout.item_video_uploading;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, UploadVideoManager.UploadState item) {
                GlideHelper.load(holder.getView(R.id.item_image), item.getModel().getMediaUrl());

                holder.setText(R.id.item_title, item.getModel().getName());

                ProgressBar bar = holder.getView(R.id.item_progressbar);
                if(item.isError()) {
                    if(item.getState() < UploadVideoManager.UploadState.STATE_COMPLETE) {
                        bar.setProgress(0);
                        holder.setText(R.id.item_progress_text, "0/100");
                        holder.setText(R.id.item_state, "上传失败");
                    }else {
                        bar.setProgress(100);
                        holder.setText(R.id.item_progress_text, "0/100");
                        holder.setText(R.id.item_state, "信息提交失败");
                    }

                    holder.setGone(R.id.item_retry, true);
                    return;
                }

                holder.setGone(R.id.item_retry, false);

                switch (item.getState()) {
                    case UploadVideoManager.UploadState.STATE_WAIT:
                        holder.setText(R.id.item_state, "等待中");
                        holder.setText(R.id.item_progress_text, "0/100");
                        bar.setProgress(0);
                        break;
                    case UploadVideoManager.UploadState.STATE_BEGIN:
                        holder.setText(R.id.item_state, "正在上传");
                        holder.setText(R.id.item_progress_text, "0/100");
                        bar.setProgress(0);
                        break;
                    case UploadVideoManager.UploadState.STATE_PROGRESS:
                        holder.setText(R.id.item_state, "正在上传");
                        holder.setText(R.id.item_progress_text, item.getProgress() + "/100");
                        bar.setProgress(item.getProgress());
                        break;
                    case UploadVideoManager.UploadState.STATE_COMPLETE:
                        holder.setText(R.id.item_progress_text, "100/100");
                        bar.setProgress(100);
                        holder.setText(R.id.item_state, "上传成功");
                        break;
                }


                holder.addOnClickListener(R.id.item_delete);
                holder.addOnClickListener(R.id.item_retry);
            }

            @Override
            public void update(BaseViewHolder holder, int position, UploadVideoManager.UploadState item, Object payload) {
                ProgressBar bar = holder.getView(R.id.item_progressbar);

                if(item.isError()) {
                    if(item.getState() < UploadVideoManager.UploadState.STATE_COMPLETE) {
                        bar.setProgress(0);
                        holder.setText(R.id.item_progress_text, "0/100");
                        holder.setText(R.id.item_state, "上传失败");
                    }else {
                        bar.setProgress(100);
                        holder.setText(R.id.item_progress_text, "0/100");
                        holder.setText(R.id.item_state, "信息提交失败");
                    }
                    holder.setGone(R.id.item_retry, true);
                    return;
                }

                holder.setGone(R.id.item_retry, false);

                switch (item.getState()) {
                    case UploadVideoManager.UploadState.STATE_WAIT:
                        holder.setText(R.id.item_state, "等待中");
                        holder.setText(R.id.item_progress_text, "0/100");
                        bar.setProgress(0);
                        break;
                    case UploadVideoManager.UploadState.STATE_BEGIN:
                        holder.setText(R.id.item_state, "正在上传");
                        holder.setText(R.id.item_progress_text, "0/100");
                        bar.setProgress(0);
                        break;
                    case UploadVideoManager.UploadState.STATE_PROGRESS:
                        holder.setText(R.id.item_state, "正在上传");
                        holder.setText(R.id.item_progress_text, item.getProgress() + "/100");
                        bar.setProgress(item.getProgress());
                        break;
                    case UploadVideoManager.UploadState.STATE_COMPLETE:
                        holder.setText(R.id.item_progress_text, "100/100");
                        bar.setProgress(100);
                        holder.setText(R.id.item_state, "上传成功");
                        break;
                }
            }
        };
    }
}
