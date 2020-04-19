package com.lnr.android.base.framework.data.asyn;



import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


/**
 * author:lnr
 * date:2018/5/11
 */

public class AsynTransformer {

//    /**
//     * 错误处理转换器 -> 抛出异常
//     */
//    public static ObservableTransformer<TaskResult, TaskResult> checkError() {
//        return new ObservableTransformer<TaskResult, TaskResult>() {
//            @Override
//            public ObservableSource<TaskResult> apply(Observable<TaskResult> observable) {
//                return observable.flatMap(new Function<TaskResult, ObservableSource<TaskResult>>() {
//                    @Override
//                    public ObservableSource<TaskResult> apply(TaskResult result) throws Exception {
//                        if(result == null) {
//                            return Observable.error(new TaskException(TaskResultCode.CODE_DATA_IS_NULL, ""));
//                        }
//
//                        if (TaskResultCode.isSucceed(result.getCode())) {
//                            return Observable.just(result);
//                        }
//                        return Observable.error(new TaskException(result.getCode(), result.getDesc()));
//                    }
//                });
//            }
//        };
//    }
//
//
//    /**
//     * 错误处理转换器 -> 抛出异常
//     */
//    public static <T> ObservableTransformer<TaskResult<T>, T> transformerError() {
//        return new ObservableTransformer<TaskResult<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<TaskResult<T>> observable) {
//                return observable.flatMap(new Function<TaskResult<T>, ObservableSource<T>>() {
//                    @Override
//                    public ObservableSource<T> apply(TaskResult<T> result) throws Exception {
//                        if(result == null) {
//                            return Observable.error(new TaskException(TaskResultCode.CODE_DATA_IS_NULL, ""));
//                        }
//
//                        if (TaskResultCode.isSucceed(result.getCode())) {
//                            return Observable.just(result.getParams());
//                        }
//                        return Observable.error(new TaskException(result.getCode(), result.getDesc()));
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 错误处理转换器 -> 转成正确的数据
//     */
//    public static <T> ObservableTransformer<TaskResult<T>, T> transformerCorrect(final T correct) {
//        return new ObservableTransformer<TaskResult<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<TaskResult<T>> observable) {
//                return observable.flatMap(new Function<TaskResult<T>, ObservableSource<T>>() {
//                    @Override
//                    public ObservableSource<T> apply(TaskResult<T> result) throws Exception {
//                        if(result == null) {
//                            return Observable.just(correct);
//                        }
//
//                        if (TaskResultCode.isSucceed(result.getCode())) {
//                            return Observable.just(result.getParams());
//                        }
//                        return Observable.just(correct);
//                    }
//                });
//            }
//        };
//    }
//
//    public static <T> Function<? super Throwable, ? extends ObservableSource<? extends T>> onErrorResumeNext(final T error) {
//        return new Function<Throwable, ObservableSource<? extends T>>() {
//            @Override
//            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
//                return Observable.just(error);
//            }
//        };
//    }

}
