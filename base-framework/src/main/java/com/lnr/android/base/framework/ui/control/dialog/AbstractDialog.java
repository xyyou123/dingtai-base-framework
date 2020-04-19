package com.lnr.android.base.framework.ui.control.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public abstract class AbstractDialog extends Dialog {

    private boolean mCreated;

    public AbstractDialog(@NonNull Context context) {
        super(context);
    }

    public AbstractDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getContext();

        View view = View.inflate(context, layoutId(), null);
        setContentView(view);

        Window dialogWindow = getWindow();
        if(dialogWindow == null) return;
        dialogWindow.setBackgroundDrawable(null);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        int[] size = getDialogSize(context.getResources().getDisplayMetrics());
        lp.width = size[0];
        lp.height = size[1];
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(getGravity());

        int anim = getWindowAnimations();
        if(anim > 0) {
            dialogWindow.setWindowAnimations(anim);
        }
        initView(view);

        mCreated = true;
    }

    protected abstract int layoutId();

    protected abstract void initView(View view);

    public boolean isCreated() {
        return mCreated;
    }

    protected abstract int[] getDialogSize(DisplayMetrics displayMetrics);

    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    protected int getWindowAnimations() {
        return -1;
    }
}
