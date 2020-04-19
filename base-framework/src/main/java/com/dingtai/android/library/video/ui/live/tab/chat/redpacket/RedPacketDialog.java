package com.dingtai.android.library.video.ui.live.tab.chat.redpacket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.lnr.android.base.framework.R;
import com.lnr.android.base.framework.ui.control.dialog.AbstractDialog;
import com.lnr.android.base.framework.ui.control.listener.ViewListen;

/**
 * author:lnr
 * date:2018/12/13
 */
public class RedPacketDialog extends AbstractDialog {

    private boolean send;
    private ImageView mImageView;
    private View.OnClickListener mOnClickListener;


    public RedPacketDialog(@NonNull Context context, View.OnClickListener listener) {
        super(context);
        mOnClickListener = listener;
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog_redpacket;
    }

    @Override
    protected void initView(View view) {

        ViewListen.setClick(findViewById(R.id.btn_close), new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                dismiss();
            }
        });


        ViewListen.setClick(findViewById(R.id.btn_share), new com.lnr.android.base.framework.ui.control.listener.OnClickListener() {
            @Override
            protected void onSafeClick(View v) {
                dismiss();
                mOnClickListener.onClick(v);
            }
        });

        mImageView = findViewById(R.id.image_icon);
        setImage();
    }

    @Override
    protected int[] getDialogSize(DisplayMetrics displayMetrics) {
        return new int[]{ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT};
    }

    private void setImage() {
        mImageView.setImageResource(send ? R.drawable.icon_redpacket_send : R.drawable.icon_redpacket_open);
    }

    public void show(boolean send) {
        this.send = send;
        if(mImageView != null) {
            setImage();
        }
        show();
    }

    @Override
    protected int getGravity() {
        return Gravity.CENTER;
    }

}
