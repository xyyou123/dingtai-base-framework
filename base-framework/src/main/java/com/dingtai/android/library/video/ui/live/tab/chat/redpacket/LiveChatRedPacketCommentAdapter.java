package com.dingtai.android.library.video.ui.live.tab.chat.redpacket;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.model.LiveCommentModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.NumberUtil;

import java.text.MessageFormat;

/**
 * author:lnr
 * date:2018/9/3
 */
public class LiveChatRedPacketCommentAdapter extends BaseAdapter<LiveCommentModel> {

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
    protected int getDefItemViewType(int position) {
        return NumberUtil.parseInt(getItem(position).getComments());
    }

    @Override
    protected ItemConverter<LiveCommentModel> createItemConverter(int viewType) {
        switch (viewType) {
            case 1://红包
               return new ItemConverter<LiveCommentModel>() {
                   @Override
                   public int layoutId() {
                       return R.layout.item_live_chat_redpacket;
                   }

                   @Override
                   public void convert(BaseViewHolder holder, int position, LiveCommentModel item) {
                       holder.setText(R.id.text_username, fotmatCommentUserName(item.getUserNickName(), item.getUserName()));
                       GlideHelper.loadCircle(holder.getView(R.id.image_usericon), item.getUserLOGO());

                       holder.addOnClickListener(R.id.item_redpacket_open);
                   }
               };
            case 2://领取历史
                return new ItemConverter<LiveCommentModel>() {
                    @Override
                    public int layoutId() {
                        return R.layout.item_live_chat_redpacket_history;
                    }

                    @Override
                    public void convert(BaseViewHolder holder, int position, LiveCommentModel item) {
                        String content = item.getCommentContent();
                        SpannableStringBuilder style = new SpannableStringBuilder(content);
                        for (int i = 0, size = content.length(); i < size; i++) {
                            char a = content.charAt(i);
                            if (a >= '0' && a <= '9') {
                                style.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        holder.setText(R.id.item_content, style);
                    }
                };
            default://正常数据
                return new ItemConverter<LiveCommentModel>() {
                    @Override
                    public int layoutId() {
                        return R.layout.item_live_chat_redpacket_comment;
                    }

                    @Override
                    public void convert(BaseViewHolder holder, int position, LiveCommentModel model) {
                        holder.setText(R.id.text_username, fotmatCommentUserName(model.getUserNickName(), model.getUserName()));
                        holder.setText(R.id.text_content, model.getCommentContent());
                        GlideHelper.loadCircle(holder.getView(R.id.image_usericon), model.getUserLOGO());
                    }
                };
        }
    }
}
