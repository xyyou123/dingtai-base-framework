package com.lnr.android.base.framework.data.asyn.core;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.uitl.logger.Logger;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.umeng.analytics.MobclickAgent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 任务执行者
 */
public class AsynCallExecutor {

    /**
     * 任务生命周期监测器
     */
    private LifecycleTransformer life;
    /**
     * 加载框代理
     */
    private LoadingProxy loadingProxy;

    public AsynCallExecutor(LifecycleTransformer life, LoadingProxy loadingProxy) {
        this.life = life;
        this.loadingProxy = loadingProxy;
    }

    public final class CallExecutor<T> implements Executor<T> {

        private AsynInnerCallback<T> asynCallback;

        private AsynCall<T> call;

        private AsynParams params;

        public void excuteWithLoading(final AsynCallback<T> callback) {
            excute(true, callback);
        }

        public void excuteNoLoading(final AsynCallback<T> callback) {
            excute(false, callback);
        }

        @Override
        public void excute(boolean loading, final AsynCallback<T> callback) {
            asynCallback = new AsynInnerCallback<T>(loading ? loadingProxy : null) {
                @Override
                protected void onTaskResponse(T r) {
                    if(callback != null) {
                        callback.onCallResponse(r);
                    }
                }

                @Override
                protected void onTaskError(Throwable ex) {
                    if(callback != null) {
                        callback.onCallError(ex);
                    }
                    Logger.log("Executor", "onTaskError\n" + "AsynCall = " + call.getClass().getName(), ex);
                }
            };

            if(params == null) {
                params = AsynParams.create();
            }
            Observable<AsynParams> observable = Observable.just(params)
                    .subscribeOn(Schedulers.io());

            if(life != null) {
                observable = observable.compose(life);
            }

            Observable<T> result = observable.flatMap(new Function<AsynParams, ObservableSource<T>>() {
                        @Override
                        public ObservableSource<T> apply(AsynParams params) {
                            return call.call(params).subscribeOn(Schedulers.io());
                        }
                    });
            result.observeOn(AndroidSchedulers.mainThread()).subscribe(asynCallback);
        }
    }

    public final class ObservableExecutor<T> implements Executor<T> {

        private AsynInnerCallback<T> asynCallback;

        private Observable<T> call;

        private AsynParams params;

        public void excuteWithLoading(final AsynCallback<T> callback) {
            excute(true, callback);
        }

        public void excuteNoLoading(final AsynCallback<T> callback) {
            excute(false, callback);
        }

        @Override
        public void excute(boolean loading, final AsynCallback<T> callback) {
            asynCallback = new AsynInnerCallback<T>(loading ? loadingProxy : null) {
                @Override
                protected void onTaskResponse(T r) {
                    if(callback != null) {
                        callback.onCallResponse(r);
                    }
                }

                @Override
                protected void onTaskError(Throwable ex) {
                    if(callback != null) {
                        callback.onCallError(ex);
                    }

                    MobclickAgent.reportError(Framework.getInstance().getApplication(), ex);
                    Logger.log("Executor", "onTaskError\n" + "Observable = "
                            + call.getClass().getName(), ex);
                }
            };
            if(params == null) params = AsynParams.create();
            Observable<AsynParams> observable = Observable.just(params)
                    .subscribeOn(Schedulers.io());

            if(life != null) {
                observable = observable.compose(life);
            }

            Observable<T> result = observable.flatMap(new Function<AsynParams, ObservableSource<T>>() {
                @Override
                public ObservableSource<T> apply(AsynParams params) {
                    return call.subscribeOn(Schedulers.io());
                }
            });
            result.observeOn(AndroidSchedulers.mainThread()).subscribe(asynCallback);
        }
    }

    public <T> CallExecutor<T> create(AsynCall<T> call, AsynParams params) {
        CallExecutor<T> executor = new CallExecutor<>();
        executor.call = call;
        executor.params = params;
        return executor;
    }

    public <T> ObservableExecutor<T> create(Observable<T> call) {
        ObservableExecutor<T> executor = new ObservableExecutor<>();
        executor.call = call;
        executor.params = AsynParams.create();
        return executor;
    }
}
