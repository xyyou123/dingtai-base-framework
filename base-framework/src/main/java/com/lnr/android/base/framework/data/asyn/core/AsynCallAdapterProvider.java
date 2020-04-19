package com.lnr.android.base.framework.data.asyn.core;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018-08-09
 * 异步任务适配器提供者
 */
public final class AsynCallAdapterProvider {

    private static final HashMap<String, AsynCallAdapter> ASYN_ENGINES = new HashMap<>();
    private AsynCallAdapterProvider() {
    }
    private static final class SingleHodler {
        static final AsynCallAdapterProvider INSTANCE = new AsynCallAdapterProvider();
    }

    public static AsynCallAdapterProvider getInstance() {
        return SingleHodler.INSTANCE;
    }

    /**
     * 添加http任务适配器
     * @param adapter 适配器
     * @return 异步任务适配器提供者
     */
    public AsynCallAdapterProvider addHttpAsynCallAdapter(AsynCallAdapter adapter) {
        ASYN_ENGINES.put(AsynCallAdapterType.HTTP.key, adapter);
        return this;
    }

    /**
     * 添加database任务适配器
     * @param adapter 适配器
     * @return 异步任务适配器提供者
     */
    public AsynCallAdapterProvider addDatabaseAsynCallAdapter(AsynCallAdapter adapter) {
        ASYN_ENGINES.put(AsynCallAdapterType.DATABASE.key, adapter);
        return this;
    }

    /**
     * 添加自定义任务适配器
     * @param key 关键字
     * @param adapter 适配器
     * @return 异步任务适配器提供者
     */
    public AsynCallAdapterProvider addAsynCallAdapter(String key, AsynCallAdapter adapter) {
        ASYN_ENGINES.put(key, adapter);
        return this;
    }

    /**
     * 根据关键字获取任务适配器
     * @param key 关键字
     * @return 任务适配器
     */
    public AsynCallAdapter get(String key) {
        return ASYN_ENGINES.get(key);
    }

    /**
     * 根据 默认提供的类型 获取任务适配器
     * @param type 类型
     * @return 任务适配器
     */
    public AsynCallAdapter get(AsynCallAdapterType type) {
        return get(type.key);
    }
}
