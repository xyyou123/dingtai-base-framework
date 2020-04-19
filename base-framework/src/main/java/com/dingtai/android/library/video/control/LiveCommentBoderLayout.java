package com.dingtai.android.library.video.control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

import com.lnr.android.base.framework.R;


/**
 * author:lnr
 * date:2018/9/3
 * 直播评论layout
 */
public class LiveCommentBoderLayout extends FrameLayout {

    private int padding;

    private boolean isFirst;

    /**
     * 线
     */
    private Paint linePaint;
    /**
     * 圆圈
     */
    private Paint circlePaint;
    /**
     * 时间
     */
    private Paint timePaint;

    private int textHeight;

    /**
     * 线的宽度
     */
    private int LINE = 1;

    /**
     * 圆的半径
     */
    private int circle;

    private int leftTopHeight;

    private int contentLeft, contentTop;

    private String time = "刚刚";

    public LiveCommentBoderLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LiveCommentBoderLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LiveCommentBoderLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        padding = getResources().getDimensionPixelOffset(R.dimen.dp_12);

        circle = getResources().getDimensionPixelOffset(R.dimen.dp_4);

        timePaint = new Paint();
        timePaint.setAntiAlias(true);
        timePaint.setColor(Color.RED);
        timePaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));

        Paint.FontMetrics fm = timePaint.getFontMetrics();
        textHeight = (int) ((fm.bottom - fm.top) / 2 - fm.descent);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.RED);
        circlePaint.setStrokeWidth(LINE);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.parseColor("#e5e5e5"));
        linePaint.setStrokeWidth(LINE);

        leftTopHeight = (int) (padding * 1.5f);

        contentLeft = padding + circle;
        contentTop = leftTopHeight * 2 + circle * 2;

        setPadding(contentLeft, contentTop, padding, 0);
    }

    public void setValue(boolean isFirst, String time) {
        this.isFirst = isFirst;
        this.time = time;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float right = getRight();

        float top = 0;
        float bottom = getHeight();

        if(!isFirst) {
            canvas.drawLine(contentLeft, top, contentLeft, leftTopHeight + top, linePaint);
        }

        canvas.drawCircle(contentLeft, leftTopHeight + circle + top, circle, circlePaint);

        //左边线
        canvas.drawLine(contentLeft, contentTop - leftTopHeight + top, padding + circle, bottom, linePaint);

        //上边线
        canvas.drawLine(contentLeft, contentTop + top, right - padding, contentTop + top, linePaint);
        //右边线
        canvas.drawLine(right - padding, contentTop + top, right - padding, bottom, linePaint);
        //下边线
        canvas.drawLine(contentLeft, bottom, right - padding, bottom, linePaint);

        //时间文字
        canvas.drawText(time, contentLeft * 2, leftTopHeight + circle + textHeight + top, timePaint);
    }
}
