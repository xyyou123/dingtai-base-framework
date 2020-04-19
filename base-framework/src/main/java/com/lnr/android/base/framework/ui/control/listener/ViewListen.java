package com.lnr.android.base.framework.ui.control.listener;

import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

/**
 * author:lnr
 * date:2018/6/4
 */

public class ViewListen {

    /**
     * 添加点击事件
     * @param view view
     * @param listener 事件
     */
    public static void setClick(View view, OnClickListener listener) {
        if(view == null) return;
        view.setOnClickListener(listener);
    }

    /**
     * 添加多EditText 输入监听
     * @param listener 监听器
     * @param views 需要监听的EditText
     */
    public static void addTextChangeWatcher(OnTextChangeWatcher.OnTextChangeListener listener, final TextView... views) {
        OnTextChangeWatcher watcher = new OnTextChangeWatcher(views, listener);
        for (TextView view : views) {
            if(view == null) continue;
            view.addTextChangedListener(watcher);
        }
    }

    public static void addTextChangeWatcher(TextView view, TextWatcher watcher) {
        if(view == null) return;
        view.addTextChangedListener(watcher);
    }
}
