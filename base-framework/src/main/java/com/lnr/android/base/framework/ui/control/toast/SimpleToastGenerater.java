package com.lnr.android.base.framework.ui.control.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/6/4
 */

public class SimpleToastGenerater extends AbsToastGenerater {

    private int imgRes;
    private String content;

    public SimpleToastGenerater(int imgRes, String content) {
        this.imgRes = imgRes;
        this.content = content;
    }

    @Override
    protected View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        ImageView img = view.findViewById(R.id.toast_img);
        TextView cont = view.findViewById(R.id.toast_content);

        initView(img, cont);

        img.setImageResource(imgRes);
        cont.setText(content);
        return view;
    }


    protected void initView(ImageView img, TextView cont){

    }
}
