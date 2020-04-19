package com.lnr.android.base.framework.ui.control.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/12/14
 */
public class NumImageView extends FrameLayout {

    private ImageView imageView;
    private TextView numTextView;

    private int mNum;
    private boolean mShowEmpty;

    public NumImageView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public NumImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NumImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        imageView = new ImageView(context);

        int padding1 = getResources().getDimensionPixelOffset(R.dimen.dp_4);
        int padding2 = getResources().getDimensionPixelOffset(R.dimen.dp_6);
        setPadding(padding2, padding1, padding2, padding1);

        int icon = 0;
        int numColor = Color.WHITE;
        int numBg = R.drawable.bg_actionbar_badge;

        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumImageView);
            icon = a.getResourceId(R.styleable.NumImageView_icon, icon);
            numColor = a.getColor(R.styleable.NumImageView_num_color, numColor);
            numBg = a.getResourceId(R.styleable.NumImageView_num_bg, R.drawable.bg_actionbar_badge);
            a.recycle();
        }

        int iconSize = context.getResources().getDimensionPixelOffset(R.dimen.dp_28);
        LayoutParams lp = new LayoutParams(iconSize, iconSize);
        lp.gravity = Gravity.CENTER;
        imageView.setLayoutParams(lp);
        imageView.setImageResource(icon);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        addView(imageView);

        numTextView = new TextView(context);
        numTextView.setTextColor(numColor);
        numTextView.setBackgroundResource(numBg);
        numTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.RIGHT;
        numTextView.setLayoutParams(lp);
        addView(numTextView);

        setNum(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Resources resources = getResources();
        super.onMeasure(MeasureSpec.makeMeasureSpec(resources.getDimensionPixelOffset(R.dimen.dp_44), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(resources.getDimensionPixelOffset(R.dimen.dp_36), MeasureSpec.EXACTLY));
    }

    /**
     * 无数据是否显示num
     * @param show 是否显示
     */
    public void setWhenEmptyShow(boolean show) {
        mShowEmpty = show;
        numTextView.setVisibility((mShowEmpty || mNum > 0) ? VISIBLE : GONE);
    }

    public void setNum(int num) {
        mNum = num;
        String numStr;
        if(num <= 0) {
            numStr = "0";
            numTextView.setVisibility(mShowEmpty ? VISIBLE : GONE);
        }else if(num > 99) {
            numStr = "99+";
        }else {
            numStr = num + "";
        }

        numTextView.setText(numStr);
        setWhenEmptyShow(mShowEmpty);
    }

    public void setIcon(int res) {
        imageView.setImageResource(res);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getNumTextView() {
        return numTextView;
    }
}
