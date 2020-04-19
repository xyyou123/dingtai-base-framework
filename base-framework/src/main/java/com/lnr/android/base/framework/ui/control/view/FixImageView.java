package com.lnr.android.base.framework.ui.control.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.lnr.android.base.framework.R;

public class FixImageView extends android.support.v7.widget.AppCompatImageView {

    private float fixWidth = -1;
    private float fixHeight = -1;
    private float offset = 0;

    public FixImageView(Context context) {
        super(context);
    }

    public FixImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FixImageView);
        fixWidth = a.getFloat(R.styleable.FixImageView_fixWidth, -1);
        fixHeight = a.getFloat(R.styleable.FixImageView_fixHeight, -1);
        offset = a.getDimensionPixelOffset(R.styleable.FixImageView_offset, -1);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(fixHeight > 0) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * fixHeight + offset), MeasureSpec.EXACTLY));
        }else if(fixWidth > 0) {
            super.onMeasure(MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(heightMeasureSpec) * fixWidth + offset), MeasureSpec.EXACTLY), heightMeasureSpec);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setFixWidth(float fixWidth) {
        this.fixWidth = fixWidth;
        this.fixHeight = -1;
        requestLayout();
    }

    public void setFixHeight(float fixHeight) {
        this.fixHeight = fixHeight;
        this.fixWidth = -1;
        requestLayout();
    }
}
