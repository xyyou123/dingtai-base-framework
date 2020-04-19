package com.lnr.android.base.framework.ui.control.web.more;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lnr.android.base.framework.R;


/**
 * author :  lnr
 * date : 2018/8/18.
 */

public class WebTextRatingBar extends View {

    private float[] rates = new float[]{0.8f, 1f, 1.2f, 1.5f, 1.8f};

    private Paint linePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint textPaint2 = new Paint();
    private Paint selectedPaint = new Paint();

    private int height, itemWidth, lineHeight, r;

    private int current = 2;

    public WebTextRatingBar(Context context) {
        this(context, null);
    }

    public WebTextRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebTextRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setCurrent(int current) {
        this.current = current;
        invalidate();
    }

    private void init(Context context) {
        lineHeight = context.getResources().getDimensionPixelOffset(R.dimen.dp_4);
        height = context.getResources().getDimensionPixelOffset(R.dimen.dp_80);

        r = context.getResources().getDimensionPixelOffset(R.dimen.dp_12);

        linePaint.setStrokeWidth(context.getResources().getDimensionPixelOffset(R.dimen.dp_1));
        linePaint.setColor(Color.parseColor("#606060"));

        textPaint.setColor(getResources().getColor(R.color.black));
        textPaint.setAntiAlias(true);

        textPaint2.setColor(getResources().getColor(R.color.textcolor_Caption));
        textPaint2.setAntiAlias(true);
        textPaint2.setTextSize(getResources().getDimensionPixelSize(R.dimen.textsize_Body2));

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        selectedPaint.setColor(getResources().getColor(R.color.white));
        selectedPaint.setAntiAlias(true);
        selectedPaint.setShadowLayer(4f ,-2,2, getResources().getColor(R.color.backgroundDark_4));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY));
    }

    private int nodeStart(int position) {
        return getPaddingLeft() + itemWidth * position;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = rates.length;

        //上半部分
        int width = (getWidth() - getPaddingLeft() - getPaddingRight());
        itemWidth = width / (count - 1);

        int Y = height / 3;
        int textY = Y + getPaddingTop();
        int lineY = Y * 2 + getPaddingTop();

        canvas.drawLine(getPaddingLeft(), lineY, getPaddingLeft() + width, lineY, linePaint);

        for (int i = 0; i < count; i++) {
            int start = nodeStart(i);
            canvas.drawLine(start, lineY - lineHeight, start, lineY + lineHeight, linePaint);

            if (i == 0) {
                textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textsize_Body1));
                canvas.drawText("A", start - textPaint.measureText("A") / 2, textY, textPaint);
            } else if (i == 2) {
                canvas.drawText("标准", start - textPaint2.measureText("标准") / 2, textY, textPaint2);
            } else if (i == count - 1) {
                textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.textsize_Title));
                canvas.drawText("A", start - textPaint.measureText("A") / 2, textY, textPaint);
            }


            if(current == i) {
                canvas.drawCircle(start, lineY, r, selectedPaint);
            }
        }
    }

    // 计算出该TextView中文字的长度(像素)
    public static float getTextViewLength(Paint paint, String text) {
        return paint.measureText(text);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            int count = rates.length;
            float x = event.getX();
            float y = event.getY();

            int Y = height / 3;
            int lineY = Y * 2 + getPaddingTop();
            int width = (getWidth() - getPaddingLeft() - getPaddingRight());

            if(y < lineY - r || y > lineY + r) {
                return true;
            }

            if(x < getPaddingLeft() || x > getPaddingLeft() + width) {
                return true;
            }

            itemWidth = width / (count - 1);

            int left = (int) (x - getPaddingLeft());

            int index = left / itemWidth + (left % itemWidth ) * 2 / itemWidth;
            if(index < 0 || index == current) {
                return true;
            }

            current = index;

            if(onRatingListener != null) {
                onRatingListener.onRating(index);
            }

            invalidate();
        }
        return true;
    }

    private OnRatingListener onRatingListener;

    public void setOnRatingListener(OnRatingListener onRatingListener) {
        this.onRatingListener = onRatingListener;
    }

    public interface OnRatingListener {
        void onRating(int index);
    }
}