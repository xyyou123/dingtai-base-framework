/*
     The MIT License (MIT)
     Copyright (c) 2017 Jenly Yu
     https://github.com/jenly1314

     Permission is hereby granted, free of charge, to any person obtaining
     a copy of this software and associated documentation files
     (the "Software"), to deal in the Software without restriction, including
     without limitation the rights to use, copy, modify, merge, publish,
     distribute, sublicense, and/or sell copies of the Software, and to permit
     persons to whom the Software is furnished to do so, subject to the
     following conditions:

     The above copyright notice and this permission notice shall be included
     in all copies or substantial portions of the Software.

     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
     FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
     DEALINGS IN THE SOFTWARE.
 */
package com.dingtai.android.library.video.control;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.lnr.android.base.framework.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * 飘心控件
 */
public class FlutteringLayout extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * 桃心
     */
    private int[] heartRes = new int[]{
            R.drawable.resource_heart0, R.drawable.resource_heart1, R.drawable.resource_heart2,
            R.drawable.resource_heart3, R.drawable.resource_heart3, R.drawable.resource_heart5,
            R.drawable.resource_heart6, R.drawable.resource_heart7, R.drawable.resource_heart8,
            R.drawable.resource_heart9, R.drawable.resource_heart10
    };

    private final Object LOCK = new Object();

    private SurfaceHolder surfaceHolder;
    private ArrayList<Heart> mHeartList;
    private Paint p;
    private ExecuteRunnable mExecuteRunnable;
    private Random random = new Random();

    private boolean mRequestStop;

    public FlutteringLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public FlutteringLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlutteringLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHeartList = new ArrayList<>();
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(-3);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.p = new Paint();
        this.p.setAntiAlias(true);
    }

    public void addHeart() {
        synchronized (LOCK) {
            this.mHeartList.add(new Heart(getContext(), heartRes[random.nextInt(heartRes.length)]));
            if (this.mHeartList.size() > 40) {
                this.mHeartList.remove(0);
            }
            if (this.mExecuteRunnable == null) {
                this.mExecuteRunnable = new ExecuteRunnable();
                mRequestStop = false;
                AsyncTask.THREAD_POOL_EXECUTOR.execute(mExecuteRunnable);
            }else {
                LOCK.notifyAll();
            }
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        mExecuteRunnable = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stop();
    }

    public void stop() {
        synchronized (LOCK) {
            mRequestStop = true;
            if (mExecuteRunnable != null) {
                for(int i = 0; i < this.mHeartList.size(); ++i) {
                    mHeartList.get(i).stop();
                }
            }
            LOCK.notifyAll();
        }

    }

    private class ExecuteRunnable implements Runnable {

        public void run() {
            while(!mRequestStop) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if(canvas != null) {
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    boolean isEnd = true;
                    for(int i = 0; i < mHeartList.size(); ++i) {
                        isEnd = mHeartList.get(i).isEnd;
                        mHeartList.get(i).draw(canvas, p);
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    if (isEnd) {
                        synchronized (LOCK) {
                            try {
                                LOCK.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var11) {
                    var11.printStackTrace();
                }
            }
            mExecuteRunnable = null;
        }
    }
    
    
    private class Heart {

        public Point point;
        private ValueAnimator moveAnim;
        private ValueAnimator zoomAnim;
        public int alpha = 255;
        private Bitmap bitmap;
        private Matrix matrix = new Matrix();
        private float sf = 0.5F;

        public boolean isEnd = false;

        public Heart(Context context, int resId) {
            this.bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            init(new Point(getWidth() / 2, getHeight()- bitmap.getHeight() / 2), new Point((random.nextInt(getWidth())), 0));
        }

        @TargetApi(11)
        private void init(final Point startPoint, Point endPoint) {
            this.moveAnim = ValueAnimator.ofObject(new BezierEvaluator(new Point(random.nextInt(startPoint.x * 2), Math.abs(endPoint.y - startPoint.y) / 2)), new Object[]{startPoint, endPoint});
            this.moveAnim.setDuration(1500L);
            this.moveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    point = (Point)animation.getAnimatedValue();
                    alpha = (int)((float)point.y / (float)startPoint.y * 255.0F);
                }
            });
            this.moveAnim.start();
            this.zoomAnim = ValueAnimator.ofFloat(new float[]{0.5F, 1.5F}).setDuration(700L);
            this.zoomAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float f = (Float)animation.getAnimatedValue();
                    sf = f;
                }
            });
            this.zoomAnim.start();
        }

        public void stop() {
            if (this.moveAnim != null) {
                this.moveAnim.cancel();
                this.moveAnim = null;
            }

            if (this.zoomAnim != null) {
                this.zoomAnim.cancel();
                this.zoomAnim = null;
            }

            if(bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }

        }

        public void draw(Canvas canvas, Paint p) {
            if(bitmap == null || bitmap.isRecycled()) return;

            if(alpha > 0) {
                p.setAlpha(this.alpha);
                matrix.setScale(this.sf, this.sf, (float)(this.bitmap.getWidth() / 2), (float)(this.bitmap.getHeight() / 2));
                matrix.postTranslate((float)(this.point.x - this.bitmap.getWidth() / 2), (float)(this.point.y - this.bitmap.getHeight() / 2));
                canvas.drawBitmap(this.bitmap, this.matrix, p);
            }else {
                bitmap.recycle();
                bitmap = null;
            }
        }

        @TargetApi(11)
        private class BezierEvaluator implements TypeEvaluator<Point> {
            private Point centerPoint;

            public BezierEvaluator(Point centerPoint) {
                this.centerPoint = centerPoint;
            }

            public Point evaluate(float t, Point startValue, Point endValue) {
                int x = (int)((1.0F - t) * (1.0F - t) * (float)startValue.x + 2.0F * t * (1.0F - t) * (float)this.centerPoint.x + t * t * (float)endValue.x);
                int y = (int)((1.0F - t) * (1.0F - t) * (float)startValue.y + 2.0F * t * (1.0F - t) * (float)this.centerPoint.y + t * t * (float)endValue.y);
                return new Point(x, y);
            }
        }
        
    }
}
