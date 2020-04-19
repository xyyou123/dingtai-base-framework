package com.lnr.android.base.framework.common.html.span;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/10/24
 */
public class VideoSpan extends ImageSpan {

    private String mVideoPath;

    private Paint mPlayBtnPaint;

    private Bitmap bitmap;

    public VideoSpan(Context context, Drawable d, String source) {
        super(d, source);
        mPlayBtnPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_play);
    }

    public String getVideoPath() {
        return mVideoPath;
    }

    public void setVideoPath(String videoPath) {
        this.mVideoPath = videoPath;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {

        super.draw(canvas, text, start, end, x, top, y, bottom, paint);

        int l = canvas.getWidth() / 2 - 60;
        int t = (bottom - top) / 2 + top - 60;

        Rect rect = new Rect(l, t, l + 120, t + 120);

        canvas.drawBitmap(bitmap, null, rect, mPlayBtnPaint);

    }
}
