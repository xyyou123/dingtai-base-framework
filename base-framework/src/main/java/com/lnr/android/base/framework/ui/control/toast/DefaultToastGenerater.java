package com.lnr.android.base.framework.ui.control.toast;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/6/4
 */

public class DefaultToastGenerater extends AbsToastGenerater {

    private String msg;

    public DefaultToastGenerater(String msg) {
        this.msg = msg;
    }

    @Override
    protected View createView(Context context) {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.WHITE);
        textView.setMinWidth(context.getResources().getDimensionPixelOffset(R.dimen.dp_80));
        textView.setMinHeight(context.getResources().getDimensionPixelOffset(R.dimen.dp_36));
        textView.setBackgroundResource(R.drawable.bg_toast);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setText(msg);
        return textView;
    }
}
