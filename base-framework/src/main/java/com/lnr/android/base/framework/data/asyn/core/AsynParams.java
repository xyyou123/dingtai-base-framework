package com.lnr.android.base.framework.data.asyn.core;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018/5/10
 * 异步任务参数
 */

public class AsynParams {

    private final HashMap<String, Object> map;

    private AsynParams() {
        map = new HashMap<>();
    }

    public static AsynParams create(String key, Object value) {
        return new AsynParams().put(key, value);
    }

    public static AsynParams create() {
        return new AsynParams();
    }

    public HashMap<String, Object> map() {
        return map;
    }

    public AsynParams put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public AsynParams put(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public <T> T get(String key) {
        return (T) map.get(key);
    }

    public void clear() {
        map.clear();
    }

    public HashMap<String, Object> getMap() {
        return map;
    }
}
