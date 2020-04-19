package com.lnr.android.base.framework.ui.control.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * author:lnr
 * date:2018/7/5
 */

public class SwitchButton extends SwitchCompat {

    private boolean manual;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void setOnCheckedChangeListener(@Nullable OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
        super.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(manual) {
                    manual = false;
                    return;
                }

                if(mOnCheckedChangeListener != null){
                    mOnCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });
    }

    public void manualCheck(boolean checked) {
        if(checked == isChecked()) {
            return;
        }

        manual = true;
        super.setChecked(checked);
    }
}
