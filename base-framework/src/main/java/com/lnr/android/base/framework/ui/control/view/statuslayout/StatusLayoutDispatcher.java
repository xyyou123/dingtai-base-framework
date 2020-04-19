package com.lnr.android.base.framework.ui.control.view.statuslayout;

import java.util.Collection;

/**
 * author:lnr
 * date:2018/5/28
 */

public final class StatusLayoutDispatcher {

    public static void loading(StatusLayoutManager manager) {
        manager.showLoading();
    }

    public static void content(StatusLayoutManager manager) {
        manager.showContent();
    }

    public static boolean result(StatusLayoutManager manager, Collection data) {
        boolean isEmpty = data == null || data.isEmpty();
        if(isEmpty) {
            manager.showEmpty();
        }else {
            manager.showContent();
        }

        return isEmpty;
    }

    public static void error(StatusLayoutManager manager, String message) {
        manager.showError(message);
    }


}
