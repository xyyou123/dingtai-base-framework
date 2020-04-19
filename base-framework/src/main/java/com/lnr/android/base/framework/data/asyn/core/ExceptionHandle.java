package com.lnr.android.base.framework.data.asyn.core;

import android.database.sqlite.SQLiteException;


import com.lnr.android.base.framework.data.asyn.AsynException;

import org.greenrobot.greendao.DaoException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ConcurrentModificationException;

import javax.net.ssl.SSLHandshakeException;

/**
 * author:lnr
 * date:2018/9/11
 */
public class ExceptionHandle {

    public static String handle(Throwable e) {
        if(e instanceof AsynException) {
            return e.getMessage();
        }else if (e instanceof ConnectException || e instanceof UnknownHostException || e instanceof SSLHandshakeException) {
            return "网络连接异常";
        } else if (e instanceof SocketTimeoutException) {
            return "请求超时";
        } else if (e instanceof ClassCastException
                || e instanceof NullPointerException
                || e instanceof DaoException
                || e instanceof IOException
                || e instanceof SQLiteException
                || e instanceof ConcurrentModificationException
                ) {
            return "运行时异常：" + e.getClass().getSimpleName();
        }
        return "未知错误";
    }
}
