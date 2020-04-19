package com.lnr.android.base.framework.ui.control.permission;

import android.Manifest;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/9/7
 */
public class PermissionContants {

    public static final HashMap<String, String> MESSAGE;

    static {
        MESSAGE = new HashMap<>();

        MESSAGE.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "文件读写");
        MESSAGE.put(Manifest.permission.CAMERA, "相机");
        MESSAGE.put(Manifest.permission.RECORD_AUDIO, "录音");
        MESSAGE.put(Manifest.permission.ACCESS_COARSE_LOCATION, "定位");
        MESSAGE.put(Manifest.permission.ACCESS_FINE_LOCATION, "定位");
        MESSAGE.put(Manifest.permission.CALL_PHONE, "拨打电话");
    }
}
