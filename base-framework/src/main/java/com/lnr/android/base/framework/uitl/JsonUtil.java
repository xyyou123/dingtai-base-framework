package com.lnr.android.base.framework.uitl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class JsonUtil {

    public static JSONObject parseObject(String data) {
        try {
            return JSON.parseObject(data);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parseObject(String data, Class<T> tClass) {
        try {
            TypeUtils.compatibleWithJavaBean = true;
            //TypeUtils.compatibleWithFieldName = true;
            return JSON.parseObject(data, tClass);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> parseArray(String data, Class<T> tClass) {
        try {
            TypeUtils.compatibleWithJavaBean = true;
            //TypeUtils.compatibleWithFieldName = true;
            List<T> ts = JSON.parseArray(data, tClass);
            if(ts == null) ts = new ArrayList<>();
            return ts;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
