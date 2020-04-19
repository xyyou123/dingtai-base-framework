package com.dingtai.android.library.video.ui.live.tab.chat;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.control.LiveCommentBoderLayout;
import com.dingtai.android.library.video.model.LiveCommentModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.date.DateUtil;

import java.text.MessageFormat;

/**
 * author:lnr
 * date:2018/9/3
 */
public class LiveChatCommentAdapter extends BaseAdapter<LiveCommentModel> {

    public static int DEFAULT_ICON_ID = R.id.image_usericon;

    private String fotmatCommentUserName(String NickName, String UserName) {
        if (TextUtils.isEmpty(NickName) && !TextUtils.isEmpty(UserName)) {
            if (UserName.length() > 7) {
                return MessageFormat.format("{0}****{1}", UserName.substring(0, 3), UserName.substring(7, 11));
            } else {
                return UserName;
            }
        } else if (TextUtils.isEmpty(NickName)) {
            return "**";
        } else {
            return NickName;
        }
    }



    @Override
    protected ItemConverter<LiveCommentModel> createItemConverter(int viewType) {
        return new ItemConverter<LiveCommentModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_live_chat_comment;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, LiveCommentModel model) {
                if(TextUtils.isEmpty(model.getSubContent())) {
                    holder.getView(R.id.comment_layout).setSelected(false);
                    holder.getView(R.id.comment_layout_sub).setVisibility(View.GONE);
                }else {
                    holder.getView(R.id.comment_layout).setSelected(true);
                    holder.getView(R.id.comment_layout_sub).setVisibility(View.VISIBLE);

                    holder.setText(R.id.text_username_sub, fotmatCommentUserName(model.getSubUserNickName(), model.getSubUserName()));

                    holder.setText(R.id.text_content_sub, model.getSubContent());
                    holder.setText(R.id.text_time_sub, DateUtil.formatFromString(model.getSubTime()));
                    GlideHelper.loadCircle(holder.getView(DEFAULT_ICON_ID), model.getSubUserLOGO());
                }

                LiveCommentBoderLayout layout = (LiveCommentBoderLayout) holder.itemView;
                layout.setValue(position == 0, DateUtil.formatFromString(model.getCommentTime()));

                holder.setText(R.id.text_username, fotmatCommentUserName(model.getUserNickName(), model.getUserName()));
                holder.setText(R.id.text_content, model.getCommentContent());
                GlideHelper.loadCircle(holder.getView(DEFAULT_ICON_ID), model.getUserLOGO());
            }
        };
    }
}
