package com.lnr.android.base.framework.ui.control.dialog;

import android.content.Context;

/**
 * author:lnr
 * date:2018/6/19
 */

public class BottomMenuHelper {

    public static BottomMenu newHasTitle(Context context, String title) {
        return new BottomMenu(context).builder().setTitle(title, null);
    }

    public static BottomMenu newHasTitle(Context context, String title, BottomMenu.MenuColor color) {
        return new BottomMenu(context).builder().setTitle(title, color);
    }

    public static BottomMenu newNoTitle(Context context) {
        return new BottomMenu(context).builder();
    }

}
