package com.lnr.android.base.framework.data.provider;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * author:lnr
 * date:2018/9/30
 * 数据提供者
 */
public interface IDataProvider {

    /**
     * 获取数据
     * @param params 参数
     * @return 数据
     */
    HashMap<String, Object> get(HashMap<String, Object> params);

    /**
     * 获取数据 异步
     * @param params 参数
     * @return Observable
     */
    Observable<Object> getAsync(HashMap<String, Object> params);

    /**
     * 添加数据
     * @param params 数据
     */
    void put(HashMap<String, Object> params);

    /**
     * 删除数据
     * @param params 数据
     */
    void delete(HashMap<String, Object> params);
}
