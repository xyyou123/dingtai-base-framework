package com.lnr.android.base.framework.ui.control.web;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tencent.smtt.sdk.WebView;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;

/**
 * author:lnr
 * date:2019/1/28
 */
public class WebImageLoader extends SimpleTarget<Bitmap> implements Runnable {

    private PipedOutputStream mOut;
    private WebView mContext;
    private String mPath;

    private WebImageLoader() {
    }

    public static PipedInputStream load(WebView context, String path) throws IOException {
        WebImageLoader loader = new WebImageLoader();
        return loader.loadImage(context, path);
    }

    private PipedInputStream loadImage(WebView context, String path) throws IOException  {
        mOut = new PipedOutputStream();
        mContext = context;
        mPath = path;
        PipedInputStream in = new PipedInputStream(mOut);
        context.post(this);
        return in;
    }

    @Override
    public void run() {
        Glide.with(mContext)
                .asBitmap()
                .load(mPath)
                .into(this);
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        try {
            mOut.write(ByteBuffer.allocate(resource.getByteCount()).array());
            mOut.close();
            mOut = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void intoOutStream(File file) throws IOException  {


    }
}
