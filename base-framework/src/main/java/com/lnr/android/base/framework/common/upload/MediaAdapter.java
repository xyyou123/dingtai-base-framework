package com.lnr.android.base.framework.common.upload;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.common.image.look.ImageLook;
import com.lnr.android.base.framework.ui.control.listener.OnClickListener;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;
import com.lnr.android.base.framework.ui.control.view.adapterview.BaseAdapterViewAdapter;
import com.lnr.android.base.framework.uitl.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lnr
 * date:2018/10/11
 */
public class MediaAdapter extends BaseAdapterViewAdapter<MediaAdapter.MediaItem> {

    private int imageCount;
    private int videoCount;

    public void setOpLister(OpLister opLister) {
        this.opLister = opLister;
    }

    private OpLister opLister;

    private boolean showDelete;

    public static class MediaItem {

        public String imageUrl;

        public boolean video;

        public String videoUrl;

        public Object data;

        public MediaItem(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public MediaItem(String imageUrl, boolean video) {
            this.imageUrl = imageUrl;
            this.video = video;
        }

        public MediaItem(String imageUrl, Object data) {
            this.imageUrl = imageUrl;
            this.data = data;
        }

        public MediaItem(String imageUrl, boolean video, Object data) {
            this.imageUrl = imageUrl;
            this.video = video;
            this.data = data;
        }

        public MediaItem(String imageUrl, String videoUrl, Object data) {
            this.imageUrl = imageUrl;
            this.videoUrl = videoUrl;
            this.video = true;
            this.data = data;
        }
    }

    public MediaAdapter() {
    }

    public MediaAdapter(List<MediaItem> datas) {
        super(datas);
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
        notifyDataSetChanged();
    }

    @Override
    public void add(MediaItem mediaItem) {
        super.add(mediaItem);

        if (mediaItem.video) {
            videoCount++;
        } else {
            imageCount++;
        }
    }


    @Override
    public void addAll(List<MediaItem> list) {
        super.addAll(list);

        for (MediaItem item : list) {
            if (item.video) {
                videoCount++;
            } else {
                imageCount++;
            }
        }
    }

    @Override
    public void remove(int position) {

        MediaItem item = getItem(position);
        if (item.video) {
            videoCount--;
        } else {
            imageCount--;
        }
        super.remove(position);
    }

    public int getImageCount() {
        return imageCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    @Override
    protected View createView(ViewGroup parent, Context context, int type) {
        return LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
    }

    @Override
    protected void convert(BaseAdapterViewAdapter.ViewHolder holder, final int position, final MediaItem mediaItem) {
        if (!mediaItem.video) {
            holder.getView(R.id.item_play).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.item_play).setVisibility(View.VISIBLE);
        }
        GlideHelper.load(holder.getImageView(R.id.item_image), mediaItem.imageUrl);

        holder.getView(R.id.item_delete).setVisibility(showDelete ? View.VISIBLE : View.GONE);

        ViewListen.setClick(holder.getView(R.id.item_delete), new OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                MediaItem deleteItem = getDatas().get(position);
                if (opLister != null) {
                    opLister.delete(deleteItem, position);
                }
                remove(position);

                notifyDataSetChanged();
            }
        });
    }


    public List<String> getAllImagePath() {
        List<String> paths = new ArrayList<>();
        for (MediaItem item : getDatas()) {
            if (!item.video) {
                paths.add(item.imageUrl);
            }
        }

        return paths;
    }

    /**
     * grid点击事件
     */
    public AdapterView.OnItemClickListener getSimpleItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaItem item = getItem(position);
                if (!item.video) {
                    List<String> contents = new ArrayList<>();
                    ImageLook.look(item.imageUrl, getAllImagePath(), contents);
                } else {
                    String videoUrl =TextUtils.isEmpty(item.videoUrl)?
                            (TextUtils.isEmpty(item.imageUrl)?"":item.imageUrl)
                            : item.videoUrl;
                    if (!TextUtils.isEmpty(videoUrl)) {
                        ARouter.getInstance().build(Routes.Video.PLAYER_SIMPLE)
                                .withParcelable("model", PlayerModel.Builder.newBuilder(PlayerModel.TYPE_VOD)
                                        .addUrls(videoUrl.startsWith("http") ? videoUrl : FileUtil.contentPath(videoUrl)).build())
                                .navigation();
                    }

                }
            }
        };
    }


    public interface OpLister {
        void delete(MediaItem mediaItem, int postion);
    }
}

