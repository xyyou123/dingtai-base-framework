package com.lnr.android.base.framework.data.provider;

import java.util.HashMap;

/**
 * author:lnr
 * date:2018-08-09
 * 异步任务适配器提供者
 */
public final class DataProviderManager {

    private static final HashMap<String, IDataProvider> ENGINES = new HashMap<>();
    private DataProviderManager() {
    }
    private static final class SingleHodler {
        static final DataProviderManager INSTANCE = new DataProviderManager();
    }

    public static DataProviderManager getInstance() {
        return SingleHodler.INSTANCE;
    }

    /**
     * 添加提供者
     * @param name 名称
     * @param provider 提供者
     */
    public DataProviderManager registe(String name, IDataProvider provider) {
        ENGINES.put(name, provider);
        return this;
    }

    /**
     * 根据 名称 获取提供者
     * @param name 类型
     * @return 提供者
     */
    public IDataProvider get(String name) {
        return ENGINES.get(name);
    }
}
