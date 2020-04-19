package com.lnr.android.base.framework.ui.control.view.adapterview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * author:lnr
 * date:2018/9/6
 */
public class FixGridView extends GridView {

    public FixGridView(Context context) {
        super(context);
    }

    public FixGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getActionMasked() == MotionEvent.ACTION_UP) {
            final int motionPosition = pointToPosition((int)ev.getX(), (int)ev.getY());
            if( motionPosition == INVALID_POSITION) {
                super.onTouchEvent(ev);
                return false;
            }
        }

        return super.onTouchEvent(ev);
    }
}
