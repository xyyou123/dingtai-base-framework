package com.lnr.android.base.framework.ui.control.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * author:lnr
 * date:2018/6/4
 */

public abstract class AbsToastGenerater implements IToastGenerater {

    @Override
    public final Toast generater(Context context) {
        Toast toast = new Toast(context);
        toast.setView(createView(context));
        toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }

    protected abstract View createView(Context context);
}
