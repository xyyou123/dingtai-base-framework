package com.lnr.android.base.framework.data.asyn;

import com.lnr.android.base.framework.data.asyn.core.ExceptionHandle;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * author:lnr
 * date:2018/5/10
 */

public class AsynException extends RuntimeException {

    private int code;
    private String message;
    private Throwable throwable;

    public AsynException(Throwable throwable) {
        this.throwable = throwable;
        transform(throwable);
    }

    public AsynException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AsynException(AsynCode.Error error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    private void transform(Throwable throwable) {
        if(throwable == null) {
            code = AsynCode.UNKNOW;
            message = null;
        }else {
            message = ExceptionHandle.handle(throwable);
        }
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void printStackTrace() {
        if(throwable != null) {
            throwable.printStackTrace();
        }else {
            super.printStackTrace();
        }
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        if(throwable != null) {
            throwable.printStackTrace(s);
        }else {
            super.printStackTrace(s);
        }
    }

    @Override
    public void printStackTrace(PrintStream s) {
        if(throwable != null) {
            throwable.printStackTrace(s);
        }else {
            super.printStackTrace(s);
        }
    }
}
