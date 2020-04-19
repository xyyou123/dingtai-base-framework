package com.dingtai.android.library.video.ui.live.list.channel.adapter;

import com.dingtai.android.library.video.model.LiveChannelModel;
import com.dingtai.android.library.video.ui.live.list.channel.adapter.converter.ItemConverter_1;
import com.dingtai.android.library.video.ui.live.list.channel.adapter.converter.ItemConverter_3;
import com.lnr.android.base.framework.ui.control.view.recyclerview.BaseAdapter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverterCreator;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/10/9
 */
    public class AdapterProvider {
    
    private static final HashMap<String, ItemConverterCreator<LiveChannelModel>> CREATOR = new HashMap<>();

    public static BaseAdapter<LiveChannelModel> getAdapter(String type) {
        return new LiveChannelListAdapter(type);
    }

    public static ItemConverter<LiveChannelModel> getItemConvert(String type) {
        ItemConverterCreator<LiveChannelModel> creator = CREATOR.get(type + "");
        if(creator != null) return creator.create();
        switch (type) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                return new ItemConverter_3();
        }

        return new ItemConverter_1();
    }

    public static void registe(String type, ItemConverterCreator<LiveChannelModel> creator) {
        CREATOR.put(type, creator);
    }
}
