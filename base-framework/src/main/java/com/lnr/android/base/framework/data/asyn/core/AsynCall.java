package com.lnr.android.base.framework.data.asyn.core;

import io.reactivex.Observable;

/**
 * 异步任务
 * @param <T>
 */
public interface AsynCall<T> {

    /**
     * 创建异步任务
     * @param params 参数
     * @return 任务
     */
    Observable<T> call(AsynParams params);
}
