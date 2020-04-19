package com.lnr.android.base.framework.data.asyn.core;

/**
 * 任务执行者
 * @param <T>
 */
public interface Executor<T> {

    void excute(boolean loading, AsynCallback<T> callback);


}
