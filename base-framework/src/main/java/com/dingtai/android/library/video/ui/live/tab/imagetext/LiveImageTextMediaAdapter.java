package com.dingtai.android.library.video.ui.live.tab.imagetext;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.model.models.PlayerModel;
import com.dingtai.android.library.resource.Routes;

import com.dingtai.android.library.video.model.LiveImageTextModel;
import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.common.image.load.GlideHelper;
import com.lnr.android.base.framework.common.image.look.ImageLook;

import java.util.Arrays;
import java.util.List;

/**
 * author:lnr
 * date:2018/9/27
 */
public class LiveImageTextMediaAdapter extends android.widget.BaseAdapter {

    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_VIDEO = 3;

    public static class ImageItem {

        public int type;
        public String url;
        public String videoUrl;
        public LiveImageTextModel model;

        public ImageItem(int type, String url, LiveImageTextModel model) {
            this.type = type;
            this.url = url;
            this.model = model;
        }

        public ImageItem(int type, String url, String videoUrl, LiveImageTextModel model) {
            this.type = type;
            this.url = url;
            this.videoUrl = videoUrl;
            this.model = model;
        }
    }


    protected List<ImageItem> imageItems;

    public LiveImageTextMediaAdapter(List<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public int getCount() {
        return imageItems.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return imageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(parent.getContext(), R.layout.item_live_image, null);
        view.findViewById(R.id.item_delete).setVisibility(View.GONE);
        ImageItem imageItem = getItem(position);
        GlideHelper.load(view.findViewById(R.id.item_image), imageItem.url);
        view.findViewById(R.id.item_play).setVisibility(imageItem.type == TYPE_VIDEO ? View.VISIBLE : View.GONE);
        return view;
    }

    public static AdapterView.OnItemClickListener createListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LiveImageTextMediaAdapter adapter = (LiveImageTextMediaAdapter) parent.getAdapter();
                if(adapter == null) return;

                ImageItem item = adapter.getItem(position);
                if(item.type == TYPE_IMAGE) {
                    ImageLook.look(position, Arrays.asList(item.model.getPicUrl().split(",")), item.model.getNewsLiveContent());
                }else if(item.type == TYPE_VIDEO) {
                    PlayerModel model = com.dingtai.android.library.model.models.PlayerModel.Builder.newBuilder(com.dingtai.android.library.model.models.PlayerModel.TYPE_VOD)
                            .addUrls(item.videoUrl)
                            .build();
                    ARouter.getInstance().build(Routes.Video.PLAYER_SIMPLE)
                            .withParcelable("model", model).navigation();
                }
            }
        };
    }
}
