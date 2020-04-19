package com.dingtai.android.library.video.ui.live.tab.imagetext;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import com.dingtai.android.library.video.control.LiveCommentBoderLayout;
import com.dingtai.android.library.video.model.LiveImageTextModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.ui.control.view.adapterview.FixGridView;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.uitl.NumberUtil;
import com.lnr.android.base.framework.uitl.date.DateUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.dingtai.android.library.video.ui.live.tab.imagetext.LiveImageTextMediaAdapter.TYPE_IMAGE;
import static com.dingtai.android.library.video.ui.live.tab.imagetext.LiveImageTextMediaAdapter.TYPE_VIDEO;

/**
 * author:lnr
 * date:2018/9/29
 * 图文适配器
 */
public class LiveImageTextAdapter extends BaseAdapter<LiveImageTextModel> {
    public static int placeholder_circle = R.drawable.icon_default_user;
    public static int error_circle = R.drawable.icon_default_user;

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
    protected ItemConverter<LiveImageTextModel> createItemConverter(int viewType) {
        return new ItemConverter<LiveImageTextModel>() {
            @Override
            public int layoutId() {
                return R.layout.item_live_image_text;
            }

            @Override
            public void convert(BaseViewHolder holder, int position, LiveImageTextModel model) {
                if (TextUtils.isEmpty(model.getCommentContent())) {
                    holder.getView(R.id.comment_layout).setSelected(false);
                    holder.getView(R.id.comment_layout_sub).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.comment_layout).setSelected(true);
                    holder.getView(R.id.comment_layout_sub).setVisibility(View.VISIBLE);

                    holder.setText(R.id.text_username_sub, fotmatCommentUserName(model.getUserNickName(), model.getUserName()));

                    holder.setText(R.id.text_content_sub, model.getCommentContent());
                    holder.setText(R.id.text_time_sub, DateUtil.formatFromString(model.getCommentTime()));
                    GlideHelper.loadCircle(holder.getView(R.id.image_usericon_sub), model.getUserLOGO());
                }

                holder.setGone(R.id.image_top, "True".equals(model.getIsTop()));

                LiveCommentBoderLayout layout = (LiveCommentBoderLayout) holder.itemView;
                layout.setValue(position == 0, DateUtil.formatFromString(model.getCreateTime()));

                holder.setText(R.id.text_username, TextUtils.isEmpty(model.getHostName()) ? "主持人[直播酱]" : model.getHostName());
                holder.setText(R.id.text_content, TextUtils.isEmpty(model.getNewsLiveContent()) ? model.getNewsLiveTitle() : model.getNewsLiveContent());
                GlideHelper.loadCircle(holder.getView(R.id.image_usericon), model.getHostLogo(), placeholder_circle, error_circle);

                if (!TextUtils.isEmpty(model.getMediaURL())) {
                    holder.setGone(R.id.item_video, true);
                    holder.addOnClickListener(R.id.item_video);
                    GlideHelper.load(holder.getView(R.id.item_video_icon), model.getMediaLogo());
                } else {
                    holder.setGone(R.id.item_video, false);
                }

                FixGridView grid = holder.getView(R.id.FixGridView);
                String imageUrls = null;
                String videoUrls = null;
                switch (NumberUtil.parseInt(model.getNewsLiveType())) {
                    case 2:
                        imageUrls = model.getPicUrl();
                        break;
                    case 4:
                        imageUrls = model.getPicUrl();
                        videoUrls = model.getMediaURL();
                        break;
                }

                boolean emptyImage = TextUtils.isEmpty(imageUrls);
                boolean emptyVideo = TextUtils.isEmpty(videoUrls);
                if (emptyImage) {
                    grid.setVisibility(View.GONE);
                } else {
                    List<LiveImageTextMediaAdapter.ImageItem> itemList = new ArrayList<>();
                    if (emptyVideo) {
                        String[] images = imageUrls.split(",");
                        for (String path : images) {
                            itemList.add(new LiveImageTextMediaAdapter.ImageItem(TYPE_IMAGE, path, model));
                        }
                    } else {
                        String[] images = imageUrls.split(",");
                        String[] videos = videoUrls.split(",");
                        int count = Math.min(images.length, videos.length);
                        for (int i = 0; i < count; i++) {
                            itemList.add(new LiveImageTextMediaAdapter.ImageItem(TYPE_VIDEO,
                                    images[i], videos[i], model));
                        }
                    }

                    if (itemList.isEmpty()) {
                        grid.setVisibility(View.GONE);
                    } else {
                        grid.setVisibility(View.VISIBLE);
                        int size = itemList.size();
                        if (size < 4) {
                            grid.setNumColumns(size);
                        } else if (size == 4) {
                            grid.setNumColumns(2);
                        } else {
                            grid.setNumColumns(3);
                        }
                        grid.setAdapter(new LiveImageTextMediaAdapter(itemList));
                        grid.setOnItemClickListener(LiveImageTextMediaAdapter.createListener());
                    }
                }
            }
        };
    }
}
