package com.lnr.android.base.framework.data.asyn;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lnr.android.base.framework.uitl.Base64Util;
import com.lnr.android.base.framework.uitl.logger.Logger;

import java.util.Iterator;
import java.util.Map;

import io.reactivex.functions.Function;

/**
 * 数据转换器  将base64数据转换成正确数据
 * @param <T>
 */
public class CallResultDecodeBase64<T extends JSON> implements Function<Object, T> {

    @Override
    public T apply(Object o) throws Exception {
        T result;if(o instanceof JSONObject) {
            result = (T) parse((JSONObject)o);
        }else {
            result = (T) parse((JSONArray)o);
        }
        Logger.log("response", result.toString());
        return result;
    }

    private JSONObject parse(JSONObject object) throws Exception {
        Iterator<Map.Entry<String, Object>> iterator = object.entrySet().iterator();
        JSONObject result = new JSONObject();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            Object value = entry.getValue();
            if(value instanceof JSONArray) {
                result.put(entry.getKey(), parse((JSONArray) value));
            }else if(value instanceof JSONObject) {
                result.put(entry.getKey(), parse((JSONObject) value));
            }else {
                result.put(entry.getKey(), parse(value));
            }
        }
        return result;
    }

    private JSONArray parse(JSONArray array) throws Exception {
        JSONArray result = new JSONArray();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            Object data = array.get(i);
            if(data instanceof JSONArray) {
                result.set(i, parse((JSONArray) data));
            }else if(data instanceof JSONObject) {
                result.set(i, parse((JSONObject) data));
            }else {
                result.set(i, parse(data));
            }
        }

        return result;
    }

    private String parse(Object value) {
        String data = value.toString();
        String result = Base64Util.decodeBase64ToUTF8(data);

        return result;
//        if(data.equals(Base64Util.encodeBase64ToUTF8(result))) {
//            //经过加密的数据
//            return result;
//        }
//        return data;
    }
}
