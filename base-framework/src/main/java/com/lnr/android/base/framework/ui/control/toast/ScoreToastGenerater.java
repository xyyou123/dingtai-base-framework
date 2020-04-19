package com.lnr.android.base.framework.ui.control.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/26
 */
public class ScoreToastGenerater extends AbsToastGenerater {

    private int score;

    public ScoreToastGenerater(int score) {
        this.score = score;
    }

    @Override
    protected View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast_score, null);
        TextView cont = view.findViewById(R.id.text_count);
        cont.setText("+" + score);
        return view;
    }
}
