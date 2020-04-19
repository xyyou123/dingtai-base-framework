package com.lnr.android.base.framework.data.asyn.http.retrofit;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Predicate;

/**
 * author:lnr
 * date:2018/5/24
 */

public class RetryPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        return throwable instanceof ConnectException
                || throwable instanceof UnknownHostException
                || throwable instanceof SocketTimeoutException;
    }
}
