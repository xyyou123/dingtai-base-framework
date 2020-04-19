package com.lnr.android.base.framework.ui.control.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.lnr.android.base.framework.R;

public abstract class AbstractBottomDialog extends AbstractDialog {

    public AbstractBottomDialog(@NonNull Context context) {
        super(context);
    }

    public AbstractBottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected int[] getDialogSize(DisplayMetrics displayMetrics) {
        return new int[]{displayMetrics.widthPixels,WindowManager.LayoutParams.WRAP_CONTENT};
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.BottomMenuAnimStyle;
    }
}
