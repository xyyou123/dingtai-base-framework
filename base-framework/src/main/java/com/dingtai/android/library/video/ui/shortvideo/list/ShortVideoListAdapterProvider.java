package com.dingtai.android.library.video.ui.shortvideo.list;

import com.dingtai.android.library.video.model.ShortVideoModel;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverterCreator;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/10/9
 */
public class ShortVideoListAdapterProvider {

    private static final HashMap<String, ItemConverterCreator<ShortVideoModel>> CREATOR = new HashMap<>();

    public static int getItemType(ShortVideoModel model) {
        return 0;
    }

    public static ItemConverter<ShortVideoModel> getItemConvert(int type) {
        ItemConverterCreator<ShortVideoModel> creator = CREATOR.get(type + "");
        if(creator != null) return creator.create();

        return new ListItemConverter();
    }

    public static void registe(String type, ItemConverterCreator<ShortVideoModel> creator) {
        CREATOR.put(type, creator);
    }
}