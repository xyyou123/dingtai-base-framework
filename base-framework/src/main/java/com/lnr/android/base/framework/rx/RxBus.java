package com.lnr.android.base.framework.rx;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * author:lnr
 * date:2018/8/1
 */
public class RxBus {
    private static volatile RxBus defaultInstance;

    private final FlowableProcessor<Object> mBus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // 发送一个新的事件
    public RxBus post(Object o) {
        mBus.onNext(o);
        return this;
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Flowable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}
