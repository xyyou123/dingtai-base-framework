package com.lnr.android.base.framework.ui.control.web.more;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractBottomDialog;

/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class ChangeTextSizeDialog extends AbstractBottomDialog {

    private int currentIndex;
    private WebTextRatingBar.OnRatingListener listener;
    private WebTextRatingBar bar;
    public ChangeTextSizeDialog(Context context, int currentIndex, @NonNull WebTextRatingBar.OnRatingListener onRatingListener) {
        super(context);
        this.currentIndex = currentIndex;
        this.listener = onRatingListener;
    }

    @Override
    protected int layoutId() {
        return R.layout.layout_change_textsize;
    }

    @Override
    protected void initView(View view) {
        bar = view.findViewById(R.id.WebTextRatingBar);
        bar.setCurrent(currentIndex);
        bar.setOnRatingListener(listener);
    }

    public void updateTextSize(int currentIndex) {
        this.currentIndex = currentIndex;
        if(bar != null) {
            bar.setCurrent(currentIndex);
        }
    }
}
