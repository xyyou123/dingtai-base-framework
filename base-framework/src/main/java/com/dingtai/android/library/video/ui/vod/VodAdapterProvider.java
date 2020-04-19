package com.dingtai.android.library.video.ui.vod;

import com.dingtai.android.library.video.model.VodListModel;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverter;
import com.lnr.android.base.framework.ui.control.view.recyclerview.ItemConverterCreator;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/10/9
 */
public class VodAdapterProvider {

    private static final HashMap<String, ItemConverterCreator<VodListModel>> CREATOR = new HashMap<>();

    public static int getItemType(VodListModel model) {
        return 0;
    }

    public static ItemConverter<VodListModel> getItemConvert(int type) {
        ItemConverterCreator<VodListModel> creator = CREATOR.get(type + "");
        if(creator != null) return creator.create();

        return new DefaultItemConverter();
    }

    public static void registe(String type, ItemConverterCreator<VodListModel> creator) {
        CREATOR.put(type, creator);
    }
}
