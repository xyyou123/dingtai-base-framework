package com.lnr.android.base.framework.data.asyn.core;

/**
 * 异步任务适配器
 */
public interface AsynCallAdapter {

   /**
    * 返回任务创建者
    * @param tClass 创建者class
    * @param os 扩展参数
    * @param <T> 创建者类型
    * @return 任务创建者
    */
   <T> T call(Class<T> tClass, Object... os);

}