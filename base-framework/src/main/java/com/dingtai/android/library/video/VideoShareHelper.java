package com.dingtai.android.library.video;

import com.dingtai.android.library.resource.GlobalConfig;
import com.dingtai.android.library.resource.Resource;

/**
 * author:lnr
 * date:2019/1/9
 */
public class VideoShareHelper {

    public static String getLiveShareUrl(int type, String id) {
        String url = null;
        switch (type) {
            case VideoComponentConstant.LiveType.VEDIO:
                url = GlobalConfig.SHARE_URL + "/Share2/dszb.aspx?id=" + id;
                break;
            case VideoComponentConstant.LiveType.AUDIO:
                url = GlobalConfig.SHARE_URL + "/Share2/dtzb.aspx?id=" + id;
                break;
            case VideoComponentConstant.LiveType.ACTIVITY:
                url = GlobalConfig.SHARE_URL + "/Share2/hdzb.aspx?id=" + id;
                break;
            case VideoComponentConstant.LiveType.IMAGE_AND_TEXT:
                url = GlobalConfig.SHARE_URL + "/Share2/twzb.aspx?id=" + id;
                break;
        }

        if(url != null) {
            url += "&stID=" + Resource.API.STID;
        }

        return url;
    }

    public static String getVodShareUrl(String id) {
        return GlobalConfig.SHARE_URL + "/share/VodMediaShares.aspx?ID=" + id + "&stID=" + Resource.API.STID;
    }
}
