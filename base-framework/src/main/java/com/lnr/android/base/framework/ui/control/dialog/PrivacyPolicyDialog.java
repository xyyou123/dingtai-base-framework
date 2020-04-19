package com.lnr.android.base.framework.ui.control.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtai.android.library.resource.Routes;
import com.lnr.android.base.framework.R;


/**
 * Created by Administrator on 2019/12/2.
 */

public class PrivacyPolicyDialog {


    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView to_policy;
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
    private String url;
    public static String hint = "您可以查看完整版 <<用户协议和隐私政策>>";
    public PrivacyPolicyDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public PrivacyPolicyDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_privacy_policy_dialog, null);

        lLayout_bg = view.findViewById(R.id.messagedialog_layout);
        txt_title = view.findViewById(R.id.messagedialog_title);
        txt_title.setVisibility(View.VISIBLE);
        txt_msg = view.findViewById(R.id.messagedialog_message);
        txt_msg.setVisibility(View.VISIBLE);
        btn_neg = view.findViewById(R.id.messagedialog_NegativeButton);
        btn_neg.setVisibility(View.GONE);
        btn_pos = view.findViewById(R.id.messagedialog_PositiveButton);
        btn_pos.setVisibility(View.GONE);
        img_line = view.findViewById(R.id.messagedialog_line);
        img_line.setVisibility(View.GONE);

        txt_msg.setText("1.我们会遵循隐私政策收集、使用信息，但不会仅因同意本隐私政策而采用强制捆绑的方式收集信息。\n2.在仅浏览时，为保障服务所必需，我们会收集设备信息与日志信息用于资讯推送。\n3.GPS、摄像头、麦克风、相册权限均不会默认开启，只有经过明示授权才会在实现功能或服务时使用，不会在功能或服务不需要时而通过您授权的权限收集信息。");
        txt_title.setText("个人信息保护指引");
        to_policy = view.findViewById(R.id.messagedialog_policy);
        SpannableString spannableString = new SpannableString(hint);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#1b8ee2"));
        spannableString.setSpan(colorSpan, 8, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        to_policy.setText(spannableString);


        to_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Routes.Setting.PRIVACY).navigation();
            }
        });

        dialog = new Dialog(context, R.style.MessageDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(false);

        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public TextView getMessageView() {
        return txt_msg;
    }

    public TextView getTitleView() {
        return txt_title;
    }

    public PrivacyPolicyDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public PrivacyPolicyDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText("内容");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }


    public PrivacyPolicyDialog setUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
        }
        return this;
    }

    public PrivacyPolicyDialog setAutoDismiss(boolean auto) {
        autoDismiss = auto;
        return this;
    }

    public PrivacyPolicyDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }


    public PrivacyPolicyDialog setPositiveButton(String text) {
        setPositiveButton(text, null);
        return this;
    }


    public PrivacyPolicyDialog setPositiveButton(String text,
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
                if (autoDismiss) {
                    dialog.dismiss();
                }
                if (listener != null)
                    listener.onClick(v);
            }
        });
        return this;
    }

    public PrivacyPolicyDialog setNegativeButton(String text,
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
                if (autoDismiss) {
                    dialog.dismiss();
                }
                if (listener != null) {
                    listener.onClick(v);
                } else {
                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("个人信息保护指引");
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
        if (isShowing()) return;
        setLayout();
        dialog.show();
    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void dismiss() {
        if (isShowing()) {
            dialog.dismiss();
        }
    }
}
