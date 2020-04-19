package com.lnr.android.base.framework.ui.control.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lnr.android.base.framework.R;


/**
 * author:lnr
 * date:2018/3/21
 */

public class MessageDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean autoDismiss = true;

    public MessageDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MessageDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_messagedialog, null);

        lLayout_bg = view.findViewById(R.id.messagedialog_layout);
        txt_title = view.findViewById(R.id.messagedialog_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = view.findViewById(R.id.messagedialog_message);
        txt_msg.setVisibility(View.GONE);
        btn_neg = view.findViewById(R.id.messagedialog_NegativeButton);
        btn_neg.setVisibility(View.GONE);
        btn_pos = view.findViewById(R.id.messagedialog_PositiveButton);
        btn_pos.setVisibility(View.GONE);
        img_line = view.findViewById(R.id.messagedialog_line);
        img_line.setVisibility(View.GONE);

        dialog = new Dialog(context, R.style.MessageDialogStyle);
        dialog.setContentView(view);

        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public TextView getMessageView() {
        return txt_msg;
    }

    public TextView getTitleView() {
        return txt_title;
    }

    public MessageDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public MessageDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public MessageDialog setAutoDismiss(boolean auto) {
        autoDismiss = auto;
        return this;
    }

    public MessageDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }


    public MessageDialog setPositiveButton(String text) {
        setPositiveButton(text, null);
        return this;
    }


    public MessageDialog setPositiveButton(String text,
                                         final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoDismiss) {
                    dialog.dismiss();
                }
                if(listener != null)
                listener.onClick(v);
            }
        });
        return this;
    }

    public MessageDialog setNegativeButton(String text,
                                         final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoDismiss) {
                    dialog.dismiss();
                }
                if(listener != null) {
                    listener.onClick(v);
                }else {
                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
//          btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
//          btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
//          btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
//          btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
//          btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    public void show() {
        if(isShowing()) return;
        setLayout();
        dialog.show();
    }

    public boolean isShowing()  {
        return dialog != null && dialog.isShowing();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void dismiss() {
        if(isShowing()) {
            dialog.dismiss();
        }
    }
}
