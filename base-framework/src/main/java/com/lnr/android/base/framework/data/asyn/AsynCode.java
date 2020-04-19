package com.lnr.android.base.framework.data.asyn;

/**
 * author:lnr
 * date:2018/5/10
 */

public final class AsynCode {

    /**
     * code 大于等于200即为成功
     */
    public static final int SUCCEED = 200;

    /**
     * 错误码为负值
     */
    public static final int CODE_VIEW_RECYCLED = -100;
    public static final int CODE_ERROR = -200;
    public static final int CODE_DATA_IS_NULL = -1000;
    public static final int UNKNOW = -10000;

    public enum Error {
        VIEW_RECYCLED(CODE_VIEW_RECYCLED, "view is recycled!"),
        DATA_IS_NULL(CODE_DATA_IS_NULL, "data is null!");

        private int code;
        private String message;

        Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public static boolean isSucceed(int code) {
        return SUCCEED == code;
    }
}
